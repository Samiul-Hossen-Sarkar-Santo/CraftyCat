
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html>
    <head>
        <title>User Registration</title>
        <link rel="stylesheet" href="userRegistration.css" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div class="container">
            <h2>User Registration</h2>
            <form action="register" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>

                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" required>

                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" required>

                <label for="addressLine1">Address Line 1:</label>
                <input type="text" id="addressLine1" name="addressLine1" required>

                <label for="city">City:</label>
                <input type="text" id="city" name="city" required>

                <label for="state">State:</label>
                <input type="text" id="state" name="state" required>

                <label for="phone">Phone:</label>
                <input type="tel" id="phone" name="phone" required>
                
                <label for="text">Shop Name:</label>
                <input type="text" id="text" name="shopName" placeholder="Fill this up if you're a seller">
                <input type="hidden" name="role" value="<%= session.getAttribute("userRole") %>">

                <input type="submit" value="Register">
            </form>
        </div>
    </body>
</html>
