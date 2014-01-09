<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
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
	src="${pageContext.request.contextPath}/resources/js/bootstrap-alert.js"></script>
	<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
	<title><spring:message code="title.books"/></title>
</head>
<body>
	<tiles:insertAttribute name="header" />
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span1">
					<tiles:insertAttribute name="left" /> 
				</div>
				<tiles:insertAttribute name="body" /> 
			</div>
		</div> 
	<tiles:insertAttribute name="footer" />  
</body>  
</html>  
