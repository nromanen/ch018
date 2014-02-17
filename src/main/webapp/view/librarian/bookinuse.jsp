<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<!-- Content -->
<script src="${pageContext.request.contextPath}/resources/js/showimage.js"></script>
<div class="span10 offset1">
	<c:if test="${book != null}">
		<div class="alert alert-info" id="bookname">${book.title}, ${book.authors}, ${book.year}, ${book.publication}</div>
	</c:if>
	<c:if test="${person != null}">
		<div class="alert alert-info" id="username">${person.name}, ${person.surname}, ${person.email}</div>
	</c:if> 
	<!-- Table class="TableBooks" -->
	<div style="overflow-y: scroll">
		<table class="table table-striped table-condensed table-hover">
		<c:if test="${book != null}">
			<thead>
				<tr>
					<th>#</th>
					<th><spring:message code="person.firstname" /></th>
					<th><spring:message code="person.lastname" /></th>
					<th><spring:message code="person.mail" /></th>
					<th><spring:message code="person.mobile" /></th>
					<th><spring:message code="person.returndate" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${booksinuse}" var="bookinuse">
					<tr id="bookinuse${bookinuse.buid}" class="table${bookinuse.buid}">
						<td><c:out value="${bookinuse.buid}" escapeXml="true" /></td>
						<td class="pid${bookinuse.buid}  hide"><c:out
								value="${bookinuse.person.id}" escapeXml="true" /></td>
						<td class="tdname"><c:out value="${bookinuse.person.name}"
								escapeXml="true" /></td>
						<td class="tdsurname"><c:out
								value="${bookinuse.person.surname}" escapeXml="true" /></td>
						<td><c:out value="${bookinuse.person.email}" escapeXml="true" /></td>
						<td><c:out value="${bookinuse.person.cellphone}"
								escapeXml="true" /></td>
						<td><c:out value="${bookinuse.returnDate}" escapeXml="true" /></td>
						<td class="tdbookcase hide"><c:out
								value="${bookinuse.book.bookcase}" escapeXml="true" /></td>
						<td class="tdshelf hide"><c:out
								value="${bookinuse.book.shelf}" escapeXml="true" /></td>


						<td><a id="returnbook${bookinuse.buid}"
							class="btn btn-primary" href="#"><spring:message
									code="button.return" /></a></td>
						<td><a id="deletebiu${bookinuse.buid}" class="btn btn-danger"
							href="#"><spring:message code="button.delete" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
			</c:if>
			
			<c:if test="${person != null}">
			<thead>
				<tr>
					<th><spring:message code="book.title" /></th>
					<th><spring:message code="book.authors" /></th>
					<th><spring:message code="book.year" /></th>
					<th><spring:message code="book.publication" /></th>
					<th><spring:message code="person.returndate" /></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${booksinuse}" var="bookinuse">
					<tr id="bookinuse${bookinuse.buid}" class="table${bookinuse.buid}">
						<td class="hide"><c:out value="${bookinuse.buid}" escapeXml="true" /></td>
						<td class="pid${bookinuse.buid}  hide"><c:out value="${bookinuse.book.id}" escapeXml="true" /></td>
						<td class="hide"><c:out value="${bookinuse.book.image}"></c:out></td>
						<td class="tdtitle"><span class="show_image"><c:out value="${bookinuse.book.title}" escapeXml="true" /></span></td>
						<td class="tdauthors"><c:out value="${bookinuse.book.authors}" escapeXml="true" /></td>
						<td><c:out value="${bookinuse.book.year}" escapeXml="true" /></td>
						<td><c:out value="${bookinuse.book.publication}" escapeXml="true" /></td>
						<td><c:out value="${bookinuse.returnDate}" escapeXml="true" /></td>
						<td class="tdbookcase hide"><c:out value="${bookinuse.book.bookcase}" escapeXml="true" /></td>
						<td class="tdshelf hide"><c:out	value="${bookinuse.book.shelf}" escapeXml="true" /></td>

						<td><a id="returnuserbook${bookinuse.buid}" class="btn btn-primary" href="#">
								<spring:message	code="button.return" />
							</a>
						</td>
						<td><a id="deletebiu${bookinuse.buid}" class="btn btn-danger" href="#">
								<spring:message code="button.delete" />
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<div id = "popover_show_img" style="display: none;overflow:hidden;"><img id="testimg" src="" class="img-rounded pull-left"></div>
			</c:if>
		</table>
	</div>

	<!-- Delete popup -->
	<div id="popup" class="modal hide fade" role="dialog" aria-labelledby="deleteLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="deleteLabel">
				<spring:message code="button.delete" />
			</h3>
		</div>
		<div class="modal-body">
			<span><spring:message code="message.delete.inuse" /></span> 
			<span id="name"	class="hide"></span>
		</div>
		<div class="modal-footer">
			<a id="deleteLink" data-dismiss="modal"
				href="${pageContext.request.contextPath}/booksinuse/delete"
				class="btn btn-danger">
			<spring:message code="button.delete" /></a> 
			<a id="canceldelete" href="#" class="btn" data-dismiss="modal"
				aria-hidden="true" value="Cancel">
			<spring:message	code="button.cancel" /></a>
		</div>
	</div>


	<div id="progress" class="progress progress-striped active">
		<div class="bar" style="width: 100%; display: none;">Process...
		</div>
	</div>



	<!-- Return popup -->
	<div id="action_popup" class="modal hide fade" role="dialog" aria-labelledby="returnLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="returnLabel">
				<spring:message code="button.return" />
			</h3>
		</div>
		<div class="modal-body">
				<%-- <span><spring:message code="message.return" /></span> --%> <span
					id="aname" class="hide"></span>
			<p>
				<strong><spring:message code="message.book" /> </strong>
				<span id="booknamemessage"></span>
			</p>
			<p>
				<strong><spring:message code="message.user" /> </strong>
				<span id="usernamemessage"></span>
			</p>
			<p>
				<strong><spring:message code="message.bookplace" /> </strong>
				<span id="bookplacemessage"></span>
			</p>
		</div>
		<div class="modal-footer"> 
			<a id="actionLink" data-dismiss="modal"	href="${pageContext.request.contextPath}/booksinuse/return"
				class="btn btn-primary"><spring:message code="button.return" />
			</a>
			<a id="cancelaction" href="#" class="btn" data-dismiss="modal" aria-hidden="true" value="Cancel">
				<spring:message	code="button.cancel" />
			</a>
		</div>
	</div>
	<div class="modalloading"></div>
</div>
<div class="span1"></div>