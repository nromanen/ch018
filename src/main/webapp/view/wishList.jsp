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
        
   <!--     <table>
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
                        <td><a href="<c:url value="/order"/>">Create order</a></td>
		</tr>
	</c:forEach>
        </table>
        <br>      -->
         <table>
	 <thead>
		<tr>
                    <td>&nbsp;</td>
			<td>booksID</td>
			<td>personId</td>
		</tr>
	 </thead>
            <c:forEach items="${wishByPers}" var="wishByPers">
		<tr>
                        <td><input type="checkbox" name="maths" checked="checked" /></td>
                        <!--<td>${wishByPers.id}</td>  -->
			<td>${wishByPers.book.id}</td>
			<td>${wishByPers.person.id}</td>
                        <td><a href="<c:url value="/delete?del=${wishByPers.id}"/>">Delete</a></td>
                        <td><a href="<c:url value="/order?id=${wishByPers.person.id}"/>"><input type="submit" value="Create Order"/></a></td>
		</tr>
	</c:forEach>
        </table>
    </body>
</html>
