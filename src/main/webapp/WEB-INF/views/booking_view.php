<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Booking Management</title>

		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

		<link rel="stylesheet" href="<?php echo base_url('assets/css/jquery-ui.css'); ?>">
		<link rel="stylesheet" href="<?php echo base_url('assets/css/bootstrap.css'); ?>">
		<link rel="stylesheet" href="<?php echo base_url('assets/css/chosen.css'); ?>">
		<link rel="stylesheet" href="<?php echo base_url('assets/css/style.css"'); ?>" media="all">
		
		<script src="<?php echo base_url('assets/js/jquery-1.11.3.min.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/jquery-ui.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/jquery-ui.multidatespicker.js'); ?>"></script>
		<!-- <script src="<?php echo base_url('assets/js/modernizr-custom.min.js'); ?>"></script> -->
		<script src="<?php echo base_url('assets/js/bootstrap.min.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/chosen.jquery.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/jquery.maskedinput.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/accounting.min.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/jquery.validate.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/additional-methods.min.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/function.js'); ?>"></script>

		<script>
		$(function(){
			$('input[name="booking_id"]').focus();

			/* pagination */
			$('.pagination-area>a, .pagination-area>strong').addClass('btn btn-sm btn-primary');
			$('.pagination-area>strong').addClass('disabled');

			/*--------- date mask ---------*/
			$('.date-mask').mask('9999-99-99');

			/* bookingitem-insert-btn */
			$(document).on('click', '.bookingitem-insert-btn', function(){
				add_bookingitem_row();
			});

			/* bookingitem-delete-btn */
			$(document).on('click', '.delete-btn', function(){
				if(confirm('Confirm delete?')){
					$(this).closest('tr').remove();
					calc();
				}else{
					return false;
				}
			});

			/* client loader */
			<?php if($this->router->fetch_method() == 'insert' && isset($this->uri->uri_to_assoc()['booking_client_id'])){ ?>
			client_loader();
			<?php } ?>

			<?php if($this->router->fetch_method() == 'update' && isset($this->uri->uri_to_assoc()['booking_id'])){ ?>
			invoice_loader();
			<?php } ?>

			$(document).on('change', 'select[name="booking_client_id"]', function(){
				client_loader();
			});

			/* product loader */
			$(document).on('change', 'select[name="bookingitem_product_id[]"]', function(){
				product_loader($(this));
			});

			/* invoice loader */
			$("#invoiceModal form").submit(function(e) {
				e.preventDefault();
				$.ajax( {
		      type: "POST",
		      url: $("#invoiceModal form").attr( 'action' ),
		      data: $("#invoiceModal form").serialize(),
		      success: function( response ) {
		        invoice_loader();
		      }
		    });
		  });

			/* trigger calc */
			$(document).on('blur', 'input[name="booking_discount"]', function(){
				calc();
			});
			$(document).on('blur', 'input[name="bookingitem_product_price[]"]', function(){
				calc();
			});
			$(document).on('blur', 'input[name="bookingitem_product_hour[]"]', function(){
				calc();
			});
			$(document).on('blur', 'input[name="bookingitem_quantity[]"]', function(){
				calc();
			});
			$(document).on('change', 'select[name="booking_language"]', function(){
				$.each($('select[name="bookingitem_product_id[]"]'), function(key, val){
					product_loader($(this));
				});
			});
			$(document).on('change', 'select[name="booking_currency"]', function(){
				$.each($('select[name="bookingitem_product_id[]"]'), function(key, val){
					product_loader($(this));
				});
			});

			/* up & down btn */
			$(document).on('click', '.up-btn', function(){
				if($(this).closest('tr').index() > 0){
					$('table.list tbody tr').eq($(this).closest('tr').index()).after($('table.list tbody tr').eq($(this).closest('tr').index() - 1));
				}
			});
			$(document).on('click', '.down-btn', function(){
				if($('table.list tbody tr').length > $(this).closest('tr').index()){
					$('table.list tbody tr').eq($(this).closest('tr').index()).before($('table.list tbody tr').eq($(this).closest('tr').index() + 1));
				}
			});

			/* textarea auto height */
			textarea_auto_height();
			$(document).on('keyup', 'textarea', function(){
				textarea_auto_height();
			});
		});

		function client_loader(){
			$('.scriptLoader').load('<?php echo base_url("load"); ?>', {'thisTableId': 'clientLoader', 'thisRecordId': $('select[name="booking_client_id"]').val(), 't': timestamp()}, function(){
				clientLoader();
			});
		}

		function product_loader(thisObject){
			thisRow = $(thisObject).closest('tr').index();
			thisLanguage = $('select[name="booking_language"]').val();
			thisCurrency = $('select[name="booking_currency"]').val();
			if($(thisObject).val() == -1){
				$("table.list tbody tr:eq("+thisRow+") input[name=\'bookingitem_product_type_name[]\']").val("customise");
				textarea_auto_height();
				calc();
			} else {
				$('.scriptLoader').load('<?php echo base_url("load"); ?>', {'thisTableId': 'productLoader', 'thisRecordId': $(thisObject).val(), 'thisLanguage': thisLanguage, 'thisCurrency': thisCurrency, 'thisRow': thisRow, 't': timestamp()}, function(){
					productLoader();
					textarea_auto_height();
					calc();
				});
			}
		}

		function invoice_loader(){
			$('.scriptLoader').load('<?php echo base_url("load"); ?>', {'thisTableId': 'invoiceLoader', 'thisRecordId': $("#invoiceModal input[name='invoice_booking_id']").val(), 't': timestamp()}, function(){
				invoiceLoader();
			});
		}

		function textarea_auto_height(){
			$.each($('textarea'), function(key, val){
				$(this).attr('rows', $(this).val().split('\n').length + 1);
			});
		}

		function calc(){
			var total = 0.0;
			$.each($('table.list tbody tr'), function(key, val){
				// if($(this).find('input[name="bookingitem_product_type_name[]"]').val() == 'customise'){
				// 	$(this).find('input[name="bookingitem_product_price[]"]').val($(this).find('input[name="bookingitem_product_hour[]"]').val() * $('input[name="booking_hourlyrate"]').val());
				// }
				$(this).find('input[name="bookingitem_subtotal[]"]').val($(this).find('input[name="bookingitem_product_price[]"]').val() * $(this).find('input[name="bookingitem_quantity[]"]').val()).css('display', 'none').fadeIn();
				total += parseFloat($(this).find('input[name="bookingitem_subtotal[]"]').val());
			});
			var discount = parseFloat($('input[name="booking_discount"]').val());
			total = total - discount;
			$('input[name="booking_total"]').val(total).css('display', 'none').fadeIn();
			invoice_loader();
		}

		function check_delete(id){
			var answer = prompt("Confirm delete?");
			if(answer){
				$('input[name="booking_id"]').val(id);
				$('input[name="booking_delete_reason"]').val(encodeURI(answer));
				$('form[name="list"]').attr('action', "<?=base_url('booking/delete')?>");
				$('form[name="list"]').submit();
			}else{
				return false;
			}
		}

		function check_delete_invoice(id){
			var answer = prompt("Confirm delete?");
			if(answer){
				$.ajax( {
		      type: "POST",
		      url: "<?=base_url('booking/deleteInvoice')?>",
		      data: {invoice_id: id, invoice_delete_reason: encodeURI(answer)},
		      success: function( response ) {
		        invoice_loader();
		      }
		    });

			}else{
				return false;
			}
		}

		function check_confirm(id){
			var answer = confirm("Booking confirm by client?");
			if(answer){
				$('input[name="booking_id"]').val(id);
				$('form[name="list"]').attr('action', "<?=base_url('booking/confirm')?>");
				$('form[name="list"]').submit();
			}else{
				return false;
			}
		}

		<?php if($this->router->fetch_method() == 'update' || $this->router->fetch_method() == 'insert' || $this->router->fetch_method() == 'duplicate'){ ?>
		function add_bookingitem_row(){
			bookingitem_row = '';
			bookingitem_row += '<tr>';
			bookingitem_row += '<td>';
			bookingitem_row += '<div>';
			bookingitem_row += '<input name="bookingitem_id[]" type="hidden" value="" />';
			bookingitem_row += '<input name="bookingitem_booking_id[]" type="hidden" value="<?=$booking->booking_id?>" />';
			bookingitem_row += '<input name="bookingitem_product_type_name[]" type="hidden" value="customise" />';
			// bookingitem_row += '<input id="bookingitem_product_code" name="bookingitem_product_code[]" type="text" class="form-control input-sm" placeholder="Code" value="" />';
			bookingitem_row += '</div>';
			// bookingitem_row += '<div class="margin-top-10">';
			// bookingitem_row += '<a class="btn btn-sm btn-primary delete-btn" data-toggle="tooltip" title="Delete">';
			// bookingitem_row += '<i class="glyphicon glyphicon-remove"></i>';
			// bookingitem_row += '</a>';
			// bookingitem_row += '</div>';
			bookingitem_row += '<div class="margin-top-10">';
			bookingitem_row += '<div class="btn-group">';
			bookingitem_row += '<button type="button" class="btn btn-sm btn-primary delete-btn"><i class="glyphicon glyphicon-remove"></i></button>';
			bookingitem_row += '<button type="button" class="btn btn-sm btn-primary up-btn"><i class="glyphicon glyphicon-chevron-up"></i></button>';
			bookingitem_row += '<button type="button" class="btn btn-sm btn-primary down-btn"><i class="glyphicon glyphicon-chevron-down"></i></button>';
			bookingitem_row += '</div>';
			bookingitem_row += '</div>';
			bookingitem_row += '</td>';
			bookingitem_row += '<td>';
			bookingitem_row += '<div>';
			bookingitem_row += '<select id="bookingitem_product_id" name="bookingitem_product_id[]" data-placeholder="Product" class="chosen-select">';
			bookingitem_row += '<option value="-1">CUSTOMISE PRODUCT</option>';
			<?php foreach($products as $key1 => $value1){ ?>
			bookingitem_row += '<option value="<?=$value1->product_id?>"><?=$value1->product_name?></option>';
			<?php } ?>
			bookingitem_row += '</select>';
			bookingitem_row += '</div>';

			bookingitem_row += '<div class="margin-top-10">'
			bookingitem_row += '<div class="input-group">'
			bookingitem_row += '<span class="input-group-addon corpcolor-font">Title</span>'
			bookingitem_row += '<input id="bookingitem_product_name" name="bookingitem_product_name[]" type="text" class="form-control input-sm" placeholder="Name" value="" />';
			bookingitem_row += '</div>'
			bookingitem_row += '</div>';
			bookingitem_row += '<div>';
			bookingitem_row += '<textarea id="bookingitem_product_detail" name="bookingitem_product_detail[]" class="form-control input-sm" placeholder="Detail"></textarea>';
			bookingitem_row += '</div>';
			bookingitem_row += '</td>';
			bookingitem_row += '<td>';
			bookingitem_row += '<input id="bookingitem_product_price" name="bookingitem_product_price[]" type="text" class="form-control input-sm" placeholder="Price" value="" />';
			bookingitem_row += '<div class="input-group">';
			// bookingitem_row += '<input id="bookingitem_product_hour" name="bookingitem_product_hour[]" type="text" class="form-control input-sm" placeholder="Hour" value="" />';
			// bookingitem_row += '<span class="input-group-addon">hrs</span>';
			bookingitem_row += '</div>';
			bookingitem_row += '</td>';
			bookingitem_row += '<td>';
			bookingitem_row += '<input id="bookingitem_quantity" name="bookingitem_quantity[]" type="text" class="form-control input-sm required" placeholder="Quantity" value="1" />';
			bookingitem_row += '</td>';
			bookingitem_row += '<td>';
			bookingitem_row += '<input readonly="readonly" id="bookingitem_subtotal" name="bookingitem_subtotal[]" type="text" class="form-control input-sm" placeholder="Subtotal" value="" />';
			bookingitem_row += '</td>';
			bookingitem_row += '</tr>';

			$('table.list tbody').append(bookingitem_row);
			$('.chosen-select').chosen();
		}
		<?php } ?>
		</script>
	</head>

	<body>

		<?php $this->load->view('inc/header-area.php'); ?>

		








































		<?php if($this->router->fetch_method() == 'update' || $this->router->fetch_method() == 'insert'){ ?>
		<div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12"><a href="<?=base_url('booking')?>">Booking Management</a> > <?=($this->router->fetch_method() == 'update') ? 'Upate' : 'Insert'?> booking</h2>

					<div class="col-sm-12">
						<form method="post" enctype="multipart/form-data">
							<input type="hidden" name="booking_id" value="<?=$booking->booking_id?>" />
							<input type="hidden" name="booking_version" value="<?=$booking->booking_id?>" />
							<input type="hidden" name="booking_serial" value="<?=$booking->booking_id?>" />
							<input type="hidden" name="referrer" value="<?=$this->agent->referrer()?>" />
							<div class="fieldset">
								<?=$this->session->tempdata('alert');?>
								<div class="row">
									
									<div class="col-sm-3 col-xs-12 pull-right">
										<blockquote>
											<h4 class="corpcolor-font">Instructions</h4>
											<p><span class="highlight">*</span> is a required field</p>
											<p>
												<b>BOLD</b>: &lt;b&gt;content&lt;/b&gt;
												<br /><b>ITALIC</b>: &lt;i&gt;content&lt;/i&gt;
												<br /><b>UNDERLINE</b>: &lt;u&gt;content&lt;/u&gt;
											</p>
										</blockquote>
										<h4 class="corpcolor-font">Setting</h4>
										<!-- <p class="form-group">
											<label for="booking_name">Booking name <span class="highlight">*</span></label>
											<input id="booking_name" name="booking_name" type="text" class="form-control input-sm required" placeholder="Booking name" value="<?=$booking->booking_id?>" />
										</p> -->
										<p class="form-group">
											<label for="booking_currency">Currency</label>
											<select id="booking_currency" name="booking_currency" data-placeholder="Currency" class="chosen-select required">
												<?php foreach($currencys as $key => $value){ 
												$selected = ($booking->booking_currency == $value->currency_name) ? 'selected' : (empty($booking->booking_currency) && $value->currency_name == 'hkd') ? 'selected' : '';
												?> 
												<option value="<?=$value->currency_name?>" <?=$selected?>><?=$value->currency_name?></option>
												<?php } ?>
											</select>
										</p>
										<!-- <p class="form-group">
											<label for="booking_display_number">Index number / Part number</label>
											<select id="booking_display_number" name="booking_display_number" data-placeholder="Index number / Part number" class="chosen-select required">
												<option value></option>
											</select>
										</p> -->
										<!-- <p class="form-group">
											<label for="attachment">Business registration</label>
											<input id="attachment" name="attachment" type="file" class="form-control input-sm" placeholder="Business registration" accept="image/*" />
										</p> -->
										<p class="form-group">
											<label for="booking_status">Status</label>
											<select id="booking_status" name="booking_status" data-placeholder="Status" class="chosen-select required">
												<?php foreach($statuses as $key => $value){ 
												$selected = ($booking->booking_status == $value->status_name) ? 'selected' : (empty($booking->booking_status) && $value->status_name == 'complete') ? 'selected' : '';
												?> 
												<option value="<?=$value->status_name?>" <?=$selected?>><?=$value->status_name?></option>
												<?php } ?>
											</select>
										</p>
										<br/>
										<h4 class="corpcolor-font">Invoice</h4>
										<!-- <p class="form-group">
											<label for="booking_name">Booking name <span class="highlight">*</span></label>
											<input id="booking_name" name="booking_name" type="text" class="form-control input-sm required" placeholder="Booking name" value="<?=$booking->booking_id?>" />
										</p> -->
										<div class="form-group">
											<div class="invoice-list">
												<!-- <p><a href="<?=base_url('booking')?>">Invoice 1 - (Paid: 500, Remain: 400), download here</a></p> -->
											</div>
											<p>Remaining: <b class="invoice-remaining">0</b></p>
											<p><b>
												<span data-toggle="modal" data-target="#invoiceModal" class="modal-btn" rel="">
													<a href="javascript:;" data-toggle="tooltip" title="More">
														Create Invoice
													</a>
												</span></b></p>

										</div>

									</div>
									<div class="col-sm-9 col-xs-12">
										<h4 class="corpcolor-font">Booking</h4>
										<div class="row">
											<div class="col-sm-6 col-xs-6">
												<table class="table table-condensed table-borderless">
													<tr>
														<td colspan="2">
															<select id="booking_client_id" name="booking_client_id" data-placeholder="Client" class="chosen-select">
																<option value></option>
																<?php foreach($clients as $key => $value){ 
																$selected = ($value->client_id == $booking->booking_client_id) ? 'selected' : '';
																?>
																<option value="<?=$value->client_id?>" <?=$selected?>><?=($value->client_company_name) ? $value->client_company_name : $value->client_firstname.' '.$value->client_lastname?></option>
																<?php }?>
															</select>
														</td>
													</tr>

													<tr>
														<td><label for="booking_client_name">Attn</label></td>
														<td><input id="booking_client_name" name="booking_client_name" type="text" class="form-control input-sm required" placeholder="Attn." value="<?=$booking->booking_client_name?>" /></td>
													</tr>
													<tr>
														<td><label for="booking_client_company_name">To</label></td>
														<td><input id="booking_client_company_name" name="booking_client_company_name" type="text" class="form-control input-sm" placeholder="Company/Domain/Client" value="<?=$booking->booking_client_company_name?>" /></td>
													</tr>
													<tr>
														<td><label for="booking_client_company_address">Address</label></td>
														<td><textarea id="booking_client_company_address" name="booking_client_company_address" class="form-control input-sm" placeholder="Address"><?=$booking->booking_client_company_address?></textarea></td>
													</tr>
													<tr>
														<td><label for="booking_client_company_phone">Phone</label></td>
														<td><input id="booking_client_company_phone" name="booking_client_company_phone" type="text" class="form-control input-sm" placeholder="Phone" value="<?=$booking->booking_client_company_phone?>" /></td>
													</tr>
													<tr>
														<td><label for="booking_client_phone">Mobile</label></td>
														<td><input id="booking_client_phone" name="booking_client_phone" type="text" class="form-control input-sm" placeholder="Fax" value="<?=$booking->booking_client_phone?>" /></td>
													</tr>
													<tr>
														<td><label for="booking_client_email">Email</label></td>
														<td><input id="booking_client_email" name="booking_client_email" type="text" class="form-control input-sm" placeholder="Email" value="<?=$booking->booking_client_email?>" /></td>
													</tr>
												</table>
											</div>
											<div class="col-sm-1 col-xs-1">
											</div>
											<div class="col-sm-5 col-xs-5">
												<table class="table table-condensed table-borderless">
													<tr>
														<td><label for="booking_number">Booking#</label></td>
														<td>
															<div class="input-group">
																<input readonly="readonly" id="booking_number" name="booking_number" type="text" class="form-control input-sm" placeholder="Booking#" value="<?=$booking->booking_number?>" />
																<span class="input-group-addon"><?='v'.$booking->booking_version?></span>
															</div>
														</td>
													</tr>
													<tr>
														<td><label for="booking_issue">Date</label></td>
														<td><input id="booking_issue" name="booking_issue" type="text" class="form-control input-sm date-mask required" placeholder="Issue date" value="<?php echo $booking->booking_issue ? $booking->booking_issue : date('Y-m-d'); ?>" /></td>
													</tr>
													<tr>
														<td><label for="booking_user_name">Sales</label></td>
														<td><input readonly="readonly" id="booking_user_name" name="booking_user_name" type="text" class="form-control input-sm required" placeholder="Sales" value="<?=($booking->booking_user_name) ? $booking->booking_user_name : $user->user_name?>" /></td>
													</tr>
													<!-- <tr>
														<td><label for="booking_terms">Payment terms</label></td>
														<td><input id="booking_terms" name="booking_terms" type="text" class="form-control input-sm required" placeholder="Payment terms" value="<?=$booking->booking_id?>" /></td>
													</tr> -->
													<!-- <tr>
														<td><label for="booking_expire">Expire Date</label></td>
														<td><input id="booking_expire" name="booking_expire" type="text" class="form-control input-sm date-mask" placeholder="Expire Date" value="<?=$booking->booking_id?>" /></td>
													</tr> -->
												</table>
											</div>
										</div>
										<div class="list-area">
											<table class="table list" id="booking">
												<thead>
													<tr>
														<th width="10%">
															<a class="btn btn-sm btn-primary bookingitem-insert-btn" data-toggle="tooltip" title="Insert">
																<i class="glyphicon glyphicon-plus"></i>
															</a>
														</th>
														<th>Detail</th>
														<th width="12%">Price</th>
														<th width="8%">Quantity</th>
														<th width="12%">Subtotal</th>
													</tr>
												</thead>
												<tbody>
													<?php foreach($bookingitems as $key => $value){ ?>
													<tr>
														<td>
															<div>
																<input name="bookingitem_id[]" type="hidden" value="<?=$value->bookingitem_id?>" />
																<input name="bookingitem_booking_id[]" type="hidden" value="<?=$value->bookingitem_booking_id?>" />
																<input name="bookingitem_product_type_name[]" type="hidden" value="<?=$value->bookingitem_product_type_name?>" />
																<!-- <input id="bookingitem_product_code" name="bookingitem_product_code[]" type="text" class="form-control input-sm" placeholder="Code" value="<?=$booking->bookingitem_product_code?>" /> -->
															</div>
															<div class="margin-top-10">
																<div class="btn-group">
																	<button type="button" class="btn btn-sm btn-primary delete-btn"><i class="glyphicon glyphicon-remove"></i></button>
																	<button type="button" class="btn btn-sm btn-primary up-btn"><i class="glyphicon glyphicon-chevron-up"></i></button>
																	<button type="button" class="btn btn-sm btn-primary down-btn"><i class="glyphicon glyphicon-chevron-down"></i></button>
																</div>
															</div>
														</td>
														<td>
															<div>
																<select id="bookingitem_product_id" name="bookingitem_product_id[]" data-placeholder="Product" class="chosen-select">
																	<option value="-1">CUSTOMISE PRODUCT</option>
																	<?php foreach($products as $key2 => $a_product){
                                  $selected = ($a_product->product_id == $value->bookingitem_product_id) ? ' selected="selected"' : "" ; ?>
                                  <option value="<?php echo $a_product->product_id ?>" <?php echo $selected ?>><?php echo $a_product->product_name ?></option>
                                  <?php } ?>
																</select>
															</div>
															<div class="margin-top-10">
																<div class="input-group">
																	<span class="input-group-addon corpcolor-font">Title</span>
																	<input id="bookingitem_product_name" name="bookingitem_product_name[]" type="text" class="form-control input-sm" placeholder="Name" value="<?=$value->bookingitem_product_name?>" />
																</div>
															</div>
															<div>
																<textarea id="bookingitem_product_detail" name="bookingitem_product_detail[]" class="form-control input-sm" placeholder="Detail"><?=$value->bookingitem_product_detail?></textarea>
															</div>
														</td>
														<td>
															<input id="bookingitem_product_price" name="bookingitem_product_price[]" type="number" class="form-control input-sm" placeholder="Price" value="<?=$value->bookingitem_product_price?>" />
														</td>
														<td>
															<input id="bookingitem_quantity" name="bookingitem_quantity[]" type="number" class="form-control input-sm required" placeholder="Quantity" value="<?=$value->bookingitem_quantity?>" />
														</td>
														<td>
															<input readonly="readonly" id="bookingitem_subtotal" name="bookingitem_subtotal[]" type="text" class="form-control input-sm" placeholder="Subtotal" value="<?=$value->bookingitem_subtotal?>" />
														</td>
													</tr>
													<?php } ?>
												</tbody>
												<tfoot>
													<tr>
														<th></th>
														<th></th>
														<th></th>
														<th>Discount</th>
														<th><input id="booking_discount" name="booking_discount" type="number" class="form-control input-sm required" placeholder="Discount" value="<?=($booking->booking_discount) ? $booking->booking_discount : 0?>" /></th>
													</tr>
													<tr>
														<th width="10%">
															<a class="btn btn-sm btn-primary bookingitem-insert-btn" data-toggle="tooltip" title="Insert">
																<i class="glyphicon glyphicon-plus"></i>
															</a>
														</th>
														<th></th>
														<th></th>
														<th>Grand total</th>
														<th><input readonly="readonly" id="booking_total" name="booking_total" type="text" class="form-control input-sm" placeholder="Grand total" value="<?=$booking->booking_total?>" /></th>
													</tr>
												</tfoot>
											</table>
										</div>
										<div class="hr"></div>
										<p class="form-group">
											<label for="booking_remark">Remark</label>
											<textarea id="booking_remark" name="booking_remark" class="form-control input-sm" placeholder="Remark"><?=$booking->booking_remark?></textarea>
										</p>
										<p class="form-group">
											<label for="booking_payment">Payment</label>
											<textarea id="booking_payment" name="booking_payment" class="form-control input-sm" placeholder="Payment"><?=$booking->booking_payment?></textarea>
										</p>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12">
										<button type="submit" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-floppy-disk"></i> Save</button>
									</div>
								</div>

							</div>
						</form>
					</div>

				</div>
			</div>




		</div>
		<?php } ?>

		











































		<?php if($this->router->fetch_method() == 'select'){ ?>
		<div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12">Booking Management</h2>

					<div class="content-column-area col-md-12 col-sm-12">

						<div class="fieldset">
							<div class="search-area">

								<form booking="form" method="get">
									<input type="hidden" name="booking_id" />
									<table>
										<tbody>
											<tr>
												<td width="90%">
													<div class="row">
														<div class="col-sm-2"><h6>Booking</h6></div>
														<div class="col-sm-2">
															<input type="text" name="booking_id" class="form-control input-sm" placeholder="#" value="" />
														</div>
														<div class="col-sm-2">
															<input type="text" name="booking_name_like" class="form-control input-sm" placeholder="Booking name" value="" />
														</div>
														<div class="col-sm-2"></div>
														<div class="col-sm-2"></div>
														<div class="col-sm-2"></div>
														<div class="col-sm-2"></div>
													</div>
												</td>
												<td valign="top" width="10%" class="text-right">
													<button type="submit" class="btn btn-sm btn-primary" data-toggle="tooltip" title="Search">
														<i class="glyphicon glyphicon-search"></i>
													</button>
												</td>
											</tr>
										</tbody>
									</table>
								</form>

							</div> <!-- list-container -->
						</div>
						<div class="fieldset">

							<form name="invoice-form" action="<?=base_url('booking/deleteInvoice')?>" method="post" class="hide">
									<input type="hidden" name="invoice_id" />
									<input type="hidden" name="invoice_delete_reason" />
							</form>

							<div class="list-area">
								<form name="list" action="<?=base_url('booking/delete')?>" method="post">
									<input type="hidden" name="booking_id" />
									<input type="hidden" name="booking_delete_reason" />
									<div class="page-area">
										<span class="btn btn-sm btn-default"><?php print_r($num_rows); ?></span>
										<?=$this->pagination->create_links()?>
									</div>
									<table id="booking" class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>#</th>
												<th>
													<a href="<?=get_order_link('booking_number')?>">
														Booking# <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th>
														Name
												</th>
												<th>
														Remark
												</th>
												<th width="120">
														Stock Out
												</th>
												<th width="120">
														Status
												</th>
												<th width="120">
													<a href="<?=get_order_link('booking_modify')?>">
														Modify <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<!-- <th width="40"></th> -->
												<th width="40"></th>
												<th width="40" class="text-right">
													<a href="<?=base_url('booking/insert')?>" data-toggle="tooltip" title="Insert">
														<i class="glyphicon glyphicon-plus"></i>
													</a>
												</th>
											</tr>
										</thead>
										<tbody>
											<?php foreach($bookings as $key => $value){ ?>
											<tr>
												<td title="<?=$value->booking_id?>"><?=$key+1?></td>
												<td><?=$value->booking_number?></td>
												<td><?=($value->booking_client_name) ? ucfirst($value->booking_client_company_name) : ucfirst($value->booking_client_name)?></td>
												<td><?=$value->booking_remark?></td>
												<td>
													<?php if(!empty($value->stock_outs)){
																	foreach($value->stock_outs as $key2 => $value2){ ?>
													<a href="<?=base_url('stock_out/update/stock_out_id/'.$value2->stock_out_id)?>">SO-<?=$value2->stock_out_id?></a><br/>
													<?php }
													} else { ?>
													<a href="<?=base_url('booking/defaultStockOut/booking_id/'.$value->booking_id.'/referrer/booking')?>">default stock out</a><br/>
													<a href="<?=base_url('stock_out/insert/booking_id/'.$value->booking_id.'/referrer/booking')?>">create stock out</a>
													<?php } ?>
													
												</td>
												<td><?=$value->booking_status?></td>
												<td><?=convert_datetime_to_date($value->booking_modify)?></td>
												<!-- <td class="text-right">
													<span data-toggle="modal" data-target="#myModal" class="modal-btn" rel="<?=$value->booking_id?>">
														<a data-toggle="tooltip" title="More">
															<i class="glyphicon glyphicon-chevron-right"></i>
														</a>
													</span>
												</td> -->
												<td class="text-right">
													<a href="<?=base_url('booking/update/booking_id/'.$value->booking_id)?>" data-toggle="tooltip" title="Update">
														<i class="glyphicon glyphicon-edit"></i>
													</a>
												</td>
												<td class="text-right">
													<a onclick="check_delete('<?=$value->booking_id?>');" class="<?=check_permission('booking_delete', 'disable')?>" data-toggle="tooltip" title="Delete">
														<i class="glyphicon glyphicon-remove"></i>
													</a>
												</td>
											</tr>
											<?php } ?>
										</tbody>
									</table>
									<div class="page-area">
										<span class="btn btn-sm btn-default"><?php print_r($num_rows); ?></span>
										<?=$this->pagination->create_links()?>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<?php } ?>












































		<?php $this->load->view('inc/footer-area.php'); ?>

	</body>
