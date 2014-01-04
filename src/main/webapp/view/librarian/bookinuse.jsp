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
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
<title>Users</title>
<script type="text/javascript"></script>
</head>
<body>
    <!-- Include header -->
    <%@ include file="/view/top.jsp"%>
    
	<div class="container-fluid">
	
		<div class="row-fluid">

			<!-- Left side -->
			<div class="span1">
				<%@ include file="/view/left.jsp"%>
			</div>

			<!-- Content -->
			<div class="span11">
				<div class="alert alert-info">${book}</div>
				<!-- Table -->
				<div class="TableBooks">
					<table>
						<thead>
							<tr>
								<th>#</th>
								<th>First Name<a
									href="<c:url value="/persons?orderby=fname"/>">^v</a></th>
								<th>Second Name<a
									href="<c:url value="/persons?orderby=sname"/>">^v</a></th>
								<th>E-mail<a href="<c:url value="/persons?orderby=mail"/>">^v</a></th>
								<th>Mobile<a
									href="<c:url value="/persons?orderby=mobile"/>">^v</a></th>
								<th>Return Date</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${booksinuse}" var="bookinuse">
								<tr id="bookinuse${bookinuse.buid}"
									class="table${bookinuse.buid}">
									<td><c:out value="${bookinuse.buid}" escapeXml="true" /></td>
									<td hidden="true" class="pid${bookinuse.buid}"><c:out value="${bookinuse.person.id}" escapeXml="true" /></td>
									<td><c:out value="${bookinuse.person.name}"
											escapeXml="true" /></td>
									<td><c:out value="${bookinuse.person.surname}"
											escapeXml="true" /></td>
									<td><c:out value="${bookinuse.person.email}"
											escapeXml="true" /></td>
									<td><c:out value="${bookinuse.person.cellphone}"
											escapeXml="true" /></td>
									<td><c:out value="${bookinuse.returnDate}"
											escapeXml="true" /></td>
								

									<td><a id="returnbook${bookinuse.buid}"
										class="btn btn-primary" href="#">Return</a></td>
									<td><a id="deletebiu${bookinuse.buid}"
										class="btn btn-danger" href="#">Delete</a></td>
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
						<h3 id="deleteLabel">Delete</h3>
					</div>
					<div class="modal-body">
						<span>Are you sure you want to delete :</span> <span id="name"></span>
					</div>
					<div class="modal-footer">
						<a id="deleteLink" data-dismiss="modal"
							href="${pageContext.request.contextPath}/booksinuse/delete"
							class="btn btn-danger">Delete</a> <a id="canceldelete" href="#"
							class="btn" data-dismiss="modal" aria-hidden="true"
							value="Cancel">Cancel</a>
					</div>
				</div>

				<!-- Return popup -->
				<div id="action_popup" class="modal hide fade" role="dialog"
					aria-labelledby="returnLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="returnLabel">Return</h3>
					</div>
					<div class="modal-body">
						<span>Return book?</span> <span id="aname"></span>
					</div>
					<div class="modal-footer">
						<a id="actionLink" data-dismiss="modal"
							href="${pageContext.request.contextPath}/booksinuse/return"
							class="btn btn-primary">Return</a> <a id="cancelaction" href="#"
							class="btn" data-dismiss="modal" aria-hidden="true"
							value="Cancel">Cancel</a>
					</div>
				</div>
			</div>
		</div>
	</div>
		<!-- footer -->
            
            <footer class="footer">
				<div class="container">
					<div class="row">
						<div class="span12">
							<p>This is FOOTER</p>
						</div>
					</div>
				</div>
			</footer>
</body>
</html>