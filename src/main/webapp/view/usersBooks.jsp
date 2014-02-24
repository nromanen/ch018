<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<!-- Center -->
				<div class="span8">
 <h3 class="text-info"><small><spring:message code="menu.mybooks" /></small></h3>
                                    <table class="table table-bordered">
            <thead>
                <tr>
                    <td><spring:message code="book.title"/></td>
                    <td><spring:message code="person.issuedate"/></td>
                    <td><spring:message code="person.returndate"/></td>
                    <td><spring:message code="message.rate" /></td>
                </tr>
            </thead>
            <c:forEach items="${books}" var="book">
                <tr class="info">
                    <td><a href="<c:url value="/rate?bookID=${book.book.id}&buID=${book.buid}"/>">${book.book.title}</a></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${book.issueDate}" /></td>
                    <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${book.returnDate}" /></td>
                    <td><c:if test="${book.mark >0}">${book.mark}</c:if>
                             <c:if test="${book.mark == 0}">
                                 <a href="<c:url value="/rate?bookID=${book.book.id}&buID=${book.buid}"/>">
                                 <spring:message code="rate"/>
                                 </a>
                              </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
</div>
			