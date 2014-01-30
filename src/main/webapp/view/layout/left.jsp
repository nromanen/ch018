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
 <div class="row-fluid">
 <div class="span12">
 	<div class="my-nav-header"><small><spring:message code="book.genre" /></small></div>
	<ul class="nav nav-pills nav-stacked">
		<li><a href="<c:url value="/"/>"><spring:message code="genre.all" /></a></li>
		<c:forEach var="genre" items="${genres}">
			<li><a href="<c:url value="/?genre=${genre.id}"/>">${genre.name}</a></li>
		</c:forEach>
	</ul>
	</div>
 </div>
 <div class="row-fluid">
   <div class="span12">
	<form action="${pageContext.request.contextPath}/advsearch" id = "advancedsearch" name = "advancedsearch" method="POST">
		<fieldset>
			<legend><small>Advanced Search <c:if test="${advancedSearch != null}"><a href="<c:url value="?show=all"/>" ><spring:message code="search.showall" /></a></c:if></small></legend>
		</fieldset>
			<label><spring:message code="book.title" /></label> 
			<input id="advtitle" name="title" type="text" placeholder="Title…" class="fluid" value="${advancedSearch.title}"> 
			
			<label><spring:message code="book.authors" /></label> 
			<input id="advautors" name="authors" type="text" placeholder="Authors…" class="fluid" value="${advancedSearch.authors}">  
			
			<label><spring:message code="book.publication" /></label> 
			<input id="advpublication" name="publication" type="text" placeholder="Publication…" class="fluid" value="${advancedSearch.publication}">
			 
			<label><spring:message code="book.year" /></label> 
			<input id="advyear" name="year" type="number" placeholder="Year…" class="fluid" value="${advancedSearch.year}">
			
			<label class="checkbox"> 
			<input id="advavailable" name="available" type="checkbox" > <spring:message code="book.available" /></label>
			
			<label><spring:message code="sort.field" /></label> 
			<select id="advsort" name="sortby" class="fluid" >
				<option value="title" ><spring:message code="book.title" /></option>
				<option value="authors"><spring:message code="book.authors" /></option>
				<option value="publication"><spring:message code="book.publication" /></option>
				<option value="year"><spring:message code="book.year" /></option>
				<option value="available"><spring:message code="book.available" /></option>
			</select>
			
			<button type="submit" class="btn"><spring:message code="book.search" /></button>
	</form>
  </div>
</div>
</div>