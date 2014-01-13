<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!-- TODO: remove old files-->
<html>
<head>
 <title>Test</title>
</head>
<body>
<%@ include file="/view/top.jsp" %>
<%@ include file="/view/left.jsp" %>
<div id = "content">
	<h4> Congradulations! </h4>
	You are <b>${visitorCount}</b> visitor <br/>
	Today is <b>${today}</b>
	<a href="<c:url value="/books"/>">Books</a>
	<a href="<c:url value="/users"/>">Users</a>
	<a href="<c:url value= "/wishList"/>">Wish lists</a>
        <a href="<c:url value="/authorizedUser"/>">Authorized User</a>
</div>
</body>
</html>