<%@ include file="/view/includes.jsp"%>
<!-- Center -->
<div class="span8">
	<c:forEach items="${latest}" var="latest" varStatus="rowCounter">
		<c:if test="${(rowCounter.count + 2) % 3 == 0}">
			<div class="row-fluid">
		</c:if>
				<div class="thumbnail span4">
					<img src="${latest.image}" class="img-rounded">
					<h3>
						<small>${latest.title}</small>
					</h3>
					<p>${latest.authors}</p>
					<p>${latest.description}</p>
					<sec:authorize access="isAuthenticated()">
						<a href="<c:url value="/wishlist?bookId=${latest.id}"/>"
							class="btn-mini"><spring:message code="message.cart" /></a>
						<br />
						<a href="<c:url value="/order?book=${latest.id}&wish=0"/>"
							class="btn-mini"><spring:message code="message.ordernow" /></a>
					</sec:authorize>
				</div>
		<c:if test="${(rowCounter.count % 3 == 0) || (rowCounter.last)}">
			</div>
		</c:if>
	</c:forEach>
</div>