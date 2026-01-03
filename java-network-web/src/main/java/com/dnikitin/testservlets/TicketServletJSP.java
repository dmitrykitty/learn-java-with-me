package com.dnikitin.testservlets;

import com.dnikitin.dto.request.TicketFilter;
import com.dnikitin.service.TicketService;
import com.dnikitin.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tickets-jsp")
public class TicketServletJSP extends HttpServlet {
    TicketService ticketService = TicketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long flightId = Long.parseLong(req.getParameter("flightId"));
        req.setAttribute("tickets", ticketService.findAll(
                new TicketFilter(20, 0, null, null, flightId)));
        req.setAttribute("flightId",  flightId);

        req.getRequestDispatcher(JspHelper.getPath("tickets-jsp.jsp")).forward(req, resp);
    }
}
