package com.dnikitin.testservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * What is an HTTP Cookie?
 * An HTTP Cookie (web cookie, browser cookie) is a small piece of data that a server sends to the user's web browser.
 * The browser may store it and send it back with later requests to the same server.
 * Since HTTP is a stateless protocol (the server does not remember previous requests by default),
 * cookies are used to implement stateful sessions.
 * Key characteristics:
 *  1. Storage: Stored on the client-side (browser).
 *  2. Transport: Sent via HTTP Headers (Set-Cookie in response, Cookie in request).
 *  3. Capacity: Limited size (usually around 4KB per cookie).
 *  4. Usage: Session management (logins, shopping carts), personalization, and user tracking.
 <p>
 * Servlet responsible for tracking unique visitors using HTTP Cookies.
 * It maintains a global counter that increments only when a client
 * without a specific cookie ("userId") visits the page.
 */
@WebServlet("/cookies")
public class CookiesServlet extends HttpServlet {

    // The key (name) of the cookie we will look for.
    private static final String UNIQUE_ID = "userId";

    /**
     * Thread-safe counter.
     * Servlets are Singletons (one instance handles multiple threads/requests).
     * Using a primitive 'int' would lead to Race Conditions (concurrency issues).
     * AtomicInteger guarantees that increment operations are atomic.
     */
    private static final AtomicInteger counter = new AtomicInteger(0);

    /**
     * Handles HTTP GET requests.
     * Checks if the user is "new" (no cookie) or "returning" (has cookie).
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve all cookies sent by the browser in the HTTP request header.
        // Returns null if no cookies are present.
        Cookie[] cookies = req.getCookies();

        /*
         * logic: Check if the user is visiting for the first time.
         * The user is considered "new" if:
         * 1. The cookies array is null (browser has no cookies for this domain).
         * OR
         * 2. The stream filter cannot find a cookie with the name UNIQUE_ID.
         */
        if (cookies == null || Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(UNIQUE_ID))
                .findFirst()
                .isEmpty()) {

            // Create a new Cookie object (Key: "userId", Value: "1").
            // In a real app, the value would be a UUID or a SessionID.
            Cookie cookie = new Cookie(UNIQUE_ID, "1");

            // Path attribute: The cookie is only sent for requests to this path and its subdirectories.
            // If not set, it defaults to the context root of the app.
            cookie.setPath("/cookies");

            // Max-Age attribute: Sets the lifespan of the cookie in seconds.
            // 15 * 60 = 900 seconds (15 minutes).
            // If set to -1 (default), it becomes a "Session Cookie" (deleted when browser closes).
            // If set to 0, the cookie is deleted immediately.
            cookie.setMaxAge(15 * 60);

            /*
             * Security flags (Best Practices):
             * setHttpOnly(true): Mitigates XSS (Cross-Site Scripting) attacks.
             * Prevents JavaScript (document.cookie) from accessing this cookie.
             */
            //cookie.setHttpOnly(true);

            /*
             * setSecure(true): The cookie will ONLY be sent over encrypted (HTTPS) connections.
             * Prevents cookie theft via Man-in-the-Middle attacks.
             */
            //cookie.setSecure(true);

            // Add the "Set-Cookie" header to the HTTP response.
            // This instructs the browser to save the cookie.
            resp.addCookie(cookie);

            // Increment the unique visitor counter safely.
            counter.incrementAndGet();
        }

        // Set the MIME type and character encoding for the response body.
        resp.setContentType("text/html;charset=UTF-8");

        // Write the current value of the counter to the response body.
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(counter.get() + "\n");
        }
    }
}