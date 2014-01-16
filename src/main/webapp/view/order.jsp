<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ page import="java.util.*" %>
			<!-- Center -->
<div class="span8">
<!-- TODO: use <fmt:formatDate instead />-->
        <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
      <!--  <input type="text" name="orderDate" value="<%= df.format(new java.util.Date()) %>"/> -->
        <br><br>
                       <form:form method="POST" modelAttribute="order">
                           <p>You may took this book for: ${term}</p>
                            <table>
                                <tr><td>Choose issue date:</td> 
                                    <td><form:input path="issueDate" class="datetimepicker"/></td></tr>
                                <tr><td></td>
                                    <td><form:input path="book.title"/></td>
                                </tr>
                                <tr><td></td>
                                    <td><form:input path="book.id" class="hidden"/></td>
                                </tr>
                                <tr><td></td>
                                    <td><form:input path="person.id" class="hidden"/></td>
                                </tr>
                                <tr><td><input type="submit" class="btn" value="Order"/></td></tr>
                             </table>
                        </form:form>
</div> 
          