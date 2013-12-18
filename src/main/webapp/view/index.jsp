<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<html>
<head>
 <title>Test</title>
</head>
<body>
<h4> Congradulations! </h4>
You are <b>${visitorCount}</b> visitor <br/>
Today is <b>${today}</b>
<a href="<c:url value="/books"/>">Books</a>
</body>
</html>