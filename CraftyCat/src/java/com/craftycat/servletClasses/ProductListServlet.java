package com.craftycat.servletClasses;

import com.craftycat.normalClasses.DataRetrieving;
import com.craftycat.normalClasses.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = null;
        String userID = request.getParameter("userID");
        if(userID != null){
            System.out.println("set userID in productListServlet on line 23 as: " + userID);
            try {
                productList = DataRetrieving.getAllProducts();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProductListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("productList", productList);
            request.setAttribute("userID", userID);
            System.out.println("Attributes set at line 31 in productListServlet for user: " + userID);
            request.getRequestDispatcher("/productList.jsp").forward(request, response);
        }else{
            System.out.println("set userID in productListServlet on line 23 as: " + userID);
            try {
                productList = DataRetrieving.getAllProducts();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProductListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("productList", productList);
            System.out.println("Loaded product List without user ID");
            request.getRequestDispatcher("/shop.jsp").forward(request, response);
        }
    }
}
