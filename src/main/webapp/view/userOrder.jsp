<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
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
