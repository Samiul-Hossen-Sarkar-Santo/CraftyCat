package com.craftycat.servletClasses;

import com.craftycat.normalClasses.DataRetrieving;
import com.craftycat.normalClasses.Product;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class AddProductServlet extends HttpServlet {
    
    Part filePart;
    String imagePath;
    String fileName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Processing request in the AddProductServlet");

        // Retrieve the action parameter
        Part actionPart = request.getPart("action");
        String action = "";
        if (actionPart != null) {

            try (InputStream is = actionPart.getInputStream()) {
                action = new String(is.readAllBytes(), "UTF-8");
            }
            System.out.println("Action: " + action);
            filePart = request.getPart("image");
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            imagePath = "F:/My Projects/Crafty Cat/Crafty Cat v.1/CraftyCat/web/images/" + fileName;

            System.out.println("All the attributes achieved including filepath in the productServlet on the addProdcut method: " + imagePath);

            // Save the file to the server
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);
            }
        }

        System.out.println("Action in addServlet: " + action);

        if ("add".equals(action)) {
            // Handle the "add" action
            try {
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
                int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                int SellerID = Integer.parseInt(request.getParameter("sellerID"));

                System.out.println(name + " " + description + " " + price + " " + stockQuantity + " " + categoryID + " " + SellerID);

                if (filePart != null) {
                    
                    System.out.println("Line 68 of addProductServlet");

                    // Create a new product object
                    Product product = new Product();
                    product.setName(name);
                    product.setDescription(description);
                    product.setPrice(price);
                    product.setStockQuantity(stockQuantity);
                    product.setCategoryID(categoryID);
                    product.setSellerID(SellerID);
                    product.setImagePath(fileName);

                    System.out.println("New product object created in the productServlet on the addProductServlet on line 80. Going to the dataretrieving class to add");

                    // Add the product to the database
                    DataRetrieving.addProduct(product);

                    System.out.println("Product added to the database");
                    // Redirect back to the product list page
                    request.setAttribute("sellerID", SellerID);
                    response.sendRedirect("successMessage.jsp");
                } else {
                    // Handle the case where filePart is null (no file uploaded)
                    response.sendRedirect("errorPage.jsp");
                }
            } catch (NumberFormatException ex) {
                // Handle NumberFormatException (e.g., invalid integer conversion)
                response.sendRedirect("errorPage.jsp");
            }
        } else {
            // Handle other actions or provide an error response
            response.sendRedirect("errorPage.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Redirect to an error page
            response.sendRedirect("errorPage.jsp");
        }
    }
}
