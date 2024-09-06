/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.craftycat.servletClasses;

import com.craftycat.normalClasses.DataRetrieving;
import com.craftycat.normalClasses.OrderStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOrderStatusServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        // Get the order ID and new order status from the submitted form data
        String orderIDString = request.getParameter("orderID");
        String newOrderStatus = request.getParameter("orderStatus");
        String userID = request.getParameter("userID");
        System.out.println("User ID achieved in UpdateOrderStatusServlet: " + userID);

        // Validate and parse order ID
        try {
            int orderID = Integer.parseInt(orderIDString);

            // Update the order status in the database
            DataRetrieving.updateOrderStatus(orderID, OrderStatus.valueOf(newOrderStatus.toUpperCase()));

            // Redirect back to the seller dashboard or another appropriate page
            response.sendRedirect("SellerDashboardRedirectServlet?userID=" + userID);
        } catch (NumberFormatException e) {
            // Handle invalid order ID
            response.getWriter().println("Invalid order ID");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdateOrderStatusServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
