<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="span7 offset2">
	<div class="well">
		${errorMessage} Go <a href="<c:url value="/"/>"><spring:message code="menu.home" /></a>!
	</div>
	<img src="<c:url value="/resources/img/error.png"/>" class="img-error">
</div>
<div class="span3"></div>