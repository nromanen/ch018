<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
<title><spring:message code="title.login"/></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
			</div>
			<div class="span5">
			<div class="well">
				<form class="form-horizontal" action="j_spring_security_check" method="post">
					<div class="control-group">
						<label class="control-label" for="inputEmail"><spring:message code="person.mail"/></label>
						<div class="controls">
							<input name="j_username" id="inputEmail" class="span8" type="email" required>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword"><spring:message code="person.pass"/></label>
						<div class="controls">
							<input name="j_password" type="password" class="span8" id="inputPassword" placeholder="Pass">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox"> <input type="checkbox" name="_spring_security_remember_me">
								<spring:message code="menu.remember"/>
							</label>
							<button type="submit" class="btn"><spring:message code="button.login"/></button>
						</div>
					</div>
				</form>
				</div>
				
			</div>
			<div class="span4">
			</div>
		</div>
	</div>
</body>
</html>