package com.dnikitin.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    //============DO GET==============
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getHeader("user-agent"); //return http header with info about user
        //Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36

        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + req.getHeader(headerName));
        }

        //===============WORKING WITH PARAMETRES=================
        //http://localhost:8081/first?param=25&id=1&id=4
        String param = req.getParameter("param"); //to get param
        req.getParameterMap()
                .forEach((key, value) -> System.out.println(key + ": " + Arrays.toString(value)));

        //===================SETTING RESPONSE===================
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8"); //dobra praktyka zeby poprawnie odczytywano rowne litery
        resp.setHeader("token", "31415");
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<h1>Hello from first servlet</h1>");
        }
    }

    //===============DO POST===============
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if we use special header x-www-form-urlencoded in postman -> params inside body
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((key, values) -> System.out.println(key + ": " + Arrays.toString(values)));
        System.out.println();

        //================READING FROM POST REQUEST======================
        try (BufferedReader reader = req.getReader();
             Stream<String> lines = reader.lines()) {
            //for binary format - getInputStream()
            lines.forEach(System.out::println);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
