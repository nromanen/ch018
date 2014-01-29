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

	<form action="${pageContext.request.contextPath}/advsearch" id = "advancedsearch">
		<fieldset>
			<legend><small>Advanced Search</small></legend>
			<label><spring:message code="book.title" /></label> 
			<input id="advtitle" type="text" placeholder="Title…"> 
			
			<label><spring:message code="book.authors" /></label> 
			<input id="advautors" type="text" placeholder="Authors…">  
			
			<label><spring:message code="book.publication" /></label> 
			<input id="advpublication" type="number" placeholder="Publication…">
			 
			<label><spring:message code="book.year" /></label> 
			<input id="advyear" type="number" placeholder="Year…">
			
			<label class="checkbox"> 
			<input id="advavailable" type="checkbox"> <spring:message code="book.available" /></label>
			
			<label><spring:message code="sort.field" /></label> 
			<select id="advsort">
				<option value="title"><spring:message code="book.title" /></option>
				<option value="authors"><spring:message code="book.authors" /></option>
				<option value="publication"><spring:message code="book.publication" /></option>
				<option value="year"><spring:message code="book.year" /></option>
				<option value="available"><spring:message code="book.available" /></option>
			</select>
			
			<button type="submit" class="btn"><spring:message code="book.search" /></button>
		</fieldset>

	</form>
</div>