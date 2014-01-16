<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="span7 offset2">
	<div class="well">
		<div class="modal-header">
			<h3 id="myModalLabel">
				<spring:message code="message.remind" />
			</h3>
			<h5 id="myModalLabel">
				<spring:message code="message.mesremind" />
			</h5>
		</div>
		<div class="modal-body">
			<h5 class="error">${message}</h5>
			<form id="remind" class="form-horizontal" method="POST">

				<div class="control-group">
					<label class="control-label" for="inputEmailReg"><spring:message
							code="person.mail" /></label>
					<div class="controls">
						<input name="email" type="text" id="inputEmailReg" />
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-primary">
						<spring:message code="button.send" />
					</button>
					<button id="cancel" type="button" class="btn">
						<spring:message code="button.cancel" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="span3"></div>