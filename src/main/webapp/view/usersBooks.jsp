<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp"%>
<!DOCTYPE html>
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
        <title></title>
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
                                    <table class="table table-bordered">
            <thead>
                <tr>
                    <td>Book Title</td>
                    <td>Issue Date</td>
                    <td>Return Date</td>
                    <td>term</td>
                </tr>
            </thead>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.book.title}</td>
                    <td>${book.issueDate}</td>
                    <td>${book.returnDate}</td>
                    <td>${book.term}</td>
                </tr>
            </c:forEach>
        </table>
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
