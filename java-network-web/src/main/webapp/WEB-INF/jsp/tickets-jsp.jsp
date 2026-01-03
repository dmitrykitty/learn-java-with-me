<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03/01/2026
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${not empty requestScope.tickets}">
        <h1>List of tickets for ID ${requestScope.flightId}</h1>
        <ul>
            <c:forEach var="ticket" items="${requestScope.tickets}">
                <li>${fn:toLowerCase(ticket.seatNo)}</li>
            </c:forEach>
        </ul>
    </c:if>

</body>
</html>
