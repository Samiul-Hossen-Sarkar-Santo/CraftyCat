<%@ page import="java.util.List" %>
<%@ page import="com.craftycat.normalClasses.User" %>
<%@ page import="com.craftycat.normalClasses.DataRetrieving" %>
<%@ page import="com.craftycat.normalClasses.Order" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Buyer Dashboard</title>
        <link rel="stylesheet" type="text/css" href="BuyerDashboard.css">
    </head>
    <body>
        <% User user = (User) session.getAttribute("user");
            List<Order> orders = DataRetrieving.getOrdersByUserID(user.getUserID());
        %>
        <div class="dashboard-container">
            <% if (user != null) {%>
            <h1>Welcome, <%= user.getUserName()%>!</h1>

            <!-- Order Management Section -->
            <div class="section order-management">
                <h2>Order Management</h2>
                <!-- Display orders -->
                <div class="order-list">
                    <% if (orders != null && !orders.isEmpty()) { %>
                    <h3>Orders</h3>
                    <table border="1">
                        <!-- Table header -->
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Date & Time</th>
                            <th>Status</th>
                        </tr>
                        <!-- Iterate over orders and display them in the table -->
                        <% for (Order order : orders) {%>
                        <tr>
                            <td><%= order.getProductName()%></td>
                            <td><%= order.getAddress()%></td>
                            <td><%= order.getFormattedOrderDate()%></td>
                            <td><%= order.getOrderStatus()%></td>
                        </tr>
                        <% } %>
                    </table>
                    <% } else { %>
                    <h3>You don't have any orders yet.</h3>
                    <% } %>
                </div>
            </div>

            <!-- Return to home Section -->
            <div class="section return-home">
                <!-- Display return home button -->
                <div class="return-home-button" style="text-align: center">
                    <a href="userDataRetrievement?username=<%= user.getUserName() %>" style="text-align: center"><button style="text-align: center">Return to you dash board</button></a>
                </div>
            </div>

        </div>

        <% } else { %>
        <h1>Welcome to Your Dashboard</h1>
        <p>User information not available.</p>
        <% }%>
    </body>
</html>
