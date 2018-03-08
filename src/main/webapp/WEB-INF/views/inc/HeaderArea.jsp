		

		
		<div class="header-area">
			<div class="no-wrapper">

				<div class="header-header1-area">

					<div class="container-fluid">
						<div class="row hidden-xs">
							<div class="col-xs-12">

								<div class="header-desktop-area">
									<div class="pull-left">
										<h1>Exercise <small>Alarm</small></h1>
									</div>
									<div class="pull-right">
										<i class="glyphicon glyphicon-user corpcolor-font"></i> <?=ucfirst(get_user($this->session->userdata('user_id'))->user_name)?> (<a href="<?=base_url('login')?>">Logout</a>)
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="header-header2-area">
					<div class="container-fluid">
						<div class="row">

							<div class="col-xs-12">
								<nav class="navbar navbar-default">
									<div class="wrapper">
										<div class="navbar-header">
											<button type="button" class="btn btn-primary navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
												&nbsp;<i class="glyphicon glyphicon-th"></i>&nbsp;
											</button>
											<div class="navbar-brand hidden-lg hidden-md hidden-sm">Eyepopper <small style="font-size:12px;">ERP</small></div>
										</div>
										<div id="navbar" class="navbar-collapse collapse" aria-expanded="false">
											<ul class="nav navbar-nav">
												<li class="active">
													<a href="<?=base_url('dashboard')?>">Dashboard</a>
												</li>
												<li>
													<a href="<?=base_url('product')?>">Product</a>
												</li>
												<li>
													<a href="<?=base_url('booking')?>">Booking</a>
												</li>
												<li>
													<a href="<?=base_url('z_stock_product')?>">Warehouse</a>
												</li>
												<li class="active">
													<a href="<?=base_url('maintenance')?>">Maint.</a>
												</li>
												<li class="active">
													<a href="<?=base_url('report')?>">Report</a>
												</li>
											</ul>
										</div>
									</div>
								</nav>
							</div>

						</div>
					</div>

				</div>
			</div>
		</div>



