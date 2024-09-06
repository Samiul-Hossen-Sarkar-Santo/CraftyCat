package com.craftycat.servletClasses;

import com.craftycat.normalClasses.DatabaseConnection;
import com.craftycat.normalClasses.PasswordHashing;
import com.craftycat.normalClasses.User;
import com.craftycat.normalClasses.DataRetrieving;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String n = request.getParameter("name");
        String p = request.getParameter("password");
        String r = request.getParameter("role");
        
        HttpSession session = request.getSession();
        session.setAttribute("userName", n);
        
        try {
            if (isValidUser(n, p)) {
                if(checkUserRole(n, r)){
                    if (r.equals("Buyer")) {
                        User user = DataRetrieving.getUserByUsername(n);
                        session.setAttribute("user", user);
                        response.sendRedirect("buyerIndex.jsp");
                    } else {
                        User user = DataRetrieving.getUserByUsername(n);
                        session.setAttribute("user", user);
                        response.sendRedirect("sellerIndex.jsp");
                    }
                }else{
                    if(r.equals("Buyer")){
                        out.println("<h2>You are not a Buyer</h2>");

                        out.println("<a href =\"roleSelection.html\"> Try again </a>");
                    }else{
                        out.println("<h2>You are not a Seller</h2>");

                        out.println("<a href =\"roleSelection.html\"> Try again </a>");
                    }
                }
            } else {
                out.println("<h2>Wrong Credentials. Try Again</h2>");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public boolean isValidUser(String name, String password) throws ClassNotFoundException, SQLException{
        
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT HashedPassword, Salt FROM Users WHERE Username=?");
        pstmt.setString(1, name);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // User found, retrieve hashed password and salt
            String storedHashedPassword = rs.getString("HashedPassword");
            String storedSalt = rs.getString("Salt");

            // Hash the entered password with the retrieved salt
            String hashedEnteredPassword = PasswordHashing.hashingThePassword(password, storedSalt);

            // Compare the hashed passwords
            boolean userExists = hashedEnteredPassword.equals(storedHashedPassword);

            System.out.println("User " + (userExists ? "Available" : "Not Available"));

            rs.close();
            pstmt.close();
            return userExists;
        } else {
            // User not found
            System.out.println("User Not Available");
            rs.close();
            pstmt.close();
            return false;
        }
    }
    
    private boolean checkUserRole(String username, String userRole) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT UserRole FROM Users WHERE Username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            resultSet = pstmt.executeQuery();

            // If the user is found in the database
            if (resultSet.next()) {
                String dbUserRole = resultSet.getString("UserRole");

                // Check if the user's role matches the role in the database
                return userRole.equals(dbUserRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (resultSet != null && pstmt != null && conn != null) {
                    resultSet.close();
                    pstmt.close();
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return false;
    }
}
