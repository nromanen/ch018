<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="request" value="${pageContext.request.queryString}"/>
<c:set var="spage" value="&page=${page}" />
<c:if test="${fn:contains(request, 'page')}">
	<c:set var="request" value="${fn:replace(request, spage, '')}" />
</c:if>
<!-- Center -->
<div class="span8">
     <input type="hidden" id="href" value="${pageContext.request.contextPath}/wishlist" />
     <input type="hidden" id="hrefOrder" value="${pageContext.request.contextPath}/prepareorder" />
     <input type="hidden" id="hrefNewOrder" value="${pageContext.request.contextPath}/order" />
	<c:if test="${indexSearch != null && indexSearch != ''}">
	<div class="alert">
		<span><spring:message code="search.results" /> </span>"<c:out value="${indexSearch}"/>" | <a href="<c:url value="/"/>" ><spring:message code="search.showall" /></a>
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
						<a id="addToWish${latest.id}" class="btn btn-success btn-mini"><spring:message code="message.cart" /></a>
						<input type="hidden" value="${latest.id}"/>
					    <a id="orderNow${latest.id}"
							class="btn btn-warning btn-mini">  <spring:message code="message.ordernow" />  
						</a>
						 
						</div>
						 <div id="inOrder${latest.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body">${latest.title} already in your orders!</div>
                        </div>
                         <div id="inUse${latest.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body">${latest.title} is now in your hands</div>
                        </div>
                         <div id="fail${latest.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body"><spring:message code="message.failorder"/></div>
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
	<c:if test="${pages > 1}">
	<div class="pagination pagination-centered">
		<ul>
			<c:if test="${page == 1}">
				<li class = "disabled">
					<a >«</a>
				</li>
			</c:if>
			<c:if test="${page > 1}">
				<li>
					<c:if test="${(indexSearch != null && indexSearch != '') || advancedSearch != null}">
						<a href="<c:url value="?${request}&page=${page-1}"/>">«</a>
					</c:if>
					<c:if test="${(indexSearch == null || indexSearch == '') && advancedSearch == null}">
						<a href="<c:url value="?page=${page-1}"/>">«</a>
					</c:if>
				</li>
			</c:if>
			<c:forEach var="i" begin="1" end="${pages}">
   				<li>
	   				<c:if test="${(indexSearch != null && indexSearch != '') || advancedSearch != null}">
						<a href="?${request}&page=${i}"><c:out value="${i}"/></a>
					</c:if>
					<c:if test="${(indexSearch == null || indexSearch == '') && advancedSearch == null}">
						<a href="?page=${i}"><c:out value="${i}"/></a>
					</c:if>
				</li>
			</c:forEach>
			<c:if test="${page == pages}">
				<li class = "disabled">
					<a>»</a>
				</li>
			</c:if>
			<c:if test="${page < pages}">
				<li>
					<c:if test="${(indexSearch != null && indexSearch != '') || advancedSearch != null}">
						<a href="<c:url value="?${request}&page=${page+1}"/>">»</a>
					</c:if>
					<c:if test="${(indexSearch == null || indexSearch == '') && advancedSearch == null}">
						<a href="<c:url value="?page=${page+1}"/>">»</a>
					</c:if>
				</li>
			</c:if>
		</ul>
	</div>
	</c:if>
	<div class="modalloading"></div>
	<div id="push"></div>
</div>