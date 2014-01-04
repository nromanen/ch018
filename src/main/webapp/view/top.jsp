<%@ include file="/view/includes.jsp"%>
<div class="navbar navbar-inverse navbar-fixed-top">
  			<div class="navbar-inner">
	  			<div class="container">
	  				<a class="brand" href="<c:url value="/"/>">jLibrary</a>
	  				<div class="nav-collapse collapse">
	  					<ul class="nav">
	  						<li class="active">
	  							<a href="<c:url value="/"/>">Home</a>
	  						</li>
	  						<sec:authorize access="isAuthenticated()">
	  							<li>
	  								<a href="<c:url value="/wishList"/>">Wish List</a>
	  							</li>
	  							<li>
	  								<a href="<c:url value="/userOrder"/>">Orders</a>
	  							</li>
	  							<li>
	  								<a href="<c:url value="/usersBooks"/>">My books</a>
	  							</li>
	  						</sec:authorize>
	  						<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
		  						<li>
		  							<a href="<c:url value="/books"/>">Books</a>
		  						</li>
		  						<li>
		  							<a href="<c:url value="/users"/>">Users</a>
		  						</li>
		  					</sec:authorize>
		  					<sec:authorize access="isAuthenticated()">
			  					<li>
			  						<a href="<c:url value="/userAccount"/>" rel="tooltip" data-placement="bottom" data-original-title="Account" >
			  							<sec:authentication property="principal.username" />
			  						</a>
			  					</li>
			  					<li>
	   								<a href="<c:url value="/logout"/>">Logout</a>
			  					</li>
			  					<li>
	   								<form class="navbar-search pull-right" method="POST" action="${pageContext.request.contextPath}/">
										<input name="search" type="text" class="search-query input-small" placeholder="Search">
									</form>
			  					</li>
			  					<div id="tooltip_content_wrapper" style="display: none">This <strong>is</strong> your div content</div>
		  					</sec:authorize>
		  					
	  					</ul>
	  					
	  					
	  					<sec:authorize access="isAnonymous()">
		  					<form class="navbar-form pull-right" action="j_spring_security_check" method="post">
		  					<fieldset>
		  						<input name="j_username" type="text" class="input-medium" placeholder="Email">
		  						<input name="j_password" type="password" class="input-medium" placeholder="Password">
		  						<button type="submit" class="btn btn-inverse">Sign in</button>
		  						<label class="checkbox inline">
		  							<input type="checkbox" value="remember-me"> Remember me </label>
		  						<a class="help-inline" href="#">Forgot password?</a>
		  					</fieldset>
		  					</form>
		  				</sec:authorize>
	  				</div>

	  			</div>
  			</div>
		</div>
		<header>
			<div class="container">
				<div class="page-header">
  					<h1>jLibrary <small>your internet library</small></h1>
				</div>
			</div>
		</header>