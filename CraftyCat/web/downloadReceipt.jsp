<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Download Receipt</title>
    </head>
    <body>
        <h1 style="text-align: center">Payment Successful!!!</h1>
        <p style="text-align: center">Your receipt has been generated. Click the link below to download</p>

        <%
            String fileName = (String) request.getAttribute("fileName");
        %>
        <form action="DownloadServlet" method="post" style="text-align: center">
            <input type="hidden" name="fileName" value="<%= fileName %>">
            <button type="submit" class="add-to-cart-button">Download Receipt</button>
        </form>
        <div style="text-align: center">
            <a href="index.html"><button class="add-to-cart-button">Return to home</button></a>
        </div>

    </body>
</html>
<style>
    .add-to-cart-button {
        margin-top: 10px;
        padding: 5px 10px;
        background-color: #e55472;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    button:hover {
        background-color: #434343;
    }
</style>
