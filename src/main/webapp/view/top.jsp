<%@ include file="/view/includes.jsp"%>
<div id="head" class="row-fluid">
	<div class="span8"></div>
	<div class="span4">
	<sec:authorize access="isAuthenticated()">
   		<sec:authentication property="principal.username" />
   		<a class="btn" href="<c:url value="/logout"/>">Logout</a>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<form class="form-inline" action="j_spring_security_check" method="post">
			<input name="j_username" type="text" class="input-small" placeholder="Email"> 
			<input name="j_password" type="password" class="input-small" placeholder="Password">
			<label class="checkbox"> <input type="checkbox">
				Remember me
			</label>
			<button type="submit" class="btn">Login</button>
		</form>
	</sec:authorize>
	</div>
</div>