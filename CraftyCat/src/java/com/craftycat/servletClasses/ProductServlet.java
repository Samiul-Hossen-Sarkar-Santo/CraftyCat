package com.craftycat.servletClasses;

import com.craftycat.normalClasses.DataRetrieving;
import com.craftycat.normalClasses.Product;
import java.io.IOException;
import java.util.List;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProductServlet extends HttpServlet {

    List<Product> productList;
    String sellerID;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        System.out.println("it's here in the product servlet beginning");
        // Retrieve all products from the database
        sellerID = request.getParameter("sellerID");
        System.out.println("seller id achieved in the productServlet");
        if (sellerID != null) {
            productList = DataRetrieving.getAllProducts(Integer.parseInt(sellerID));
            System.out.println("productlist achieved in the productServlet");
        } else {
            response.sendRedirect("errorpage.jsp");
        }
        // Set the product list as an attribute to be used in JSP
        request.setAttribute("productList", productList);
        request.setAttribute("sellerID", sellerID);
        System.out.println("attributes set sellerID: " + sellerID);
        for (Product product : productList) {
            System.out.println(product.getProductName());
        }
        // Forward the request to the product list JSP page
        request.getRequestDispatcher("productManagement.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("post method reached in the productServlet");

        String action = request.getParameter("action");
        String sellerID = request.getParameter("sellerID");
        System.out.println(action);
        System.out.println("action acquired in the productServlet");

        if (action != null) {
            System.out.println("action in productservlet post method is: " + action);
        }

        if (action != null) {
            switch (action) {
                case "update": {
                    System.out.println("in the productServlet on the update action");
                    try {
                        updateProduct(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case "delete": {
                    try {
                        deleteProduct(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case "search": {
                    try {
                        searchProduct(request, response);
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                default:
                    //response.sendRedirect("ProductServlet");
                    break;
            }
        } else {
            response.sendRedirect("errorPage.jsp");
        }
    }


    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        // Extract product information from the request
        sellerID = request.getParameter("sellerID");
        System.out.println("seller id achieved in the searchProduct method");
        int productID = Integer.parseInt(request.getParameter("productID"));
        String name = request.getParameter("updateName");
        String description = request.getParameter("updateDescription");
        double price = Double.parseDouble(request.getParameter("updatePrice"));
        int stockQuantity = Integer.parseInt(request.getParameter("updateStockQuantity"));
        int categoryID = Integer.parseInt(request.getParameter("updateCategoryID"));

        // Create a new product object with updated information
        Product updatedProduct = new Product();
        updatedProduct.setProductID(productID);
        updatedProduct.setName(name);
        updatedProduct.setDescription(description);
        updatedProduct.setPrice(price);
        updatedProduct.setStockQuantity(stockQuantity);
        updatedProduct.setCategoryID(categoryID);

        // Update the product in the database
        DataRetrieving.updateProduct(updatedProduct);

        // Redirect back to the product list page
        request.setAttribute("sellerID", sellerID);
        response.sendRedirect("successMessage.jsp");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        // Extract product ID from the request
        int productID = Integer.parseInt(request.getParameter("productID"));

        // Delete the product from the database
        DataRetrieving.deleteProduct(productID);

        // Redirect back to the product list page
        response.sendRedirect("ProductServlet");
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        String productName = request.getParameter("productName");
        sellerID = request.getParameter("sellerID");
        System.out.println("seller id achieved in the searchProduct method");
        if (sellerID != null) {
            productList = DataRetrieving.getAllProducts(Integer.parseInt(sellerID));
            System.out.println("productlist achieved in the searchProduct method");
        } else {
            response.sendRedirect("errorpage.jsp");
        }
        // Set the product list as an attribute to be used in JSP
        request.setAttribute("productList", productList);
        request.setAttribute("sellerID", sellerID);
        System.out.println("attributes set in the searchProduct method. They are: " + productList + " " + sellerID);

        System.out.println("Product name recieved. It is: " + productName);

        // Perform the search based on the product name
        Product searchedProduct = DataRetrieving.getProductByName(productName, Integer.parseInt(sellerID));

        if (searchedProduct != null) {
            // Set the searched product as an attribute
            System.out.println("setting attribute");
            request.setAttribute("searchedProduct", searchedProduct);
            request.setAttribute("sellerID", sellerID);
        } else {
            System.out.println("searched product is null");
        }

        // Forward the request to the JSP page for displaying the product details
        request.getRequestDispatcher("productManagement.jsp").forward(request, response);
    }
}
