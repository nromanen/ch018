<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
<title>Users</title>
	<script type="text/javascript"></script>
</head>
<body>	<div id="wrapper">
	<div id="header">
    	<%@ include file="/view/top.jsp" %>
    </div>
	<div id="contentliquid">
<div id = "content">
	<div class = "TableBooks">
	<p>${book}</p>
		<table>
			<thead>
				<tr>
					<th>#</th>
					<th>First Name<a href="<c:url value="/persons?orderby=fname"/>">^v</a></th>
					<th>Second Name<a href="<c:url value="/persons?orderby=sname"/>">^v</a></th>
					<th>E-mail<a href="<c:url value="/persons?orderby=mail"/>">^v</a></th>
					<th>Mobile<a href="<c:url value="/persons?orderby=mobile"/>">^v</a></th>
					<th>Return Date</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${booksinuse}" var="bookinuse">
				<tr id="bookinuse${bookinuse.buid}">
					<td><c:out value="${bookinuse.buid}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.person.name}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.person.surname}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.person.email}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.person.cellphone}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.returnDate}" escapeXml="true"/></td>

					<td><a id = "returnbook${bookinuse.buid}" href="<c:url value="#"/>">Return</a></td>
					<td><a id = "deletebiu${bookinuse.buid}" href="<c:url value="#"/>">Delete</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="popup">
			<span>Are you sure you want to delete :</span>
			<span id="name"></span>
			<br>
			<a id="deleteLink" href="${pageContext.request.contextPath}/booksinuse/delete/">Delete</a>
			<a id="canceldelete" href="#">Cancel</a>
	</div>
	<div id="action_popup">
			<span>Return book?</span>
			<span id="name"></span>
			<br>
			<a id="actionLink" href="${pageContext.request.contextPath}/booksinuse/return/">Return</a>
			<a id="cancelaction" href="#">Cancel</a>
	</div>
				<div id="background"></div>
</div>
</div>
<div id="leftcolumn">
<%@ include file="/view/left.jsp" %>
</div>
<div id="footer">
            <p>This is the Footer</p>
        </div>
</div>
</body>
</html>