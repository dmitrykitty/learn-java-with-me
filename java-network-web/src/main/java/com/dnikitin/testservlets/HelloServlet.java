package com.dnikitin.testservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/hello")
public class HelloServlet extends HttpServlet {

    // ObjectMapper odpowiada za mapowanie obiektów na JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // definicja danych (analogicznie do Map.of w kontrolerze)
        Map<String, String> data = new HashMap<>();
        data.put("msg", "Hello World!");

        // ręczne ustawienie nagłółwka Content-Type.
        // bez tego klient (np. przeglądarka) potraktuje to jako zwykły tekst.
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // serializacja: zamiana mapy na String w formacie JSON
        String jsonResponse = objectMapper.writeValueAsString(data);

        // wysłanie strumienia danych do klienta
        PrintWriter out = resp.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}