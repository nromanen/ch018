<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <td>Book Title</td>
                    <td>Issue Date</td>
                    <td>Return Date</td>
                    <td>term</td>
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
    </body>
</html>
