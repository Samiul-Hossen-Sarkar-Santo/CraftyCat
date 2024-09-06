package com.craftycat.normalClasses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {

    private int orderID;
    private int userID;
    private int sellerID;
    private int productID;
    private String productName;
    private LocalDateTime orderDateTime;
    private String address; // Assuming this is the delivery address
    private boolean isPaid;
    private OrderStatus orderStatus;

    public Order(int userID, int sellerID, int productID) {
        this.userID = userID;
        this.sellerID = sellerID;
        this.productID = productID;
        this.orderDateTime = LocalDateTime.now();
        this.isPaid = false; // Default to not paid
        this.orderStatus = OrderStatus.PENDING; // Default order status
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }
    public LocalDate getOrderDate() {
        return orderDateTime.toLocalDate();
    }
    
    public String getFormattedOrderDate() {
        // Define a custom date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM, yyyy");

        // Format the date
        return orderDateTime.toLocalDate().format(formatter);
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public void printDetails() {
        System.out.println("Order Details:");
        System.out.println("Order ID: " + orderID);
        System.out.println("User ID: " + userID);
        System.out.println("Seller ID: " + sellerID);
        System.out.println("Product ID: " + productID);
        System.out.println("Product Name: " + productName);
        System.out.println("Order Date and Time: " + orderDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Delivery Address: " + address);
        System.out.println("Is Paid: " + isPaid);
        System.out.println("Order Status: " + orderStatus);
    }
}
