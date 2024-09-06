package com.craftycat.servletClasses;

import com.craftycat.normalClasses.DataRetrieving;
import com.craftycat.normalClasses.DatabaseConnection;
import com.craftycat.normalClasses.PasswordHashing;
import com.craftycat.normalClasses.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class userRegistrationServlet extends HttpServlet {

    User user;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String addressLine1 = request.getParameter("addressLine1");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String phone = request.getParameter("phone");
        String userRole = request.getParameter("role");
        String shopName = request.getParameter("shopName");

        HttpSession session = request.getSession();
        session.setAttribute("userName", name);
        session.setAttribute("email", email);
        session.setAttribute("firstName", firstname);
        session.setAttribute("lastName", lastName);
        session.setAttribute("addressLine1", addressLine1);
        session.setAttribute("phone", phone);
        session.setAttribute("userRole", userRole);
        
        System.out.println("Parameters recieved and attributes set...");

        if (!doesUserExist(name, password)) {
            System.out.println("It's here on userRegistrationServlet line 52");
            addUser(name, password, email, firstname, lastName, addressLine1, city, state, phone, userRole);
            System.out.println("User added on userRegistrationServlet line 54");
            if (checkUserRole(name, userRole)) {
                user = DataRetrieving.getUserByUsername(name);
                if (user != null) {
                    System.out.println("User is not nullon userRegistrationServlet line 58");
                    if (user.getUserRole().equals("Buyer")) {
                        request.setAttribute("user", user);  // Set user as an attribute
                        request.getRequestDispatcher("buyerIndex.jsp").forward(request, response);
                    } else if (user.getUserRole().equals("Seller")) {
                        request.setAttribute("user", user);  // Set user as an attribute
                        addSeller(user.getUserID(), shopName);
                        request.getRequestDispatcher("sellerIndex.jsp").forward(request, response);
                    } else {
                        out.println(name);
                    }
                } else {
                    System.out.println("User is null");
                    out.println("User not found");
                }
            }else{
                out.println("You're not a " + userRole);
            }
        } else {
            out.println("User already exists");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Processing request...");
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(userRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean doesUserExist(String name, String password) throws ClassNotFoundException, SQLException {
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

            System.out.println("User " + (userExists ? "Available" : "Not Available from here "));

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

    public void addUser(String name, String password, String email, String firstName, String lastName, String address, String city, String state, String phone, String role)
            throws ClassNotFoundException, SQLException {

        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Users (Username, Email, FirstName, LastName, AddressLine1, City, State, Phone, HashedPassword, Salt, UserRole) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        // Hash the password with the generated salt
        String hashedPassword = PasswordHashing.hashingThePassword(password);

        // Set values in the prepared statement
        pstmt.setString(1, name);
        pstmt.setString(2, email);
        pstmt.setString(3, firstName);
        pstmt.setString(4, lastName);
        pstmt.setString(5, address);
        pstmt.setString(6, city);
        pstmt.setString(7, state);
        pstmt.setString(8, phone);
        pstmt.setString(9, hashedPassword);
        pstmt.setString(10, PasswordHashing.getSaltValue());
        pstmt.setString(11, role);

        // Execute the insert query
        pstmt.executeUpdate();

        System.out.println("User added successfully on userRegistrationServlet line 148");

        pstmt.close();
    }

    public void addSeller(int userID, String shopName) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO Sellers (UserID, ShopName) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, shopName);

            preparedStatement.executeUpdate();
        } finally {
            // Close resources in a finally block
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
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
