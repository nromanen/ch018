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
        <script
			src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
        <title>Library</title>
    </head>
    <body>
    <!-- Include header -->
    <%@ include file="/view/top.jsp"%>
    	
        <div class="container-fluid">
                
            <!-- Content -->
            
            <div class="row-fluid">
            
            <!-- Left side -->
            	<div class="span2">
					<%@ include file="/view/left.jsp"%>
				</div>
				
			<!-- Center -->
				<div class="span8">
				

					<c:forEach items="${latest}" var="latest" varStatus="rowCounter">
						<c:if test="${(rowCounter.count + 2) % 3 == 0}">
							<div class="row-fluid">
						</c:if>
								<div class="thumbnail span4">
								<img src="${latest.image}" class="img-rounded">
									<h3><small>${latest.title}</small></h3>
									<p>${latest.authors}</p>
									<p>${latest.description}</p>
									<sec:authorize access="isAuthenticated()">
										<a href="<c:url value="/wishlist?bookId=${latest.id}"/>" class="btn-mini">Add to cart</a>
                                                                                <br/>
                                                                                <a href="<c:url value="/order?book=${latest.id}&wish=0"/>" class="btn-mini">Order now</a>
		                           	</sec:authorize>
	                           	</div>
	                    <c:if test="${(rowCounter.count % 3 == 0) || (rowCounter.last)}">  
                            </div>
                        </c:if>         
                        </c:forEach>
                    
				</div>
				
			<!-- Right side -->
            	<div class="span2">
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
