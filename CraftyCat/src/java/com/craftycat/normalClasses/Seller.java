package com.craftycat.normalClasses;

public class Seller extends User {

    private int sellerID;
    private String shopName;

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String getUserRole() {
        return super.getUserRole(); 
    }

    @Override
    public int getPhone() {
        return super.getPhone(); 
    }

    @Override
    public String getState() {
        return super.getState(); 
    }

    @Override
    public String getCity() {
        return super.getCity(); 
    }

    @Override
    public String getAddress() {
        return super.getAddress(); 
    }

    @Override
    public String getLastName() {
        return super.getLastName(); 
    }

    @Override
    public String getFirstName() {
        return super.getFirstName(); 
    }

    @Override
    public String getEmail() {
        return super.getEmail(); 
    }

    @Override
    public String getUserName() {
        return super.getUserName(); 
    }

    @Override
    public int getUserID() {
        return super.getUserID(); 
    }

    @Override
    public void setUserRole(String UserRole) {
        super.setUserRole(UserRole); 
    }

    @Override
    public void setPhone(int Phone) {
        super.setPhone(Phone); 
    }

    @Override
    public void setState(String State) {
        super.setState(State); 
    }

    @Override
    public void setCity(String City) {
        super.setCity(City); 
    }

    @Override
    public void setAddress(String Address) {
        super.setAddress(Address); 
    }

    @Override
    public void setLastName(String LastName) {
        super.setLastName(LastName); 
    }

    @Override
    public void setFirstName(String FirstName) {
        super.setFirstName(FirstName); 
    }

    @Override
    public void setEmail(String Email) {
        super.setEmail(Email); 
    }

    @Override
    public void setUserName(String UserName) {
        super.setUserName(UserName); 
    }

    @Override
    public void setUserID(int UserID) {
        super.setUserID(UserID); 
    }

    @Override
    public void printUserInfo() {
        super.printUserInfo(); 
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); 
    }

    @Override
    public String toString() {
        return super.toString(); 
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); 
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); 
    }

    @Override
    public int hashCode() {
        return super.hashCode(); 
    }

    public String getShopName() {
        return shopName;
    }
}
