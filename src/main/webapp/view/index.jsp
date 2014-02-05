<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!-- Center -->
<div class="span8">
     <input type="hidden" id="href" value="${pageContext.request.contextPath}/wishlist" />
     <input type="hidden" id="hrefOrder" value="${pageContext.request.contextPath}/prepareorder" />
     <input type="hidden" id="hrefNewOrder" value="${pageContext.request.contextPath}/order" />
	<c:if test="${indexSearch != null && indexSearch != ''}">
	<div class="alert">
		<span><spring:message code="search.results" /> </span>"<c:out value="${indexSearch}"/>" | <a href="<c:url value="?show=all"/>" ><spring:message code="search.showall" /></a>
	</div>
	</c:if>
	<c:forEach items="${latest}" var="latest" varStatus="rowCounter">
		<c:if test="${(rowCounter.count + 3) % 4 == 0}">
			<div class="row-fluid">
		</c:if>
				<div class="thumbnail span3 ">
				<div class="media">
					<a href="<c:url value="/book/${latest.id}"/>"><img src="${pageContext.request.contextPath}${latest.image}" class="img-rounded pull-left"></a>
					<sec:authorize access="isAuthenticated()">
						<div class="btn-group btn-group-vertical  pull-right">
						<!-- <a href="<c:url value="/wishlist?bookId=${latest.id}"/>"
							class="btn btn-success btn-mini"><spring:message code="message.cart" /></a>  -->
						<input type="button" value="<spring:message code="message.cart" />" class="btn btn-success btn-mini" id="addToWish${latest.id}"/>
						<input type="hidden" value="${latest.id}">
						<input type="button" id="orderNow${latest.id}" value="<spring:message code="message.ordernow"/>" class="btn btn-warning btn-mini"/>
						<input type="hidden" value="${latest.id}">
					    <a href="<c:url value="/order?book=${latest.id}&wish=0"/>"
							class="btn btn-warning btn-mini">  <spring:message code="message.ordernow" />  
							</a>
						</div>
					</sec:authorize>
				</div>
				<div class="caption">
					<div class = "booktitle">${latest.title} <br>
					${latest.authors}</div>
					<div class = "box">
						<div class = "textdesc"><small>${latest.description}</small></div>
					</div>
					<a class="pull-right" href="<c:url value="/book/${latest.id}"/>"><small>More</small></a><br>
                    <p><c:choose>
                    	<c:when test="${latest.available==0}"><span class="label label-important"><spring:message code="book.notavailable" /></span></c:when>
                        <c:otherwise><span class="label label-success"><spring:message code="book.available" /></span></c:otherwise>
                       </c:choose></p>
                </div>
					
				</div>
				<div id="successAdd${latest.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body">${latest.title} successfully added to wish list</div>
                </div>
                <div id="errorAdd${latest.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body">You already have ${latest.title} in your wish list</div>
                </div>
		<c:if test="${(rowCounter.count % 4 == 0) || (rowCounter.last)}">
			</div>
		</c:if>
	</c:forEach>
	<!-- Pagination -->
	<div class="pagination pagination-centered">
		<ul>
			<c:if test="${page == 1}">
				<li class = "disabled">
					<a href="<c:url value="#"/>">«</a>
				</li>
			</c:if>
			<c:if test="${page > 1}">
				<li>
					<a href="<c:url value="?page=${page-1}"/>">«</a>
				</li>
			</c:if>
			<c:forEach var="i" begin="1" end="${pages}">
   				<li>
   					<a href="?page=${i}"><c:out value="${i}"/></a>
				</li>
			</c:forEach>
			<c:if test="${page == pages}">
				<li class = "disabled">
					<a href="<c:url value="#"/>">»</a>
				</li>
			</c:if>
			<c:if test="${page < pages}">
				<li>
					<a href="<c:url value="?page=${page+1}"/>">»</a>
				</li>
			</c:if>
		</ul>
	</div>
	<div class="modalloading"></div>
	<div id="push"></div>
</div>