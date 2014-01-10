<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/css/userView.css"
	      rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"	rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/resources/css/jquery.datetimepicker.css"	rel="stylesheet" type="text/css"/>
        <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
        <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script	src="${pageContext.request.contextPath}/resources/js/bootstrap-alert.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/account.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
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
        <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
      <!--  <input type="text" name="orderDate" value="<%= df.format(new java.util.Date()) %>"/> -->
        <br><br>
             <!--  <table>
                   
                    <tr><td>You wish to order </td>
                        <td>${book.title}, ${book.authors}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Choose Issue Date:</td>
                        <td><input class="datetimepicker" type="text"/></td>
                    </tr>                  
               </table> -->
                        <form:form method="POST" modelAttribute="order">
                            <table>
                                <tr><td>Choose issue date:</td> 
                                    <td><form:input path="issueDate" class="datetimepicker"/></td></tr>
                                <tr><td></td>
                                    <td><form:input path="book.title"/></td>
                                </tr>
                                <tr><td></td>
                                    <td><form:input path="book.id" class="hidden"/></td>
                                </tr>
                                <tr><td></td>
                                    <td><form:input path="person.id" class="hidden"/></td>
                                </tr>
                                <tr><td><input type="submit" class="btn" value="Order"/></td></tr>
                             </table>
                        </form:form>
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
