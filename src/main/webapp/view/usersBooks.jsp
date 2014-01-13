<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp"%>
			<!-- Center -->
				<div class="span8">
 <h3 class="text-info"><small><spring:message code="menu.mybooks" /></small></h3>
                                    <table class="table table-bordered">
            <thead>
                <tr>
                    <td><spring:message code="book.title"/></td>
                    <td><spring:message code="person.issuedate"/></td>
                    <td><spring:message code="person.returndate"/></td>
                    <td><spring:message code="message.term">/td>
                </tr>
            </thead>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.book.title}</td>
                    <td>${book.issueDate}</td>
                    <td>${book.returnDate}</td>
                    <td>${book.term}</td>
                </tr>
            </c:forEach>
        </table>
</div>
			