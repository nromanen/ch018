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
	        $('#editbook').submit(function(event) {
	              
	              var title = $('#title').val();
	              var authors = $('#authors').val();
	              var year = $('#year').val();
	              var json = { "title" : title, "authors" : authors, "year": year};
	              
	        $.ajax({
	                url: $("#editbook").attr( "action"),
	                data: JSON.stringify(json),
	                type: "PUT",
	                
	                beforeSend: function(xhr) {
	                        xhr.setRequestHeader("Accept", "application/json");
	                        xhr.setRequestHeader("Content-Type", "application/json");
	                        $(".error").remove();
	                },
	                success: function(smartphone) {
	                        var respContent = "";
	                        
	                                  respContent += "<span class='success'>Book was edited: [";
	                                  respContent += smartphone.producer + " : ";
	                                  respContent += smartphone.model + " : " ;
	                                  respContent += smartphone.price + "]</span>";
	                        
	                        $("#sPhoneFromResponse").html(respContent);        
	                },
	                error: function(jqXHR, textStatus, errorThrown) {
	                        var respBody = $.parseJSON(jqXHR.responseText);
	                        var respContent = "";
	                        
	                        respContent += "<span class='error-main'>";
	                        respContent += respBody.message;
	                        respContent += "</span>";
	                        
	                        $("#sPhoneFromResponse").html(respContent);
	                        
	                        $.each(respBody.fieldErrors, function(index, errEntity) {
	                                var tdEl = $("."+errEntity.fieldName+"-info");
	                                tdEl.html("<span class=\"error\">"+errEntity.fieldError+"</span>");
	                        });
	                }
	        });
	        
	        event.preventDefault();
	      });
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
				<div id="linksblock">
					<a href="<c:url value="/books?show=all"/>">All</a> <a
						href="<c:url value="/books?show=issuetd"/>">To issue today</a> <a
						href="<c:url value="/books?show=issueph"/>">To issue per hour</a>
					<a href="<c:url value="/books?show=return"/>">To return</a> <a
						href="<c:url value="/books?show=returntd"/>">All</a>
				</div>
				<div class="TableBooks">
					<table>
						<thead>
							<tr>
								<th>#<a href="<c:url value="/books?orderby=id"/>">^v</a></th>
								<th>Title<a href="<c:url value="/books?orderby=title"/>">^v</a></th>
								<th>Authors<a
									href="<c:url value="/books?orderby=authors"/>">^v</a></th>
								<th>Publication<a
									href="<c:url value="/books?orderby=publ"/>">^v</a></th>
								<th>Year<a href="<c:url value="/books?orderby=year"/>">^v</a></th>
								<th>Pages<a href="<c:url value="/books?orderby=pages"/>">^v</a></th>
								<th>Bookcase<a href="<c:url value="/books?orderby=bc"/>">^v</a></th>
								<th>Shelf<a href="<c:url value="/books?orderby=shelf"/>">^v</a></th>
								<th>Genre<a href="<c:url value="/books?orderby=genre"/>">^v</a></th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${books}" var="book">
								<tr>
									<td class="id${book.id}">${book.id}</td>
									<td class="title${book.id}"><c:out value="${book.title}" escapeXml="true" /></td>
									<td class="authors${book.id}"><c:out value="${book.authors}" escapeXml="true" /></td>
									<td class="publication${book.id}"><c:out value="${book.publication}" escapeXml="true" /></td>
									<td class="year${book.id}">${book.year}</td>
									<td class="pages${book.id}">${book.pages}</td>
									<td class="bookcase${book.id}">${book.bookcase}</td>
									<td class="shelf${book.id}">${book.shelf}</td>
									<td><c:out value="${book.genre}" escapeXml="true" /><p class="genre${book.id}" hidden = "true">${book.genre.id}</p></td>

									<td><a href="<c:url value="/bookusers?id=${book.id}"/>">Users</a></td>
									<td><a href="#" class="book${book.id}" onclick="open_andfill('#newbook',${book.id});">Edit</a></td>
									<td><a href="<c:url value="/deletebook?id=${book.id}"/>">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<a href="#" id="newbookbutton" onclick="open_popup('#newbook');">New
					Book</a>

				<div id="newbook">
					<form:form id="editbook" method="PUT" commandName="book" action="${pageContext.request.contextPath}/books/edit/${book.id}.json">
						<form:label path="id" />
						<table>
							<tr>
								<td>Title</td>
								<td><form:input path="title" id="title" /></td>
							</tr>
							<tr>
								<td>Authors</td>
								<td><form:input path="authors" id="authors" /></td>
							</tr>
							<tr>
								<td>Year</td>
								<td><form:input path="year" id="year" /></td>
							</tr>
							<tr>
								<td>Publication</td>
								<td><form:input path="publication" id="publication" /></td>
							</tr>
							<tr>
								<td>Pages</td>
								<td><form:input path="pages" id="pages" /></td>
							</tr>
							<tr>
								<td>Description</td>
								<td><form:input path="description" id="description" /></td>
							</tr>
							<tr>
								<td>Term</td>
								<td><form:input path="term" id="term" /></td>
							</tr>
							<tr>
								<td>Bookcase</td>
								<td><form:input path="bookcase" id="bookcase" /></td>
							</tr>
							<tr>
								<td>Shelf</td>
								<td><form:input path="shelf" id="shelf" /></td>
							</tr>
							<tr>
								<td>Genre</td>
								<td><form:select path="genre" id="genre" items="${genre}"
										itemValue="id" itemLabel="name" /></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="Save" /></td>
								<td colspan="2"><input type="button" value="Cancel"
									onclick="close_popup('#newbook');" /></td>
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