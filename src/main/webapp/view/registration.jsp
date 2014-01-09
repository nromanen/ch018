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

<%-- <script src="${pageContext.request.contextPath}/resources/js/validform.js"></script> --%>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
<title><spring:message code="title.reg"/></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
			</div>
			<div class="span5">

				<div class="well" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
    					<h3 id="myModalLabel"><spring:message code="reg.registration"/></h3>
  					</div>
  					<div class="modal-body">
  					
					<form:form id="registration" class="form-horizontal" method="POST" commandName="registration" action="${pageContext.request.contextPath}/registration">
						
						<div class="control-group">
    						<label class="control-label" for="inputEmailReg"><spring:message code="person.mail"/></label>
    						<div class="controls">
      							<form:input path="email" type="text" id="inputEmailReg" />
      							<form:errors path="email" cssClass="error"></form:errors>
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="inputPasswordReg" ><spring:message code="person.pass"/></label>
    						<div class="controls">
      							<form:input path="password" type="password" id="inputPasswordReg" placeholder="Pass" />
    							<form:errors path="password" cssClass="error"></form:errors>
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="confirmPasswordReg"><spring:message code="person.confirmpass"/></label>
    						<div class="controls">
      							<form:input path="confirmPassword" type="password" id="confirmPasswordReg" placeholder="Pass" />
    							<form:errors path="confirmPassword" cssClass="error"></form:errors>
    						</div>
  						</div>

  						<div class="form-actions">
							<button type="submit" class="btn btn-primary" ><spring:message code="button.reg"/></button>
							<button id="cancel" type="button" class="btn" ><spring:message code="button.cancel"/></button>
						</div>
					</form:form>
					</div>
				</div>
				
			</div>
			<div class="span4">
			</div>
		</div>
	</div>
</body>
</html>