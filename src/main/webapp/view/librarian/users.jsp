<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<title>Users</title>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<%@ include file="/view/top.jsp"%>
			</div>
		</div>

		<div class="row-fluid">
			<div class="span1">
				<%@ include file="/view/left.jsp"%>
			</div>
			<div class="span11">
				<!-- Alert -->	
				<div class="alert alert-error" style="display: none">
					<button type="button" class="close" >&times;</button>
  					<h4>ERROR!</h4> We cant delete this book
				</div>
				
				<div class="TableBooks">
					<table id="utable">
						<thead>
							<tr>
								<th>First Name
									<div></div>
								</th>
								<th>Second Name
									<div></div>
								</th>
								<th>E-mail
									<div></div>
								</th>
								<th>Mobile
									<div></div>
								</th>
								<th>Multibook Allowed
									<div></div>
								</th>
								<th>Untimelly
									<div></div>
								</th>
								<th>Timelly
									<div></div>
								</th>
								<th>Failed Orders</th>

								<th>Confirmed</th>
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
									<td><input class="confirm${person.id}" type="checkbox"
										name="confirm" value="confirm"
										${person.confirm == true ? 'checked' : ''}></td>

									<td><a href="#" id="edituser${book.id}"
										class="btn btn-warning">Edit</a></td>
									<td><a href="#" id="deleteuser${book.id}"
										class="btn btn-danger">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- New user button -->
				<a href="#" id="newuserbutton" class="btn">New User</a>

				<!-- Delete user -->
				<div id="popup" class="modal hide fade" role="dialog"
					aria-labelledby="deleteLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="deleteLabel">Delete user</h3>
					</div>
					<div class="modal-body">
						<span>Are you sure you want to delete :</span> <span id="name"></span>
					</div>
					<div class="modal-footer">
						<a id="deleteLink" data-dismiss="modal"
							href="${pageContext.request.contextPath}/user/delete"
							class="btn btn-danger">Delete</a> <a id="canceldelete" href="#"
							class="btn" data-dismiss="modal" aria-hidden="true"
							value="Cancel">Cancel</a>
					</div>
				</div>

				<!-- Edit user -->
				<div id="action_popup" class="modal hide fade" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">Add user</h3>
					</div>
					<div class="modal-body">
						<form:form id="addedituser" class="form-horizontal" method="POST" commandName="person"
							action="${pageContext.request.contextPath}/person/update">
							<form:input path="id" id="id" class="hide" disabled="disabled" />
							
							<div class="control-group">
    							<label class="control-label" for="uname">First Name</label>
    							<div class="controls">
      								<form:input path="name" type="text" id="uname" placeholder="Name" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="surname">Second Name</label>
    							<div class="controls">
      								<form:input path="surname" type="text" id="surname" placeholder="Second Name" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="email">E-mail</label>
    							<div class="controls">
      								<form:input path="email" type="email" id="email" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="cellphone">Mobile</label>
    							<div class="controls">
      								<form:input path="cellphone" type="text" id="cellphone" placeholder="Mobile" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="multibookAllowed">Multibook Allowed</label>
    							<div class="controls">
      								<form:input path="multibookAllowed" type="text" id="multibookAllowed" placeholder="10" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="untimelyReturns">Untimelly returns</label>
    							<div class="controls">
      								<form:input path="untimelyReturns" type="text" id="untimelyReturns" placeholder="0" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="timelyReturns">Timelly returns</label>
    							<div class="controls">
      								<form:input path="timelyReturns" type="text" id="timelyReturns" placeholder="0" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="failedOrders">Failed Orders</label>
    							<div class="controls">
      								<form:input path="failedOrders" type="text" id="failedOrders" placeholder="0" />
    							</div>
  							</div>
  							
  							<div class="control-group">
    							<label class="control-label" for="confirm">Confirmed</label>
    							<div class="controls">
      								<form:checkbox path="confirm" id="confirm" />
    							</div>
  							</div>
  							<div class="control-group"  style="display: none">
    							<div class="controls">
      								<form:input path="password" type="text"  id="password" />
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
		</div>
		<!-- footer -->
		<div class="row-fluid">
			<div class="span12" id="footer">Footer</div>
		</div>
	</div>
</body>
</html>