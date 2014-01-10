<%@ include file="/view/includes.jsp"%>
			<div class="span7 offset2">

				<div class="well" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
    					<h3 id="myModalLabel"><spring:message code="reg.registration"/></h3>
  					</div>
  					<div class="modal-body">
  					
					<form:form id="registration" class="form-horizontal" method="POST" commandName="registration" action="${pageContext.request.contextPath}/registration">
						
						<div class="control-group">
    						<label class="control-label" for="inputEmailReg"><spring:message code="person.mail"/></label>
    						<div class="controls">
      							<form:input path="email" type="text" id="inputEmailReg" />
      							<form:errors path="email" cssClass="error"></form:errors>
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="inputPasswordReg" ><spring:message code="person.pass"/></label>
    						<div class="controls">
      							<form:input path="password" type="password" id="inputPasswordReg" placeholder="Pass" />
    							<form:errors path="password" cssClass="error"></form:errors>
    						</div>
  						</div>
  						
  						<div class="control-group">
    						<label class="control-label" for="confirmPasswordReg"><spring:message code="person.confirmpass"/></label>
    						<div class="controls">
      							<form:input path="confirmPassword" type="password" id="confirmPasswordReg" placeholder="Pass" />
    							<form:errors path="confirmPassword" cssClass="error"></form:errors>
    						</div>
  						</div>

  						<div class="form-actions">
							<button type="submit" class="btn btn-primary" ><spring:message code="button.reg"/></button>
							<button id="cancel" type="button" class="btn" ><spring:message code="button.cancel"/></button>
						</div>
					</form:form>
					</div>
				</div>
				
			</div>
			<div class="span3">
			</div>