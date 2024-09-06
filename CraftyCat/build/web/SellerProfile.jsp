<%@ page import="com.craftycat.normalClasses.User" %>
<%@ page import="com.craftycat.normalClasses.Seller" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="SellerProfile.css">
        <title>Seller Profile</title>
    </head>
    <body>
        
        <main class="profile-container">
            <h1>Welcome to Your Profile</h1>
            <% 
                    User user = (User) session.getAttribute("user");
                    if (user != null) { 
            %>
            <p>Role: <%= user.getUserRole() %></p><br><br>

            <!-- Display user information -->
            <div class="user-info">
                <label>Username:</label>
                <span id="username"><%= user.getUserName() %></span>

                <label>Email:</label>
                <span id="email"><%= user.getEmail() %></span>

                <label>First Name:</label>
                <span id="firstName"><%= user.getFirstName() %></span>

                <label>Last Name:</label>
                <span id="lastName"><%= user.getLastName() %></span>

                <label>Address:</label>
                <span id="address"><%= user.getAddress() %></span>

                <label>Phone:</label>
                <span id="phone"><%= user.getPhone() %></span>
            </div>



            <!-- Form for seller dashboard -->
            <form action="SellerDashboardRedirectServlet" method="post">
                <!-- Hidden input field to store user ID -->
                <input type="hidden" name="userID" value="<%= user.getUserID() %>">

                <button type="submit">Go to your Dashboard</button>
            </form>

            <% } else { %>
            <p>User information not available.</p>
            <% } %>
        </main>

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

