<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<tilesx:useAttribute name="books" />
<div class="span2 sp">
	<ul class="nav nav-pills nav-stacked">
		<li class="nav-header">Top 5</li>
	     	<c:forEach var="bookz" items="${books}">
	     	   <li></li>
		       <li>
		           <a href="<c:url value="/book/${bookz.id}"/>">
		           <img src="${bookz.image}" class="img-top5">
		           <br>
		           <img src="${pageContext.request.contextPath}/resources/img/star.jpg" class="star"> <span>${bookz.rating}</span>
		           <br>
		           ${bookz.title}, 
		           <br> ${bookz.authors}</a>
		           </li>
		       <li></li>
		</c:forEach>
	</ul>
</div>