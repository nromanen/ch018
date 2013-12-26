<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scripts.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.tablesorter.js"></script>
<title>Books</title>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<%@ include file="/view/top.jsp"%>
		</div>
		<div id="contentliquid">
			<div id="content">
			
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