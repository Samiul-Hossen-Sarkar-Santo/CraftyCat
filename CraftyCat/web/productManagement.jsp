<%@ page import="com.craftycat.normalClasses.Product" %>
<%@ page import="com.craftycat.normalClasses.User" %>
<%@ page import="com.craftycat.normalClasses.Seller" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product Management</title>
        <link rel="stylesheet" href="productManagement.css">
    </head>
    <body>

        <h1>Product Management</h1><br>

        <!-- Add Product Section -->
        <section id="addSection">
            <h2>Add Product</h2><br>

            <form action="AddProductServlet" method="post" enctype="multipart/form-data">

                <input type="hidden" name="action" value="add">

                <label for="name">Product Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>

                <label for="price">Price:</label>
                <input type="number" id="price" name="price" step="0.01" required>

                <label for="stockQuantity">Stock Quantity:</label>
                <input type="number" id="stockQuantity" name="stockQuantity" required>

                <label for="categoryID">Category ID:</label>
                <input type="number" id="categoryID" name="categoryID" required>

                <label for="image">Product Image:</label>
                <input type="file" id="image" name="image" accept="image/*" required>
                
                <input type="hidden" name="sellerID" value="<%= request.getAttribute("sellerID")%>">

                <button type="submit">Add Product</button>
            </form>
        </section>



        <!-- Search Product Section -->
        <section id="searchSection">
            <h2>Search Product</h2><br>

            <form action="ProductServlet" method="post">
                <input type="hidden" name="action" value="search">
                <input type="hidden" name="sellerID" value="<%= request.getAttribute("sellerID")%>">
                <label for="productName">Product Name:</label>
                <input type="text" id="productName" name="productName" required>

                <button type="submit">Search Product</button>
            </form>
        </section>

        <!-- Display Product Section -->
        <% 
            Product searchedProduct = (Product) request.getAttribute("searchedProduct");

            if (searchedProduct != null) {
        %>
        <section id="displaySection">
            <h2>Product Details - <%= searchedProduct.getProductName() %></h2><br>

            <form action="ProductServlet" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="productID" value="<%= searchedProduct.getProductID() %>">
                <input type="hidden" name="sellerID" value="<%= request.getAttribute("sellerID") %>">

                <label for="updateName">Name:</label>
                <input type="text" id="updateName" name="updateName" value="<%= searchedProduct.getProductName() %>" required>

                <label for="updateDescription">Description:</label>
                <textarea id="updateDescription" name="updateDescription" required><%= searchedProduct.getDescription() %></textarea>

                <label for="updatePrice">Price:</label>
                <input type="number" id="updatePrice" name="updatePrice" step="0.01" value="<%= searchedProduct.getProductPrice() %>" required>

                <label for="updateStockQuantity">Stock Quantity:</label>
                <input type="number" id="updateStockQuantity" name="updateStockQuantity" value="<%= searchedProduct.getStockQuantity() %>" required>

                <label for="updateCategoryID">Category ID:</label>
                <input type="number" id="updateCategoryID" name="updateCategoryID" value="<%= searchedProduct.getCategoryID() %>" required>

                <button type="submit">Update Product</button>
            </form>
        </section>
        <%
            }
        %>




        <!-- List Products Section -->
        <section id="listSection">
            <h2>Product List</h2>

            <table border="1">
                <!-- Table header -->
                <tr>
                    <th>Image</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Stock Quantity</th>
                    <th>Category ID</th>
                </tr>

                <!-- Iterate over products and display them in the table -->
                <% List<Product> productList = (List<Product>) request.getAttribute("productList");
                    
               for (Product product : productList) { %>
                <tr>
                    <td><img src="images/<%= product.getImagePath()%>" alt="Product Image" style="max-width: 100px; max-height: 100px;"></td>
                    <td><%= product.getProductID() %></td>
                    <td><%= product.getProductName() %></td>
                    <td><%= product.getDescription() %></td>
                    <td><%= product.getProductPrice() %></td>
                    <td><%= product.getStockQuantity() %></td>
                    <td><%= product.getCategoryID() %></td>
                </tr>
                <% } %>
            </table>
        </section>
        <!-- Return to home Section -->


        <!-- Display communication features -->
        <section id="rth">
            <a href="sellerIndex.jsp"><button>Return to home</button></a>
        </section>

    </body>
</html>
