<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<div class="span10">
	<!-- Alert -->
	<div class="alert alert-error" style="display: none">
		<button type="button" class="close">&times;</button>
		<h4>
			<spring:message code="message.error" />
		</h4>
		<spring:message code="person.errordel" />
	</div>

	<div class="TableBooks">
		<table id="utable">
			<thead>
				<tr>
					<th hidden="true"></th>
					<th><spring:message code="person.firstname" /></th>
					<th><spring:message code="person.lastname" /></th>
					<th><spring:message code="person.mail" /></th>
					<th><spring:message code="person.mobile" /></th>
					<th><spring:message code="person.multibookallowed" /></th>
					<th><spring:message code="person.untimelly" /></th>
					<th><spring:message code="person.timelly" />
						<div></div></th>
					<th><spring:message code="person.failed" /></th>
					<th><spring:message code="person.rating" /></th>

					<th><spring:message code="person.confirmed" /></th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${persons}" var="person">
					<tr id="person${person.id}" class="table${person.id}">
						<td hidden="true" class="id${person.id}">${person.id}</td>
						<td class="uname${person.id}"><c:out value="${person.name}"
								escapeXml="true" /></td>
						<td class="surname${person.id}"><c:out
								value="${person.surname}" escapeXml="true" /></td>
						<td class="email${person.id}"><c:out value="${person.email}"
								escapeXml="true" /></td>
						<td class="cellphone${person.id}"><c:out
								value="${person.cellphone}" escapeXml="true" /></td>
						<td class="multibookAllowed${person.id}">${person.multibookAllowed}</td>
						<td class="untimelyReturns${person.id}">${person.untimelyReturns}</td>
						<td class="timelyReturns${person.id}">${person.timelyReturns}</td>
						<td class="failedOrders${person.id}">${person.failedOrders}</td>
						<td class="rating${person.id}">${person.rating}</td>
						<td><input class="confirm${person.id}" type="checkbox"
							name="confirm" value="confirm"
							${person.confirm == true ? 'checked' : ''} disabled></td>
							
						<td>
							<div class="btn-group">
								<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
									Action
	    							<span class="caret"></span>
	    						</button>
	    						<ul class="dropdown-menu">
	    							<li>
	    								<a href="<c:url value="/bookusers/user/${person.id}"/>">
	    									<spring:message code="menu.books" />
	    								</a>
	    							</li>
	    							<li>
	    								<a href="<c:url value="/orders/user/${person.id}"/>">
	    									<spring:message code="book.orders" />
	    								</a>
	    							</li>
	    						</ul>
	    					</div>			
						</td>

						<td><a href="#" id="edituser${person.id}"
							class="btn btn-warning"><spring:message code="button.edit" /></a></td>
						<td>
							<c:if test="${person.role != 'ROLE_LIBRARIAN'}">
								<a href="#" id="deleteuser${person.id}"
								class="btn btn-danger"><spring:message code="button.delete" /></a>
							</c:if>	
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- New user button -->
	<a href="#" id="newuserbutton" class="btn"><spring:message
			code="person.new" /></a>

	<!-- Delete user -->
	<div id="popup" class="modal hide fade" role="dialog"
		aria-labelledby="deleteLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="deleteLabel">
				<spring:message code="person.delete" />
			</h3>
		</div>
		<div class="modal-body">
			<span><spring:message code="message.delete" /></span> <span id="name"></span>
		</div>
		<div class="modal-footer">
			<a id="deleteLink" data-dismiss="modal"
				href="${pageContext.request.contextPath}/user/delete"
				class="btn btn-danger"><spring:message code="button.delete" /></a> <a
				id="canceldelete" href="#" class="btn" data-dismiss="modal"
				aria-hidden="true"><spring:message code="button.cancel" /></a>
		</div>
	</div>

	<!-- Edit user -->
	<div id="action_popup" class="modal hide fade" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3 class="myModalLabelEdit hide">
				<spring:message code="button.edit" />
				<span class="pull-right" id="rating"><spring:message code="person.rating" />:</span>
			</h3>
			<h3 class="myModalLabelNew hide">
				<spring:message code="person.new" />
			</h3>
		</div>
		<div class="modal-body">
			<form:form id="addedituser" class="form-horizontal" method="POST"
				commandName="person"
				action="${pageContext.request.contextPath}/user/update">
				<form:input path="id" id="id" class="hide" disabled="disabled" />

				<div class="control-group">
					<label class="control-label" for="uname"><spring:message
							code="person.firstname" /></label>
					<div class="controls">
						<form:input path="name" type="text" id="uname" placeholder="Name" />
						<form:errors path="name" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="surname"><spring:message
							code="person.lastname" /></label>
					<div class="controls">
						<form:input path="surname" type="text" id="surname"
							placeholder="Second Name" />
						<form:errors path="surname" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="email"><spring:message
							code="person.mail" /></label>
					<div class="controls">
						<form:input path="email" type="email" id="email" />
						<form:errors path="email" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="cellphone"><spring:message
							code="person.mobile" /></label>
					<div class="controls">
						<form:input path="cellphone" type="text" id="cellphone"
							placeholder="Mobile" />
						<form:errors path="cellphone" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="multibookAllowed"><spring:message
							code="person.multibookallowed" /></label>
					<div class="controls">
						<form:input path="multibookAllowed" type="number"
							id="multibookAllowed"  />
						<form:errors path="multibookAllowed" cssClass="error"></form:errors>
					</div>
				</div>

				<div class="control-group hide">
					<label class="control-label" for="untimelyReturns"><spring:message
							code="person.untimelly" /></label>
					<div class="controls">
						<form:hidden path="untimelyReturns" id="untimelyReturns"
							placeholder="0" />
					</div>
				</div>

				<div class="control-group hide">
					<label class="control-label" for="timelyReturns"><spring:message
							code="person.timelly" /></label>
					<div class="controls">
						<form:hidden path="timelyReturns" id="timelyReturns"
							placeholder="0" />
					</div>
				</div>

				<div class="control-group hide">
					<label class="control-label" for="failedOrders"><spring:message
							code="person.failed" /></label>
					<div class="controls">
						<form:hidden path="failedOrders" id="failedOrders" placeholder="0" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="confirm"><spring:message
							code="person.confirmed" /></label>
					<div class="controls">
						<form:checkbox path="confirm" id="confirm" />
					</div>
				</div>
				<div class="form-actions">
					<input type="submit" value="Save" class="btn btn-primary" /> <input
						id="cancel" type="button" class="btn" data-dismiss="modal"
						aria-hidden="true" value="Cancel" />
				</div>
			</form:form>
		</div>
	</div>
</div>