<%@ page import="com.craftycat.normalClasses.Product" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
            }

            #notification {
                display: none;
                background-color: #4CAF50;
                color: white;
                padding: 10px;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>

        <%
            // In a real application, you would get the product details from the database based on the productID.
            // For now, I'll assume you have a Product object named "product" available in the request.
            Product product = new Product();
            product.setProductID(1);
            product.setName("Product A");
            product.setDescription("This is a sample product description.");
            product.setPrice(19.99);
        %>

        <h1><%= product.getProductName() %></h1>
        <p>Description: <%= product.getDescription() %></p>
        <p>Price: $<%= product.getProductPrice() %></p>

        <form id="addToCartForm" action="AddToCartServlet" method="post">
            <input type="hidden" name="productID" value="<%= product.getProductID() %>">
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" value="1" required>
            <button type="submit" onclick="showNotification()">Add to Cart</button>
        </form>

        <div id="notification">
            Product added to cart!
            <a href="ViewCartServlet"><button style="margin-left: 10px;">View Cart</button></a>
        </div>

        <script>
            function showNotification() {
                // Show the notification div
                document.getElementById('notification').style.display = 'block';

                // Wait for 3 seconds and then hide the notification
                setTimeout(function () {
                    document.getElementById('notification').style.display = 'none';
                }, 3000);
            }
        </script>

    </body>
</html>
