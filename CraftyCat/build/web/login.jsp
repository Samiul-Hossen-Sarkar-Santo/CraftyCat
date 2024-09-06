
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="login.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

</head>
<body>
    
    <div class="background"></div>
    <div class="cont">
        <div class="item">
            <h1 class="logo"><span class="material-symbols-outlined">brush</span>Crafty Cat</h1>
            <div class="text-item">
                <h3>Welcome To <br></h3><br>
                <h2><span>Crafty Cat</span></h2><br><br>
                <p>Please Login here. If you don't have an account, click on "Sign Up"</p>
                
            </div>
        </div>
        <div class="login-section">
            <div class="form-box login">
                <form action="login" method="post">
                    <h2>Sign In</h2>
                    <div class="input-box">
                        <span class="icon"><i class='bx bx-user'></i></span>
                        <input type="text" id="name" name="name" required>
                        <label for="name">User Name</label>
                    </div>
                    <div class="input-box">
                        <span class="icon"><i class='bx bx-lock-alt' ></i></span>
                        <input type="password" id="password" name="password" required>
                        <label for="password">Password</label>
                    </div>
                    <input type="hidden" name="role" value="<%= session.getAttribute("userRole") %>">
                    
                    <input type="submit" value="Login">
                    <div class="create-account">
                        <p>Create a new account? <a href="userRegistration.jsp" class="register-link">Sign Up</a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
   

</body>
</html>