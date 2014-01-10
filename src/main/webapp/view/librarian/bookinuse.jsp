<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
			<!-- Content -->
			<div class="span10">
				<div class="alert alert-info" id="bookname">${book}</div>
				<!-- Table -->
				<div class="TableBooks">
					<table>
						<thead>
							<tr>
								<th>#</th>
								<th><spring:message code="person.firstname"/></th>
								<th><spring:message code="person.lastname"/></th>
								<th><spring:message code="person.mail"/></th>
								<th><spring:message code="person.mobile"/></th>
								<th><spring:message code="person.returndate"/></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${booksinuse}" var="bookinuse">
								<tr id="bookinuse${bookinuse.buid}"
									class="table${bookinuse.buid}">
									<td><c:out value="${bookinuse.buid}" escapeXml="true" /></td>
									<td class="pid${bookinuse.buid}  hide"><c:out value="${bookinuse.person.id}" escapeXml="true" /></td>
									<td class = "tdname"><c:out value="${bookinuse.person.name}"
											escapeXml="true" /></td>
									<td class = "tdsurname"><c:out value="${bookinuse.person.surname}"
											escapeXml="true" /></td>
									<td><c:out value="${bookinuse.person.email}"
											escapeXml="true" /></td>
									<td><c:out value="${bookinuse.person.cellphone}"
											escapeXml="true" /></td>
									<td><c:out value="${bookinuse.returnDate}"
											escapeXml="true" /></td>
									<td class = "tdbookcase hide"><c:out value="${bookinuse.book.bookcase}"
											escapeXml="true" /></td>
									<td class = "tdshelf hide"><c:out value="${bookinuse.book.shelf}"
											escapeXml="true" /></td>
								

									<td><a id="returnbook${bookinuse.buid}"
										class="btn btn-primary" href="#"><spring:message code="button.return"/></a></td>
									<td><a id="deletebiu${bookinuse.buid}"
										class="btn btn-danger" href="#"><spring:message code="button.delete"/></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- Delete popup -->
				<div id="popup" class="modal hide fade" role="dialog"
					aria-labelledby="deleteLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="deleteLabel"><spring:message code="button.delete"/></h3>
					</div>
					<div class="modal-body">
						<span><spring:message code="message.delete"/></span> <span id="name" class = "hide"></span>
					</div>
					<div class="modal-footer">
						<a id="deleteLink" data-dismiss="modal"
							href="${pageContext.request.contextPath}/booksinuse/delete"
							class="btn btn-danger"><spring:message code="button.delete"/></a> <a id="canceldelete" href="#"
							class="btn" data-dismiss="modal" aria-hidden="true"
							value="Cancel"><spring:message code="button.cancel"/></a>
					</div>>
				</div>
				
				
					<div id="progress" class="progress progress-striped active">
  						<div class="bar" style="width: 100%; display: none;"> Process... </div>
					</div>
				
				

				<!-- Return popup -->
				<div id="action_popup" class="modal hide fade" role="dialog"
					aria-labelledby="returnLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="returnLabel"><spring:message code="button.return"/></h3>
					</div>
					<div class="modal-body">
						<span><spring:message code="message.return"/></span> <span id="aname"  class = "hide"></span>
					</div>
					<p><strong><spring:message code="message.book"/> </strong><span id="booknamemessage"></span></p>
					<p><strong><spring:message code="message.user"/> </strong><span id="usernamemessage"></span></p>
					<p><strong><spring:message code="message.bookplace"/> </strong><span id="bookplacemessage"></span></p>
					<div class="modal-footer">
						<a id="actionLink" data-dismiss="modal"
							href="${pageContext.request.contextPath}/booksinuse/return"
							class="btn btn-primary"><spring:message code="button.return"/></a> <a id="cancelaction" href="#"
							class="btn" data-dismiss="modal" aria-hidden="true"
							value="Cancel"><spring:message code="button.cancel"/></a>
					</div>
				</div>
			</div>