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
				<spring:message code="menu.sign" />
			</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" action="j_spring_security_check"
				method="post">
				<div class="control-group">
					<label class="control-label" for="inputEmail"><spring:message
							code="person.mail" /></label>
					<div class="controls">
						<input name="j_username" id="inputEmail" type="email" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPassword"><spring:message
							code="person.pass" /></label>
					<div class="controls">
						<input name="j_password" type="password" id="inputPassword"
							placeholder="Pass">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="checkbox"> <input type="checkbox"
							name="_spring_security_remember_me"> <spring:message
								code="menu.remember" />
						</label>
						<button type="submit" class="btn">
							<spring:message code="button.login" />
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="span3"></div>