<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Stock In Management</title>

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
		<script src="<?php echo base_url('assets/js/echarts.min.js'); ?>"></script>
		<script src="<?php echo base_url('assets/js/function.js'); ?>"></script>

		<script>
		$(function(){
			$('input[name="invoice_id"]').focus();

			/* pagination */
			$('.pagination-area>a, .pagination-area>strong').addClass('btn btn-sm btn-primary');
			$('.pagination-area>strong').addClass('disabled');

			/*--------- date mask ---------*/
			$('.date-mask').mask('9999-99-99');

			/* invoice-completed-btn */
			$(document).on('click', '.completed-btn', function(){
				if($(this).is(':checked')){
					$(this).parent().parent().find('input[name="invoice_account_remark[]"]').prop('disabled', false).focus();
				}else{
					$(this).parent().parent().find('input[name="invoice_account_remark[]"]').prop('disabled', true);
				}
			});
		});
		</script>
	</head>

	<body>

		<?php $this->load->view('inc/header-area.php'); ?>

		








































		<?php if($this->router->fetch_method() == 'update' || $this->router->fetch_method() == 'insert'){ ?>
		<div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12"><a href="<?=base_url('stock_in')?>">Stock In Management</a> > <?=($this->router->fetch_method() == 'update') ? 'Upate' : 'Insert'?> Stock In</h2>

					<div class="col-sm-12">
						<form method="post" enctype="multipart/form-data">
							<input type="hidden" name="stock_in_id" value="<?=$stock_in->stock_in_id?>" />
							<!-- <input type="hidden" name="z_stock_in_product_stock_in_id[]" value="<?=$stock_in->stock_in_id?>" /> -->
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
										<!-- <h4 class="corpcolor-font">Setting</h4> -->
										<!-- <p class="form-group">
											<label for="stock_in_project_name">Project name <span class="highlight">*</span></label>
											<input id="stock_in_project_name" name="stock_in_project_name" type="text" class="form-control input-sm required" placeholder="Project name" value="<?=$stock_in->stock_in_id?>" />
										</p> -->
										<!-- <p class="form-group">
											<label for="stock_in_currency">Currency</label>
											<select id="stock_in_currency" name="stock_in_currency" data-placeholder="Currency" class="chosen-select required">
												<option value></option>
											</select>
										</p> -->
										<!-- <p class="form-group">
											<label for="stock_in_display_number">Index number / Part number</label>
											<select id="stock_in_display_number" name="stock_in_display_number" data-placeholder="Index number / Part number" class="chosen-select required">
												<option value></option>
											</select>
										</p>
										<p class="form-group">
											<label for="attachment">Business registration</label>
											<input id="attachment" name="attachment" type="file" class="form-control input-sm" placeholder="Business registration" accept="image/*" />
										</p> -->
										<!-- <p class="form-group">
											<label for="stock_in_status">Status</label>
											<select id="stock_in_status" name="stock_in_status" data-placeholder="Status" class="chosen-select required">
												<option value></option>
											</select>
										</p> -->
									</div>
									<div class="col-sm-9 col-xs-12">
										<div class="row">
											<div class="col-sm-6 col-xs-6">
												<table class="table table-condensed table-borderless">
													<tr>
														<td colspan="2">
															<select id="stock_in_vendor_id" name="stock_in_vendor_id" data-placeholder="Company/Vendor" class="chosen-select">
																<option value></option>
																<?php
																foreach($vendors as $key1 => $value1){
																	$selected = ($value1->vendor_id == $stock_in->stock_in_vendor_id || $value1->vendor_id == $this->uri->uri_to_assoc()['stock_in_vendor_id']) ? ' selected="selected"' : "" ;
																	echo '<option value="'.$value1->vendor_id.'"'.$selected.'>'.$value1->vendor_name.'</option>';
																}
																?>
															</select>
														</td>
													</tr>
													<tr>
														<td><label for="stock_in_vendor_name">From (if not in the list)</label></td>
														<td><input id="stock_in_vendor_name" name="stock_in_vendor_name" type="text" class="form-control input-sm" placeholder="Company/Vendor" value="<?=$stock_in->stock_in_vendor_name?>" /></td>
													</tr>
													<!-- <tr>
														<td><label for="stock_in_vendor_company_address">Address</label></td>
														<td><textarea id="stock_in_vendor_company_address" name="stock_in_vendor_company_address" class="form-control input-sm" placeholder="Address"><?=$stock_in->stock_in_id?></textarea></td>
													</tr>
													<tr>
														<td><label for="stock_in_vendor_company_phone">Phone</label></td>
														<td><input id="stock_in_vendor_company_phone" name="stock_in_vendor_company_phone" type="text" class="form-control input-sm" placeholder="Phone" value="<?=$stock_in->stock_in_id?>" /></td>
													</tr>
													<tr>
														<td><label for="stock_in_vendor_phone">Mobile</label></td>
														<td><input id="stock_in_vendor_phone" name="stock_in_vendor_phone" type="text" class="form-control input-sm" placeholder="Fax" value="<?=$stock_in->stock_in_id?>" /></td>
													</tr>
													<tr>
														<td><label for="stock_in_vendor_email">Email</label></td>
														<td><input id="stock_in_vendor_email" name="stock_in_vendor_email" type="text" class="form-control input-sm" placeholder="Email" value="<?=$stock_in->stock_in_id?>" /></td>
													</tr>
													<tr>
														<td><label for="stock_in_vendor_name">Attn</label></td>
														<td><input id="stock_in_vendor_name" name="stock_in_vendor_name" type="text" class="form-control input-sm required" placeholder="Attn." value="<?=$stock_in->stock_in_id?>" /></td>
													</tr> -->
												</table>
											</div>
											<div class="col-sm-1 col-xs-1">
											</div>
											<div class="col-sm-5 col-xs-5">
												<table class="table table-condensed table-borderless">
													<!-- <tr>
														<td><label for="stock_in_number">Quotation#</label></td>
														<td>
															<div class="input-group">
																<input readonly="readonly" id="stock_in_number" name="stock_in_number" type="text" class="form-control input-sm" placeholder="Quotation#" value="<?=$stock_in->stock_in_id?>" />
																<span class="input-group-addon"><?='v'.$stock_in->stock_in_id?></span>
															</div>
														</td>
													</tr> -->
													<tr>
														<td><label for="stock_in_issue">Date (Y-m-d)</label></td>
														<td><input id="stock_in_issue" name="stock_in_issue" type="text" class="form-control input-sm date-mask required" placeholder="Issue date" value="<?php echo $stock_in->stock_in_issue ? $stock_in->stock_in_issue : date('Y-m-d'); ?>" /></td>
													</tr>
													<!-- <tr>
														<td><label for="stock_in_user_name">Sales</label></td>
														<td><input readonly="readonly" id="stock_in_user_name" name="stock_in_user_name" type="text" class="form-control input-sm required" placeholder="Sales" value="<?=$stock_in->stock_in_id?>" /></td>
													</tr> -->
													<!-- <tr>
														<td><label for="stock_in_terms">Payment terms</label></td>
														<td><input id="stock_in_terms" name="stock_in_terms" type="text" class="form-control input-sm required" placeholder="Payment terms" value="<?=$stock_in->stock_in_id?>" /></td>
													</tr>
													<tr>
														<td><label for="stock_in_expire">Expire Date</label></td>
														<td><input id="stock_in_expire" name="stock_in_expire" type="text" class="form-control input-sm date-mask" placeholder="Expire Date" value="<?=$stock_in->stock_in_id?>" /></td>
													</tr> -->
												</table>
											</div>
										</div>
										<div class="list-area">
											<table class="table" id="stock_in">
												<thead>
													<tr>
														<th width="10%">
															<a class="btn btn-sm btn-primary stock_in_product-insert-btn" data-toggle="tooltip" title="Insert">
																<i class="glyphicon glyphicon-plus"></i>
															</a>
														</th>
														<th>Detail</th>
														<th width="16%">Warehouse</th>
														<th width="16%">Quantity</th>
													</tr>
												</thead>
												<tbody>
													<?php foreach($products as $key => $product){ ?>
													<tr>
														<td>
															<div>
																<input name="z_stock_in_product_id[]" type="hidden" value="" />
																<!-- <input name="stock_initem_product_type_name[]" type="hidden" value="<?=$stock_in->stock_in_id?>" /> -->
																<!-- <input id="stock_initem_product_code" name="stock_initem_product_code[]" type="text" class="form-control input-sm" placeholder="Code" value="<?=$stock_in->stock_in_id?>" /> -->
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
																<select id="z_stock_in_product_product_id" name="z_stock_in_product_product_id[]" data-placeholder="Product" class="chosen-select">
																	<option value="0"></option>
																	<?php foreach($a_products as $key => $a_product){
																	$selected = ($a_product->product_id == $product->z_stock_in_product_product_id) ? ' selected="selected"' : "" ; ?>
																	<option value="<?php echo $a_product->product_id ?>" <?php echo $selected ?>><?php echo $a_product->product_name ?></option>
														      <?php } ?>
																</select>
															</div>
															<div>
																<textarea id="z_stock_in_product_remark" name="z_stock_in_product_remark[]" class="form-control input-sm" placeholder="Remark"></textarea>
															</div>
														</td>
                            <td>
                              <div>
                                <select id="z_stock_in_product_warehouse_id" name="z_stock_in_product_warehouse_id[]" data-placeholder="Warehouse" class="chosen-select">
                                	<?php foreach($warehouses as $key => $warehouse){ 
                                	$selected = ($warehouse->warehouse_id == $product->z_stock_in_product_warehouse_id) ? ' selected="selected"' : "" ; ?>
                                	<option value="<?php echo $warehouse->warehouse_id ?>" <?php echo $selected ?>><?php echo $warehouse->warehouse_name ?></option>
                                	<?php } ?>
                                </select>
                              </div>
                            </td>
														<td>
															<input id="z_stock_in_product_quantity" name="z_stock_in_product_quantity[]" type="text" class="required form-control input-sm" placeholder="Quantity" value="<?php echo $product->z_stock_in_product_quantity ?>" />
														</td>
													</tr>
													<?php } ?>
												</tbody>
												<!-- <tfoot>
													<tr>
														<th></th>
														<th></th>
														<th></th>
														<th>Discount</th>
														<th><input id="stock_in_discount" name="stock_in_discount" type="text" class="form-control input-sm required" placeholder="Discount" value="<?=$stock_in->stock_in_id?>" /></th>
													</tr>
													<tr>
														<th width="10%">
															<a class="btn btn-sm btn-primary stock_initem-insert-btn" data-toggle="tooltip" title="Insert">
																<i class="glyphicon glyphicon-plus"></i>
															</a>
														</th>
														<th></th>
														<th></th>
														<th>Grand total</th>
														<th><input readonly="readonly" id="stock_in_total" name="stock_in_total" type="text" class="form-control input-sm" placeholder="Grand total" value="<?=$stock_in->stock_in_id?>" /></th>
													</tr>
												</tfoot> -->
											</table>
										</div>
										<div class="hr"></div>
										<p class="form-group">
											<label for="stock_in_remark">Remark</label>
											<textarea id="stock_in_remark" name="stock_in_remark" class="form-control input-sm" placeholder="Remark"><?=$stock_in->stock_in_id?></textarea>
										</p>
										<!-- <p class="form-group">
											<label for="stock_in_payment">Payment</label>
											<textarea id="stock_in_payment" name="stock_in_payment" class="form-control input-sm" placeholder="Payment"><?=$stock_in->stock_in_id?></textarea>
										</p> -->
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

          <h2 class="col-sm-12">Warehouse Management - Stock In List</h2>

            <div class="content-column-area col-md-12 col-sm-12">

              <div class="fieldset min-height-500">
                <!-- <div class="row">

                  <div class="col-md-3 col-sm-12">
                    <blockquote>
                      <i class="glyphicon glyphicon-user"></i>
                      <a href="<?=base_url('stock_in')?>">Stock In</a>
                    </blockquote>
                  </div>
                  <div class="col-md-3 col-sm-12">
                    <blockquote>
                      <i class="glyphicon glyphicon-user"></i>
                      <a href="<?=base_url('stock_out')?>">Stock Out</a>
                    </blockquote>
                  </div>
                  <div class="col-md-3 col-sm-12">
                    <blockquote>
                      <i class="glyphicon glyphicon-user"></i>
                      <a href="<?=base_url('stock_switch')?>">Stock Transfer</a>
                    </blockquote>
                  </div>
                  <div class="col-md-3 col-sm-12">
                    <blockquote>
                      <i class="glyphicon glyphicon-user"></i>
                      <a href="<?=base_url('consignment')?>">Consignment</a>
                    </blockquote>
                  </div>

                </div>

                <div class="hr"></div> -->

                

								<div class="fieldset">
									<div class="search-area">

										<form stock_in="form" method="get">
											<input type="hidden" name="stock_in_id" />
											<input type="hidden" name="z_stock_in_product_stock_in_id" />
											<table>
												<tbody>
													<tr>
														<td width="90%">
															<div class="row">
																<div class="col-sm-2"><h6>Filters</h6></div>
																<div class="col-sm-2">
																	<input type="text" name="stock_in_id" class="form-control input-sm" placeholder="#" value="" />
																</div>
																<div class="col-sm-2">
																	<input type="text" name="stock_in_name_like" class="form-control input-sm" placeholder="Name" value="" />
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

									<div class="list-area">
										<form name="list" action="<?=base_url('stock_in/delete')?>" method="post">
											<input type="hidden" name="stock_in_id" />
											<input type="hidden" name="z_stock_in_product_stock_in_id" />
											<input type="hidden" name="stock_in_delete_reason" />
											<div class="page-area">
												<span class="btn btn-sm btn-default"><?php print_r($num_rows); ?></span>
												<?=$this->pagination->create_links()?>
											</div>
											<table id="stock_in" class="table table-striped table-bordered">
												<thead>
													<tr>
														<th width="40">#</th>
														<!-- <th>
															<a href="<?=get_order_link('stock_in_name')?>">
																Name <i class="glyphicon glyphicon-sort corpcolor-font"></i>
															</a>
														</th> -->
														<th>
															<a>
																Source/Vendor</i>
															</a>
														</th>
														<th width="160">
															<a href="<?=get_order_link('stock_in_issue')?>">
																Issue <i class="glyphicon glyphicon-sort corpcolor-font"></i>
															</a>
														</th>
														<th width="160">
															<a href="<?=get_order_link('stock_in_modify')?>">
																Modify <i class="glyphicon glyphicon-sort corpcolor-font"></i>
															</a>
														</th>
														<!-- <th width="40"></th> -->
														<th width="40"></th>
														<th width="40" class="text-right">
															<a href="<?=base_url('stock_in/insert')?>" data-toggle="tooltip" title="Insert">
																<i class="glyphicon glyphicon-plus"></i>
															</a>
														</th>
													</tr>
												</thead>
												<tbody>
													<?php foreach($stock_ins as $key => $value){ ?>
													<tr>
														<td title="<?=$value->stock_in_id?>"><?=$key+1?></td>
														<!-- <td><?=ucfirst($value->stock_in_name)?></td> -->
														<td><?=ucfirst($value->stock_in_vendor_name)?></td>
														<td><?=convert_datetime_to_date($value->stock_in_issue)?></td>
														<td><?=convert_datetime_to_date($value->stock_in_modify)?></td>
														<!-- <td class="text-right">
															<span data-toggle="modal" data-target="#myModal" class="modal-btn" rel="<?=$value->stock_in_id?>">
																<a data-toggle="tooltip" title="More">
																	<i class="glyphicon glyphicon-chevron-right"></i>
																</a>
															</span>
														</td> -->
														<td class="text-right">
															<a href="<?=base_url('stock_in/update/stock_in_id/'.$value->stock_in_id)?>" data-toggle="tooltip" title="Update">
																<i class="glyphicon glyphicon-edit"></i>
															</a>
														</td>
														<td class="text-right">
															<a onclick="check_delete('<?=$value->stock_in_id?>');" class="<?=check_permission('stock_in_delete', 'disable')?>" data-toggle="tooltip" title="Delete">
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

    </div>

		<?php } ?>












































		<?php $this->load->view('inc/footer-area.php'); ?>

	</body>
</html>

<div class="scriptLoader"></div>

<script type="text/javascript">

	$(function(){
		$('input[name="stock_in_id"]').focus();

		/* pagination */
		$('.pagination-area>a, .pagination-area>strong').addClass('btn btn-sm btn-primary');
		$('.pagination-area>strong').addClass('disabled');

		/*--------- date mask ---------*/
		$('.date-mask').mask('9999-99-99');

		/* stock_in_product-insert-btn */
		$(document).on('click', '.stock_in_product-insert-btn', function(){
			add_stock_in_product_row();
		});

		/* stock_in_product-delete-btn */
		$(document).on('click', '.delete-btn', function(){
			if(confirm('Confirm delete?')){
				$(this).closest('tr').remove();
				calc();
			}else{
				return false;
			}
		});

		/* vendor loader */
		$(document).on('change', 'select[name="stock_in_vendor_id"]', function(){
			vendor_loader();
		});

		/* product loader */
		$(document).on('change', 'select[name="stock_in_product_product_id[]"]', function(){
			product_loader($(this));
		});

		/* trigger calc */
		$(document).on('blur', 'input[name="stock_in_product_quantity[]"]', function(){
			calc();
		});
		$(document).on('change', 'select[name="stock_in_currency"]', function(){
			$.each($('select[name="stock_in_product_product_id[]"]'), function(key, val){
				product_loader($(this));
			});
		});

		/* up & down btn */
		$(document).on('click', '.up-btn', function(){
			if($(this).closest('tr').index() > 0){
				$('table#stock_in tbody tr').eq($(this).closest('tr').index()).after($('table#stock_in tbody tr').eq($(this).closest('tr').index() - 1));
			}
		});
		$(document).on('click', '.down-btn', function(){
			if($('table#stock_in tbody tr').length > $(this).closest('tr').index()){
				$('table#stock_in tbody tr').eq($(this).closest('tr').index()).before($('table#stock_in tbody tr').eq($(this).closest('tr').index() + 1));
			}
		});

		/* textarea auto height */
		textarea_auto_height();
		$(document).on('keyup', 'textarea', function(){
			textarea_auto_height();
		});
	});

	function vendor_loader(){
		$('.scriptLoader').load("<?php echo base_url('load'); ?>", {'thisTableId': 'vendorLoader', 'thisRecordId': $('select[name="stock_in_vendor_id"]').val(), 't': timestamp()}, function(){
			vendorLoader();
		});
	}

	function product_loader(thisObject){
		thisRow = $(thisObject).closest('tr').index();
		thisLanguage = $('select[name="stock_in_language"]').val();
		thisCurrency = $('select[name="stock_in_currency"]').val();
		$('.scriptLoader').load('/load', {'thisTableId': 'productLoader', 'thisRecordId': $(thisObject).val(), 'thisLanguage': thisLanguage, 'thisCurrency': thisCurrency, 'thisRow': thisRow, 't': timestamp()}, function(){
			productLoader();
			textarea_auto_height();
			calc();
		});
	}

	function textarea_auto_height(){
		$.each($('textarea'), function(key, val){
			$(this).attr('rows', $(this).val().split('\n').length + 1);
		});
	}

	function calc(){
		var total = 0;
		$.each($('table.list tbody tr'), function(key, val){
			if($(this).find('input[name="stock_in_product_product_type_name[]"]').val() == 'customise'){
				$(this).find('input[name="stock_in_product_product_price[]"]').val($(this).find('input[name="stock_in_product_product_hour[]"]').val() * $('input[name="stock_in_hourlyrate"]').val());
			}
			$(this).find('input[name="stock_in_product_subtotal[]"]').val($(this).find('input[name="stock_in_product_product_price[]"]').val() * $(this).find('input[name="stock_in_product_quantity[]"]').val()).css('display', 'none').fadeIn();
			total += parseInt($(this).find('input[name="stock_in_product_subtotal[]"]').val());
			$('input[name="stock_in_total"]').val(total).css('display', 'none').fadeIn();
		});
	}

	function check_delete(id){
		var answer = prompt("Confirm delete?");
		if(answer){
			$('input[name="stock_in_id"]').val(id);
			$('input[name="z_stock_in_product_stock_in_id"]').val(id);
			$('input[name="stock_in_delete_reason"]').val(encodeURI(answer));
			$('form[name="list"]').attr('action', "<?php echo base_url('stock_in/delete'); ?>");
			$('form[name="list"]').submit();
		}else{
			return false;
		}
	}

	function check_confirm(id){
		var answer = confirm("Quotation confirm by vendor?");
		if(answer){
			$('input[name="stock_in_id"]').val(id);
			$('form[name="list"]').attr('action', "<?php echo base_url('stock_in/delete'); ?>");
			$('form[name="list"]').submit();
		}else{
			return false;
		}
	}

	function add_stock_in_product_row(){
		stock_in_product_row = '';
		stock_in_product_row += '<tr>';

		stock_in_product_row += '<td>';
		stock_in_product_row += '<div>';
		stock_in_product_row += '<input name="z_stock_in_product_id[]" type="hidden" value="" />';
		stock_in_product_row += '</div>';
		stock_in_product_row += '<div class="margin-top-10">';
		stock_in_product_row += '<div class="btn-group">';
		stock_in_product_row += '<button type="button" class="btn btn-sm btn-primary delete-btn"><i class="glyphicon glyphicon-remove"></i></button>';
		stock_in_product_row += '<button type="button" class="btn btn-sm btn-primary up-btn"><i class="glyphicon glyphicon-chevron-up"></i></button>';
		stock_in_product_row += '<button type="button" class="btn btn-sm btn-primary down-btn"><i class="glyphicon glyphicon-chevron-down"></i></button>';
		stock_in_product_row += '</div>';
		stock_in_product_row += '</div>';
		stock_in_product_row += '</td>';

		stock_in_product_row += '<td>';
		stock_in_product_row += '<div>';
		stock_in_product_row += '<select id="z_stock_in_product_product_id" name="z_stock_in_product_product_id[]" data-placeholder="Product" class="chosen-select">';
		stock_in_product_row += '<option value="0"></option>';
		<?php foreach($a_products as $key => $product){ ?> 
      stock_in_product_row += '<option value="<?php echo $product->product_id ?>"><?php echo $product->product_name ?></option>';
    <?php } ?>
		stock_in_product_row += '</select>';
		stock_in_product_row += '</div>';
		stock_in_product_row += '<div>';
		stock_in_product_row += '<textarea id="z_stock_in_product_remark" name="z_stock_in_product_remark[]" class="form-control input-sm" placeholder="Remark"></textarea>';
		stock_in_product_row += '</div>';
		stock_in_product_row += '</td>';

		stock_in_product_row += '<td>';
		stock_in_product_row += '<div>';
		stock_in_product_row += '<select id="z_stock_in_product_warehouse_id" name="z_stock_in_product_warehouse_id[]" data-placeholder="Warehouse" class="chosen-select">';
		<?php foreach($warehouses as $key => $warehouse){ ?> 
      stock_in_product_row += '<option value="<?php echo $warehouse->warehouse_id ?>"><?php echo $warehouse->warehouse_name ?></option>';
    <?php } ?>
		stock_in_product_row += '</select>';
		stock_in_product_row += '</div>';
		stock_in_product_row += '</td>';

		stock_in_product_row += '<td>';
		stock_in_product_row += '<input id="z_stock_in_product_quantity" name="z_stock_in_product_quantity[]" type="text" class="required form-control input-sm" placeholder="Quantity" value="" />';
		stock_in_product_row += '</td>';

		stock_in_product_row += '</tr>';

		// productCount++;

		$('#stock_in tbody').append(stock_in_product_row);
		$('.chosen-select').chosen();
	}
</script>