package com.dnikitin.servlet;

import com.dnikitin.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<h1>List of flights</h1>");
            writer.println("<ul>");
            flightService.findAll().forEach(flightDto -> {
                writer.println("""
                        <li>
                            <a href="/tickets?flightId=%d"> %s </a>
                        </li>
                        """.formatted(flightDto.id(), flightDto.description()));
            });

            writer.println("</ul>");

        }
    }
}

