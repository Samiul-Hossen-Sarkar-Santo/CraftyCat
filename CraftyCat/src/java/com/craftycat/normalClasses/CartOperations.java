package com.craftycat.normalClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class CartOperations {

    // Add item to the cart
    public static void addToCart(int userID, int productID, int quantity, double price) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Cart (UserID, ProductID, Quantity, price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                preparedStatement.setInt(2, productID);
                preparedStatement.setInt(3, quantity);
                preparedStatement.setDouble(4, price);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get cart items for a user
    public static List<CartItem> getCartItems(int userID) throws SQLException, ClassNotFoundException {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Cart WHERE UserID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int productID = resultSet.getInt("ProductID");
                        int quantity = resultSet.getInt("Quantity");
                        int userId = resultSet.getInt("UserID");
                        int cartID = resultSet.getInt("CartID");

                        // You may want to fetch additional product details from the Products table
                        // and construct a CartItem object
                        CartItem cartItem = new CartItem(cartID, userId, productID, quantity);
                        cartItems.add(cartItem);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    // Update quantity of an item in the cart
    public static void updateCartItemQuantity(int userID, int productID, int newQuantity) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE Cart SET Quantity = ? WHERE UserID = ? AND ProductID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, newQuantity);
                preparedStatement.setInt(2, userID);
                preparedStatement.setInt(3, productID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove item from the cart
    public static void removeFromCart(int userID, int productID) throws SQLException, ClassNotFoundException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Cart WHERE UserID = ? AND ProductID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                preparedStatement.setInt(2, productID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double calculateTotalPrice(int userID) throws SQLException {
        double totalPrice = 0;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT SUM(quantity * price) AS total FROM cart WHERE userID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        totalPrice = resultSet.getDouble("total");
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return totalPrice;
    }

    public static String checkout(int userID) throws SQLException, ClassNotFoundException {
        String fileName = null;
        try {
            // Get cart items for the user
            List<CartItem> cartItems = getCartItems(userID);

            // Create PDF receipt
            fileName = generateReceipt(cartItems, userID);

            // Clear the cart after creating the receipt
            clearCart(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private static void clearCart(int userID) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM Cart WHERE UserID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userID);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String generateReceipt(List<CartItem> cartItems, int userID) throws ClassNotFoundException {
        User user = DataRetrieving.getUserByUserID(userID);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "invoice_of_" + user.getUserName() + "_" + timestamp + ".pdf";

        String filePath = null;

        try {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                System.out.println("new page has been created in the pdf");
                document.addPage(page);
                System.out.println("the page has been added to the pdf");

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    System.out.println("below the contentstream");

                    // Title
                    PDType0Font font = PDType0Font.load(document, new FileInputStream("F:/My Projects/External things/Fonts/Helvetica-Bold.ttf"));

                    System.out.println("font added");

                    contentStream.setFont(font, 16);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 700);
                    contentStream.showText("Receipt");
                    contentStream.endText();
                    System.out.println("receipt title set");

                    // Receipt details
                    PDType0Font font1 = PDType0Font.load(document, new FileInputStream("F:/My Projects/External things/Fonts/Helvetica.ttf"));
                    contentStream.setFont(font1, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 680);
                    System.out.println("eikhane achi generatepdf e");

                    for (CartItem item : cartItems) {
                        contentStream.newLineAtOffset(0, -20); // Move to the next line
                        contentStream.showText("Product: " + item.getProductName());
                        contentStream.newLineAtOffset(0, -20);
                        contentStream.showText("Quantity: " + item.getQuantity());
                        contentStream.newLineAtOffset(0, -20);
                        contentStream.showText("Total: $" + (item.getPrice() * item.getQuantity()));
                        contentStream.newLineAtOffset(0, -40);
                    }

                    contentStream.showText("Thank you for shopping at Crafty Cat ^.^");

                    System.out.println("receipt set");
                    contentStream.endText();
                }
                filePath = "F:/My Projects/Crafty Cat/Crafty Cat v.1/CraftyCat/web/PDF/" + fileName;
                document.save(filePath);
                System.out.println("Printing filePath at line 192 on generateReceipt method: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}
