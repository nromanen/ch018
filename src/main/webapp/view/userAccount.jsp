<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
<div class="span8">
    <h3><spring:message code="message.accprop"/></h3>
                            <form:form   method="POST"  commandName="person">
                                 <table>
                                       <tr>
                                           <td><spring:message code="person.firstname"/>: </td>
                                          <td><form:input path="name"/></td>
                                       </tr>
                                       <tr>
                                          <td><spring:message code="person.lastname"/>: </td>
                                          <td><form:input path="surname"/></td>
                                       </tr>
                                       <tr>
                                          <td><spring:message code="person.mobile"/>: </td>
                                          <td><form:input path="cellphone" value="${person.cellphone}"/></td>
                                       </tr>
                                       <tr>
                                          <td>SMS: </td>
                                          <td><form:checkbox path="sms"/></td>
                                       </tr>
                                       <tr>
                                           <td><spring:message code="person.mail"/></td>
                                           <td><form:input path="email"/></td>
                                       </tr>
                                       
                                       <tr>
                                          <td>
                                              <input type="submit" value="<spring:message code="button.savechanges"/>" class="btn" id="refreshResult"/>
                                          </td>
                                       </tr>
                                 </table>
                             </form:form>
                               
                            <a href="<c:url value="/pass"/>" class="btn"><spring:message code="button.changepass"/></a>
</div>