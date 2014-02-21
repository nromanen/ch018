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
<c:choose>
	<c:when test="${indexSearch != null && indexSearch != ''}">
		<c:set var="search" value="&search=${indexSearch}" />
	</c:when>
	<c:otherwise>
		<c:set var="search" value=""/>
	</c:otherwise>
</c:choose>
<!-- Center -->
<div class="span8">
<!-- 
<div id="myCarousel" class="carousel slide">
  
  <div class="carousel-inner">
    <div class="active item"><img src="http://bootstrap-ru.com/assets/img/bootstrap-mdo-sfmoma-03.jpg" ></div>
    <div class="item">…</div>
    <div class="item">…</div>
  </div>
 
  <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
  <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div>
 -->
<div class="navbar-inner row-margin">
	<div class="container" style="width: auto; padding: 5px 20px;">
		<a type="button" class="btn pull-left" id="sortby_btn" > <spring:message code="sort.field" /> <b class="caret"></b></a>
		<c:if test="${indexSearch != null && indexSearch != ''}">
			<span><spring:message code="search.results" /> </span>"<c:out value="${indexSearch}"/>" | <a href="<c:url value="/"/>" ><spring:message code="search.showall" /></a>
		</c:if>
		<ul class="pager pull-right">
			<c:if test="${page > 1}">
				<li><a href="<c:url value="?${request}&page=${page-1}"/>">&larr;</a></li>
			</c:if>
				<li><input rel="<c:url value="?${request}&page="/>" class="input_page" type="text" value="${page}" style="overflow: visible; width: 28px; margin-top: 0; margin-bottom:2px; padding: 0;"> of </li><li class="total-pages">${pages}</li>
			<c:if test="${page < pages}">
				<li><a href="<c:url value="?${request}&page=${page+1}"/>">&rarr;</a></li>
			</c:if>
		</ul>
	</div>
</div>
<div class="sort_options" style="display: none">
	<dl>
		<dt class="book_title"><spring:message code="book.title" /></dt>
		<dd class="book_t_ask">
			<a href="<c:url value="/search?sort=title&isasc=true${search}"/>">
				<span><spring:message code="sort.froma" /></span>
			</a>
		</dd>
		<dd class="book_t_desk">
			<a href="<c:url value="/search?sort=title${search}"/>">
				<span><spring:message code="sort.fromz" /></span>
			</a>
		</dd>
		
		<dt class="book_authors"><spring:message code="book.authors" /></dt>
		<dd class="book_a_ask">
			<a href="<c:url value="/search?sort=authors&isasc=true${search}"/>">
				<span><spring:message code="sort.froma" /></span>
			</a>
		</dd>
		<dd class="book_a_desk">
			<a href="<c:url value="/search?sort=authors${search}"/>">
				<span><spring:message code="sort.fromz" /></span>
			</a>
		</dd>
		
		<dt class="book_publication"><spring:message code="book.publication" /></dt>
		<dd class="book_p_ask">
			<a href="<c:url value="/search?sort=publication&isasc=true${search}"/>">
				<span><spring:message code="sort.froma" /></span>
			</a>
		</dd>
		<dd class="book_p_desk">
			<a href="<c:url value="/search?sort=publication${search}"/>">
				<span><spring:message code="sort.fromz" /></span>
			</a>
		</dd>
		
		<dt class="book_year"><spring:message code="book.year" /></dt>
		<dd class="book_y_ask">
			<a href="<c:url value="/search?sort=year&isasc=true${search}"/>">
				<span><spring:message code="sort.froml" /></span>
			</a>
		</dd>
		<dd class="book_y_desk">
			<a href="<c:url value="/search?sort=year${search}"/>">
				<span><spring:message code="sort.fromg" /></span>
			</a>
		</dd>
		
		<dt class="book_rating"><spring:message code="book.rating" /></dt>
		<dd class="book_r_ask">
			<a href="<c:url value="/search?sort=rating&isasc=true${search}"/>">
				<span><spring:message code="sort.froml" /></span>
			</a>
		</dd>
		<dd class="book_r_desk">
			<a href="<c:url value="/search?sort=rating${search}"/>">
				<span><spring:message code="sort.fromg" /></span>
			</a>
		</dd>
		
		<dt class="book_available"><spring:message code="book.available" /></dt>
		<dd class="book_av_ask">
			<a href="<c:url value="/search?sort=available&isasc=true${search}"/>">
			<span><spring:message code="sort.froml" /></span>
			</a>
		</dd>
		<dd class="book_av_desk">
			<a href="<c:url value="/search?sort=available${search}"/>">
			<span><spring:message code="sort.fromg" /></span>
			</a>
		</dd>
	</dl>
