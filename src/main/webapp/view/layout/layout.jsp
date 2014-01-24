<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
	<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/jquery.datetimepicker.css"	
        rel="stylesheet" type="text/css"/>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap-alert.js"></script>
	<%-- <script src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script> --%>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/account.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/person.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/books.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.maskedinput.min.js"></script>
	<title><spring:message code="title.books"/></title>
</head>
<body>
	<tiles:insertAttribute name="header" />
		<div class="container-fluid body-content">
			<div class="row-fluid rf">
				<tiles:insertAttribute name="left" /> 
				<tiles:insertAttribute name="body" /> 
				<tiles:insertAttribute name="right" />
			</div>
		</div> 
	<tiles:insertAttribute name="footer" />  
</body>  
</html>  
