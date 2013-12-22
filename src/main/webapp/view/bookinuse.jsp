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
<script src="${pageContext.request.contextPath}/resources/js/addbook.js"></script>
<title>Users</title>
	<script type="text/javascript">
   function toggle_visibility(id) {
       var e = document.getElementById(id);
       if(e.style.display == 'block')
          e.style.display = 'none';
       else
          e.style.display = 'block';
   }
</script>
</head>
<body>	<div id="wrapper">
	<div id="header">
    	<%@ include file="/view/top.jsp" %>
    </div>
	<div id="contentliquid">
<div id = "content">
	<div class = "TableBooks">
		<table>
				<tr>
					<td>First Name<a href="<c:url value="/persons?orderby=fname"/>">^v</a></td>
					<td>Second Name<a href="<c:url value="/persons?orderby=sname"/>">^v</a></td>
					<td>E-mail<a href="<c:url value="/persons?orderby=mail"/>">^v</a></td>
					<td>Mobile<a href="<c:url value="/persons?orderby=mobile"/>">^v</a></td>
					<td>Return Date</td>
					<td></td>
					<td></td>
				</tr>
			<c:forEach items="${booksinuse}" var="bookinuse">
				<tr>
					<td><c:out value="${bookinuse.person.name}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.person.surname}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.person.email}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.person.cellphone}" escapeXml="true"/></td>
					<td><c:out value="${bookinuse.returnDate}" escapeXml="true"/></td>

					<td><a href="<c:url value="/edituser?id=${student.id}"/>">Edit</a></td>
					<td><a href="<c:url value="/deleteuser?id=${student.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
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