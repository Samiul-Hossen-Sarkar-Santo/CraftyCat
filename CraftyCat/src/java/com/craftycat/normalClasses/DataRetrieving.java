package com.craftycat.normalClasses;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataRetrieving {

    // Method to retrieve user information by username
    public static User getUserByUsername(String username) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Users WHERE Username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // Map the ResultSet to a User object (you need to have a User class)
                User user = new User();
                user.setUserID(Integer.parseInt(resultSet.getString("UserID")));
                user.setUserName(resultSet.getString("Username"));
                user.setEmail(resultSet.getString("Email"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAddress(resultSet.getString("AddressLine1"));
                user.setCity(resultSet.getString("City"));
                user.setState(resultSet.getString("State"));
                user.setPhone(Integer.parseInt(resultSet.getString("Phone")));
                user.setUserRole(resultSet.getString("UserRole"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, resultSet);
        }

        return null;
    }

    // Method to retrieve user information by username
    public static User getUserByUserID(int UserID) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Users WHERE UserID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, UserID);

            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // Map the ResultSet to a User object (you need to have a User class)
                User user = new User();
                user.setUserID(Integer.parseInt(resultSet.getString("UserID")));
                user.setUserName(resultSet.getString("Username"));
                user.setEmail(resultSet.getString("Email"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAddress(resultSet.getString("AddressLine1"));
                user.setCity(resultSet.getString("City"));
                user.setState(resultSet.getString("State"));
                user.setPhone(Integer.parseInt(resultSet.getString("Phone")));
                user.setUserRole(resultSet.getString("UserRole"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, resultSet);
        }

        return null;
    }
    
    public static boolean updateUserInfo(String userID, String newUsername, String newEmail, String newFirstName,
            String newLastName, String newAddress, String newPhone) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE Users SET Username=?, Email=?, FirstName=?, LastName=?, AddressLine1=?, Phone=? WHERE UserID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUsername);
            pstmt.setString(2, newEmail);
            pstmt.setString(3, newFirstName);
            pstmt.setString(4, newLastName);
            pstmt.setString(5, newAddress);
            pstmt.setString(6, newPhone);
            pstmt.setString(7, userID);

            int rowsUpdated = pstmt.executeUpdate();

            // Check if the update was successful
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // Method to retrieve all users
    public static List<User> getAllUsers() throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Users";
            pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                // Map each row to a User object and add it to the list
                User user = new User();
                user.setUserID(Integer.parseInt(resultSet.getString("UserID")));
                user.setUserName(resultSet.getString("Username"));
                user.setEmail(resultSet.getString("Email"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAddress(resultSet.getString("AddressLine1"));
                user.setCity(resultSet.getString("City"));
                user.setState(resultSet.getString("State"));
                user.setPhone(Integer.parseInt(resultSet.getString("Phone")));
                user.setUserRole(resultSet.getString("UserRole"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, resultSet);
        }

        return users;
    }

    public static Seller getSellerByUserID(int userID) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Sellers WHERE UserID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);

            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // Map the ResultSet to a Seller object (you need to have a Seller class)
                Seller seller = new Seller();
                seller.setSellerID(Integer.parseInt(resultSet.getString("SellerID")));
                seller.setShopName(resultSet.getString("ShopName"));

                return seller;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, resultSet);
        }

        return null;
    }
    public static Seller getSellerBySellerID(int sellerID) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Sellers WHERE SellerID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, sellerID);

            resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // Map the ResultSet to a Seller object (you need to have a Seller class)
                Seller seller = new Seller();
                seller.setSellerID(Integer.parseInt(resultSet.getString("SellerID")));
                seller.setShopName(resultSet.getString("ShopName"));

                return seller;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, resultSet);
        }

        return null;
    }

    public static void updateSellerInfo(int sellerID, String shopName) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "UPDATE Sellers SET ShopName = ? WHERE SellerID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, shopName);
            preparedStatement.setInt(2, sellerID);

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

    public static List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
        System.out.println("Came to getAllProducts method in dataRetrieving");
        List<Product> productList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Products";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("Name"));
                product.setDescription(resultSet.getString("Description"));
                product.setPrice(resultSet.getDouble("Price"));
                product.setStockQuantity(resultSet.getInt("StockQuantity"));
                product.setCategoryID(resultSet.getInt("CategoryID"));
                product.setImagePath(resultSet.getString("image_path"));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception according to your application's needs
        }
        System.out.println("adding data successful in data retriving class at getAllProducts method on line 265");
        return productList;
    }
    public static List<Product> getAllProducts(int sellerID) throws ClassNotFoundException, SQLException {
        System.out.println("Came to getAllProducts method in dataRetrieving");
        List<Product> productList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Products where SellerID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, sellerID);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("Name"));
                product.setDescription(resultSet.getString("Description"));
                product.setPrice(resultSet.getDouble("Price"));
                product.setStockQuantity(resultSet.getInt("StockQuantity"));
                product.setCategoryID(resultSet.getInt("CategoryID"));
                product.setImagePath(resultSet.getString("image_path"));

                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception according to your application's needs
        }

        return productList;
    }
    
    public static Product getProductByName(String productName, int sellerID) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM Products WHERE Name = ? AND SellerID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, productName);
        preparedStatement.setInt(2, sellerID);
        ResultSet resultSet = preparedStatement.executeQuery();

        try {
            if (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("Name"));
                product.setDescription(resultSet.getString("Description"));
                product.setPrice(resultSet.getDouble("Price"));
                product.setStockQuantity(resultSet.getInt("StockQuantity"));
                product.setCategoryID(resultSet.getInt("CategoryID"));
                product.setImagePath(resultSet.getString("image_path"));

                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Return null if the product is not found
    }
    
    public static Product getProductByID(int productID) throws SQLException, ClassNotFoundException {
        // Implement logic to retrieve product details from the database based on productID
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Products WHERE ProductID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, productID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Create a Product object and set its attributes based on the retrieved data
                        Product product = new Product();
                        product.setProductID(resultSet.getInt("ProductID"));
                        product.setName(resultSet.getString("Name"));
                        product.setDescription(resultSet.getString("Description"));
                        product.setPrice(resultSet.getDouble("Price"));
                        product.setImagePath(resultSet.getString("image_path"));
                        
                        return product;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }


    public static void addProduct(Product product) throws ClassNotFoundException, SQLException {
        System.out.println("entered addProductmethod in dataRetrieving");
        Connection connection = DatabaseConnection.getConnection();
        System.out.println("connected to the database");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Products (`Name`, `Description`, `Price`, `StockQuantity`, `CategoryID`, `SellerID`, `image_path`) VALUES (?, ?, ?, ?, ?, ?, ?)");
        try {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setInt(4, product.getStockQuantity());
            preparedStatement.setInt(5, product.getCategoryID());
            preparedStatement.setInt(6, product.getSellerID());
            preparedStatement.setString(7, product.getImagePath());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct(Product product) throws ClassNotFoundException {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE products SET Name = ?, Description = ?, Price = ?, StockQuantity = ?, CategoryID = ?, image_path = ? WHERE ProductID = ?"
        )) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getProductPrice());
            preparedStatement.setInt(4, product.getStockQuantity());
            preparedStatement.setInt(5, product.getCategoryID());
            preparedStatement.setInt(6, product.getProductID());
            preparedStatement.setString(7, product.getImagePath());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int productID) throws ClassNotFoundException {
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM products WHERE ProductID = ?"
        )) {
            preparedStatement.setInt(1, productID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void saveOrder(Order order) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO Orders (userID, sellerID, productID, orderDateTime, address, isPaid, orderStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, order.getUserID());
            preparedStatement.setInt(2, order.getSellerID());
            preparedStatement.setInt(3, order.getProductID());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(order.getOrderDateTime()));
            preparedStatement.setString(5, order.getAddress());
            preparedStatement.setBoolean(6, order.isPaid());
            preparedStatement.setString(7, order.getOrderStatus().toString());
            preparedStatement.executeUpdate();
        }
    }

    // Method to update the order status
    public static void updateOrderStatus(int orderID, OrderStatus newStatus) throws SQLException, ClassNotFoundException {
        String query = "UPDATE Orders SET orderStatus = ? WHERE orderID = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, newStatus.toString());
            preparedStatement.setInt(2, orderID);
            preparedStatement.executeUpdate();
        }
    }

    // Method to retrieve all orders for a specific user
    public static List<Order> getOrdersByUserID(int userID) throws SQLException, ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE userID = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(mapResultSetToOrder(resultSet));
                }
            }
        }
        return orders;
    }

    // Method to retrieve all orders for a specific seller
    public static List<Order> getOrdersBySellerID(int userID) throws SQLException, ClassNotFoundException {
        System.out.println("came to getOrdersBySellerID method");
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE sellerID = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("till this with no error getOrdersBySellerID");
                while (resultSet.next()) {
                    orders.add(mapResultSetToOrder(resultSet));
                }
            }
        }
        System.out.println("Added all orders to the list");
        return orders;
    }

    private static Order mapResultSetToOrder(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        System.out.println("entered mapResultSetToOrder method");
        int orderID = resultSet.getInt("orderID");
        int userID = resultSet.getInt("userID");
        int sellerID = resultSet.getInt("sellerID");
        int productID = resultSet.getInt("productID");
        String productName = DataRetrieving.getProductByID(productID).getProductName();
        LocalDateTime orderDateTime = resultSet.getTimestamp("orderDateTime").toLocalDateTime();
        String address = resultSet.getString("address");
        boolean isPaid = resultSet.getBoolean("isPaid");
        OrderStatus orderStatus = OrderStatus.valueOf(resultSet.getString("orderStatus"));

        System.out.println("found all data from the db in mapResultSetToOrder");

        Order order = new Order(userID, sellerID, productID);
        order.setOrderID(orderID);
        order.setProductName(productName);
        order.setOrderDateTime(orderDateTime);
        order.setAddress(address);
        order.setPaid(isPaid);
        order.setOrderStatus(orderStatus);
        System.out.println("added all the orders to the single object in mapResultSetToOrder");

        return order;
    }

    private static void closeResources(Connection conn, PreparedStatement pstmt, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
