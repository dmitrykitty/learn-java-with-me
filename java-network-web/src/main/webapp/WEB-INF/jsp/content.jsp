<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03/01/2026
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="header.jsp" %>
    <div>
        <span>Content</span>
        <p>Size: ${requestScope.flights.size()}</p>
        <p>Id: ${requestScope.flights.get(0).id}</p>
        <p>Id: ${requestScope.flights[1].id}</p>
        <p>Map: ${sessionScope.flightMap[1]}</p>
        <p>Cookie: ${cookie["JSESSIONID"].value}, unique</p>
        <p>Header: ${header["Cookie"]}</p>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>
