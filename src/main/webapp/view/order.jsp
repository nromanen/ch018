<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
			<!-- Center -->
<div class="span8">
    <br><br>
	<div class="alert alert-error" style="display: none">
		<button type="button" class="close">&times;</button>
		<spring:message code="couldnotOrder"/>
		<span id="tryagain" style="display:none"><spring:message code="tryAgainAfter"/></span>: <span id="failed"></span>	
	</div>
	<span id="dateExpired" class="hidden"><spring:message code="date.expired"/></span>
                       <form:form method="POST" id="ord"  modelAttribute="order">
                           <p><span>${order.book.title}</span></p>
                           <c:choose>
                               <c:when test="${order.book.available==0}">
                                   <spring:message code="message.orderfail"/>
                                   <br> <spring:message code="message.orderafter"/> <fmt:formatDate pattern="dd.MM.yyyy" value="${date}"/> 
                               </c:when>
                               <c:otherwise>
                                   <table>
                                       <tr><td><spring:message code="message.issuedate"/></td> 
                                           <td><form:input path="issueDate" class="datetimepicker" required="true" id="orderDate"/>
                                                <form:errors path="issueDate" cssClass="error"/>
                                           </td>
                                            <tr>
                                      <td><spring:message code = "choose.term"/></td>
                                         <td><form:select path="term" id="term">
                                                 <form:option value="0" label="------"></form:option>
                                             	 <c:forEach var="i" begin="1" end="${order.book.term}">
                                              		<form:option value="${i}" label="${i}"></form:option>
                                           		 </c:forEach>
                                             </form:select></td>
                                      </tr>
                                       <tr><td></td>
                                           <td><form:input path="book.title" type="hidden"/></td>
                                       </tr>
                                       <tr><td></td>
                                           <td><form:input path="book.id" type="hidden"/></td>
                                       </tr>
                                       <tr><td></td>
                                           <td><form:input path="person.id" type="hidden"/></td>
                                       </tr>
                                       <tr><td><input type="submit" class="btn" id="submitOrder" value=<spring:message code="message.order"/>></td></tr>
                                   </table>
                               </c:otherwise>
                           </c:choose>
                        </form:form>
                        <div id="orderSuccess" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body">Order created successfully</div>
                       </div>
                        <div id="inOrder" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body">${order.book.title} <spring:message code="ordered"/></div>
                        			  </div>
</div> 
          