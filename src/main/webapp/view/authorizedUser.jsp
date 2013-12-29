<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/userView.css"
	rel="stylesheet" type="text/css" />
        <title>Library</title>
    </head>
    <body>
        <div id="wrapper">
            <div class="header">
                <center>
                    <br><br><br><br>
                    <input type="text" id="search" width="300px"/>
                    <input type="submit" value="Serch in Library" id="searchButton"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value="/wishList"/>">Wish List</a>&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value="/userOrder"/>">Orders</a>&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value="/usersBooks"/>">My books</a>&nbsp;&nbsp;&nbsp;
                </center>
            </div>
            <div class="leftcolumn">leftcol</div>
            <div class="content">content</div>
            <div class="rightcolumn">right</div>
            <div class="footer">footer</div>
        </div>
    </body>
</html>
