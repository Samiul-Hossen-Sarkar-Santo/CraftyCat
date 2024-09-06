package com.craftycat.servletClasses;
import com.craftycat.normalClasses.User;
import com.craftycat.normalClasses.Seller;
import com.craftycat.normalClasses.Product;
import com.craftycat.normalClasses.DataRetrieving;
import com.craftycat.normalClasses.Order;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SellerDashboardRedirectServlet extends HttpServlet {
    
    User user;
    Seller seller;
    List<Product> products;
    String userID;
    List<Order> orders;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        // Get user ID from request parameter
        userID = request.getParameter("userID");

        // Use the user ID to retrieve the complete user information
        user = DataRetrieving.getUserByUserID(Integer.parseInt(userID));
        System.out.println("found user: " + user.getUserName());
        System.out.println("userID: " + user.getUserID());
        seller = DataRetrieving.getSellerByUserID(user.getUserID());
        System.out.println("found seller: " + user.getUserName());
        System.out.println("sellerID: " + seller.getSellerID());
        products = DataRetrieving.getAllProducts(seller.getSellerID());
        System.out.println("Got all products. Currently in the SellerDashboardRedirectServlet");
        orders = DataRetrieving.getOrdersBySellerID(seller.getSellerID());
        
        if(!orders.isEmpty()){
            System.out.println("retrived orders in SellerDashboardRedirectServlet");
            for(Order order:orders){
                order.printDetails();
            }
        }
        
        System.out.println("retrived products and orders in SellerDashboardRedirectServlet");

        // Set the user information as a request attribute
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("products", products);
        session.setAttribute("seller", seller);
        session.setAttribute("orders", orders);
        
        System.out.println("Attributes are set as well in SellerDashboardRedirectServlet");
        // Forward the request to the Seller Dashboard JSP
        request.getRequestDispatcher("SellerDashboard.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SellerDashboardRedirectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SellerDashboardRedirectServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
