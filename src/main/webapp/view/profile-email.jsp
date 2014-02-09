<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="span8">
  На этой странице можно сменить email адрес, который используется для входа на сайт, 
     получения уведомлений о новых сообщениях, 
     восстановления пароля и прочих полезных вещей.
     <br>
     <br>
     <br>
      
       <span>Your current email:</span><span> ${person.email}</span>
       <br>
       <br>
   <form:form id="account" method="POST" commandName="person" class="form-horizontal">
     <div class="control-group">
                                      <label class="control-label" for="email1"><spring:message code="person.mail"/>:</label>
                                      <div class="controls"> 
                                          <form:input path="email" />
                                          <form:errors path="email" cssClass="error"/>
                                          <!--  <input type="button" value="Change e-mail" class="btn btn-info"/> -->
                                        <input type="submit" value="Change email"/>
                                      </div>  
                                   </div>
   </form:form>
</div>