</div>
     <input type="hidden" id="href" value="${pageContext.request.contextPath}/wishlist" />
     <input type="hidden" id="hrefOrder" value="${pageContext.request.contextPath}/prepareorder" />
     <input type="hidden" id="hrefNewOrder" value="${pageContext.request.contextPath}/order" />
	
	<c:forEach items="${latest}" var="latest" varStatus="rowCounter">
		<input type="hidden" id="bookTitle${latest.id}" value="${latest.title}">
		<c:if test="${(rowCounter.count + 3) % 4 == 0}">
			<div class="row-fluid row-margin">
		</c:if>
				<div class="thumbnail span3 ">
				<div class="media">
					<a href="<c:url value="/book/${latest.id}"/>"><img src="<c:url value="${latest.image}"/>" class="img-rounded pull-left"></a>
					<sec:authorize access="isAuthenticated()">
						<div class="btn-group btn-group-vertical  pull-right">
						<a id="addToWish${latest.id}" class="btn btn-success btn-mini"><spring:message code="message.cart" /></a>
						<input type="hidden" value="${latest.id}"/>
					    <a id="orderNow${latest.id}"
							class="btn btn-warning btn-mini">  <spring:message code="message.ordernow" />  
						</a>
						 
						</div>
						 <div id="inOrder${latest.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body">${latest.title} <spring:message code="ordered"/></div>
                        </div>
                         <div id="inUse${latest.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body">${latest.title} <spring:message code="inuse"/></div>
                        </div>
                         <div id="fail${latest.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
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
					<a class="pull-right" href="<c:url value="/book/${latest.id}"/>"><small><spring:message code="more"/></small></a><br>
                    <p><c:choose>
                    	<c:when test="${latest.available==0}"><span class="label label-important"><spring:message code="book.notavailable" /></span></c:when>
                        <c:otherwise><span class="label label-success"><spring:message code="book.available" /></span></c:otherwise>
                       </c:choose></p>
                </div>
					
				</div>
				<div id="successAdd${latest.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body">${latest.title} <spring:message code="wishlistAdded"/></div>
                </div>
                <div id="errorAdd${latest.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body"><spring:message code="has"/> ${latest.title} <spring:message code="inwish"/></div>
                </div>
                <div id="alreadyUse${latest.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body"><spring:message code="has"/> ${latest.title} <spring:message code="inuse"/></div>
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
					<c:if test="${indexSearch != null || advancedSearch != null}">
						<a href="<c:url value="?${request}&page=${page-1}"/>">«</a>
					</c:if>
					<c:if test="${indexSearch == null && advancedSearch == null}">
						<a href="<c:url value="?page=${page-1}"/>">«</a>
					</c:if>
				</li>
			</c:if>
			<c:forEach var="i" begin="1" end="${pages}">
   				<li>
	   				<c:if test="${indexSearch != null || advancedSearch != null}">
						<a href="?${request}&page=${i}"><c:out value="${i}"/></a>
					</c:if>
					<c:if test="${indexSearch == null && advancedSearch == null}">
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
					<c:if test="${indexSearch != null || advancedSearch != null}">
						<a href="<c:url value="?${request}&page=${page+1}"/>">»</a>
					</c:if>
					<c:if test="${indexSearch == null && advancedSearch == null}">
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