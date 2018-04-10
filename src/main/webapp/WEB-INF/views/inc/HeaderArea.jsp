		

		
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
										<i class="glyphicon glyphicon-user corpcolor-font"></i> ${user_nickname} (<a href="<c:url value="/cms/logout"></c:url>">Logout</a>)
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
											<div class="navbar-brand hidden-lg hidden-md hidden-sm">Exercise <small style="font-size:12px;">Alarm</small></div>
										</div>
										<div id="navbar" class="navbar-collapse collapse" aria-expanded="false">
											<ul class="nav navbar-nav">
												<li <c:if test="${classLower=='dashboard'}">class="active"</c:if>>
													<a href="<c:url value="/cms/dashboard/select"></c:url>">Dashboard</a>
												</li>
												<li <c:if test="${classLower=='discuss'}">class="active"</c:if>>
													<a href="<c:url value="/cms/discuss/select"></c:url>">Discuss</a>
												</li>
												<li <c:if test="${classLower=='expert'}">class="active"</c:if>>
													<a href="<c:url value="/cms/expert/select"></c:url>">Expert</a>
												</li>
												<li <c:if test="${classLower=='comment'}">class="active"</c:if>>
													<a href="<c:url value="/cms/comment/select"></c:url>">Comment</a>
												</li>
												<li <c:if test="${classLower=='user'}">class="active"</c:if>>
													<a href="<c:url value="/cms/user/select"></c:url>">User</a>
												</li>
												<li <c:if test="${classLower=='config'}">class="active"</c:if>>
													<a href="<c:url value="/cms/config/update"></c:url>">Config</a>
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



