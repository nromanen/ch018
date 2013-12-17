<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books</title>
</head>
<body>
<table>
	<thead>
		<tr>
			<td>ID</td>
			<td>Title</td>
			<td>Authors</td>
			<td>Publication</td>
			<td>Year</td>
		</tr>
	</thead>
	<c:forEach items="${books}" var="book">
		<tr>
			<td>${book.id}</td>
			<td><c:out value="${book.title}" escapeXml="true"/></td>
			<td><c:out value="${book.authors}" escapeXml="true"/></td>
			<td>${book.publication}</td>
			<td>${book.year}</td>
			<td><a href="<c:url value="/editbook?id=${book.id}"/>">Edit</a></td>
			<td><a href="<c:url value="/deletebook?id=${book.id}"/>">Delete</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>