<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<script src="${pageContext.request.contextPath}/resources/js/genres.js"></script>
<!-- Content -->
<div class="span10 offset1">
	<!-- Table class="TableGenres"-->
	<div style="overflow-y: scroll">
		<table class="table table-striped table-condensed table-hover">
			<thead>
				<tr>
					<th>№</th>
					<th><spring:message code="book.genre" /></th>
					<%-- <th><spring:message code="person.mail" /></th>
					<th><spring:message code="person.mobile" /></th>
					<th></th>
					<th></th> --%>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${genres}" var="genre">
					<tr id="genre${genre.id}" class="table${genre.id}">
						<td><c:out value="${genre.id}" escapeXml="true" /></td>
						<td class="tdname"><c:out value="${genre.name}"	escapeXml="true" /></td>
						
						

						<%-- <td><a id="issueorder${order.id}" class="btn btn-primary"
							href="#"><spring:message code="button.issue" /></a></td>
						<td><a id="deleteorder${order.id}" class="btn btn-danger"
							href="#"><spring:message code="button.delete" /></a></td>
						<td class="hide"><input class="tdconfirmed" type="checkbox"
							name="confirm" value="confirm"
							${order.person.confirm == true ? 'checked' : ''}></td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- New genre button -->
	<a href="#" id="newgenrebutton" class="btn"><spring:message
			code="genre.add" /></a>

	<!-- Delete popup -->
	<div id="popup" class="modal hide fade" role="dialog"
		aria-labelledby="deleteLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="deleteLabel">
				<spring:message code="button.delete" />
			</h3>
		</div>
		<div class="modal-body">
			<span><spring:message code="message.delete" /></span> <span id="name"
				class="hide"></span>
		</div>
		<div class="modal-footer">
			<a id="deleteLink" data-dismiss="modal"
				href="${pageContext.request.contextPath}/genres/delete"
				class="btn btn-danger"><spring:message code="button.delete" /></a> <a
				id="canceldelete" href="#" class="btn" data-dismiss="modal"
				aria-hidden="true" value="Cancel"><spring:message
					code="button.cancel" /></a>
		</div>
	</div>
	
	<div id="action_popup" class="modal hide fade" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 class="myModalLabelEdit">
				<spring:message code="genre.add" />
			</h3>
			<span id="errorgenre" class="error"></span>
		</div>
		<div class="modal-body">
			<form id="newgenre" class="form-horizontal" method="POST" commandName="genre" action="${pageContext.request.contextPath}/genre/add">
				<div class="control-group">
					<label class="control-label" for="enname">
					<spring:message code="genre.en" /></label>
					<div class="controls">
						<input name="enname" type="text" id="enname" />
						<label id="errorengenre" class="error" > </label>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="runame">
					<spring:message	code="genre.ru" /></label>
					<div class="controls">
						<input name="runame" type="text" id="runame" />
						<label id="errorrugenre" class="error" > </label>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="uaname">
					<spring:message code="genre.ua" /></label>
					<div class="controls">
						<input name="uaname" type="text" id="uaname" />
						<label id="erroruagenre" class="error" > </label>
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
			</form>
		</div>
	</div>

	<div id="progress" class="progress progress-striped active hide">
		<div class="bar" style="width: 100%;">Process...
		</div>
	</div>
	
	

	<div class="modalloading"></div>
</div>
<div class="span1"></div>