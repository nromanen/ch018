<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath}/resources/css/userView.css"
	rel="stylesheet" type="text/css" />
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
	src="${pageContext.request.contextPath}/resources/js/bootstrap-alert.js"></script>
<title>Order</title>
</head>
<body>
	<!-- Include header -->
	<%@ include file="/view/top.jsp"%>

	<div class="container-fluid">

		<!-- Content -->

		<div class="row-fluid">

			<!-- Left side -->
			<div class="span1">
				<%@ include file="/view/left.jsp"%>
			</div>

			<!-- Center -->
			<div class="span10">   
                            <br><br><br><br>
                            <form:form method="POST" commandName="password">
                                       <table>
                                           <tr>
                                               <td>Old password:</td>
                                               <td><form:password path="password"/></td>
                                           </tr>
                                           <tr>
                                               <td>Enter new passord:</td>
                                               <td><form:password path="newPassword"/></td>
                                           </tr>
                                           <tr>
                                               <td>Repeat new password:</td>
                                               <td><form:password path="confirmPassword"/></td>
                                           </tr>
                                           <tr><td><input type="submit" value="Change password" class="btn"/></td></tr>
                                       </table>
                                           </form:form>    
			</div>

			<!-- Right side -->
			<div class="span1">Right side</div>
		</div>
	</div>

	<!-- footer -->

	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="span12">
					<p>This is FOOTER</p>
				</div>
			</div>
		</div>
	</footer>

</body>
</html>
