<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<tilesx:useAttribute name="countWish" />
<tilesx:useAttribute name="countOrders" />
<tilesx:useAttribute name="countBooks" />
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">

		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse"	data-target=".nav-collapse">
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="brand mybrand" href="<c:url value="/"/>">jLibrary</a>
			<form class="navbar-search pull-right" method="POST"
				action="${pageContext.request.contextPath}/">
				<input name="search" type="text" class="search-query input-small"
					placeholder="Search" value="${indexSearch}">
			</form>
			<div class="nav-collapse collapse" style="height: 0px;">
				<ul class="nav">
					<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
						<li><a href="<c:url value="/books"/>"><spring:message
									code="menu.books" /></a></li>
						<li><a href="<c:url value="/users"/>"><spring:message
									code="menu.users" /></a></li>
					</sec:authorize>
						<li><a href="<c:url value="/"/>"><spring:message
									code="menu.home" /></a></li>
					<sec:authorize access="isAnonymous()">
						<li><a href="<c:url value="#"/>" id="signtoggle"
							rel="popover"><spring:message code="menu.sign" /></a></li>
						<li><a href="<c:url value="/registration"/>"><spring:message
									code="menu.registration" /></a></li>
					</sec:authorize>
					<sec:authorize access="hasAnyRole({'ROLE_LIBRARIAN','ROLE_USER'})">
						<li><a href="<c:url value="/wishList"/>"><spring:message
									code="menu.wish" />
									<c:if test="${countWish > 0}"> <span class="badge badge-info">${countWish}</span></c:if></a></li>
						<li><a href="<c:url value="/userOrder"/>"><spring:message
									code="menu.orders" />
									<c:if test="${countOrders > 0}"> <span class="badge badge-info">${countOrders}</span></c:if></a></li>
						<li><a href="<c:url value="/usersBooks"/>"><spring:message
									code="menu.mbooks" />
									<c:if test="${countBooks > 0}"> <span class="badge badge-info">${countBooks}</span></c:if></a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
						<li><a href="<c:url value="/admusers"/>"><spring:message
									code="menu.users" /></a></li>
					</sec:authorize>
					
					<sec:authorize access="isAuthenticated()">
						<li><a href="<c:url value="/userAccount"/>" rel="tooltip"
							data-placement="bottom" data-original-title="Account"> <sec:authentication
									property="principal.username" />
						</a></li>
						<li><a href="<c:url value="/logout"/>"><spring:message
									code="menu.logout" /></a></li>
					</sec:authorize>
				</ul>
			</div>
			
			


			<div id="popover_content_wrapper" style="display: none">
				<form class="" action="j_spring_security_check" method="post">
					<div class="control-group">
						<label class="control-label" for="inputEmail"><spring:message
								code="person.mail" /></label>
						<div class="controls">
							<input name="j_username" id="inputEmail" type="email" required>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword"><spring:message
								code="person.pass" /></label>
						<div class="controls">
							<input name="j_password" type="password" id="inputPassword"
								placeholder="Pass">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox"> <input type="checkbox"
								name="_spring_security_remember_me"> <spring:message
									code="menu.remember" />
							</label>
							<button type="submit" class="btn btn-primary">
								<spring:message code="button.login" />
							</button>
							<a href="<c:url value="/remind"/>">Forgot your password?</a>
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
</div>
<article itemscope="" itemtype="http://schema.org/Article">
<header>
	<span style="float: right"> <a href="?lang=en"><img
			src="${pageContext.request.contextPath}/resources/img/lang/GB.png"
			alt="english"></a> | <a href="?lang=ru"><img
			src="${pageContext.request.contextPath}/resources/img/lang/RU.png"
			alt="english"></a> | <a href="?lang=uk"><img
			src="${pageContext.request.contextPath}/resources/img/lang/UA.png"
			alt="english"></a> <!-- <embed src="http://www.kissfm.ua/player_emb.swf" quality="high" width="313" height="45" name="radioplayernewemb" align="middle" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer" /> -->
	</span>
	<div class="container">
		<div class="page-header">
			<h1>
				jLibrary <small>your internet library</small>
			</h1>
		</div>
	</div>
</header>