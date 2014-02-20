<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="span8">
    <h3><spring:message code="message.accprop"/></h3>
    
      <div class="alert alert-success" style="display: none" id="success">
		<button type="button" class="close">&times;</button>
		<h4>
			<spring:message code="message.message"/>
		</h4>
		<spring:message code="updateAccount"/>
	</div>	
	<div class="alert alert-error" style="display: none" id="errorDiv">
		<button type="button" class="close">&times;</button>
		<h4>
		<spring:message code="message.error" />
		</h4>
		<spring:message code="error.occurred"/>
	</div>	
                            <form:form id="account" method="POST" commandName="person" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label" for="name"><spring:message code="person.firstname"/>:</label>
                                    <div class="controls"> 
                                        <form:input id="name" path="name" />
                                     <!--    <form:errors path="name" cssClass="error"/> -->
                                        <span id="errorname" class="error"></span>
                                    </div>
                                </div>
                                    
                                <div class="control-group">
                                        <label class="control-label" for="surname"><spring:message code="person.lastname"/>:</label>
                                        <div class="controls">
                                            <form:input id="surname" path="surname" />
                                         <!--    <form:errors path="surname" cssClass="error"/> -->
                                         <span id="errorsurname" class="error"></span>
                                        </div> 
                                </div>
                                
                                <div class="control-group">
                                    <label class="control-label" for="cellphone"><spring:message code="person.mobile"/>:</label>
                                             <div class="controls">
                                                  <form:input path="cellphone" id="cellphone" value="${person.cellphone}" />
                                                  <span id="errorcellphone" class="error"></span>
                                                <!--   <form:errors path="cellphone" cssClass="error"/> -->
                                             </div>
                                </div>
                                
                                <div class="control-group">  
                                    <label class="control-label" for="sms">SMS:</label>
                                    <div class="controls"> 
                                        <form:checkbox path="sms" id="sms"/>
                                        <form:errors path="sms" cssClass="error"/>
                                    </div>
                                </div>
                                
                                    <div class="control-group">
                                      <label class="control-label" for="email1"><spring:message code="person.mail"/>:</label>
                                      <div class="controls"> 
                                          <form:input path="email" type="hidden"/>
                                          <form:errors path="email" cssClass="error"/>
                                          <span>${person.email}</span>
                                          <a href="<c:url value="/profile-email"/>" class="btn btn-info"><spring:message code="button.chEmail"/></a>
                                      </div>  
                                   </div>
                                   
                                   <div class="control-group">
                                         <label class="control-label"><spring:message code="person.pass"/></label>
                                         <div class=controls>
                                            <a href="<c:url value="/pass"/>" class="btn btn-info"><spring:message code="button.changepass"/></a>
                                         </div>
                                   </div>
                               
                                <input type="submit" value="<spring:message code="button.savechanges"/>" class="btn" id="refreshResult"/>
                             
                             </form:form>
                                
                               
</div>