<%@ include file="/view/includes.jsp"%>
<div class="navbar navbar-inverse navbar-fixed-top">
  			<div class="navbar-inner">
	  			<div class="container-fluid">
	  				<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar" ></span>
						<span class="icon-bar" ></span>
						<span class="icon-bar" ></span>
					</button>
					
	  				<a class="brand mybrand" href="<c:url value="/"/>">jLibrary</a>
	  				
	  				<div class="nav-collapse collapse">
	  					<ul class="nav">
	  						<li class="active">
	  							<a href="<c:url value="/"/>"><spring:message code="menu.home"/></a>
	  						</li>
	  						<sec:authorize access="isAnonymous()">
	  							<li>
	  								<a href="<c:url value="/registration"/>"><spring:message code="menu.registration"/></a>
	  							</li>  						
	  						</sec:authorize>
	  						<sec:authorize access="isAuthenticated()">
	  							<li>
	  								<a href="<c:url value="/wishList"/>"><spring:message code="menu.wish"/></a>
	  							</li>
	  							<li>
	  								<a href="<c:url value="/userOrder"/>"><spring:message code="menu.orders"/></a>
	  							</li>
	  							<li>
	  								<a href="<c:url value="/usersBooks"/>"><spring:message code="menu.mbooks"/></a>
	  							</li>
	  						</sec:authorize>
	  						<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
		  						<li>
		  							<a href="<c:url value="/books"/>"><spring:message code="menu.books"/></a>
		  						</li>
		  						<li>
		  							<a href="<c:url value="/users"/>"><spring:message code="menu.users"/></a>
		  						</li>
		  					</sec:authorize>
		  					<sec:authorize access="isAuthenticated()">
			  					<li>
			  						<a href="<c:url value="/userAccount"/>" rel="tooltip" data-placement="bottom" data-original-title="Account" >
			  							<sec:authentication property="principal.username" />
			  						</a>
			  					</li>
			  					<li>
	   								<a href="<c:url value="/logout"/>"><spring:message code="menu.logout"/></a>
			  					</li>
		  					</sec:authorize>
		  					
	  					</ul>

	  					
	  					
	  					<sec:authorize access="isAnonymous()">
		  					<form class="navbar-form pull-right" action="j_spring_security_check" method="post">
		  					<fieldset>
		  						<input name="j_username" type="text" class="input-medium" placeholder="Email">
		  						<input name="j_password" type="password" class="input-medium" placeholder="Password">
		  						<button type="submit" class="btn btn-inverse"><spring:message code="menu.sign"/></button>
		  						<label class="checkbox inline">
		  							<input type="checkbox" value="remember-me"><spring:message code="menu.remember"/></label>
		  						<a class="help-inline" href="#"><spring:message code="menu.forgot"/></a>
		  					</fieldset>
		  					</form>
		  				</sec:authorize>
		  				<form class="navbar-search" method="POST" action="${pageContext.request.contextPath}/">
							<input name="search" type="text" class="search-query input-small" placeholder="Search">
						</form>
	  				</div>

	  			</div>
  			</div>
		</div>
		<header>
		<span style="float: right">
    <a href="?lang=en"><img src="${pageContext.request.contextPath}/resources/img/lang/GB.png" alt="english"></a> 
    | 
    <a href="?lang=ru"><img src="${pageContext.request.contextPath}/resources/img/lang/RU.png" alt="english"></a>
    | 
    <a href="?lang=uk"><img src="${pageContext.request.contextPath}/resources/img/lang/UA.png" alt="english"></a>
    <!-- <embed src="http://www.kissfm.ua/player_emb.swf" quality="high" width="313" height="45" name="radioplayernewemb" align="middle" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer" /> -->
</span>
			<div class="container">
				<div class="page-header">
  					<h1>jLibrary <small>your internet library</small></h1>
				</div>
			</div>
		</header>