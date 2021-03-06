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
                        	     <td>
                        	      <a id="showAboutBook${wishByPers.book.id}" rel="popover">
                        	             ${wishByPers.book.title}</a>
                        	      <input type="hidden" value="${wishByPers.id}"/>
                        	     </td>
                                 <td>${wishByPers.book.genre}</td>
			                     <td>${wishByPers.book.authors}</td>
                                 <td>${wishByPers.book.year}</td>
                                 <td>
                                     <input type="hidden" id="wishID" value="${wishByPers.id}">
                                     <input type="button" class="btn btn-danger" id="deleteWish${wishByPers.id}" value="<spring:message code="button.delete"/>"/>
                                     <input type="hidden" value="${wishByPers.id}">
                                        <div id="modal${wishByPers.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body">${wishByPers.book.title} <spring:message code="delete.wish"/></div>
                                       </div>
                                  </td>
                                     
                                 <td> <input type="hidden" value="${wishByPers.book.id}"/>
                                      <a id="orderNow${wishByPers.book.id}" class="btn btn-success">
                                            <spring:message code="button.order" />  
						              </a>
						              <div id="inOrder${wishByPers.book.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body">${wishByPers.book.title} <spring:message code="ordered"/></div>
                        			  </div>
                                      <div id="inUse${wishByPers.book.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body">${wishByPers.book.title} <spring:message code="inuse"/></div>
                                      </div>
                                      <div id="fail${wishByPers.book.id}" class="modal hide fade">
                                           <div class="modal-header"><spring:message code="message.message"/></div>
                                           <div class="modal-body"><spring:message code="message.failorder"/></div>
                                     </div>
                                     
                                     <div id="aboutBook${wishByPers.id}" style="display: none" class="thumbnail span3">
                                         <div class="media">
                                         <img src="<c:url value="${wishByPers.book.image}"/>" 
                                          class="img-custom pull-left">
                                            <ul class="thumbnails span6 pull-left">
                                             <li><span class="label label-info">Title: </span><br><small>${wishByPers.book.title}</small></li>
                                             <li><span class="label label-info">Publisher: </span><br><small>${wishByPers.book.publication}</small></li>
                                            </ul>
                                             </div>
                                          <div class="caption pull-left">
                                            <c:choose>
                    							<c:when test="${wishByPers.book.available==0}"><span class="label label-important"><spring:message code="book.notavailable" />
                    							</span>
                    							<br>
                    							<br></c:when>
                        						<c:otherwise><span class="label label-success"><spring:message code="book.available" />
                    							</span>
                    							<br>
                    							<br>
                    							</c:otherwise>
                              				</c:choose>
                                          </div>
                                     </div>
                                 </td>
		               </tr>
	                  </c:forEach>
             </table>
  </div>
