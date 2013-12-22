<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/addbook.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
<title>Books</title>
<script type="text/javascript">
	$(document).ready(function()
			{ 
		        $("table").tablesorter(); 
		    }
	); 
</script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<%@ include file="/view/top.jsp"%>
		</div>
		<div id="contentliquid">
			<div id="content">
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
								<tr>
									<td><c:out value="${person.name}" escapeXml="true" /></td>
									<td><c:out value="${person.surname}" escapeXml="true" /></td>
									<td><c:out value="${person.email}" escapeXml="true" /></td>
									<td><c:out value="${person.cellphone}" escapeXml="true" /></td>
									<td>${person.multibookAllowed}</td>
									<td>${person.untimelyReturns}</td>
									<td>${person.timelyReturns}</td>
									<td>${person.failedOrders}</td>
									<td><input type="checkbox" name="confirm" value="confirm"
										${person.confirm == true ? 'checked' : ''}></td>

									<td><a href="<c:url value="/edituser?id=${person.id}"/>">Edit</a></td>
									<td><a href="<c:url value="/deleteuser?id=${person.id}"/>">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<a href="#" id="newuserbutton" onclick="open_popup('#newuser');">New
					User</a>

				<div id="newuser">
					<form:form method="POST" commandName="person">
						<form:label path="id" />
						<table>
							<tr>
								<td>First Name</td>
								<td><form:input path="name" /></td>
							</tr>
							<tr>
								<td>Second Name</td>
								<td><form:input path="surname" /></td>
							</tr>
							<tr>
								<td>E-mail</td>
								<td><form:input path="email" /></td>
							</tr>
							<tr>
								<td>Mobile</td>
								<td><form:input path="cellphone" /></td>
							</tr>
							<tr>
								<td>Multibook Allowed</td>
								<td><form:input path="multibookAllowed" /></td>
							</tr>
							<tr>
								<td>Untimelly</td>
								<td><form:input path="untimelyReturns" /></td>
							</tr>
							<tr>
								<td>Timelly</td>
								<td><form:input path="timelyReturns" /></td>
							</tr>
							<tr>
								<td>Failed Orders</td>
								<td><form:input path="failedOrders" /></td>
							</tr>
							<tr>
								<td>Confirmed</td>
								<td><form:checkbox path="confirm" /></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="Save" /></td>
								<td colspan="2"><input type="button" value="Cancel"
									onclick="close_popup('#newuser');" /></td>
							</tr>
						</table>
					</form:form>
				</div>
				<div id="background"></div>

				<!--  <a href="<c:url value="/addbook"/>">New Book</a> -->
			</div>
		</div>
		<div id="leftcolumn">
			<%@ include file="/view/left.jsp"%>
		</div>
		<div id="footer">
			<p>This is the Footer</p>
		</div>
	</div>
</body>
</html>