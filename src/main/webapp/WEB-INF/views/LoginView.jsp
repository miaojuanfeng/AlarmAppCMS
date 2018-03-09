<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Login</title>

		<%@ include file="inc/MetaArea.jsp" %>
		<link rel="stylesheet" href="<c:url value="/assets/css/login.css"></c:url>">

		<script>
		$(function(){
			$('#user_username').focus();
		});
		</script>
	</head>

	<body>

		






















		<div class="login-area">
			<div class="error">${error}</div>
			<div class="container-fluid">
				<div class="row">
					<div class="content-column-area col-sm-7 col-xs-12">
						<div class="fieldset left">
							<h2 class="corpcolor-font"><span>Login</span></h2>
							<form method="post">
								<table>
									<tbody>
										<tr>
											<td><label for="user_username">Username</label></td>
											<td><input type="text" id="user_username" name="user_username" class="form-control input-sm required" placeholder="Username" value="dreamover" /></td>
										</tr>
										<tr>
											<td><label for="user_password">Password</label></td>
											<td><input type="password" id="user_password" name="user_password" class="form-control input-sm required" placeholder="Password" value="dreamover1234" /></td>
										</tr>
										<tr>
											<td></td>
											<td><button type="submit" class="btn-login btn btn-sm btn-primary"><i class="glyphicon glyphicon-send"></i> Login</button></td>
										</tr>
									</body>
								</table>
							</form>
						</div>
					</div>
					<div class="content-column-area col-sm-5 col-xs-12">
						<div class="fieldset right">
							<p>Please use IE9.0 version or later / Firefox, Chrome & Safari 2015-01-01 version or later</p>
							<p>Javascript is required</p>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="inc/FooterArea.jsp" %>
		</div>













































	</body>
</html>