package com.dnikitin.testservlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. OBTAINING REQUEST DISPATCHER
        // The RequestDispatcher is an interface that acts as a wrapper for the resource located at the given path.
        // It is obtained from the ServletContext (or via the request wrapper) and allows for server-side inter-servlet communication.
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/flights");

        //========================= FORWARD =========================

        // Setting attributes to pass data (objects) to the destination servlet within the same Request Scope.
        // Unlike parameters (which are String-only and sent by client), attributes are server-side objects.
        req.setAttribute("user", "student");

        // The 'forward' method performs a server-side transfer of control.
        // 1. The browser is unaware of this change; the URL in the address bar remains "/dispatcher".
        // 2. The response buffer is cleared before the forward (if not already committed).
        // 3. The target resource (/flights) is now responsible for generating the entire response and closing the stream.
        // 4. Execution returns to this method only after /flights finishes, but we cannot modify the response body anymore.
        requestDispatcher.forward(req, resp);

        //========================= INCLUDE ============================
        // The 'include' method merges content from another resource into the current response.
        // 1. The control is temporarily passed to /flights to write to the output stream.
        // 2. IMPORTANT: The included resource (/flights) cannot change the HTTP status code or set headers.
        //    It can only write to the response body (OutputBuffer).
        // 3. Once /flights finishes, control returns here, and we can continue writing to the response.
        requestDispatcher.include(req, resp);

        //====================== SEND REDIRECT =======================
        // This triggers a Client-Side Redirection.
        // 1. The server sends a response with HTTP status 302 (Found) or 307 (Temporary Redirect).
        // 2. The response header 'Location' is set to "/flights".
        // 3. The connection is closed. The browser reads the code and automatically issues a COMPLETELY NEW request to the new URL.
        // 4. Since it's a new request, all request attributes (req.setAttribute) from the previous request are lost.
        // 5. The URL in the browser changes to "/flights".
        resp.sendRedirect("/flights");

    }
}
