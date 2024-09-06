<%@ page import="java.util.List" %>
<%@ page import="com.craftycat.normalClasses.User" %>
<%@ page import="com.craftycat.normalClasses.DataRetrieving" %>
<%@ page import="com.craftycat.normalClasses.Seller" %>
<%@ page import="com.craftycat.normalClasses.Product" %>
<%@ page import="com.craftycat.normalClasses.Order" %>
<%@ page import="com.craftycat.normalClasses.OrderStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Seller Dashboard</title>
        <link rel="stylesheet" type="text/css" href="sellerDashboard.css">
    </head>
    <body>
        <% User user = (User) session.getAttribute("user");
            Seller seller = (Seller) session.getAttribute("seller");
            List<Product> products = DataRetrieving.getAllProducts(seller.getSellerID());
            List<Order> orders = DataRetrieving.getOrdersBySellerID(seller.getSellerID());
        %>
        <div class="dashboard-container">
            <% if (user != null && seller != null) {%>
            <h1>Welcome, <%= user.getUserName()%>!</h1>

            <!-- Product Management Section -->
            <div class="section product-management">
                <h2>Product Management</h2>
                <!-- Add product form -->
                <div style="text-align: center;">
                    <a href="ProductServlet?sellerID=<%= seller.getSellerID()%>">
                        <button type="submit">Go to your Product Management Page</button>
                    </a><br><br><br>
                </div>


                <!-- Display existing products -->
                <div class="product-list">
                    <% if (products != null && !products.isEmpty()) {%>
                    <h3>Your Products</h3>
                    <table border="1">
                        <!-- Table header -->
                        <tr>
                            <th>Name</th>
                            <th>Stock Quantity</th>
                        </tr>
                        <!-- Iterate over products and display them in the table -->
                        <% for (Product product : products) {%>
                        <tr>
                            <td><%= product.getProductName()%></td>
                            <td><%= product.getStockQuantity()%></td>
                        </tr>
                        <% } %>
                    </table>
                    <% } else {%>
                    <h3>You don't have any product added to the inventory yet.</h3>
                    <a href="ProductServlet?sellerID=<%= seller.getSellerID()%>"><button type="submit">Click here to add products</button></a>
                    <% } %>
                </div>
            </div>

            <!-- Display orders -->
            <div class="order-list">
                <% if (orders != null && !orders.isEmpty()) { %>
                <h3>Orders</h3>
                <form action="UpdateOrderStatusServlet" method="post">
                    <table border="1">
                        <!-- Table header -->
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Date & Time</th>
                            <th>Status</th>
                            <th>Action</th> <!-- New column for updating status -->
                        </tr>
                        <!-- Iterate over products and display them in the table -->
                        <% for (Order order : orders) {%>
                        <tr>
                            <td><%= order.getProductName()%></td>
                            <td><%= order.getAddress()%></td>
                            <td><%= order.getFormattedOrderDate()%></td>
                            <td><%= order.getOrderStatus()%></td>
                            <td>
                                <!-- Dropdown to update order status -->
                                <select name="orderStatus" id="orderStatus">
                                    <option value="<%= order.getOrderStatus().name()%>" selected>
                                        <%= order.getOrderStatus().name()%>
                                    </option>
                                    <% for (OrderStatus status : OrderStatus.values()) {
                                            if (!order.getOrderStatus().equals(status)) {%>
                                    <option value="<%= status.name()%>"><%= status.name()%></option>
                                    <% }
                                        }%>
                                </select>

                                <!-- Hidden input to pass order ID to the servlet -->
                                <input type="hidden" name="orderID" value="<%= order.getOrderID()%>">
                                <input type="hidden" name="userID" value="<%= user.getUserID()%>">

                                <!-- Submit button to update the order status -->
                                <input type="submit" value="Update Status">
                            </td>
                        </tr>
                        <% } %>
                    </table>
                </form>
                <% } else { %>
                <h3>You don't have any orders yet.</h3>
                <% } %>
            </div>

        </div>


        <!-- Communication Section -->
        <div class="section communication">
            <h2>Ratings & Reviews</h2>
            <!-- Display communication features -->
            <div class="communication-features">
                <h5>Reviews from buyers and comment on you Service</h5>
            </div>
        </div>

        <!-- Return to home Section -->
        <div class="section communication">

            <!-- Display communication features -->
            <div class="communication-features">
                <a href="sellerIndex.jsp"><button>Return to home</button></a>
            </div>
        </div>
        <% } else { %>
        <h1>Welcome to Your Dashboard</h1>
        <p>User information not available.</p>
        <% }%>
    </div>
</body>
</html>
