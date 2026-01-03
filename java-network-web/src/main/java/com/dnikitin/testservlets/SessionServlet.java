package com.dnikitin.testservlets;

import com.dnikitin.dto.response.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * <p>
 * <strong>Architecture: Relationship between HTTP Session and Cookies</strong>
 * </p>
 *
 * <p>
 * This Servlet demonstrates how the server maintains a stateful conversation (Session)
 * over the stateless HTTP protocol using Cookies as a transport mechanism for the Session ID.
 * </p>
 *
 * <h3>The Problem:</h3>
 * HTTP is stateless. The server does not inherently remember a user between two different requests.
 *
 * <h3>The Solution: The "Coat Check" Analogy</h3>
 * <ul>
 * <li><strong>The Session (The Coat):</strong> The actual data (User objects, shopping cart, etc.)
 * is stored in the <strong>Server's Memory (RAM)</strong> (or a database/Redis). This is heavy data.</li>
 *
 * <li><strong>The Cookie (The Ticket):</strong> A small, lightweight reference ID (e.g., <code>JSESSIONID=A1B2...</code>)
 * is stored in the <strong>Client's Browser</strong>. It contains no data, just a pointer.</li>
 * </ul>
 *
 * <h3>The Handshake Flow:</h3>
 * <ol>
 * <li><strong>Client:</strong> Sends first request (No cookie).</li>
 * <li><strong>Server:</strong> Creates a new <code>HttpSession</code> object in RAM.</li>
 * <li><strong>Server:</strong> Generates a unique String ID for this object (e.g., "555-XYZ").</li>
 * <li><strong>Server:</strong> Sends HTTP Response with header: <code>Set-Cookie: JSESSIONID=555-XYZ</code>.</li>
 * <li><strong>Client:</strong> Stores this ID in the browser.</li>
 * <li><strong>Client:</strong> Sends second request. Automatically includes header: <code>Cookie: JSESSIONID=555-XYZ</code>.</li>
 * <li><strong>Server:</strong> Reads the ID, looks up the specific <code>HttpSession</code> object in its memory map, and restores the state.</li>
 * </ol>
 */
@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {

    private static final String USER = "user";

    /**
     * Handles the GET request to demonstrate session creation and retrieval.
     *
     * @param req  the {@link HttpServletRequest} object that contains the request the client made of the servlet.
     * The server inspects this object for the <code>JSESSIONID</code> cookie.
     * @param resp the {@link HttpServletResponse} object that contains the response the servlet returns to the client.
     * If a new session is created, the server attaches the <code>Set-Cookie</code> header here.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
         * req.getSession():
         * Equivalent to req.getSession(true).
         * 1. Checks if the request contains a valid JSESSIONID cookie.
         * 2. Tries to find an active session in the server's memory map using that ID.
         * 3. If found -> returns the existing session.
         * 4. If NOT found (or no ID provided) -> creates a NEW session object in memory
         * and schedules a new JSESSIONID cookie to be sent in the response.
         */
        HttpSession session = req.getSession(); //set bool as true (create new session if not exist)
        //session is located on the server inside Map

        System.out.println(session.getId());
        System.out.println(session.getCreationTime());

        // Check if the session was just created in this request.
        // Returns true if the client does not yet know about the session
        // (i.e., the JSESSIONID cookie has not been sent back by the client yet).
        System.out.println(session.isNew());

        /*
         * Retrieving the ServletContext.
         * The session is scoped to a single user.
         * The ServletContext is scoped to the entire application (shared by all users).
         */
        // session.getServletContext();

        /*
         * session.invalidate():
         * Explicitly destroys the session object on the server.
         * Used for "Logout" functionality.
         * After this call, the JSESSIONID becomes invalid, and data is removed from RAM.
         */
        // session.invalidate();

        //=======================WORKING WITH ATRIBUTES=========================

        /*
         * Storing attributes:
         * Unlike Cookies (which store only Strings), HttpSession can store
         * ANY Java Object (String, User, List<Item>, etc.) inside an internal Map<String, Object>.
         */
        Object user = (UserDto) session.getAttribute(USER);
        if (user == null) {
            user = new UserDto(12, "test@gmail.com");

        }
        //key-value
        session.setAttribute(USER, user);


    }
}