</html>

<div class="scriptLoader"></div>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<i class="glyphicon glyphicon-remove"></i>
				</button>
				<h4 class="modal-title corpcolor-font">Detail</h4>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

<div class="modal fade" id="invoiceModal" role="dialog">
	<form method="post" enctype="multipart/form-data" action="<?=base_url('booking/createInvoice')?>" target="_tempFrame">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<i class="glyphicon glyphicon-remove"></i>
					</button>
					<h4 class="modal-title corpcolor-font">Create Invoice</h4>
				</div>
				<div class="modal-body">
						<input id="invoice_booking_id" name="invoice_booking_id" value="<?=$booking->booking_id; ?>" type="hidden">
						<input id="invoice_booking_id" name="a_invoice_booking_number" value="<?=$booking->booking_number; ?>" type="hidden">
						<div class="row">
							<div class="col-sm-6 col-xs-6">
								<table class="table table-condensed table-borderless">

									<tr>
										<td><label for="invoice_paid">Paid</label></td>
										<td><input id="invoice_paid" name="invoice_paid" type="text" class="form-control input-sm required" placeholder="Paid" value="0" /></td>
									</tr>

									<tr>
										<td><label for="booking_issue">Date</label></td>
										<td><input id="booking_issue" name="booking_issue" type="text" class="form-control input-sm date-mask required" placeholder="Issue date" value="<?php echo date('Y-m-d'); ?>" /></td>
									</tr>

								</table>
							</div>
						</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-warning">Save</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>

		</form>
	</div>
</div>
<!-- myModal -->

<iframe id="_tempFrame" name="_tempFrame" class="hide"></iframe>