<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="span8">
    <h3><spring:message code="message.accprop"/></h3>
                            <form:form   method="POST"  commandName="person" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label" for="name"><spring:message code="person.firstname"/>:</label>
                                    <div class="controls"> 
                                        <form:input id="name" path="name"/>
                                        <form:errors path="name" cssClass="error"/>
                                    </div>
                                </div>
                                    
                                <div class="control-group">
                                        <label class="control-label" for="surname"><spring:message code="person.lastname"/>:</label>
                                        <div class="controls">
                                            <form:input id="surname" path="surname"/>
                                            <form:errors path="surname" cssClass="error"/>
                                        </div> 
                                </div>
                                
                                <div class="control-group">
                                    <label class="control-label" for="cellphone"><spring:message code="person.mobile"/>:</label>
                                             <div class="controls">
                                                  <form:input path="cellphone" id="cellphone" value="${person.cellphone}"/>
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
                                    <label class="control-label" for="email"><spring:message code="person.mail"/>:</label>
                                    <div class="controls"> 
                                        <form:input path="email" id="email" onchange="validate()"/>
                                        <form:errors path="email" cssClass="error"/>
                                       <h4 id="result">1111</h4>
                                    </div>
                                   
                                </div>
                                
                                <input type="submit" value="<spring:message code="button.savechanges"/>" class="btn" id="refreshResult"/>
                                   
                             </form:form>
                               
                                <a href="<c:url value="/pass"/>" class="btn" id="submit"><spring:message code="button.changepass"/></a>
</div>