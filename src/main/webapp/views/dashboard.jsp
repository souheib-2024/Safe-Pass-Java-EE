<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.safepass.models.User" %>
<%@ page import="com.safepass.models.PasswordEntry" %>
<%@ page import="java.util.List" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("route?action=login");
        return;
    }
    List<PasswordEntry> passwords = (List<PasswordEntry>) request.getAttribute("passwords");
%>

<html>
<head>
    <title>Coffre-fort - SafePass</title>
    <link rel="stylesheet" href="css/dashboard.css">
</head>
<body>
<div class="dashboard-container">
    <h2>🔐 Bienvenue, <%= user.getUsername() %></h2>

    <!-- ✅ Formulaire d’ajout -->
    <form method="post" action="add-password">
        <h3>➕ Enregistrer un mot de passe</h3>
        <label for="site">Nom du site :</label><br>
        <input type="text" name="site" required><br>

        <label >Identifiant / Email :</label><br>
        <input type="text" name="login" required><br>

        <label>Mot de passe :</label><br>
        <input type="text" name="password" required><br><br>

        <button type="submit">Enregistrer 🔒</button>
    </form>

    <hr>

    <!-- 📋 Tableau des entrées -->
    <h3>📄 Mots de passe enregistrés</h3>
    <table>
        <thead>
            <tr>
                <th>Site</th>
                <th>Identifiant</th>
                <th>🔒 Mot de passe</th>
            </tr>
        </thead>
        <tbody>
        <%
            if (passwords != null && !passwords.isEmpty()) {
                for (PasswordEntry entry : passwords) {
        %>
            <tr>
                <td><%= entry.getSite() %></td>
                <td><%= entry.getLogin() %></td>
                <td>●●●●●●●●</td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="3">Aucune entrée enregistrée pour le moment.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>