<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/userView.css"
	      rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"	rel="stylesheet" type="text/css" />
        <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script	src="${pageContext.request.contextPath}/resources/js/bootstrap-alert.js"></script>
        <title>New order</title>
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
           </div> 
           <!-- Right side -->
            	<div class="span1">
					Right side
				</div>
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
