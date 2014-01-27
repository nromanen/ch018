<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<tilesx:useAttribute name="genres" />
<div class="span2 sp">
	<ul class="nav nav-pills nav-stacked">
		<li class="nav-header"><spring:message code="book.genre" /></li>
		<li><a href="<c:url value="/"/>">All</a></li>
		<c:forEach var="genre" items="${genres}">
			<li><a href="<c:url value="/?genre=${genre.id}"/>">${genre.name}</a></li>
		</c:forEach>
	</ul>
</div>