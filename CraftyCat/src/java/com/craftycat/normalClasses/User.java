package com.craftycat.normalClasses;


public class User {
    
    private int UserID;
    private String UserName;
    private String Email;
    private String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String State;
    private int Phone;
    private String UserRole;     
    
    
    public void printUserInfo() {
        System.out.println("UserID: " + UserID);
        System.out.println("UserName: " + UserName);
        System.out.println("Email: " + Email);
        System.out.println("FirstName: " + FirstName);
        System.out.println("LastName: " + LastName);
        System.out.println("Address: " + Address);
        System.out.println("City: " + City);
        System.out.println("State: " + State);
        System.out.println("Phone: " + Phone);
        System.out.println("UserRole: " + UserRole);
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }

    public int getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getEmail() {
        return Email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public int getPhone() {
        return Phone;
    }

    public String getUserRole() {
        return UserRole;
    }
    
}
