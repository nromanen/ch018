<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<link href="${pageContext.request.contextPath}/resources/css/jquery.rating.css"
	    rel="stylesheet" type="text/css"/>
<tilesx:useAttribute name="books" />
<div class="span2 sp">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.rating-2.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/rating.js"></script>
<input type="hidden" value="${pageContext.request.contextPath}/resources/img/" id="starLocation">
<div class="my-nav-header-top"><small>Top 5</small></div>
	<ul class="nav nav-pills nav-stacked">
	     	<c:forEach var="bookz" items="${books}">
	     	   <li></li>
		       <li>
		           <a href="<c:url value="/book/${bookz.id}"/>">
		           <img src="${pageContext.request.contextPath}${bookz.image}" class="img-top5">
		           <br>
		           <!--  <img src="${pageContext.request.contextPath}/resources/img/star.jpg" class="star">-->
		           <br>
		           ${bookz.title}, 
		           <br> ${bookz.authors}
		           <br>
		           <span style="color: #ccc; font-size: 14px;"><spring:message code="person.rating"/></span> 
		           <span style="color: #ccc; font-size: 14px;">${bookz.rating}</span>
		           </a>
		           </li>
		       <li></li>
		</c:forEach>
	</ul>
</div>

<span style="color: #ccc; font-size: 14px;"><spring:message code="person.rating"/></span> 
		           <span style="color: #ccc; font-size: 14px;">${bookz.rating}</span>