<%@ include file="/view/includes.jsp"%>
<div class="span7 offset2">
	<div class="well">
		<div class="modal-header">
			<h3 id="myModalLabel">
				<spring:message code="message.remind" />
			</h3>
			<h5 id="myModalLabel">
				Input new password
			</h5>
		</div>
		<div class="modal-body">
			<form:form id="restore" class="form-horizontal" method="POST" commandName="resetPassword" 
			action="${pageContext.request.contextPath}/remind/pass" >
				<form:hidden path="key"/>
				<div class="control-group">
					<label class="control-label" for="inputPasswordReg"><spring:message
							code="person.pass" /></label>
					<div class="controls">
						<form:input path="password" type="password" id="inputPasswordReg" />
						<form:errors path="password" cssClass="error"></form:errors>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="confirmPasswordReg"><spring:message
							code="person.confirmpass" /></label>
					<div class="controls">
						<form:input path="confirm" type="password" id="confirmPasswordReg" />
						<form:errors path="confirm" cssClass="error"></form:errors>
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
			</form:form>
		</div>
	</div>
</div>
<div class="span3"></div>