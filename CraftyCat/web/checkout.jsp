<%@ page import="com.craftycat.normalClasses.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cart</title>
        <link rel="stylesheet" type="text/css" href="checkout.css">
    </head>
    <body>
        <div class="small-container cart-page">
            <h1 style="text-align: center">Checkout Here</h1>
            <table>
                <tr>
                    <th>Product</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                </tr>
                <% List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems"); %>
                <% if (cartItems != null && !cartItems.isEmpty()) { %>
                <% for (CartItem item : cartItems) {%>
                <tr>
                    <td>
                        <!-- Display product details from CartItem -->
                        <div class="cart-info" style="text-align: right">
                            <img src="images/<%= item.getImagePath()%>">
                            <div >
                                <p><%= item.getProductName()%></p>
                                <small>Price: $<%= item.getPrice()%></small>
                                <br><br>
                            </div>
                        </div>
                        <div style="text-align: right"><a href="#" class="remove-button">Remove</a></div>
                    </td>
                    <td style="text-align: center"><%= item.getQuantity()%></td>
                    <td style="text-align: center">$<%= item.getPrice() * item.getQuantity()%></td>
                </tr>
                <% } %>
                <% } else { %>
                <tr>
                    <td colspan="3">Your cart is empty.</td>
                </tr>
                <% }%>
            </table>
            <div class="total-price">
                <table>
                    <tr>
                        <td>Total</td>
                        <td>$<%= request.getAttribute("totalPrice")%></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center">
                            <form action="CartServlet" method="post">
                                <input type="hidden" name="action" value="checkout">
                                <input type="hidden" name="userID" value="">
                                <input type="hidden" name="totalPrice" value="<%= request.getAttribute("totalPrice")%>"> 
                                <button type="submit" class="checkout-button">Checkout here</button>
                            </form>
                        </td>
                    </tr>
                    <!-- Add tax and total rows as needed -->
                </table>
            </div>
        </div>
        <div style="text-align: center">
            <a href="index.html" style="text-align: center">
                <button class="checkout-button" >Return to home</button>
            </a>
        </div>
    </body>
</html>
