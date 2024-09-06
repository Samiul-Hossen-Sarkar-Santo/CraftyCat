package com.craftycat.normalClasses;

import java.sql.SQLException;

public class CartItem {

    private int cartID;
    private int userID;
    private int productID;
    private int quantity;
    private String imagePath;
    private String productName;
    private double price;
    Product product;
    User user;

    public CartItem(int cartID, int userID, int productID, int quantity) throws SQLException, ClassNotFoundException {
        this.product = DataRetrieving.getProductByID(productID);
        this.cartID = cartID;
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
        this.imagePath = this.product.getImagePath();
        this.price = this.product.getProductPrice();
        this.productName = this.product.getProductName();
        this.user = DataRetrieving.getUserByUserID(userID);
    }

    public int getCartID() {
        return cartID;
    }

    public int getUserID() {
        return userID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
