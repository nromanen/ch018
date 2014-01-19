<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!-- Center -->
<div class="span8">
	<c:forEach items="${latest}" var="latest" varStatus="rowCounter">
		<c:if test="${(rowCounter.count + 2) % 3 == 0}">
			<div class="row-fluid">
		</c:if>
				<div class="thumbnail span4 sp">
				<div class="media">
					<a href="<c:url value="/book/${latest.id}"/>"><img src="${latest.image}" class="img-rounded pull-left"></a>
					<sec:authorize access="isAuthenticated()">
						<div class="btn-group btn-group-vertical  pull-right">
						<a href="<c:url value="/wishlist?bookId=${latest.id}"/>"
							class="btn btn-success btn-mini"><spring:message code="message.cart" /></a>
						<a href="<c:url value="/order?book=${latest.id}&wish=0"/>"
							class="btn btn-warning btn-mini">  <spring:message code="message.ordernow" />  </a>
						</div>
					</sec:authorize>
				</div>
				<div class="caption">
					<h3>
						<small>${latest.title}</small>
					</h3>
					<p>${latest.authors}</p>
					<p>${latest.description}</p>
                    <p><c:choose>
                    	<c:when test="${latest.available==0}"><span class="label label-important">Not Available</span></c:when>
                        <c:otherwise><span class="label label-success">Available</span></c:otherwise>
                       </c:choose></p>
                </div>
					
				</div>
		<c:if test="${(rowCounter.count % 3 == 0) || (rowCounter.last)}">
			</div>
		</c:if>
	</c:forEach>
</div>