<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<div class="span8">
   <input type="hidden" id="hrefrate" value="${pageContext.request.contextPath}/delete"/>
   <input type="hidden" id="hrefOrder" value="${pageContext.request.contextPath}/prepareorder" />
   <input type="hidden" id="hrefNewOrder" value="${pageContext.request.contextPath}/order"/>
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
                                 <td>
                                     <input type="hidden" id="wishID" value="${wishByPers.id}">
                                     <input type="button" class="btn" id="deleteWish${wishByPers.id}" value="delete"/>
                                     <input type="hidden" value="${wishByPers.id}">
                                        <div id="modal${wishByPers.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body">${wishByPers.book.title} successfully deleted from wish list</div>
                                       </div>
                                  </td>
                                     
                                 <td><!--  <a href="<c:url 
                                            value="/order?book=${wishByPers.book.id}&wish=${wishByPers.id}"/>">
                                            <input type="submit" class="btn btn-success" 
                                            value="<spring:message code="button.order"/>"/>
                                      </a>-->
                                      <input type="hidden" value="${wishByPers.book.id}"/>
                                      <a id="orderNow${wishByPers.book.id}" class="btn btn-success">
                                            <spring:message code="button.order" />  
						              </a>
						              <div id="inOrder${wishByPers.book.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body">${wishByPers.book.title} already in your orders!</div>
                        			  </div>
                                      <div id="inUse${wishByPers.book.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body">${wishByPers.book.title} is now in your hands</div>
                                      </div>
                                      <div id="fail${wishByPers.book.id}" class="modal hide fade">
                                           <div class="modal-header">Message</div>
                                           <div class="modal-body"><spring:message code="message.failorder"/></div>
                                     </div>
                                 </td>
		               </tr>
	                  </c:forEach>
             </table>
             
             
             
   <!--           <a href="#modal" role="button" class="btn" data-toggle="modal">Click Me</a>
           
            <input type="button" class="btn" id="butt1" value="modal"/>  -->
             
        ${fail}
</div>
