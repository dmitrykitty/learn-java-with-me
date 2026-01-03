<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03/01/2026
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty requestScope.flights}">
    <h1>List of flights</h1>
    <ul>
        <c:forEach var="flight" items="${requestScope.flights}">
            <li>
                <a href="${pageContext.request.contextPath}/tickets-jsp?flightId=${flight.id}"> ${flight.description}</a>
            </li>
        </c:forEach>
    </ul>
</c:if>

</body>
</html>
