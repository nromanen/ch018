<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="request" value="${pageContext.request.queryString}"/>
<c:set var="spage" value="&page=${page}" />
<c:if test="${fn:contains(request, 'page')}">
	<c:set var="request" value="${fn:replace(request, spage, '')}" />
</c:if>
<c:set var="asc" value="" />
<c:if test="${isAsc == null || isAsc == false}">
	<c:set var="asc" value="&isasc=true" />
</c:if>
<div class="span10 offset1">
	<div class="row-fluid">
		<!-- Button group -->
		<div class="span7" style="overflow: scroll;">
			<div class="btn-group" data-toggle="buttons-radio">
				<a href="<c:url value="/books"/>" class="btn btn-primary"><spring:message
						code="book.all" /></a> <a href="<c:url value="/books/show/issuetd"/>"
					class="btn btn-primary"><spring:message code="book.issuetd" /></a>
				<a href="<c:url value="/books/show/issueph"/>"
					class="btn btn-primary"><spring:message code="book.issueph" /></a>
				<a href="<c:url value="/books/show/return"/>"
					class="btn btn-primary"><spring:message code="book.return" /></a> <a
					href="<c:url value="/books/show/returntd"/>"
					class="btn btn-primary"><spring:message code="book.returntd" /></a>
			</div>
		</div>

		<div class="offset1 span4">
			<form class="formi" method="GET"
				action="${pageContext.request.contextPath}/books/search">
				<div class="input-append row">
					<input name="search" type="text" class="span6">
					<button type="submit" class="btn btn-primary">
						<spring:message code="book.search" />
					</button>
					<button class="btn btn-primary" type="button" id="advtoggle" rel="popover">Advanced</button>
				</div>
			</form>
		</div>
	</div>
	
	<!-- Advanced search -->
	<div id="popover_advanced_search" style="display: none">
		<form class="" action="${pageContext.request.contextPath}/books/advsearch" method="GET">
			<div class="control-group">
				<label class="control-label" for="advtitle"><spring:message code="book.title" /></label>
				<div class="controls">
					<input name="title" id="advtitle" type="text">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="advautors"><spring:message code="book.authors" /></label>
				<div class="controls">
					<input name="authors" type="text" id="advautors">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="advpublication"><spring:message code="book.publication" /></label>
				<div class="controls">
					<input name="publication" type="text" id="advpublication">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="advyear"><spring:message code="book.year" /></label>
				<div class="controls">
					<input name="year" type="text" id="advyear">
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="advgenre"><spring:message code="book.genre" /></label> 
				<div class="controls">
					<select name="genre" id="advgenre" class="span12">
						<option value="0"><spring:message code="genre.all" /></option>
						<c:forEach var="gnr" items="${genre}">
							<option value="${gnr.id}">${gnr.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div id="adv_search_title" style="display: none"><spring:message code="search.advanced" /></div>
			<label class="checkbox"> 
			<input id="advavailable" name="available" type="checkbox" ${advancedSearch.available == true ? 'checked' : ''} > <spring:message code="book.available" /></label>

			<button type="submit" class="btn"><spring:message code="book.search" /></button>
		</form>
	</div>

	<!-- Alert -->
	<div class="alert alert-error" style="display: none">
		<button type="button" class="close">&times;</button>
		<h4>
			<spring:message code="message.error" />
		</h4>
		<spring:message code="book.errordel" />
	</div>

	<!-- Books table class="TableBooks"-->
	<div style="overflow-y: scroll">
	
	<c:if test="${search != null && search != ''}">
		<div class="alert">
			<span><spring:message code="search.results" /> </span>"<c:out value="${search}"/>"
		</div>
	</c:if>
	<c:if test="${advancedSearch != null}">
		<div class="alert">
			<span><spring:message code="search.results" /> </span>"<c:out value="${advancedSearch.title}"/> <c:out value="${advancedSearch.authors}"/> <c:out value="${advancedSearch.publication}"/> <c:out value="${advancedSearch.year}"/> "
		</div>
	</c:if>
		<table class="table table-striped table-condensed table-hover">
			<thead>
				<tr>
					<th hidden="true"></th>
					<th><nobr><spring:message code="book.title" />
					<c:if test="${show == null}">
						<a href="<c:url value="?sort=title${asc}"/>"><i class="icon-chevron-down"></i></a>
					</c:if></nobr></th>
					<th><nobr><spring:message code="book.authors" />
					<c:if test="${show == null}">
						<a href="<c:url value="?sort=authors${asc}"/>"><i class="icon-chevron-down"></i></a>
					</c:if></nobr></th>
					<th><nobr><spring:message code="book.publication" />
					<c:if test="${show == null}">
						<a href="<c:url value="?sort=publication${asc}"/>"><i class="icon-chevron-down"></i></a>
					</c:if></nobr></th>
					<th><nobr><spring:message code="book.year" />
					<c:if test="${show == null}">
						<a href="<c:url value="?sort=year${asc}"/>"><i class="icon-chevron-down"></i></a>
					</c:if></nobr></th>
					<th><nobr><spring:message code="book.pages" />
					<c:if test="${show == null}">
						<a href="<c:url value="?sort=pages${asc}"/>"><i class="icon-chevron-down"></i></a>
					</c:if></nobr></th>
					<th><nobr><spring:message code="book.bookcase" />
					<c:if test="${show == null}">
						<a href="<c:url value="?sort=bookcase${asc}"/>"><i class="icon-chevron-down"></i></a>
					</c:if></nobr></th>
					<th><nobr><spring:message code="book.shelf" />
					<c:if test="${show == null}">
						<a href="<c:url value="?sort=shelf${asc}"/>"><i class="icon-chevron-down"></i></a>
					</c:if></nobr></th>
					<th><nobr><spring:message code="book.genre" /></nobr></th>
					<th><nobr><spring:message code="book.term" />
						<c:if test="${show == null}">
							<a href="<c:url value="?sort=term${asc}"/>"><i class="icon-chevron-down"></i></a>
						</c:if></nobr></th>
					<th><nobr><spring:message code="book.count" />
						<c:if test="${show == null}">
							<a href="<c:url value="?sort=count${asc}"/>"><i class="icon-chevron-down"></i></a>
						</c:if></nobr></th>
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
						<td class="authors${book.id}"><c:out value="${book.authors}"
								escapeXml="true" /></td>
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
						<td class="iimg${book.id}" hidden="true">${book.image}</td>
						<td class="rating${book.id}" hidden="true">${book.rating}</td>
						<td class="evals${book.id}" hidden="true">${book.numberOfEvaluations}</td>
						<td class="count${book.id}">${book.available}/${book.count}</td>

						<td>
						<div class="btn-group">
							<button class="btn btn-primary dropdown-toggle btn-small" data-toggle="dropdown">
								<spring:message code="button.action" />
    							<span class="caret"></span>
    						</button>
    						<ul class="dropdown-menu">
    							<li>
    								<a href="<c:url value="/bookusers/book/${book.id}"/>">
    									<spring:message code="book.users" />
    								</a>
    							</li>
    							<li>
    								<a href="<c:url value="/orders/book/${book.id}"/>">
    									<spring:message code="book.orders" />
    								</a>
    							</li>
    						</ul>
    					</div>						
						</td>
							
						<td><a href="#" id="editbook${book.id}"
							class="btn btn-warning btn-small"><spring:message code="button.edit" /></a></td>
						<td><a href="#" id="deletebook${book.id}"
							class="btn btn-danger btn-small"><spring:message code="button.delete" /></a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- New book button -->
	<a href="#" id="newbookbutton" class="btn"><spring:message
			code="book.newbook" /></a>
			
	<!-- Pagination -->
	<c:if test="${pages > 1}">
	<div class="pagination pagination-centered">
		<ul>
			<c:if test="${page == 1}">
				<li class = "disabled">
					<a >«</a>
				</li>
			</c:if>
			<c:if test="${page > 1}">
				<li>
					<c:if test="${search != null}">
						<a href="<c:url value="?${request}&page=${page-1}"/>">«</a>
					</c:if>
					<c:if test="${search == null}">
						<a href="<c:url value="?page=${page-1}"/>">«</a>
					</c:if>
				</li>
			</c:if>
			<c:forEach var="i" begin="1" end="${pages}">
   				<li>
	   				<c:if test="${search != null}">
						<a href="?${request}&page=${i}"><c:out value="${i}"/></a>
					</c:if>
					<c:if test="${search == null}">
						<a href="?page=${i}"><c:out value="${i}"/></a>
					</c:if>
				</li>
			</c:forEach>
			<c:if test="${page == pages}">
				<li class = "disabled">
					<a>»</a>
				</li>
			</c:if>
			<c:if test="${page < pages}">
				<li>
					<c:if test="${search != null}">
						<a href="<c:url value="?${request}&page=${page+1}"/>">»</a>
					</c:if>
					<c:if test="${search == null}">
						<a href="<c:url value="?page=${page+1}"/>">»</a>
					</c:if>
				</li>
			</c:if>
		</ul>
	</div>
	</c:if>
	
	

	<!-- Delete popup -->
	<div id="popup" class="modal hide fade" role="dialog"
		aria-labelledby="deleteLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="deleteLabel">
				<spring:message code="book.deletebook" />
			</h3>
		</div>
		<div class="modal-body">
			<span><spring:message code="message.delete" /></span> <span id="name"></span>
		</div>
		<div class="modal-footer">
			<a id="deleteLink" data-dismiss="modal"
				href="${pageContext.request.contextPath}/book/delete"
				class="btn btn-danger"><spring:message code="button.delete" /></a> <a
				id="canceldelete" href="#" class="btn" data-dismiss="modal"
				aria-hidden="true" value="Cancel"><spring:message
					code="button.cancel" /></a>
		</div>
	</div>

	<!-- Edit popup -->
	<div id="action_popup" class="modal hide fade" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 class="myModalLabelEdit hide">
				<spring:message code="button.edit" />
			</h3>
			<h3 class="myModalLabelNew hide">
				<spring:message code="book.newbook" />
			</h3>
			<span id="errorbook" class="error"></span>
		</div>
		<div class="modal-body">
			<form:form id="editbook" class="form-horizontal" method="POST"
				commandName="book" enctype="multipart/form-data"
				action="${pageContext.request.contextPath}/book/update">
				<form:hidden path="id" id="id" disabled="disabled" />

				<div class="control-group">
					<label class="control-label" for="title"><spring:message
							code="book.title" /></label>
					<div class="controls">
						<form:input path="title" type="text" id="title"
							placeholder="Title" required="required" />
						<form:label id="errortitle" path="title" cssClass="error" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="authors"><spring:message
							code="book.authors" /></label>
					<div class="controls">
						<form:input path="authors" type="text" id="authors"
							placeholder="Authors" required="required" />
						<form:label id="errorauthors" path="authors" cssClass="error" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="year"><spring:message
							code="book.year" /></label>
					<div class="controls">
						<form:input path="year" type="number" id="year" placeholder="Year" required="required" />
						<form:label id="erroryear" path="year" cssClass="error" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="publication"><spring:message
							code="book.publication" /></label>
					<div class="controls">
						<form:input path="publication" type="text" id="publication"
							placeholder="Publication" required="required" />
						<form:label id="errorpublication" path="publication" cssClass="error" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="pages"><spring:message
							code="book.pages" /></label>
					<div class="controls">
						<form:input path="pages" type="number" id="pages"
							placeholder="Pages" required="required" />
						<form:label id="errorpages" path="pages" cssClass="error" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="pages"><spring:message
							code="book.description" /></label>
					<div class="controls">
						<form:input path="description" type="text" id="description"
							placeholder="Description" />
						<form:label id="errordescription" path="description" cssClass="error"  />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="term"><spring:message
							code="book.term" /></label>
					<div class="controls">
						<form:input path="term" type="number" id="term" placeholder="Term" required="required" />
						<form:label id="errorterm" path="term" cssClass="error" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="bookcase"><spring:message
							code="book.bookcase" /></label>
					<div class="controls">
						<form:input path="bookcase" type="number" id="bookcase"
							placeholder="Bookcase" required="required" />
						<form:label id="errorbookcase" path="bookcase" cssClass="error" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="shelf"><spring:message
							code="book.shelf" /></label>
					<div class="controls">
						<form:input path="shelf" type="number" id="shelf"
							placeholder="Shelf" required="required" />
						<form:label id="errorshelf" path="shelf" cssClass="error" />
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="genre"><spring:message
							code="book.genre" /></label>
					<div class="controls">
						<form:select path="genre" id="genre" items="${genre}"
							itemValue="id" itemLabel="name" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="count"><spring:message
							code="book.count" /></label>
					<div class="controls">
						<form:input path="count" type="number" id="count"
							placeholder="count" required="required" />
						<form:label id="errorcount" path="count" cssClass="error" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="available"><spring:message
							code="book.available" /></label>
					<div class="controls">
						<form:input path="available" type="number" id="available"
							placeholder="available" required="required" />
						<form:label id="erroravailable" path="available" cssClass="error" />
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="file"></label>
					<div class="controls">
						<input id="file" name="file" type="file" accept="image/*" />
						<button type="button" class="reset_img" aria-hidden="true">×</button>
					</div>
				</div>
				
				<form:hidden path="image" id="image"/>

				<div class="control-group hide">
					<label class="control-label" for="rating"></label>
					<div class="controls">
						<form:input path="rating" type="text" id="rating"
							placeholder="rating" />
					</div>
				</div>
				<div class="control-group hide">
					<label class="control-label" for="numberOfEvaluations"></label>
					<div class="controls">
						<form:input path="numberOfEvaluations" type="number" id="numberOfEvaluations"
							placeholder="numberOfEvaluations" />
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary">
						<spring:message code="button.save" />
					</button>
					<button id="cancel" type="button" class="btn" data-dismiss="modal"
						aria-hidden="true">
						<spring:message code="button.cancel" />
					</button>
				</div>
			</form:form>
		</div>
	</div>
	<div class="modalloading"></div>
</div>
<div class="span1"></div>