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
<title>Books</title>
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
<body>
<%@ include file="/view/top.jsp" %>
<%@ include file="/view/left.jsp" %>
<div id = "content">
		<div class = "TableBooks">
		<table>
				<tr>
					<td>First Name<a href="<c:url value="/persons?orderby=fname"/>">^v</a></td>
					<td>Second Name<a href="<c:url value="/persons?orderby=sname"/>">^v</a></td>
					<td>E-mail<a href="<c:url value="/persons?orderby=mail"/>">^v</a></td>
					<td>Mobile<a href="<c:url value="/persons?orderby=mobile"/>">^v</a></td>
					<td>Multibook Allowed<a href="<c:url value="/persons?orderby=mb"/>">^v</a></td>
					<td>Untimelly<a href="<c:url value="/persons?orderby=ur"/>">^v</a></td>
					<td>Timelly<a href="<c:url value="/persons?orderby=tr"/>">^v</a></td>
					<td>Failed Orders<a href="<c:url value="/persons?orderby=fails"/>">^v</a></td>
					
					<td>Confirmed<a href="<c:url value="/persons?orderby=congirm"/>">^v</a></td>
					<td></td>
					<td></td>
				</tr>
			<c:forEach items="${persons}" var="person">
				<tr>
					<td><c:out value="${person.name}" escapeXml="true"/></td>
					<td><c:out value="${person.surname}" escapeXml="true"/></td>
					<td><c:out value="${person.email}" escapeXml="true"/></td>
					<td><c:out value="${person.cellphone}" escapeXml="true"/></td>
					<td>${person.multibookAllowed}</td>
					<td>${person.untimelyReturns}</td>
					<td>${person.timelyReturns}</td>
					<td>${person.failedOrders}</td>
					<td><c:out value="${person.confirm}" escapeXml="true"/></td>
					
					<td><a href="<c:url value="/editstudent?id=${student.id}"/>">Edit</a></td>
					<td><a href="<c:url value="/deletestudent?id=${student.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<a href="#" id="newbookbutton" onclick="toggle_visibility('newuser')">New Book</a>
		
		<div id = "newbook">
			<form:form method="POST" commandName="book">
		<form:label path="id"/>
		<table>
			<tr>
				<td>Title</td>
				<td><form:input path="title"/></td>
			</tr>
			<tr>
				<td>Authors</td>
				<td><form:input path="authors"/></td>
			</tr>
			<tr>
				<td>Year</td>
				<td><form:input path="year"/></td>
			</tr>
			<tr>
				<td>Publication</td>
				<td><form:input path="publication"/></td>
			</tr>
			<tr>
				<td>Pages</td>
				<td><form:input path="pages"/></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><form:input path="description"/></td>
			</tr>
			<tr>
				<td>Term</td>
				<td><form:input path="term"/></td>
			</tr>
			<tr>
				<td>Bookcase</td>
				<td><form:input path="bookcase"/></td>
			</tr>
			<tr>
				<td>Shelf</td>
				<td><form:input path="shelf"/></td>
			</tr>
			<tr>
				<td>Genre</td>
				<td>
					<form:select path="genre" id="genre" items="${genre}" itemValue = "id" itemLabel="name" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Save"/></td>
				<td colspan="2"><input type="button" value="Cancel"/></td>
			</tr>
		</table>
	</form:form>
		</div>
		
		<!--  <a href="<c:url value="/addbook"/>">New Book</a> -->
</div>
</body>
</html>