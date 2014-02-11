<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<!-- Center -->
<div class="span8">
<!-- <span id="dateExpired" class="hidden"><spring:message code="date.expired"/></span>  -->

<div id="dateExpired" class="modal hide fade">
  <div class="modal-header">Message</div>
  <div class="modal-body"><spring:message code="date.expired"/></div>
</div>
<input type="hidden" id="href" value="${pageContext.request.contextPath}/userOrder"/>
    <h3 class="text-info"><small><spring:message code="message.orders"/></small></h3>
                                <table class="table table-bordered">
                                    <thead>
						<tr class="info">
                                                        <td><spring:message code="book.title"/></td>
                                                        <td><spring:message code="book.order"/></td>
                                                        <td><spring:message code="person.issuedate"/></td>
						</tr>
					</thead>
					<c:forEach items="${showOrders}" var="order">
						<tr class="info">
							
							<td>${order.book.title}</td>
                            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${order.date}" /></td>
                            <td> <jsp:useBean id="date" class="java.util.Date"/>
                                 <fmt:formatDate pattern = "dd.MM.yyyy" value="${date}" var="currentDate"/>
                                 <fmt:formatDate pattern = "dd.MM.yyyy" value="${order.date}" var="orderDate"/>
                                 <c:choose>
                                   <c:when test="${currentDate==orderDate}">
                                       <form:form method="POST" modelAttribute="editIssue" id="editIssue${order.id}">
                                              <span id="oldIssue${order.id}"><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${order.issueDate}"/></span>
                                              <form:input path="id" type="hidden" value="${order.id}" id="orderId${order.id}"/>       
                                           <div class="controls">
                                               <form:input path="date" type="hidden" value="${order.date}"/>
                                               <form:input path="issueDate" class="datetimepicker" style="display:none" id="newIssue${order.id}"/>
                                               <form:errors path="issueDate" cssClass="error"/>
                                               <form:input path="person" type="hidden" value="${order.person.id}"/>
                                               <form:input path="book" type="hidden" value="${order.book.id}"/>
                                           </div>
                                            <br>                                  
                                            <input type="button" value=<spring:message code="button.edit"/> id="editIssueDate${order.id}" class="btn btn-info"/>
                                            <input type="hidden" value="${order.id}"/>
                                            <input type="submit" value=<spring:message code="button.save"/> class="btn btn-info" style="display:none" id="saveNewIssue${order.id}">
                                            <input type="button" value=<spring:message code="button.cancel"/> id="cancelIssueEdit${order.id}" class="btn btn-info" style="display:none"/>
                                            <input type="hidden" value="${order.id}"/>
                                            <a href="<c:url value="/deleteorder?id=${order.id}"/>"  class = "btn btn-danger"><spring:message code="button.delete"/></a>
                                            <br>
                                        </form:form>
                                         ${fail}
                                   </c:when>
                                   <c:otherwise>
                                    <fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${order.issueDate}" />
                                   </c:otherwise>
                                 </c:choose>
                            </td>
                                
						</tr>
					</c:forEach>
				</table>
                 <div id="editissue" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body">Issue date updated successfully</div>
                        			  </div>        
</div>
