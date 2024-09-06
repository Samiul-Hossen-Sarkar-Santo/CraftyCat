
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success!!!</title>
    </head>
    <body>
        <style>

            :root {
                --bg-color: #fff;
                --main-color: #e55472;
                --text-color: #010101;
                --2nd-color: #434343;
                --other-color: #666666;
                --nav--color: #fdebdf;
                --full-bg: #efecec;
                --big-font: 5rem;
                --h2-font: 2.3rem;
                --p-font: 1.3rem;
            }

            body {
                margin: 0;
                padding: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                min-height: 100vh;
                background-color: var(--bg-color);
                flex-direction: column;
            }

            h1 {
                text-align: center;
                font-size: var(--h2-font);
                color: var(--text-color);
            }

            .home-button {
                display: inline-block;
                padding: 10px 20px;
                background-color: var(--main-color);
                color: #fff;
                text-decoration: none;
                border: none;
                border-radius: 14px;
                cursor: pointer;
                margin-top: 30px;
                margin-bottom: 30px;
            }
        </style>
        <div>
            <h1>The operation has been conducted successfully ^.^</h1>
        </div>
        
        <div class="home-button-container">
            <form action="SellerDashboardRedirectServlet" method="post">
                <input type="hidden" name="userID" value="<%= request.getParameter("userID")%>">
                <a href="sellerIndex.jsp" class="home-button">Go to Home</a>
            </form>
        </div>
    </body>
</html>
