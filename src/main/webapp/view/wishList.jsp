<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp" %>
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
			<td>${wish.person.name}</td>
                        <td><a href="<c:url value="/order"/>">Create order</a></td>
		</tr>
	</c:forEach>
        </table>
    </body>
</html>
