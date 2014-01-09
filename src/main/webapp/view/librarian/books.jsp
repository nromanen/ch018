<%@ include file="/view/includes.jsp"%>
		<div class="span11">
				<div class="row-fluid">
					<!-- Button group -->
					<div class="span8">
						<div class="btn-group">
							<a href="<c:url value="/books"/>" class="btn btn-primary"><spring:message code="book.all"/></a>
							<a href="<c:url value="/books?show=issuetd"/>"
								class="btn btn-primary"><spring:message code="book.issuetd"/></a> 
								<a href="<c:url value="/books?show=issueph"/>"
								class="btn btn-primary"><spring:message code="book.issueph"/></a> 
								<a href="<c:url value="/books?show=return"/>"
								class="btn btn-primary"><spring:message code="book.return"/></a> 
								<a href="<c:url value="/books?show=returntd"/>"
								class="btn btn-primary"><spring:message code="book.returntd"/></a>
						</div>
					</div>

					<div class="span4">
						<form class="form-search" method="POST"
							action="${pageContext.request.contextPath}/books">
							<div class="input-append">
								<select name="field" class="input-small">
									<option value="all"><spring:message code="book.searchall"/></option>
									<option value="title"><spring:message code="book.title"/></option>
									<option value="author"><spring:message code="book.authors"/></option>
									<option value="publication"><spring:message code="book.publication"/></option>
									<option value="year"><spring:message code="book.year"/></option>
									<option value="genre.name"><spring:message code="book.genre"/></option>
								</select> <input name="search" type="text"
									class="search-query input-medium">
								<button type="submit" class="btn btn-primary"><spring:message code="book.search"/></button>
							</div>
						</form>
					</div>
				</div>

				<!-- Alert -->
				<div class="alert alert-error" style="display: none">
					<button type="button" class="close">&times;</button>
					<h4><spring:message code="message.error"/></h4>
					<spring:message code="book.errordel"/>
				</div>

				<!-- Books table -->
				<div class="TableBooks">
					<table>
						<thead>
							<tr>
								<th><spring:message code="book.title"/></th>
								<th><spring:message code="book.authors"/></th>
								<th><spring:message code="book.publication"/></th>
								<th><spring:message code="book.year"/></th>
								<th><spring:message code="book.pages"/></th>
								<th><spring:message code="book.bookcase"/></th>
								<th><spring:message code="book.shelf"/></th>
								<th><spring:message code="book.genre"/></th>
								<th><spring:message code="book.term"/></th>
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

									<td><a href="<c:url value="/bookusers?id=${book.id}"/>"
										class="btn btn-info"><spring:message code="book.users"/></a><br> <a
										href="<c:url value="/orders?id=${book.id}"/>"
										class="btn btn-primary"><spring:message code="book.orders"/></a><br></td>
									<td><a href="#" id="editbook${book.id}"
										class="btn btn-warning"><spring:message code="button.edit"/></a></td>
									<td><a href="#" id="deletebook${book.id}"
										class="btn btn-danger"><spring:message code="button.delete"/></a></td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- New book button -->
				<a href="#" id="newbookbutton" class="btn"><spring:message code="book.newbook"/></a>

				<!-- Delete popup -->
				<div id="popup" class="modal hide fade" role="dialog"
					aria-labelledby="deleteLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="deleteLabel"><spring:message code="book.deletebook"/></h3>
					</div>
					<div class="modal-body">
						<span><spring:message code="message.delete"/></span> <span id="name"></span>
					</div>
					<div class="modal-footer">
						<a id="deleteLink" data-dismiss="modal"
							href="${pageContext.request.contextPath}/book/delete"
							class="btn btn-danger"><spring:message code="button.delete"/></a> <a id="canceldelete" href="#"
							class="btn" data-dismiss="modal" aria-hidden="true"
							value="Cancel"><spring:message code="button.cancel"/></a>
					</div>
				</div>

				<!-- Edit popup -->
				<div id="action_popup" class="modal hide fade" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel"><spring:message code="book.addedit"/></h3>
					</div>
					<div class="modal-body">
						<form:form id="editbook" class="form-horizontal" method="POST"
							commandName="book"
							action="${pageContext.request.contextPath}/book/update">
							<form:input path="id" id="id" class="hide" disabled="disabled" />

							<div class="control-group">
								<label class="control-label" for="title"><spring:message code="book.title"/></label>
								<div class="controls">
									<form:input path="title" type="text" id="title"
										placeholder="Title" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="authors"><spring:message code="book.authors"/></label>
								<div class="controls">
									<form:input path="authors" type="text" id="authors"
										placeholder="Authors" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="year"><spring:message code="book.year"/></label>
								<div class="controls">
									<form:input path="year" type="text" id="year"
										placeholder="Year" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="publication"><spring:message code="book.publication"/></label>
								<div class="controls">
									<form:input path="publication" type="text" id="publication"
										placeholder="Publication" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="pages"><spring:message code="book.pages"/></label>
								<div class="controls">
									<form:input path="pages" type="text" id="pages"
										placeholder="Pages" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="pages"><spring:message code="book.description"/></label>
								<div class="controls">
									<form:input path="description" type="text" id="description"
										placeholder="Description" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="term"><spring:message code="book.term"/></label>
								<div class="controls">
									<form:input path="term" type="text" id="term"
										placeholder="Term" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="bookcase"><spring:message code="book.bookcase"/></label>
								<div class="controls">
									<form:input path="bookcase" type="text" id="bookcase"
										placeholder="Bookcase" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="shelf"><spring:message code="book.shelf"/></label>
								<div class="controls">
									<form:input path="shelf" type="text" id="shelf"
										placeholder="Shelf" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="shelf"><spring:message code="book.genre"/></label>
								<div class="controls">
									<form:select path="genre" id="genre" items="${genre}"
										itemValue="id" itemLabel="name" />
								</div>
							</div>

							<div class="form-actions">
								<button type="submit" class="btn btn-primary" ><spring:message code="button.save"/></button> 
								<button id="cancel" type="button" class="btn" data-dismiss="modal" aria-hidden="true" ><spring:message code="button.cancel"/></button>
							</div>
						</form:form>
					</div>
				</div>
			</div>