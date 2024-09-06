<%@page import="com.craftycat.normalClasses.DataRetrieving"%>
<%@page import="com.craftycat.normalClasses.User"%>
<%@ page import="com.craftycat.normalClasses.Product" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Product List</title>
        <link rel="stylesheet" type="text/css" href="productList.css">
    </head>
    <body>

        <h1><b>Product List</b></h1>

        <%
            String userID = (String) request.getAttribute("userID");
            User user = DataRetrieving.getUserByUserID(Integer.parseInt(userID));
            List<Product> productList = (List<Product>) request.getAttribute("productList");
            if (productList != null) {
                for (Product product : productList) {
        %>
        <div class="product-container">
            <img class="product-image" src="images/<%= product.getImagePath()%>" alt="<%= product.getProductName()%>">
            <p><%= product.getProductName()%></p>
            <p>Tk. <%= product.getProductPrice()%></p>
            <!-- Add more product details as needed -->

            <!-- "Add to Cart" button -->
            <button class="add-to-cart-button" onclick="addToCart(<%= product.getProductID()%>)">Add to Cart</button>
        </div>
        <%
            }
        } else {
        %>
        <p>No products available.</p>
        <%
            }
        %>

        <div class="section communication">

            <!-- Display communication features -->
            <div class="communication-features" style="text-align: center">
                <br><br><br>
                <a href="index.html"><button class="add-to-cart-button">Return to home</button></a>
                <form action="CartServlet" method="post">
                    <input type="hidden" name="user" value="<%= user%>">
                    <input type="hidden" name="action" value="getAll">
                    <button type="submit" class="add-to-cart-button">Open Cart</button>
                </form>
            </div>
        </div>

        <script src="productList.js"></script>
    </body>
</html>
<script>
    function addToCart(productId) {
    // Assuming you have a variable to store the quantity, adjust as needed
    var quantity = 1;

    // Make an AJAX request to CartServlet to add the product to the cart
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "CartServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Handle the response here if needed
            alert("Product added to cart");
        }
    };

    // Prepare data to be sent in the request body
    var data = "action=add&productID=" + productId + "&quantity=" + quantity;

    // Send the request
    xhr.send(data);
}
</script>