<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	       rel="stylesheet" type="text/css" />
        <script	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <title>Account properties</title>
    </head>
    <body>
        <h4>Account properties</h4>
        <form>
            <input type="text" id="name">
            <input type="text" id="surname">
            <input type="text" id="cellphone">
            <input type="text" id="email">
            <a
        </form>
    </body>
</html>
