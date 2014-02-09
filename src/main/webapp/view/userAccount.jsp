<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="span8">
    <h3><spring:message code="message.accprop"/></h3>
                            <form:form id="account" method="POST" commandName="person" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label" for="name"><spring:message code="person.firstname"/>:</label>
                                    <div class="controls"> 
                                        <form:input id="name" path="name" required="true"/>
                                        <form:errors path="name" cssClass="error"/>
                                    </div>
                                </div>
                                    
                                <div class="control-group">
                                        <label class="control-label" for="surname"><spring:message code="person.lastname"/>:</label>
                                        <div class="controls">
                                            <form:input id="surname" path="surname" required="true"/>
                                            <form:errors path="surname" cssClass="error"/>
                                        </div> 
                                </div>
                                
                                <div class="control-group">
                                    <label class="control-label" for="cellphone"><spring:message code="person.mobile"/>:</label>
                                             <div class="controls">
                                                  <form:input path="cellphone" id="cellphone" value="${person.cellphone}" required="true"/>
                                                  <form:errors path="cellphone" cssClass="error"/>
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
                                          <!--  <input type="button" value="Change e-mail" class="btn btn-info"/> -->
                                          <a href="<c:url value="/profile-email"/>" class="btn btn-info">Change e-mail</a>
                                      </div>  
                                   </div>
                                   
                                   <div class="control-group">
                                         <label class="control-label">Password</label>
                                         <div class=controls>
                                            <a href="<c:url value="/pass"/>" class="btn btn-info"><spring:message code="button.changepass"/></a>
                                         </div>
                                   </div>
                               
                                <input type="submit" value="<spring:message code="button.savechanges"/>" class="btn" id="refreshResult"/>
                                   
                             </form:form>
                                
                               
</div>