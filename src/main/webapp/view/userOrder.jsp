<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Center -->
<div class="span8">

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
						<tr>
							
							<td>${order.book.title}</td>
                            <td><fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${order.date}" /></td>
                            <td>
                                <input type="text" id="oldIssue${order.id}" 
                                 value="<fmt:formatDate pattern="dd.MM.yyyy hh:mm" value="${order.issueDate}"/>"/>
                                 
                                <input type="text" class="datetimepicker" style="display:none" id="newIssue${order.id}" name=newIssueDate/>
                                
                                <input type="button" value="Edit" id="editIssueDate${order.id}" class="btn"/>
                                
                                <input type="hidden" value="${order.id}"/>
                                
                                <a href='<c:url value="/editIssue?id=${order.id}&date=${newIssueDate}"/>' 
                                        class="btn" style="display:none" id="saveNewIssue${order.id}">Save</a>
                                        
                                <input type="button" value="Cancel" id="cancelIssueEdit${order.id}" class="btn" style="display:none"/>
                                </td>
                                
						</tr>
					</c:forEach>
				</table>
                       
</div>
