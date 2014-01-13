<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ include file="/view/includes.jsp" %>
<%@ page import="java.util.*" %>
			<!-- Center -->
<div class="span8">
<!-- TODO: use <fmt:formatDate instead -->
        <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
      <!--  <input type="text" name="orderDate" value="<%= df.format(new java.util.Date()) %>"/> -->
        <br><br>
                       <form:form method="POST" modelAttribute="order">
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
          