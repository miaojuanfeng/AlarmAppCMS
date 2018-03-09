<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Config management</title>

		<%@ include file="inc/MetaArea.jsp" %>

	</head>

	<body>

		<%@ include file="inc/HeaderArea.jsp" %>

		








































		<c:if test="${method == 'insert' || method == 'update'}">
		<div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12"><a href="<c:url value="/cms/config/update"></c:url>">Config management</a> > ${method} config</h2>

					<div class="col-sm-12">
						<form method="post">
							<div class="fieldset">
								<div class="row">
									
									<div class="col-sm-4 col-xs-12 pull-right">
										<blockquote>
											<h4 class="corpcolor-font">Administrator information</h4>
											<p><span class="highlight">*</span> is a required field</p>
										</blockquote>
									</div>
									<div class="col-sm-4 col-xs-12">
										<h4 class="corpcolor-font">Basic information</h4>
										<p class="form-group">
											<label for="username">Username <span class="highlight">*</span></label>
											<input id="username" name="username" type="text" class="form-control input-sm required" placeholder="Username" value="${username}" />
										</p>
										<p class="form-group">
											<label for="password">Password <span class="highlight"></span></label>
											<input id="password" name="password" type="password" class="form-control input-sm" placeholder="Password" />
										</p>
									</div>
									<div class="col-sm-4 col-xs-12">

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
		</c:if>

		











































		<c:if test="${method == 'select'}">
		<%-- <div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12">Product preset</h2>

					<div class="content-column-area col-md-12 col-sm-12">

						<div class="fieldset">
							<div class="search-area">

								<form product="form" method="get">
									<input type="hidden" name="product_id" />
									<table>
										<tbody>
											<tr>
												<td width="90%">
													<div class="row">
														<div class="col-sm-2"><h6>Brand</h6></div>
														<div class="col-sm-2">
															<input type="text" name="product_id" class="form-control input-sm" placeholder="#" value="" />
														</div>
														<div class="col-sm-2">
															<input type="text" name="product_code_like" class="form-control input-sm" placeholder="Product code" value="" />
														</div>
														<div class="col-sm-2">
															<input type="text" name="product_name_like" class="form-control input-sm" placeholder="Product name" value="" />
														</div>
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
								<form name="list" action="<?=base_url('product/delete')?>" method="post">
									<input type="hidden" name="product_id" />
									<input type="hidden" name="product_delete_reason" />
									<div class="page-area">
										<span class="btn btn-sm btn-default"><?php print_r($num_rows); ?></span>
										<?=$this->pagination->create_links()?>
									</div>
									<table id="product" class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>#</th>
												<th>
													<a href="<?=get_order_link('product_code')?>">
														Code <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th>
													<a href="<?=get_order_link('product_name')?>">
														Name <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th>
													Price <a><i class="glyphicon glyphicon-sort corpcolor-font"></i></a>
													<a href="<?=get_order_link('product_price_rmb')?>">RMB</a>/<a href="<?=get_order_link('product_price_hkd')?>">HKD</a>/<a href="<?=get_order_link('product_price_usd')?>">USD</a>
												</th>
												<th>
													<a href="<?=get_order_link('product_modify')?>">
														Modify <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th width="40"></th>
												<th width="40" class="text-right">
													<a href="<?=base_url('product/insert')?>" data-toggle="tooltip" title="Insert">
														<i class="glyphicon glyphicon-plus"></i>
													</a>
												</th>
											</tr>
										</thead>
										<tbody>
											<?php foreach($products as $key => $value){ ?>
											<tr>
												<td title="<?=$value->product_id?>"><?=$key+1?></td>
												<td><?=$value->product_code?></td>
												<td><?=$value->product_name?></td>
												<td><?='RMB '.$value->product_price_rmb.' / HKD '.$value->product_price_hkd.' / USD '.$value->product_price_usd?></td>
												<td><?=convert_datetime_to_date($value->product_modify)?></td>
												<td class="text-right">
													<a href="<?=base_url('product/update/product_id/'.$value->product_id)?>" data-toggle="tooltip" title="Update">
														<i class="glyphicon glyphicon-edit"></i>
													</a>
												</td>
												<td class="text-right">
													<a onclick="check_delete('<?=$value->product_id?>');" class="<?=check_permission('product_delete', 'disable')?>" data-toggle="tooltip" title="Delete">
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

		</div> --%>
		</c:if>	












































		<%@ include file="inc/FooterArea.jsp" %>

	</body>
</html>