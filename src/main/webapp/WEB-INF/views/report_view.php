<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Account report</title>

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
		});
		</script>
	</head>

	<body>

		<?php $this->load->view('inc/header-area.php'); ?>

		








































		<?php if($this->router->fetch_method() == 'update' || $this->router->fetch_method() == 'insert'){ ?>
		<?php } ?>

		











































		<?php if($this->router->fetch_method() == 'select'){ ?>
		<div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12">Report</h2>

					<div class="content-column-area col-md-12 col-sm-12">

						<div class="fieldset min-height-500">

							<h4>Account</h4>
							<div class="row">
								<div class="col-md-3 col-sm-12">
									<blockquote>
										<i class="glyphicon glyphicon-usd"></i>
										<a href="<?=base_url('income_report')?>">Income report</a>
									</blockquote>
								</div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
							</div>

							<div class="hr"></div>

							<h4>Sales</h4>
							<div class="row">
								<div class="col-md-3 col-sm-12">
									<blockquote>
										<i class="glyphicon glyphicon-log-out"></i>
										<a href="<?=base_url('sales_report')?>">Sales report</a>
									</blockquote>
								</div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
							</div>

							<div class="hr"></div>

							<h4>Client</h4>
							<div class="row">
								<div class="col-md-3 col-sm-12">
									<blockquote>
										<i class="glyphicon glyphicon-user"></i>
										<a href="<?=base_url('client_report')?>">Client report</a>
									</blockquote>
								</div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
							</div>

							<div class="hr"></div>

							<h4>Warehouse</h4>
							<div class="row">
								<div class="col-md-3 col-sm-12">
									<blockquote>
										<i class="glyphicon glyphicon-home"></i>
										<a href="<?=base_url('product')?>">Warehouse report</a>
									</blockquote>
								</div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
							</div>

							<div class="hr"></div>

							<h4>Product</h4>
							<div class="row">
								<div class="col-md-3 col-sm-12">
									<blockquote>
										<i class="glyphicon glyphicon-hdd"></i>
										<a href="<?=base_url('product')?>">Product report</a>
									</blockquote>
								</div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
							</div>

							<div class="hr"></div>

							<h4>Log</h4>
							<div class="row">
								<div class="col-md-3 col-sm-12">
									<blockquote>
										<i class="glyphicon glyphicon-list"></i>
										<a href="<?=base_url('log')?>">Log</a>
									</blockquote>
								</div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
								<div class="col-md-3 col-sm-12"></div>
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