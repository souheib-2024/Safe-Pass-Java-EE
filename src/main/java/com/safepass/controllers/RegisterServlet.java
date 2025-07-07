package com.safepass.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.safepass.models.User;
import com.safepass.dao.UserDAO;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String plainPassword = request.getParameter("password");

        // Hash du mot de passe avec BCrypt
        String hashedPassword = BCrypt.withDefaults().hashToString(12, plainPassword.toCharArray());

        // Créer l'objet User
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPasswordHash(hashedPassword);

        // Enregistrement en base via DAO
        UserDAO userDAO = new UserDAO();
        userDAO.save(newUser); // on code cette méthode juste après 🔧

        // Redirection après succès
        response.sendRedirect("views/login.jsp");
    }
}