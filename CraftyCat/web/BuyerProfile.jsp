<%@ page import="com.craftycat.normalClasses.User" %>
<%@ page import="com.craftycat.normalClasses.DataRetrieving" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="BuyerProfile.css">
        <title>User Profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" type="text/css" href="index.css" />
        <link
            rel="stylesheet"
            href="https://unpkg.com/boxicons@latest/css/boxicons.min.css"
            />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Leckerli+One&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>

        <header>
            <a href="buyerIndex.jsp" class="logo">
                <img src="crlogo-removebg-preview.png" />
            </a>
            <ul class="navbar">
                <li><a href="buyerIndex.jsp">Home</a></li>
                <li><a href="shop.html">Shop <i class="bx bx-caret-down"></i></a>
                    <ul class="dropdown">
                        <li><a href="#">Bead Jewelry</a></li>
                        <li>
                            <a href="#">Art</a>
                        </li>
                        <li><a href="#">Pottery</a></li>
                        <li><a href="#">DIY</a></li>
                    </ul>
                </li>

                <li><a href="#blog">About Us</a></li>
                <li><a href="index.html">Logout</a></li>
            </ul>
            <div class="h-icons">
                <a href="cart.html"><i class="bx bxs-shopping-bag"></i></a>
                <a href="userDataRetrievement?username=<%= session.getAttribute("userName")%>"><i class="bx bxs-user-circle"></i></a>
                <a href="#"><i class="bx bxs-heart-circle"></i></a>
                <div class="bx bx-menu" id="menu-icon"></div>
            </div>
        </header>
        <section>
            <main class="profile-container">
                <h1>Welcome to Your Dashboard</h1>

                <%
                    User user = (User) session.getAttribute("user");
                    if (user != null) {
                %>
                <p>Role: <%= user.getUserRole()%></p>

                <!-- Display user information -->
                <div class="user-info">
                    <label>Username:</label>
                    <span id="username"><%= user.getUserName()%></span>

                    <label>Email:</label>
                    <span id="email"><%= user.getEmail()%></span>

                    <label>First Name:</label>
                    <span id="firstName"><%= user.getFirstName()%></span>

                    <label>Last Name:</label>
                    <span id="lastName"><%= user.getLastName()%></span>

                    <label>Address:</label>
                    <span id="address"><%= user.getAddress()%></span>

                    <label>Phone:</label>
                    <span id="phone"><%= user.getPhone()%></span>
                </div>

                <% } else { %>
                <p>User information not available.</p>
                <% }%>

                <!-- Buttons to go to dashboard and edit information -->
                <div class="buttons" style="text-align: center; margin-top: 20px;">
                    <section id="dashboardButton">
                        <form action="BuyerDashboard.jsp" method="get">
                            <button type="submit">Go to Dashboard</button>
                        </form>
                    </section>
                    <section id="returnToHomeButton">
                        <form action="buyerIndex.jsp" method="get">
                            <button type="submit">Return to home</button>
                        </form>
                    </section>
                    <section id="editInfoButton" style="margin-left: 10px;">
                        <button id="editInfo">Edit Information</button>
                    </section>
                </div>



                <!-- Form for updating user information -->
                <div id="updateForm" style="display: none;">
                    <form action="UpdateUserInfoServlet" method="post" >
                        <!-- Input fields for updating user information -->
                        <label for="newUsername">New Username:</label>
                        <input type="text" id="newUsername" name="newUsername" placeholder="If you don't wish to change it, enter the previous one" required>

                        <label for="newEmail">New Email:</label>
                        <input type="email" id="newEmail" name="newEmail" placeholder="If you don't wish to change it, enter the previous one" required>

                        <label for="newFirstName">New First Name:</label>
                        <input type="text" id="newFirstName" name="newFirstName" placeholder="If you don't wish to change it, enter the previous one" required>

                        <label for="newLastName">New Last Name:</label>
                        <input type="text" id="newLastName" name="newLastName" placeholder="If you don't wish to change it, enter the previous one" required>

                        <label for="newAddress">New Address:</label>
                        <input type="text" id="newAddress" name="newAddress" placeholder="If you don't wish to change it, enter the previous one" required>

                        <label for="newPhone">New Phone:</label>
                        <input type="tel" id="newPhone" name="newPhone" placeholder="If you don't wish to change it, enter the previous one" required>

                        <!-- Password field for security -->
                        <label for="password">Enter Your Password:</label>
                        <input type="password" id="password" name="password">
                        <input type="hidden" id="userID" name="userID" value="<%= user.getUserID()%>">
                        <input type="hidden" id="name" name="name" value="<%= user.getUserName()%>">

                        <div style="text-align: center; margin-top: 10px;">
                            <button type="submit">Update Information</button>
                        </div>
                    </form>
                </div>



                <!-- Form for updating user information -->

            </main>
        </section>
        <!-- Add your footer content here -->
        <script src="index.js"></script>

        <section class="contact">
            <div class="contact-box">
                <h4>MY ACCOUNT</h4>
                <li><a href="#">My Account</a></li>
                <li><a href="#">CheckOut</a></li>
                <li><a href="#">Shopping Cart</a></li>
                <li><a href="#">Wish List</a></li>
                <li><a href="#">Contact Us</a></li>
            </div>

            <div class="contact-box">
                <h4>QUICK LINKS</h4>
                <li><a href="#">Store Location</a></li>
                <li><a href="#">Orders Tracking</a></li>
                <li><a href="#">Size Guide</a></li>
                <li><a href="#">My Account</a></li>
                <li><a href="#">FAQs</a></li>
            </div>
            <div class="contact-box">
                <h4>INFORMATION</h4>
                <li><a href="#">Privacy Page</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Careers</a></li>
                <li><a href="#">Delivery Information</a></li>
                <li><a href="#">Terms & Conditions</a></li>
            </div>
            <div class="contact-box">
                <h4>CUSTOMER SERVICE</h4>
                <li><a href="#">Shipping Policy</a></li>
                <li><a href="#">Help & Contact Us</a></li>
                <li><a href="#">Returns & Refunds</a></li>
                <li><a href="#">Online Stores</a></li>
                <li><a href="#">Terms & Conditions</a></li>
            </div>
            <div class="contact-box">
                <h4>Crafty Cat</h4>
                <h5>Connect With Us</h5>
                <div class="social">
                    <a href=""><i class="bx bxl-facebook-circle"></i></a>
                    <a href=""><i class="bx bxl-instagram"></i></a>
                    <a href=""><i class="bx bxl-linkedin"></i></a>
                </div>
            </div>
        </section>
        <!--scroll-->
        <a href="#" class="scroll-top"><i class="bx bx-chevrons-up"></i></a>
        <div class="end-text">
            <p>© late 2023 BICE 2022(37,79,85). All Rights Reserved.</p>
        </div>

    </body>
</html>
<script>
    document.getElementById("editInfo").addEventListener("click", function () {
        var updateForm = document.getElementById("updateForm");
        updateForm.style.display = updateForm.style.display === "none" ? "block" : "none";
    });
</script>