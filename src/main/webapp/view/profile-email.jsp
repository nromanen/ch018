<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="span8">
  <span><spring:message code="message.chEmail"/></span> <br> <br> <br>
	<span><spring:message code="message.email" /> :</span> <span>
		${email}</span> <br> <br>
	<div class="alert alert-error" style="display: none" id="emailerror">
		<button type="button" class="close">&times;</button>
		<h4>
		  <spring:message code="error.occurred"/>
		</h4>
		
	</div>
	<div class="alert alert-success" style="display: none" id="emailSuccess">
		<button type="button" class="close">&times;</button>
		<h4>
		  <spring:message code="changed.Email"/>
		</h4>
		
	</div>
   <form:form id="email" method="POST" commandName="person" class="form-horizontal">
     <div class="control-group">
                                      <label class="control-label" for="email1"><spring:message code="person.mail"/>:</label>
                                      <div class="controls"> 
                                          <form:input path="email" required="true" type="email"/>
                                          <span id="erroremail" class="error"></span>
                                          <br> <br>
                                        <input type="submit" class="btn" value="<spring:message code="button.done"/>"/>
                                      </div>  
                                   </div>
   </form:form>
</div>
