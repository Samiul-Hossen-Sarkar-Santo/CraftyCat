package com.craftycat.servletClasses;

import com.craftycat.normalClasses.DataRetrieving;
import com.craftycat.normalClasses.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserDataRetrievementServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String un = request.getParameter("username");
        HttpSession session = request.getSession();
        
        try {
            User user = DataRetrieving.getUserByUsername(un);
            if(user != null){
                if (user.getUserRole().equals("Buyer")) {
                    session.setAttribute("user", user);  // Set user as an attribute
                    request.getRequestDispatcher("BuyerProfile.jsp").forward(request, response);
                } else{
                    session.setAttribute("user", user);  // Set user as an attribute
                    request.getRequestDispatcher("SellerProfile.jsp").forward(request, response);
                }
            }else
                out.println("User not found");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDataRetrievementServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
