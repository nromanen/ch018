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
	<form:form method="POST" commandName="password" class="form-horizontal">
            <div class="control-group">	
                <label class="control-label" for="pass"><spring:message code="pass.old" /></label>
                <div class="controls">
                    <form:password path="password" id="pass"/>
                    <form:errors path="password" cssClass="error"/>
                </div>		
            </div>  
            <div class="control-group">	
                <label class="control-label" for="newpass"><spring:message code="pass.new" /></label>
                <div class="controls">
                    <form:password path="newPassword" id="newpass"/>
                    <form:errors path="newPassword" cssClass="error"/>
                </div>		
            </div>            
                
                <div class="control-group">			
                    <label class="control-label" for="confpass"><spring:message code="pass.confirm" /></label>
                    <div class="controls">		
                        <form:password path="confirmPassword" id="confpass"/>
                        <form:errors path="confirmPassword" cssClass="error"/>
                    </div>
		</div>	
		<input type="submit"
				value="<spring:message code="button.changepass"/>" class="btn" />
		
	</form:form>
</div>
