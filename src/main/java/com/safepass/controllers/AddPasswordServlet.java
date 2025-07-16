package com.safepass.controllers;

import java.io.IOException;

import com.safepass.dao.PasswordEntryDAO;
import com.safepass.models.PasswordEntry;
import com.safepass.models.User;
import com.safepass.utils.PasswordEncryptionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-password")
public class AddPasswordServlet extends HttpServlet {
    private final PasswordEntryDAO passwordDAO = new PasswordEntryDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    	HttpSession session = request.getSession(false);
    	if (session == null) {
    	    response.sendRedirect("route?action=login");
    	    return;
    	}

    	User user = (User) session.getAttribute("user");
    	if (user == null) {
    	    response.sendRedirect("route?action=login");
    	    return;
    	}

        String site = request.getParameter("site");
        String login = request.getParameter("login");
        String plainPassword = request.getParameter("password");

        try {
            String encryptedPassword = PasswordEncryptionUtil.encrypt(plainPassword);
            PasswordEntry entry = new PasswordEntry(site, login, encryptedPassword, user);

            passwordDAO.save(entry);
            response.sendRedirect("dashboard"); // recharge la page
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("dashboard?error=chiffrement");
        }
    }
}
