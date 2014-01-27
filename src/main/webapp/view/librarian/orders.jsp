<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<!-- Content -->
<div class="span10">
	<c:if test="${book != null}">
		<div class="alert alert-info" id="bookname">${book.title}, ${book.authors}, ${book.year}, ${book.publication}</div>
	</c:if>
	<c:if test="${person != null}">
		<div class="alert alert-info" id="username">${person.name}, ${person.surname}, ${person.email}</div>
	</c:if> 
	<!-- Table class="TableBooks"-->
	<div style="overflow-y: scroll">
		<table class="table table-striped table-condensed table-hover">
		<c:if test="${book != null}">
			<thead>
				<tr>
					<th>№</th>
					<th><spring:message code="person.firstname" /></th>
					<th><spring:message code="person.lastname" /></th>
					<th><spring:message code="person.mail" /></th>
					<th><spring:message code="person.mobile" /></th>
					<th><spring:message code="person.issuedate" /></th>
					<th><spring:message code="person.term" /></th>
					<th></th>
					<th></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
					<tr id="order${order.id}" class="table${order.id}">
						<td><c:out value="${order.id}" escapeXml="true" /></td>
						<td class="tdname"><c:out value="${order.person.name}"
								escapeXml="true" /></td>
						<td class="tdsurname"><c:out value="${order.person.surname}"
								escapeXml="true" /></td>
						<td><c:out value="${order.person.email}" escapeXml="true" /></td>
						<td><c:out value="${order.person.cellphone}" escapeXml="true" /></td>
						<td><c:out value="${order.issueDate}" escapeXml="true" /></td>
						<td class="tddays"><c:out value="${order.book.term}"
								escapeXml="true" /></td>

						<td><a id="issueorder${order.id}" class="btn btn-primary"
							href="#"><spring:message code="button.issue" /></a></td>
						<td><a id="deleteorder${order.id}" class="btn btn-danger"
							href="#"><spring:message code="button.delete" /></a></td>
						<td class="hide"><input class="tdconfirmed" type="checkbox"
							name="confirm" value="confirm"
							${order.person.confirm == true ? 'checked' : ''}></td>
					</tr>
				</c:forEach>
			</tbody>
			</c:if>
			
			<c:if test="${person != null}">
			<thead>
				<tr>
					<th>№</th>
					<th><spring:message code="book.title" /></th>
					<th><spring:message code="book.authors" /></th>
					<th><spring:message code="book.year" /></th>
					<th><spring:message code="book.publication" /></th>
					<th><spring:message code="person.issuedate" /></th>
					<th><spring:message code="person.term" /></th>
					<th></th>
					<th></th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
					<tr id="order${order.id}" class="table${order.id}">
						<td><c:out value="${order.id}" escapeXml="true" /></td>
						<td class="tdtitle"><c:out value="${order.book.title}" escapeXml="true" /></td>
						<td class="tdauthors"><c:out value="${order.book.authors}" escapeXml="true" /></td>
						<td><c:out value="${order.book.year}" escapeXml="true" /></td>
						<td><c:out value="${order.book.publication}" escapeXml="true" /></td>
						<td><c:out value="${order.issueDate}" escapeXml="true" /></td>
						<td class="tddays"><c:out value="${order.book.term}" escapeXml="true" /></td>

						<td><a id="issueuserorder${order.id}" class="btn btn-primary"
							href="#"><spring:message code="button.issue" /></a></td>
						<td><a id="deleteuserorder${order.id}" class="btn btn-danger"
							href="#"><spring:message code="button.delete" /></a></td>
						<td class="hide"><input class="tdconfirmed" type="checkbox"
							name="confirm" value="confirm"
							${order.person.confirm == true ? 'checked' : ''}></td>
					</tr>
				</c:forEach>
			</tbody>
			</c:if>
		</table>
	</div>

	<!-- Delete popup -->
	<div id="popup" class="modal hide fade" role="dialog"
		aria-labelledby="deleteLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="deleteLabel">
				<spring:message code="button.delete" />
			</h3>
		</div>
		<div class="modal-body">
			<span><spring:message code="message.delete" /></span> <span id="name"
				class="hide"></span>
		</div>
		<div class="modal-footer">
			<a id="deleteLink" data-dismiss="modal"
				href="${pageContext.request.contextPath}/orders/delete"
				class="btn btn-danger"><spring:message code="button.delete" /></a> <a
				id="canceldelete" href="#" class="btn" data-dismiss="modal"
				aria-hidden="true" value="Cancel"><spring:message
					code="button.cancel" /></a>
		</div>
	</div>

	<div id="progress" class="progress progress-striped active hide">
		<div class="bar" style="width: 100%;">Process...
		</div>
	</div>

	<!-- Issue popup -->
	<div id="action_popup" class="modal hide fade" role="dialog"
		aria-labelledby="returnLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="returnLabel">
				<spring:message code="button.issue" />
			</h3>
		</div>
		<div class="modal-body">
				<%-- <span><spring:message code="message.issue"/></span> --%>
				<span id="aname" class="hide"></span>
			<p>
				<strong><spring:message code="message.book" /> </strong><span
					id="booknamemessage"></span>
			</p>
			<p>
				<strong><spring:message code="message.user" /> </strong><span
					id="usernamemessage"></span>
			</p>
			<div class="alert alert-info" id="confirmuser" style="display: none;">
				<spring:message code="message.notconfirmed" />
				<label class="checkbox"> <input type="checkbox"
					name="confimation" id="confimation"> <spring:message
						code="person.confirm" />
				</label>
			</div>
			<span><spring:message code="person.days" /></span><input type="number"
				id="days" required />
		</div>
		<div class="modal-footer">
			<a id="issueLink"
				href="${pageContext.request.contextPath}/orders/issue"
				data-dismiss="modal" class="btn btn-primary"><spring:message
					code="button.issue" /></a> <a id="cancelaction" href="#" class="btn"
				data-dismiss="modal" aria-hidden="true" value="Cancel"><spring:message
					code="button.cancel" /></a>
		</div>
	</div>
</div>