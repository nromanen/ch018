<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<% String l = org.springframework.context.i18n.LocaleContextHolder.getLocale().getLanguage();%>
<c:set var="lang" scope="session" value="<%=l%>"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"	rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap-responsive.css"	rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/css/jquery.datetimepicker.css"	
        rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/resources/css/jquery.rating.css"
	    rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.custom.css"
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
    <script src="${pageContext.request.contextPath}/resources/js/validform.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
    <c:if test="${lang.equals('ru')}">
		<script src="${pageContext.request.contextPath}/resources/js/messages_ru.js"></script>
	</c:if>
	<c:if test="${lang.equals('uk')}">
		<script src="${pageContext.request.contextPath}/resources/js/messages_uk.js"></script>
	</c:if>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.rating.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.custom.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/search.js"></script>
	<title><spring:message code="title.books"/></title>
</head>
<body>
	<tiles:insertAttribute name="header" />
		<div class="container-fluid body-content">
			<div class="row-fluid">
				<tiles:insertAttribute name="left" /> 
				<tiles:insertAttribute name="body" /> 
				<tiles:insertAttribute name="right" />
			</div>
		</div> 
	<tiles:insertAttribute name="footer" />  
</body>  
</html>  
