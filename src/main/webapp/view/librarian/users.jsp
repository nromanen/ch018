<%@ include file="/view/includes.jsp"%>
			<div class="span10">
				<!-- Alert -->	
				<div class="alert alert-error" style="display: none">
					<button type="button" class="close" >&times;</button>
  					<h4><spring:message code="message.error"/></h4><spring:message code="person.errordel"/>
				</div>
				
				<div class="TableBooks">
					<table id="utable">
						<thead>
							<tr>
								<th><spring:message code="person.firstname"/>
								</th>
								<th><spring:message code="person.lastname"/>
								</th>
								<th><spring:message code="person.mail"/>
								</th>
								<th><spring:message code="person.mobile"/>
								</th>
								<th><spring:message code="person.multibookallowed"/>
								</th>
								<th><spring:message code="person.untimelly"/>
								</th>
								<th><spring:message code="person.timelly"/>
									<div></div>
								</th>
								<th><spring:message code="person.failed"/></th>
								<th><spring:message code="person.rating"/></th>

								<th><spring:message code="person.confirmed"/></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${persons}" var="person">
								<tr id="person${person.id}" class="table${person.id}">
									<td hidden="true" class="id${person.id}">${person.id}</td>
									<td class="uname${person.id}"><c:out
											value="${person.name}" escapeXml="true" /></td>
									<td class="surname${person.id}"><c:out
											value="${person.surname}" escapeXml="true" /></td>
									<td class="email${person.id}"><c:out
											value="${person.email}" escapeXml="true" /></td>
									<td class="cellphone${person.id}"><c:out
											value="${person.cellphone}" escapeXml="true" /></td>
									<td class="multibookAllowed${person.id}">${person.multibookAllowed}</td>
									<td class="untimelyReturns${person.id}">${person.untimelyReturns}</td>
									<td class="timelyReturns${person.id}">${person.timelyReturns}</td>
									<td class="failedOrders${person.id}">${person.failedOrders}</td>
									<td class="rating${person.id}">${person.rating}</td>
									<td><input class="confirm${person.id}" type="checkbox"
										name="confirm" value="confirm"
										${person.confirm == true ? 'checked' : ''}></td>

									<td><a href="#" id="edituser${book.id}"
										class="btn btn-warning"><spring:message code="button.edit"/></a></td>
									<td><a href="#" id="deleteuser${book.id}"
										class="btn btn-danger"><spring:message code="button.delete"/></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- New user button -->
				<a href="#" id="newuserbutton" class="btn"><spring:message code="person.new"/></a>

				<!-- Delete user -->
				<div id="popup" class="modal hide fade" role="dialog"
					aria-labelledby="deleteLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="deleteLabel"><spring:message code="person.delete"/></h3>
					</div>
					<div class="modal-body">
						<span><spring:message code="message.delete"/></span> <span id="name"></span>
					</div>
					<div class="modal-footer">
						<a id="deleteLink" data-dismiss="modal"
							href="${pageContext.request.contextPath}/user/delete"
							class="btn btn-danger"><spring:message code="button.delete"/></a> <a id="canceldelete" href="#"
							class="btn" data-dismiss="modal" aria-hidden="true" ><spring:message code="button.cancel"/></a>
					</div>
				</div>

				<!-- Edit user -->
				<div id="action_popup" class="modal hide fade" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel"><spring:message code="person.add"/>: <span id="rating"></span></h3>
					</div>
					<div class="modal-body">
						<form:form id="addedituser" class="form-horizontal" method="POST" commandName="person"
							action="${pageContext.request.contextPath}/user/update">
							<form:input path="id" id="id" class="hide" disabled="disabled" />
							
							<div class="control-group">
    							<label class="control-label" for="uname"><spring:message code="person.firstname"/></label>
    							<div class="controls">
      								<form:input path="name" type="text" id="uname" placeholder="Name" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="surname"><spring:message code="person.lastname"/></label>
    							<div class="controls">
      								<form:input path="surname" type="text" id="surname" placeholder="Second Name" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="email"><spring:message code="person.mail"/></label>
    							<div class="controls">
      								<form:input path="email" type="email" id="email" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="cellphone"><spring:message code="person.mobile"/></label>
    							<div class="controls">
      								<form:input path="cellphone" type="text" id="cellphone" placeholder="Mobile" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="multibookAllowed"><spring:message code="person.multibookallowed"/></label>
    							<div class="controls">
      								<form:input path="multibookAllowed" type="text" id="multibookAllowed" placeholder="10" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="untimelyReturns"><spring:message code="person.untimelly"/></label>
    							<div class="controls">
      								<form:input path="untimelyReturns" type="text" id="untimelyReturns" placeholder="0" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="timelyReturns"><spring:message code="person.timelly"/></label>
    							<div class="controls">
      								<form:input path="timelyReturns" type="text" id="timelyReturns" placeholder="0" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="failedOrders"><spring:message code="person.failed"/></label>
    							<div class="controls">
      								<form:input path="failedOrders" type="text" id="failedOrders" placeholder="0" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="confirm"><spring:message code="person.confirmed"/></label>
    							<div class="controls">
      								<form:checkbox path="confirm" id="confirm" />
    							</div>
  							</div>
  							<div class="form-actions">
								<input type="submit" value="Save" class="btn btn-primary" />
								<input id="cancel" type="button" class="btn" data-dismiss="modal" aria-hidden="true" value="Cancel" />
							</div>
						</form:form>
					</div>
				</div>
			</div>