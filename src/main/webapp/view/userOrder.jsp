<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <h1>Current Orders</h1>
        <table>    
          <thead>
		<tr>
                    <td>&nbsp;</td>
			<td>booksID</td>
			<td>personId</td>
		</tr>
	 </thead>
         <c:forEach items="${showOrders}" var="order">
		<tr>
		<!--	<td>${order.date}</td>-->
			<td>${order.book.title}</td>
                       
                        
                        
		</tr>
	</c:forEach>
       </table>
    </body>
</html>
