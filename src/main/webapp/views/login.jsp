<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="css/login.css">
  <title>Connexion - SafePass</title>
</head>
<body>
  <div class="form-container">
    <h2>Connexion à SafePass</h2>
    <% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <p style="color: red; text-align: center;"><%= error %></p>
<% } %>
    <form action="login" method="post">
      <label>Email :</label><br>
      <input type="email" name="email" required><br><br>
      <label>Mot de passe :</label><br>
      <input type="password" name="password" required><br><br>
      <button type="submit">Se connecter</button>
    </form>
    <p>Tu n’as pas encore de compte ? <a href="route?action=register">Inscris-toi ici</a></p>
  </div>
</body>
</html>