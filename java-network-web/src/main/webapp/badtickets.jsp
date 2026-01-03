<%@ page import="com.dnikitin.service.TicketService" %>
<%@ page import="com.dnikitin.dto.request.TicketFilter" %>
<%@ page import="com.dnikitin.dto.response.TicketDto" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03/01/2026
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%-- breake single responsibility pattern by introduction some servlet logic inside view layer --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/sql" %> <%-- to provide imports we use --%>
<%@include file="index.html" %> <%-- add any other files to our jsp page --%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List of tickets</h1>
<ul>
    <%
        long flightId = Long.parseLong(request.getParameter("flightId"));
        TicketService ticketService = TicketService.getInstance();
        List<TicketDto> all = ticketService.findAll(new TicketFilter(20, 0, null, null, flightId));
        for (TicketDto ticketDto : all) {
            out.write(String.format("<li> %s </li>", ticketDto.toString()));
        }
    %>

</ul>

</body>
</html>
