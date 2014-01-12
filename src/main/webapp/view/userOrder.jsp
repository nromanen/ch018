<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
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
                                                        <td>${order.date}</td>
                                                        <td>${order.issueDate}</td>


						</tr>
					</c:forEach>
				</table>
                       
</div>
