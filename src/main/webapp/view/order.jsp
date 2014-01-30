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
                       <form:form method="POST"  id="order" modelAttribute="order">
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
                                           <form:input path="issueDate" class="datetimepicker" type="date" required="true"/></td></tr>
                                       <tr><td></td>
                                           <td><form:input path="book.title" type="hidden"/></td>
                                       </tr>
                                       <tr><td></td>
                                           <td><form:input path="book.id" type="hidden"/></td>
                                       </tr>
                                       <tr><td></td>
                                           <td><form:input path="person.id" type="hidden"/></td>
                                       </tr>
                                       <tr><td><input type="submit" class="btn" value="Order"/></td></tr>
                                   </table>
                               </c:when>
                               <c:otherwise>
                                   <spring:message code="message.order1"/> : ${term} <spring:message code="message.day"/>
                                   <table>
                                       <tr><td><spring:message code="message.issuedate"/></td> 
                                           <td><form:input path="issueDate" class="datetimepicker" type="date" required="true"/></td></tr>
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
          