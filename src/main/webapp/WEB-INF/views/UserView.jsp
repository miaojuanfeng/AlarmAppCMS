<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${classUpper} management</title>

		<%@ include file="inc/MetaArea.jsp" %>

		<script>
		$(function(){
			$('input[name="product_id"]').focus();

			/* pagination */
			$('.pagination-area>a, .pagination-area>strong').addClass('btn btn-sm btn-primary');
			$('.pagination-area>strong').addClass('disabled');
		});

		function check_delete(id){
			var answer = confirm("Confirm delete?");
			if(answer){
				$('input[name="user_id"]').val(id);
				$('form[name="list"]').submit();
			}else{
				return false;
			}
		}

		function login_as(id){
			$('input[name="product_id"]').val(id);
			$('input[name="act"]').val('login_as');
			$('form[name="list"]').submit();
		}
		</script>
	</head>

	<body>

		<%@ include file="inc/HeaderArea.jsp" %>

		








































		<c:if test="${method == 'insert' || method == 'update'}">
		<div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12"><a href="<c:url value="/cms/${classLower}/select"></c:url>">${classUpper} management</a> > ${method} ${classLower}</h2>

					<div class="col-sm-12">
						<form:form name="update" method="post" modelAttribute="user">
							<input type="hidden" name="user_id" value="${user.id}" />
							<input type="hidden" name="referrer" value="${referer}" />
							<div class="fieldset">
								<div class="row">
									
									<div class="col-sm-4 col-xs-12 pull-right">
										<blockquote>
											<h4 class="corpcolor-font">Instructions</h4>
											<p><span class="highlight">*</span> is a required field</p>
										</blockquote>
									</div>
									<div class="col-sm-4 col-xs-12">
										<h4 class="corpcolor-font">Basic information</h4>
										<p class="form-group">
											<label for="username">Username <span class="highlight">*</span></label>
											<form:input id="username" path="username" type="text" class="form-control input-sm required" placeholder="Username" />
										</p>
										<p class="form-group">
											<label for="password">Password <span class="highlight">*</span></label>
											<form:input id="password" path="password" type="password" class="form-control input-sm required" placeholder="Password" />
										</p>
									</div>
									<div class="col-sm-4 col-xs-12">
										<h4 class="corpcolor-font">Basic information</h4>
										<div class="form-group">
											<label for="nickname">Nickname <span class="highlight">*</span></label>
											<form:input id="nickname" path="nickname" type="text" class="form-control input-sm required" placeholder="Nickname" />
										</div>
										<div class="form-group">
											<label for="platform">Platform <span class="highlight">*</span></label>
											<form:select path="platform" class="form-control input-sm required">
												<form:option value="ios" selected="selected">ios</form:option>
												<form:option value="android">android</form:option>
											</form:select>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12">
										<button type="submit" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-floppy-disk"></i> Save</button>
									</div>
								</div>

							</div>
						</form:form>
					</div>

				</div>
			</div>




		</div>
		</c:if>

		











































		<c:if test="${method == 'select'}">
		<div class="content-area">

			<div class="container-fluid">
				<div class="row">

					<h2 class="col-sm-12">${classUpper} management</h2>

					<div class="content-column-area col-md-12 col-sm-12">

						<%-- <div class="fieldset">
							<div class="search-area">

								<form product="form" method="get">
									<input type="hidden" name="product_id" />
									<table>
										<tbody>
											<tr>
												<td width="90%">
													<div class="row">
														<div class="col-sm-2"><h6>Search</h6></div>
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
						</div> --%>
						<div class="fieldset">
							
							<div class="list-area">
								<form name="list" action="<c:url value="/cms/${classLower}/delete"></c:url>" method="post">
									<input type="hidden" name="user_id" />
									<%@ include file="inc/PageArea.jsp" %>
									<table id="product" class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>#</th>
												<th>
													<a href="
														<c:choose>
															<c:when test="${order == 'username' && ascend == 'asc'}">
																<c:url value="/cms/${classLower}/select/order/username/ascend/desc/1"></c:url>
															</c:when>
															<c:otherwise>
																<c:url value="/cms/${classLower}/select/order/username/ascend/asc/1"></c:url>
															</c:otherwise>
														</c:choose>
													">
														Username <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th>
													<a href="
														<c:choose>
															<c:when test="${order == 'number' && ascend == 'asc'}">
																<c:url value="/cms/${classLower}/select/order/number/ascend/desc/1"></c:url>
															</c:when>
															<c:otherwise>
																<c:url value="/cms/${classLower}/select/order/number/ascend/asc/1"></c:url>
															</c:otherwise>
														</c:choose>
													">
														Number <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th>
													<a href="
														<c:choose>
															<c:when test="${order == 'nickname' && ascend == 'asc'}">
																<c:url value="/cms/${classLower}/select/order/nickname/ascend/desc/1"></c:url>
															</c:when>
															<c:otherwise>
																<c:url value="/cms/${classLower}/select/order/nickname/ascend/asc/1"></c:url>
															</c:otherwise>
														</c:choose>">
														Nickname <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th>
													<a href="
														<c:choose>
															<c:when test="${order == 'create_date' && ascend == 'asc'}">
																<c:url value="/cms/${classLower}/select/order/create_date/ascend/desc/1"></c:url>
															</c:when>
															<c:otherwise>
																<c:url value="/cms/${classLower}/select/order/create_date/ascend/asc/1"></c:url>
															</c:otherwise>
														</c:choose>
													">
														Create <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th>
													<a href="
														<c:choose>
															<c:when test="${order == 'modify_date' && ascend == 'asc'}">
																<c:url value="/cms/${classLower}/select/order/modify_date/ascend/desc/1"></c:url>
															</c:when>
															<c:otherwise>
																<c:url value="/cms/${classLower}/select/order/modify_date/ascend/asc/1"></c:url>
															</c:otherwise>
														</c:choose>
														">
														Modify <i class="glyphicon glyphicon-sort corpcolor-font"></i>
													</a>
												</th>
												<th width="40"></th>
												<th width="40" class="text-right">
													<a href="<c:url value="/cms/${classLower}/insert"></c:url>" data-toggle="tooltip" title="Insert">
														<i class="glyphicon glyphicon-plus"></i>
													</a>
												</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${user}" var="item">
											<tr>
												<td title="${item.id}">${item.id}</td>
												<td>${item.username}</td>
												<td>${item.number}</td>
												<td>${item.nickname}</td>
												<td><fmt:formatDate  value="${item.createDate}"  pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td><fmt:formatDate  value="${item.modifyDate}"  pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td class="text-right">
													<a href="<c:url value="/cms/${classLower}/update/${item.id}"></c:url>" data-toggle="tooltip" title="Update">
														<i class="glyphicon glyphicon-edit"></i>
													</a>
												</td>
												<td class="text-right">
													<a onclick="check_delete(${item.id});" class="" data-toggle="tooltip" title="Delete">
														<i class="glyphicon glyphicon-remove"></i>
													</a>
												</td>
											</tr>
											</c:forEach>
											
											<c:if test="${totalRecord == 0}">
											<tr class="list-row">
												<td colspan="10"><a href="#" class="btn btn-sm btn-primary">No record found</a></td>
											</tr>
											</c:if>
											
										</tbody>
									</table>
									<%@ include file="inc/PageArea.jsp" %>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		</c:if>












































		<%@ include file="inc/FooterArea.jsp" %>

	</body>
</html>