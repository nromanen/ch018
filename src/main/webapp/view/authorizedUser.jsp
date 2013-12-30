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
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	       rel="stylesheet" type="text/css" />
        <script	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <title>Library</title>
    </head>
    <body>
        <div id="wrapper">
            <div class="header">
                <center>
                    <br><br><br><br>
                    <input type="text" class="span2" id="search" width="300px"/>
                    <input type="submit" value="Search in Library" id="searchButton" class="btn"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group">
                        <a href="<c:url value="/wishList"/>" class="btn">Wish List</a>&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value="/userOrder"/>" class="btn">Orders</a>&nbsp;&nbsp;&nbsp;
                    <a href="<c:url value="/usersBooks"/>" class="btn">My books</a>&nbsp;&nbsp;&nbsp;
                    </div>
                </center>
            </div>
            <div class="leftcolumn">
              <!--  <img src="${pageContext.request.contextPath}/resources/img/${latest.image}">
                   <p><label>${latest.title}</label>
                   <p><label>${latest.authors}</label>
                   <p><label>${latest.title}</label>-->
                    <!-- <ul class="thumbnails">
                        <li class="span2">
                           <div class="thumbnail">
                              <img src="${pageContext.request.contextPath}/resources/img/${latest.image}">
                              <h3 class="span2"><small>${latest.title}</small></h3>
                              <p class="span2">${latest.authors}</p>
                              <p class="span2">${latest.description}</p>
                                <button class="btn-mini" >Add to Cart</button>
                           </div>
                        </li>
           </ul>-->
            </div>
           <div class="content">content</div>
            <div class="rightcolumn">right</div>
            <div class="footer">footer</div>
        </div>
    </body>
</html>
