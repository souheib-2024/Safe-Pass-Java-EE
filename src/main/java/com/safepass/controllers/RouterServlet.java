package com.safepass.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/route")
public class RouterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("register".equals(action)) {
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        } else if ("login".equals(action)) {
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        } else if ("dashboard".equals(action)) {
            request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("views/404.jsp");
        }
    }
}
