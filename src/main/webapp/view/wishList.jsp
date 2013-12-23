<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>wishList</h3>
        
        <table>
	<thead>
		<tr>
			<td>Id</td>
			<td>booksID</td>
			<td>personId</td>
		</tr>
	</thead>
            <c:forEach items="${wish}" var="wish">
		<tr>
			<td>${wish.id}</td>
			<td>${wish.book.id}</td>
			<td>${wish.person.id}</td>
			
		</tr>
	</c:forEach>
        </table>
    </body>
</html>
