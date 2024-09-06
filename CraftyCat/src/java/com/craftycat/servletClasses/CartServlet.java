package com.craftycat.servletClasses;

import com.craftycat.normalClasses.CartItem;
import com.craftycat.normalClasses.CartOperations;
import com.craftycat.normalClasses.DataRetrieving;
import com.craftycat.normalClasses.Product;
import com.craftycat.normalClasses.User;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author UseR
 */
public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        User user = (User) request.getSession().getAttribute("user");
        String action = (String) request.getParameter("action");
        System.out.println("fount user with userID:" + user.getUserID() + " in the cartservlet on line 30");
        RequestDispatcher dispatcher;
        int productID;
        int quantity;

        if (user != null) {
            /*int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Product product = DataRetrieving.getProductByID(productID);*/

            System.out.println("Found user and action on cartservlet at line 40 as follows: " + user.getUserID() + " " + action);
            if (action != null) {
                switch (action) {
                    case "add":
                        productID = Integer.parseInt(request.getParameter("productID"));
                        quantity = Integer.parseInt(request.getParameter("quantity"));
                        Product product = DataRetrieving.getProductByID(productID);
                        processAddToCart(user, productID, quantity, product.getProductPrice(), response);
                        break;
                    case "update":
                        productID = Integer.parseInt(request.getParameter("productID"));
                        quantity = Integer.parseInt(request.getParameter("quantity"));
                        processUpdateCartItem(user, productID, quantity, response);
                        break;
                    case "calculate":
                        processCalculateTotalPrice(user, response);
                        break;
                    case "remove":
                        productID = Integer.parseInt(request.getParameter("productID"));
                        processRemoveFromCart(user, productID, response);
                        break;
                    case "getAll":
                        processGetAllCartItems(user, request, response);
                        List<CartItem> cartItems = CartOperations.getCartItems(user.getUserID());
                        double totalPrice = CartOperations.calculateTotalPrice(user.getUserID());

                        request.setAttribute("cartItems", cartItems);
                        request.setAttribute("totalPrice", totalPrice);
                        dispatcher = request.getRequestDispatcher("checkout.jsp");
                        dispatcher.forward(request, response);
                        break;

                    case "checkout":
                        String fileName = CartOperations.checkout(user.getUserID());
                        System.out.println(fileName);
                        System.out.println("Printing file path from cart servlet checkout case: "+ getServletContext().getRealPath("/" + "PDF" + File.separator + fileName));
                        request.setAttribute("fileName", fileName);
                        dispatcher = request.getRequestDispatcher("downloadReceipt.jsp");
                        dispatcher.forward(request, response);
                        break;
                    default:
                        response.sendRedirect("errorPage.jsp");
                }
            } else {
                response.sendRedirect("errorPage.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void processAddToCart(User user, int productID, int quantity, double price, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, IOException {

        CartOperations.addToCart(user.getUserID(), productID, quantity, price);

        response.getWriter().write("Product added to cart");
    }

    private void processUpdateCartItem(User user, int productID, int quantity, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, IOException {
        // Update quantity of an item in the cart
        CartOperations.updateCartItemQuantity(user.getUserID(), productID, quantity);
        response.sendRedirect("productDetail.jsp?productID=" + productID + "&updatedInCart=true");
    }

    private void processRemoveFromCart(User user, int productID, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, IOException {
        // Remove item from the cart
        CartOperations.removeFromCart(user.getUserID(), productID);
        response.sendRedirect("productDetail.jsp?productID=" + productID + "&removedFromCart=true");
    }

    private void processCalculateTotalPrice(User user, HttpServletResponse response)
            throws SQLException, IOException {
        // Calculate total price and redirect
        double totalPrice = CartOperations.calculateTotalPrice(user.getUserID());
        response.sendRedirect("cart.jsp?totalPrice=" + totalPrice);
    }

    private void processGetAllCartItems(User user, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, ServletException, IOException {
        // Get all cart items and forward to a page
        List<CartItem> cartItems = CartOperations.getCartItems(user.getUserID());
        if (cartItems != null && !cartItems.isEmpty()) {
            double totalPrice = CartOperations.calculateTotalPrice(user.getUserID());

            request.setAttribute("cartItems", cartItems);
            request.setAttribute("totalPrice", totalPrice);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("cartItems", null);
            request.setAttribute("totalPrice", null);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
