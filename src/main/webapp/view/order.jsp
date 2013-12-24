<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New order</title>
    </head>
    <body>
        <h3>Order items</h3>
        <br> Enter Order Date, and confirm order by click on button: &nbsp;
        
        <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
        <input type="text" name="orderDate" value="<%= df.format(new java.util.Date()) %>"/>
             <c:forEach items="${newOrder}" var="wish">
               <table>
                
                    <tr><td>You wish to order ${wish.book.title}</td></tr>
               <!-- <tr><td>Your name${wish.person.name}</td></tr>
                     <tr><td>Your Surname${wish.person.surname}</td></tr>-->
                 </table>
           </c:forEach>   
    </body>
</html>
