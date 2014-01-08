<!--
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
    </body>
</html> -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="${pageContext.request.contextPath}/resources/css/userView.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap-alert.js"></script>
<title>Order</title>
</head>
<body>
	<!-- Include header -->
	<%@ include file="/view/top.jsp"%>

	<div class="container-fluid">

		<!-- Content -->

		<div class="row-fluid">

			<!-- Left side -->
			<div class="span1">
				<%@ include file="/view/left.jsp"%>
			</div>

			<!-- Center -->
			<div class="span10">
                            <h3 class="text-info"><small>Wish list</small></h3>
 
<table class="table table-bordered">
	 <thead>
		<tr>
                    <td>Title</td>
		    <td>Genre</td>
		    <td>Authors</td>
                    <td>Publicated</td>
		</tr>
	 </thead>
            <c:forEach items="${wishByPers}" var="wishByPers">
                <tr class="info">
                      <!--  <td><input type="checkbox" name="maths" checked="checked" /></td>-->
                        <!--<td>${wishByPers.id}</td>  -->
			<td>${wishByPers.book.title}</td>
                        <td>${wishByPers.book.genre}</td>
			<td>${wishByPers.book.authors}</td>
                        <td>${wishByPers.book.year}</td>
                        <td><a href="<c:url value="/delete?del=${wishByPers.id}"/>" class="btn btn-warning">Delete</a></td>
                        <td><a href="<c:url value="/order?book=${wishByPers.book.id}&wish=${wishByPers.id}"/>"><input type="submit" class="btn btn-success" value="Create Order"/></a></td>
		</tr>
	  </c:forEach>
        </table>
        ${fail}
			</div>

			<!-- Right side -->
			<div class="span1">Right side</div>
		</div>
	</div>

	<!-- footer -->

	<footer class="footer">
		<div class="container">
			<div class="row">
				<div class="span12">
					<p>This is FOOTER</p>
				</div>
			</div>
		</div>
	</footer>

</body>
</html>