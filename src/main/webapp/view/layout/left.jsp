<%@ include file="/view/includes.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<tilesx:useAttribute name="genres" />
<div class="span2">
	<ul class="nav nav-pills nav-stacked">
		<li class="nav-header">Genres</li>
		<c:forEach var="genre" items="${genres}">
			<li><a href="<c:url value="/genre/${genre.id}"/>">${genre.name}</a></li>
		</c:forEach>
	</ul>
</div>