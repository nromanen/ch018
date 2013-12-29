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
<title>Books</title>
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
				<div class="row-fluid">
				<!-- Button group -->
				<div class="span8">
				<div class="btn-group">
					<a href="<c:url value="/books"/>" class="btn btn-primary">All</a> 
					<a href="<c:url value="/books?show=issuetd"/>" class="btn btn-primary">To
						issue today</a> 
					<a href="<c:url value="/books?show=issueph"/>"
						class="btn btn-primary">To issue per hour</a> 
					<a href="<c:url value="/books?show=return"/>" class="btn btn-primary">To
						return</a> 
				    <a href="<c:url value="/books?show=returntd"/>" class="btn btn-primary">To
						return today</a>
				</div>
				</div>
				
				<div class="span4">
				<form class="form-search" method="POST" action="${pageContext.request.contextPath}/books" >
  					<div class="input-append">
  						<select name="field" class="input-small">
  							<option value="all">All</option>
  							<option value="title">Title</option>
  							<option value="author">Author</option>
  							<option value="publication">Publication</option>
  							<option value="year">Year</option>
  							<option value="genre.name">Genre</option>
						</select>
	    				<input name="search" type="text" class="search-query input-medium">
	    				<button type="submit" class="btn btn-primary">Search</button>
  					</div>
  				</form>
  				</div>
  				</div>
				
				<!-- Books table -->
				<div class="TableBooks">
					<table>
						<thead>
							<tr>
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
								<th>Term</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${books}" var="book">
								<tr id="books${book.id}" class="table${book.id}">
									<td hidden="true" class="id${book.id}">${book.id}</td>
									<td class="title${book.id}"><c:out value="${book.title}"
											escapeXml="true" /></td>
									<td class="authors${book.id}"><c:out
											value="${book.authors}" escapeXml="true" /></td>
									<td class="publication${book.id}"><c:out
											value="${book.publication}" escapeXml="true" /></td>
									<td class="year${book.id}">${book.year}</td>
									<td class="pages${book.id}">${book.pages}</td>
									<td class="bookcase${book.id}">${book.bookcase}</td>
									<td class="shelf${book.id}">${book.shelf}</td>
									<td><c:out value="${book.genre}" escapeXml="true" />
										<p class="genre${book.id}" hidden="true">${book.genre.id}</p></td>
									<td class="term${book.id}">${book.term}</td>
									<td class="desc${book.id}" hidden="true">${book.description}</td>

									<td><a href="<c:url value="/bookusers?id=${book.id}"/>"class="btn btn-info">Users</a><br>
										<a href="<c:url value="/orders?id=${book.id}"/>" class="btn btn-primary">Orders</a><br>
									</td>
									<td><a href="#" id="editbook${book.id}" class="btn btn-warning">Edit</a></td>
									<td><a href="#" id="deletebook${book.id}" class="btn btn-danger">Delete</a></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<!-- New book button -->
				<a href="#" id="newbookbutton" class="btn">New Book</a>
				
				<!-- Delete popup -->
				<div id="popup" class="modal hide fade" role="dialog" aria-labelledby="deleteLabel" aria-hidden="true">
					<div class="modal-header">
    					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    					<h3 id="deleteLabel">Delete user</h3>
  					</div>
  					<div class="modal-body">
						<span>Are you sure you want to delete :</span> <span id="name"></span>
					</div>
					<div class="modal-footer">
						<a id="deleteLink" data-dismiss="modal" href="${pageContext.request.contextPath}/book/delete" class="btn btn-danger">Delete</a>
						<a id="canceldelete" href="#" class="btn" data-dismiss="modal" aria-hidden="true" value="Cancel">Cancel</a>
					</div>
				</div>

				<!-- Edit popup -->
				<div id="action_popup" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
    					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    					<h3 id="myModalLabel">Add book</h3>
  					</div>
  					<div class="modal-body">
					<form:form id="editbook" class="form-horizontal" method="POST" commandName="book"
						action="${pageContext.request.contextPath}/book/update">
						<form:input path="id" id="id" class="hide" disabled="disabled" />
						
						<div class="control-group">
    						<label class="control-label" for="title">Title</label>
    						<div class="controls">
      							<form:input path="title" type="text" id="title" placeholder="Title" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="authors">Authors</label>
    						<div class="controls">
      							<form:input path="authors" type="text" id="authors" placeholder="Authors" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="year">Year</label>
    						<div class="controls">
      							<form:input path="year" type="text" id="year" placeholder="Year" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="publication">Publication</label>
    						<div class="controls">
      							<form:input path="publication" type="text" id="publication" placeholder="Publication" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="pages">Pages</label>
    						<div class="controls">
      							<form:input path="pages" type="text" id="pages" placeholder="Pages" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="pages">Description</label>
    						<div class="controls">
      							<form:input path="description" type="text" id="description" placeholder="Description" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="term">Term</label>
    						<div class="controls">
      							<form:input path="term" type="text" id="term" placeholder="Term" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="bookcase">Bookcase</label>
    						<div class="controls">
      							<form:input path="bookcase" type="text" id="bookcase" placeholder="Bookcase" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="shelf">Shelf</label>
    						<div class="controls">
      							<form:input path="shelf" type="text" id="shelf" placeholder="Shelf" />
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="shelf">Genre</label>
    						<div class="controls">
      							<form:select path="genre" id="genre" items="${genre}"
										itemValue="id" itemLabel="name" />
    						</div>
  						</div>
  						
  						<div class="form-actions">
							<input type="submit" value="Save" class="btn btn-primary"  />
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