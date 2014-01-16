<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp" %>
<%@ page import="java.util.*" %>
			<!-- Center -->
<div class="span8">
                 <br><br>
                       <form:form method="POST" modelAttribute="order">
                           <p><span>${order.book.title}</span></p>
                           <c:choose>
                               <c:when test="${order.book.available==0}">
                                   Sorry, but you can't order this book right now.
                                   <br> This book will be available after ${date}
                               </c:when>
                               <c:when test="${order.book.available==1}">
                                   <c:if test="${orderDate!=null}">You may order this book, but you must return it till ${orderDate} </c:if> 
                                   <c:if test="${orderDate==null}">You may took this book for : ${term} days</c:if>    
                                          
                                   <table>
                                       <tr><td>Choose issue date:</td> 
                                           <td><form:input path="issueDate" class="datetimepicker"/></td></tr>
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
                                   You may took this book for : ${term} days
                                   <table>
                                       <tr><td>Choose issue date:</td> 
                                           <td><form:input path="issueDate" class="datetimepicker"/></td></tr>
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
                               </c:otherwise>
                           </c:choose>
                  
                        </form:form>
</div> 
          