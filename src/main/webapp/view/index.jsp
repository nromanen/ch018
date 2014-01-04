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
        <title>Library</title>
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
				<div class="row-fluid multiple">
					<c:forEach items="${latest}" var="latest">
						
							<div class="span3">
								<div class="thumbnail">
									<h3><small>${latest.title}</small></h3>
									<p>${latest.authors}</p>
									<p>${latest.description}</p>
									<sec:authorize access="isAuthenticated()">
										<a href="<c:url value="/wishlist?bookId=${latest.id}"/>" class="btn-mini">Add to cart</a>
	                            	</sec:authorize>
                            	</div>
                            </div>				
                        
                        
                        </c:forEach>
                    </div>
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
