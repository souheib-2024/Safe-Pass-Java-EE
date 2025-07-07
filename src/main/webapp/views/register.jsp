<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/register.css">
    <title>Inscription - SafePass</title>
</head>
<body>
    <div class="form-container">
        <h2>Créer un compte SafePass</h2>

        <form action="register" method="post">
            <label>Nom d'utilisateur :</label><br>
            <input type="text" name="username" required><br><br>

            <label>Email :</label><br>
            <input type="email" name="email" required><br><br>

            <label>Mot de passe :</label><br>
            <input type="password" name="password" required><br><br>

            <button type="submit">S'inscrire</button>
        </form>

        <p>Tu as déjà un compte ? <a href="route?action=login">Connecte-toi ici</a></p>
    </div>
</body>
</html>