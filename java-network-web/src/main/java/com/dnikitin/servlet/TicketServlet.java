package com.dnikitin.servlet;

import com.dnikitin.dto.request.TicketFilter;
import com.dnikitin.service.FlightService;
import com.dnikitin.service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flightId = Long.parseLong(req.getParameter("flightId"));

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<h1>List of tickets for flightID " +  flightId + "</h1>");
            writer.println("<ul>");
            ticketService.findAll(new TicketFilter(20, 0, null, null, flightId)).
                    forEach(ticketDto -> writer.println(
                            """
                            <li>
                            %s
                            </li>
                            """.formatted(ticketDto.toString())));
            writer.println("</ul>");
        }
    }
}
