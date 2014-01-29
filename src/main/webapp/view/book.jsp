<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!-- Center -->
<div class="span8">
	<div class="row-fluid">
		<div class="thumbnail media">
		
			<img src="${book.image}" class="img-rounded pull-left large">
			<div class="media-width">
				<h4 class="media-heading">${book.title}</h4>
				<p><strong><spring:message code="book.authors" />: </strong>${book.authors}</p>
				<p><strong><spring:message code="book.year" />: </strong>${book.year}</p>
				<p><strong><spring:message code="book.publication" />: </strong>${book.publication}</p>
				<p><strong><spring:message code="book.description" />: </strong>${book.description}</p>
				<p> 
					<c:choose>
						<c:when test="${book.available==0}"><span class="label label-important">Not Available</span></c:when>
						<c:otherwise><span class="label label-success">Available</span></c:otherwise>
					</c:choose>
				</p>
				<sec:authorize access="isAuthenticated()">
				<div class="btn-group btn-group-vertical">
					<a href="<c:url value="/wishlist?bookId=${book.id}"/>"
						class="btn btn-success"><spring:message code="message.cart" /></a>
					<a href="<c:url value="/order?book=${book.id}&wish=0"/>"
						class="btn btn-warning"><spring:message code="message.ordernow" /></a>
				</div>
				</sec:authorize>
		
		</div>
		</div>
	</div>
</div>