/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.craftycat.servletClasses;

import com.craftycat.normalClasses.DataRetrieving;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author UseR
 */
public class UpdateUserInfoServlet extends HttpServlet {

    LoginServlet loginServlet = new LoginServlet();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        // Get parameters from the form
        String name = request.getParameter("name");
        String userID = request.getParameter("userID");
        String newUsername = request.getParameter("newUsername");
        String newEmail = request.getParameter("newEmail");
        String newFirstName = request.getParameter("newFirstName");
        String newLastName = request.getParameter("newLastName");
        String newAddress = request.getParameter("newAddress");
        String newPhone = request.getParameter("newPhone");
        String password = request.getParameter("password");
        if (loginServlet.isValidUser(name, password)) {
            if (DataRetrieving.updateUserInfo(userID, newUsername, newEmail, newFirstName, newLastName, newAddress, newPhone)) {
                System.out.println("user info updated");
                request.setAttribute("username", newUsername);
                response.sendRedirect("userDataRetrievement?username=" + newUsername);
            } else {
                System.out.println("User info not updated");
                request.setAttribute("username", name);
                response.sendRedirect("userDataRetrievement?username=" + name);
            }

        } else {
            System.out.println("Password didn't match");
            request.setAttribute("username", name);
            response.sendRedirect("userDataRetrievement?username=" + name);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateUserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUserInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
