# Crafty Cat - E-commerce Web Application

Crafty Cat is a robust e-commerce web application designed to provide a seamless online shopping experience. Built using a combination of Java, JSP, Servlets, MySQL, and various front-end technologies, Crafty Cat offers an interactive and user-friendly platform for both buyers and sellers.

## Project Overview

Crafty Cat integrates several technologies to deliver a complete e-commerce solution:

- **Java**: Handles server-side logic and application functionality.
- **JSP (JavaServer Pages)**: Generates dynamic web pages with embedded Java code.
- **Java Servlets**: Manages server-side requests and responses.
- **MySQL**: Manages data storage for users, products, and orders.
- **HTML, CSS, JavaScript**: Used for creating and styling the front-end user interface.
- **Apache Tomcat**: Acts as the web server and servlet container.

## Features

### User Authentication
- **Login & SignUp**: Secure login and registration for both sellers and buyers.
- **Session Management**: Includes login, default home page, and a logout option for session security.

### Dashboards
- **Seller Dashboard**: Manage product listings, view and update orders.
- **Buyer Dashboard**: Track order history and view order details.

### Shopping Cart & Checkout
- **Cart**: Add items to the cart, review selections, and proceed to checkout.
- **Receipt Generation**: Print receipts in PDF format for completed transactions.

### Security
- **Password Hashing and Salting**: User passwords are securely hashed and salted to protect user data.

## Technologies Used

- **Java**: Core application logic.
- **JSP (JavaServer Pages)**: Dynamic content generation.
- **Java Servlets**: Handling HTTP requests.
- **MySQL**: Database management.
- **HTML, CSS, JavaScript**: Front-end design and interactivity.
- **Apache Tomcat**: Web server and servlet container.

## Libraries

- **mysql-connector-j-8.0.33.jar**: JDBC driver for MySQL.
- **pdfbox-3.0.0.jar**: Core PDF processing functionality.
- **pdfbox-io-3.0.0.jar**: Additional IO-related functionality for PDFBox.
- **fontbox-3.0.0.jar**: Functionality for managing fonts in PDFs.
- **commons-logging-1.2.jar**: Logging framework abstraction.

## Installation & Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/craftycat.git
   ```

2. **Setup MySQL Database**:
   - Ensure MySQL is installed and running.
   - Import the provided SQL script into MySQL to create the `craftycat` database.
   - Update database connection details in `DatabaseConnection.java`:
     ```java
     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/craftycat", "root", "your_password");
     ```

3. **Add Required Libraries**:
   - Ensure the following JAR files are added to your NetBeans project libraries (you can find them in the External Libraries folder):
     - `mysql-connector-j-8.0.33.jar`
     - `pdfbox-3.0.0.jar`
     - `pdfbox-io-3.0.0.jar`
     - `fontbox-3.0.0.jar`
     - `commons-logging-1.2.jar`

4. **Deploy the Project**:
   - Open the project in NetBeans.
   - Build and deploy the project using Apache Tomcat.

## Project Structure

```
/src
   /com/craftycat/normalClasses    # Business logic and database connections
   /com/craftycat/servletClasses   # HTTP request handling (Java Servlets)
/web
   /css                            # Stylesheets
   /js                             # JavaScript files
   /pages                          # JSP pages for UI
   /PDF                            # Generated receipts in PDF format
```

## New Learnings

- **Troubleshooting**: Developed skills in diagnosing and resolving issues during development.
- **Hashing and Salting**: Implemented secure password storage techniques.
- **Frontend and Backend Integration**: Gained experience in integrating front-end and back-end technologies.
- **User Experience**: Enhanced understanding of user interface design and usability.
- **Project Management**: Improved skills in managing projects and collaborating with a team.

## Contributors

- **Samiul Hossen Sarkar Santo** - Project Lead
- **Mridula Mozid** - Developer
- **Nishat Tasneem** - Developer

