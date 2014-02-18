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
                 <span id="dateExpired" class="hidden"><spring:message code="date.expired"/></span>
                       <form:form method="POST"   modelAttribute="order">
                           <p><span>${order.book.title}</span></p>
                           <c:choose>
                               <c:when test="${order.book.available==0}">
                                   <spring:message code="message.orderfail"/>
                                   <br> <spring:message code="message.orderafter"/> <fmt:formatDate pattern="dd.MM.yyyy" value="${date}"/> 
                               </c:when>
                               <c:when test="${order.book.available==1}">
                                   <c:if test="${orderDate!=null}"><spring:message code="message.order2"/> ${orderDate} </c:if> 
                                   <c:if test="${orderDate==null}"><spring:message code="message.order1"/> : ${term} <spring:message code="message.day"/></c:if>    
                                          
                                   <table>
                                       <tr><td><spring:message code="message.issuedate"/></td> 
                                           <td><form:input path="issueDate" class="datetimepicker" required="true" id="orderDate"/>
                                               <form:errors path="issueDate" cssClass="error"/>
                                           </td>
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
                                       <tr><td><input type="submit" class="btn" value="Order" id="submitOrder"/></td></tr>
                                   </table>
                               </c:when>
                               <c:otherwise>
                                   <spring:message code="message.order1"/> : ${term} <spring:message code="message.day"/>
                                   <table>
                                       <tr><td><spring:message code="message.issuedate"/></td> 
                                           <td><form:input path="issueDate" class="datetimepicker" required="true" id="orderDate"/>
                                                <form:errors path="issueDate" cssClass="error"/>
                                           </td>
                                      </tr>
                                      <tr>
                                      <td>Choose term:</td>
                                        <td><form:select path="term">
                                               <form:option value="0" label="--- Select ---"/>
                                               <form:option value="1" label="1 day"/>
                                               <form:option value="2" label="2 days"/>
                                               <form:option value="3" label="3 days"/>
                                               <form:option value="4" label="4 days"/>
                                               <form:option value="5" label="5 days"/>
                                               <form:option value="6" label="6 days"/>
                                               <form:option value="7" label="7 days"/>
                                               <form:option value="8" label="8 days"/>
                                               <form:option value="9" label="9 days"/>
                                               <form:option value="10" label="10 days"/>
                                               <form:option value="11" label="11 days"/>
                                               <form:option value="12" label="12 days"/>
                                               <form:option value="13" label="13 days"/>
                                               <form:option value="14" label="14 days"/>
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
                                       <tr><td><input type="submit" class="btn" value=<spring:message code="message.order"/>></td></tr>
                                   </table>
                               </c:otherwise>
                           </c:choose>
                  
                        </form:form>
</div> 
          