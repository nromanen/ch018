<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/view/includes.jsp"%>
<div class="span8">
    <h3 class="text-info"><small><spring:message code="menu.wish" /></small></h3>
 
            <table class="table table-bordered">
                     <thead>
                    	<tr>
                            <td><spring:message code="book.title"/></td>
                            <td><spring:message code="book.genre"/></td>
                            <td><spring:message code="book.authors"/></td>
                            <td><spring:message code="book.year"/></td>
                        </tr>
                     </thead>
                         <c:forEach items="${wishByPers}" var="wishByPers">
                               <tr class="info">
                        	<td>${wishByPers.book.title}</td>
                                 <td>${wishByPers.book.genre}</td>
			         <td>${wishByPers.book.authors}</td>
                                 <td>${wishByPers.book.year}</td>
                                 <td><a href="<c:url value="/delete?del=${wishByPers.id}"/>" class="btn btn-warning"><spring:message code="button.delete"/></a></td>
                                 <td><a href="<c:url value="/order?book=${wishByPers.book.id}&wish=${wishByPers.id}"/>"><input type="submit" class="btn btn-success" value="<spring:message code="button.order"/>"/></a></td>
		               </tr>
	                  </c:forEach>
             </table>
        ${fail}
</div>
