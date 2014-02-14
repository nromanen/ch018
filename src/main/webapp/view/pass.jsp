<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!-- Center -->
<div class="span8">
	<br>
	<br>
	<br>
	<br>
	<input type="hidden" id="href" value="${pageContext.request.contextPath}/pass" />
	<div class="alert alert-error" style="display: none">
		<button type="button" class="close">&times;</button>
		<h4>
			<spring:message code="message.error" />
		</h4>
		<spring:message code="wrong.password" />
	</div>	
	<form:form method="POST" commandName="password" class="form-horizontal" id="passForm">
            <div class="control-group">	
                <label class="control-label" for="pass"><spring:message code="pass.old" /></label>
                <div class="controls">
                    <form:password path="password" id="pass"/>
                    <span id="errorpassword" class="error"></span>
               <!--      <form:errors path="password" cssClass="error"/>  -->
                </div>		
            </div>  
            <div class="control-group">	
                <label class="control-label" for="newpass"><spring:message code="pass.new" /></label>
                <div class="controls">
                    <form:password path="newPassword" id="newpass"/>
                    <!-- <form:errors path="newPassword" cssClass="error"/> -->
                    <span id="errornewPassword" class="error"></span>
                </div>		
            </div>            
                
                <div class="control-group">			
                    <label class="control-label" for="confpass"><spring:message code="pass.confirm" /></label>
                    <div class="controls">		
                        <form:password path="confirmPassword" id="confpass"/>
                     <!--    <form:errors path="confirmPassword" cssClass="error"/> -->
                     <span id="errorconfirmPassword" class="error"></span>
                    </div>
		</div>	
		<input type="submit"
				value="<spring:message code="button.changepass"/>" class="btn" />
	</form:form>
	<div id="passModal" class="modal hide fade">
        <div class="modal-header">Message</div>
        <div class="modal-body"><spring:message code="password.success"/></div>
    </div>
	
</div>
