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
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>First Name<a href="<c:url value="/persons?orderby=fname"/>">^v</a></th>
					<th>Second Name<a href="<c:url value="/persons?orderby=sname"/>">^v</a></th>
					<th>E-mail<a href="<c:url value="/persons?orderby=mail"/>">^v</a></th>
					<th>Mobile<a href="<c:url value="/persons?orderby=mobile"/>">^v</a></th>
					<th>Issue Date</th>
					<th></th>
					<th></th>

				</tr>
			</thead>
			<tbody>
			<c:forEach items="${orders}" var="order">
				<tr>
					<td><c:out value="${order.id}" escapeXml="true"/></td>
					<td><c:out value="${order.person.name}" escapeXml="true"/></td>
					<td><c:out value="${order.person.surname}" escapeXml="true"/></td>
					<td><c:out value="${order.person.email}" escapeXml="true"/></td>
					<td><c:out value="${order.person.cellphone}" escapeXml="true"/></td>
					<td><c:out value="${order.issueDate}" escapeXml="true"/></td>

					<td><a href="<c:url value="/issueorder?id=${person.id}"/>">Issue</a></td>
					<td><a id="deleteorder" href="#">Delete</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="popup">
		<span>Are you sure you want to delete order:</span>
		<br>
		<span id="name"></span>
		<input type="submit" value="Delete" />
		<input type="button" value="Cancel"	onclick="close_popup('#popup');" />
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