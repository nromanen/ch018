<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		<c:if test="${empty book.id}">Add new Book</c:if>
		<c:if test="${not empty book.id}">Edit Book</c:if>
	</title>
</head>
<body>
	<form:form method="POST" commandName="book">
		<form:label path="id"/>
		<table>
			<tr>
				<td>Title</td>
				<td><form:input path="title"/></td>
			</tr>
			<tr>
				<td>Authors</td>
				<td><form:input path="authors"/></td>
			</tr>
			<tr>
				<td>Year</td>
				<td><form:input path="year"/></td>
			</tr>
			<tr>
				<td>Publication</td>
				<td><form:input path="year"/></td>
			</tr>
			<tr>
				<td>Pages</td>
				<td><form:input path="pages"/></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><form:input path="description"/></td>
			</tr>
			<tr>
				<td>Term</td>
				<td><form:input path="term"/></td>
			</tr>
			<tr>
				<td>Bookcase</td>
				<td><form:input path="bookcase"/></td>
			</tr>
			<tr>
				<td>Shelf</td>
				<td><form:input path="shelf"/></td>
			</tr>
			<tr>
				<td>Genre</td>
				<td>
					<form:select path="id" id="id" items="${genre}" itemValue="id" itemLabel="name" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save"/></td>
			</tr>
		</table>
	</form:form>

</body>
</html>