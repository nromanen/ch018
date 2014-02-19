<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.rating-2.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/comments.js"></script>

<link href="${pageContext.request.contextPath}/resources/css/jquery.rating.css"
	    rel="stylesheet" type="text/css"/>
<input type="hidden" value="${pageContext.request.contextPath}/resources/img/" id="starLocation">
<!-- Center -->
<div class="span8">
	<div class="row-fluid">
		<div class="thumbnail media">
			<img src="<c:url value="${book.image}"/>" class="img-rounded pull-left">
			<h4 class="media-heading">${book.title}</h4>
			<p>
				<strong><spring:message code="book.authors" />: </strong>${book.authors}</p>
			<p>
				<strong><spring:message code="book.year" />: </strong>${book.year}</p>
			<p>
				<strong><spring:message code="book.publication" />: </strong>${book.publication}</p>
			<p>
				<strong><spring:message code="book.description" />: </strong>${book.description}</p>
			<p>
				<c:choose>
					<c:when test="${book.available==0}">
						<span class="label label-important">Not Available</span>
					</c:when>
					<c:otherwise>
						<span class="label label-success">Available</span>
					</c:otherwise>
				</c:choose>
			</p>
			<sec:authorize access="isAuthenticated()">
				<c:if test="${usermark > 0}">
				<spring:message code="person.rating"/> : ${mark} <spring:message code="message.of"/> 5.00
				</c:if>
			    <c:if test="${usermark < 1}">  
				  <div class="rating"></div>
					
				</c:if>
			</sec:authorize>
		</div>
		
		<div class="thumbnail">
			<h4>Comment this book:</h4>
			<form class="form-horizontal" id = "comment_it" action="${pageContext.request.contextPath}/comment">
				<div>
					<textarea rows="4" cols="40" id="comment" name="comment" style="width: 50%"></textarea>
					<input type="hidden" name="buid" value="${buid}" />
				</div>
				<div>
					<button type="submit" class="btn">Comment</button>
				</div>
			</form>
			<h4>Comments:</h4>
			<div id="comments">
				<c:forEach items="${book.histories}" var="history">
				<c:if test="${history.comment != null}">
					<div class="comment"> <h6>${history.person.email}:</h6><c:out value=" ${history.comment}" escapeXml="true" /> </div>
				</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<input type="hidden" id="hrefrate" value="${pageContext.request.contextPath}/vote" /> 
						<input type="hidden" id="bookID" value="${book.id}" /> 
						<input type="hidden" id="buID" value="${buid}" />
   <!-- <div class="rating"></div> -->
   
</div>