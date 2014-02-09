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
 <div class="row-fluid" style="display: none">
 <div class="span12">
 	<div class="my-nav-header"><small><spring:message code="book.genre" /></small></div>
	<ul class="nav nav-pills nav-stacked hidden-nb" style="display: none;">
		<li><a href="<c:url value="/"/>"><spring:message code="genre.all" /></a></li>
		<c:forEach var="genre" items="${genres}">
			<li><a href="<c:url value="/?genre=${genre.id}"/>">${genre.name}</a></li>
		</c:forEach>
	</ul>
	</div>
 </div>
 <div class="row-fluid">
   <div class="span12">
	<form action="${pageContext.request.contextPath}/advsearch" id = "advancedsearch" name = "advancedsearch" method="GET">
		<fieldset>
			<legend><small><spring:message code="search.advanced" /> <c:if test="${advancedSearch != null}"><a href="<c:url value="/"/>" ><spring:message code="search.showall" /></a></c:if></small></legend>
		</fieldset>
		<div class="control-group">
			<label class="control-label" for="advtitle"><spring:message code="book.title" /></label> 
			<div class="controls">
				<input id="advtitle" name="title" type="text" placeholder="Title…" class="span12" value="${advancedSearch.title}"> 
			</div>
		</div>
		
		<div class="control-group">	
			<label class="control-label" for="advautors"><spring:message code="book.authors" /></label> 
			<div class="controls">
				<input id="advautors" name="authors" type="text" placeholder="Authors…" class="span12" value="${advancedSearch.authors}">  
			</div>
		</div>
		
		<div class="control-group">	
			<label class="control-label" for="advpublication"><spring:message code="book.publication" /></label> 
			<div class="controls">
				<input id="advpublication" name="publication" type="text" placeholder="Publication…" class="span12" value="${advancedSearch.publication}">
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="advgenre"><spring:message code="book.genre" /></label> 
			<div class="controls">
				<select name="genre" id="advgenre" class="span12">
					<option value="0"><spring:message code="genre.all" /></option>
					<c:forEach var="genre" items="${genres}">
						<option value="${genre.id}">${genre.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
			
		<div class="control-group">
			<label class="control-label" for="advyear"><spring:message code="book.year" /></label> 
			<div class="controls">
				<input id="advyear" name="year" type="number" placeholder="Year…" class="span12" value="${advancedSearch.year}">
			</div>
		</div>	
		<div class="control-group">
			<label class="checkbox"> 
			<input id="advavailable" name="available" type="checkbox" ${advancedSearch.available == true ? 'checked' : ''} > <spring:message code="book.available" /></label>
		</div>
		<div class="control-group">
			<label class="control-label" for="advsort"><spring:message code="sort.field" /></label> 
			<select id="advsort" name="sortby" class="fluid" >
				<option value="title" ><spring:message code="book.title" /></option>
				<option value="authors"><spring:message code="book.authors" /></option>
				<option value="publication"><spring:message code="book.publication" /></option>
				<option value="year"><spring:message code="book.year" /></option>
				<option value="available"><spring:message code="book.available" /></option>
			</select>
		</div>
			<button type="submit" class="btn"><spring:message code="book.search" /></button>
	</form>
  </div>
</div>
</div>