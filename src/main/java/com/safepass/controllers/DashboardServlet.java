package com.safepass.controllers;

import java.io.IOException;
import java.util.List;

import com.safepass.dao.PasswordEntryDAO;
import com.safepass.models.PasswordEntry;
import com.safepass.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private final PasswordEntryDAO passwordDAO = new PasswordEntryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("route?action=login");
            return;
        }

        List<PasswordEntry> passwords = passwordDAO.findByUser(user);
        request.setAttribute("passwords", passwords);

        request.getRequestDispatcher("views/dashboard.jsp").forward(request, response);
    }
}
