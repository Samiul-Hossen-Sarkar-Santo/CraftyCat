<%@ page import="com.craftycat.normalClasses.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />

        <title>Crafty Cat</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" type="text/css" href="buyerIndex.css" />
        <link
            rel="stylesheet"
            href="https://unpkg.com/boxicons@latest/css/boxicons.min.css"
            />
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Leckerli+One&display=swap"
            rel="stylesheet"
            />
    </head>
    <body>
        <% User user = (User) session.getAttribute("user");%>
        <!--header section-->
        <header>
            <a href="#" class="logo">
                <img src="crlogo-removebg-preview.png" />
            </a>
            <ul class="navbar">
                <li><a href="buyerIndex.jsp">Home</a></li>
                <li><a href="ProductListServlet?userID=<%= user.getUserID()%>">Shop <i class="bx bx-caret-down"></i></a>
                    <ul class="dropdown">
                        <li><a href="pages.html">Bead Jewelry</a></li>
                        <li>
                            <a href="#">Art</a>
                        </li>
                        <li><a href="#">Pottery</a></li>
                        <li><a href="#">DIY</a></li>
                    </ul>
                </li>

                <li><a href="buyerIndex.jsp#blog">About Us</a></li>
                <li><a href="index.html">Logout</a></li>
            </ul>
            <div class="h-icons">
                <a href="checkout.jsp"><i class="bx bxs-shopping-bag"></i></a>
                <a href="userDataRetrievement?username=<%= session.getAttribute("userName") %>"><i class="bx bxs-user-circle"></i></a>
                <a href="wishlist.html"><i class="bx bxs-heart-circle"></i></a>
                <div class="bx bx-menu" id="menu-icon"></div>
            </div>
        </header>
        <!--home section-->
        <section class="Home">
            <div class="home-text">
                <h1>
                    Crafting Dreams & <br />
                    Happiness
                </h1>
                <p>
                    Craft whatever you like and discover a world of handmade treasures
                    crafted with love, <br />
                    all delivered to your doorstep across Bangladesh.
                </p>
                <a href="#" class="btn">Pick A Treasure</a>
            </div>
        </section>
        <!--banner section-->
        <section class="banner">
            <div class="banner-img">
                <img src="sale.png" />
            </div>

            <div class="banner-img">
                <img src="sale2.png" />
            </div>
            <div class="banner-img">
                <img src="sale3.png" />
            </div>
        </section>
        <!--new product section-->
        <section class="new product">
            <div class="center-text">
                <h2>New Arrivals</h2>
            </div>
            <div class="new-content">
                <div class="row">
                    <img src="art.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>

                <div class="row">
                    <img src="art2.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>

                <div class="row">
                    <img src="art3.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>

                <div class="row">
                    <img src="art4.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>

                <div class="row">
                    <img src="art5.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>

                <div class="row">
                    <img src="art6.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>
            </div>
        </section>
        <!--banner 02 section-->
        <section class="banner">
            <div class="banner-img">
                <img src="idk.png" />
            </div>

            <div class="banner-img">
                <img src="idk2.png" />
            </div>
        </section>

        <!--new product 02 section-->
        <section class="new product">
            <div class="center-text">
                <h2>Top Products</h2>
            </div>
            <div class="new-content">
                <div class="row">
                    <img src="art.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>

                <div class="row">
                    <img src="art5.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>

                <div class="row">
                    <img src="art3.png" />
                    <h4>SERENITY</h4>
                    <h5>$18.00</h5>
                    <div class="top">
                        <p>HOT</p>
                    </div>
                    <div class="bbtn">
                        <a href="#"> Add to cart</a>
                    </div>
                </div>
            </div>
        </section>
        <!--blog section-->
        <section class="blog">
            <div class="center-text" id="blog">
                <h2>About Us</h2>
            </div>

            <div class="blog-content">
                <div class="main-box">
                    <div class="box-img">
                        <img src="r.jpg" />
                    </div>
                    <div class="in-bxx">
                        <div class="in-text">
                            <h2>Nishat Tasneem</h2>
                        </div>
                        <div class="in-icon">
                            <a href="https://www.facebook.com/profile.php?id=100012598305266"
                               target="_blank"><i class="bx bxl-facebook-circle"></i
                                ></a>
                        </div>
                    </div>
                    <h3>
                        Hey! I'm Nishat Tasneem. I'm an undergraduate student in the Department of Information and 
                        Communication Technology at Bangladesh University of Professionals. 
                        I'm an art-enthusiastic person. I would love to follow along in my 
                        career where I can be creative with the codes and colors.
                    </h3>
                </div>

                <div class="main-box">
                    <div class="box-img">
                        <img src="s.jpg" />
                    </div>
                    <div class="in-bxx">
                        <div class="in-text">
                            <h2>Samiul Hossen Sarkar Santo</h2>
                        </div>
                        <div class="in-icon">
                            <a href="https://www.facebook.com/shamiulhossensanto"
                               target="_blank"><i class="bx bxl-facebook-circle"></i
                                ></a>
                        </div>
                    </div>

                    <h3>
                        Hey! I'm Samiul Hossen Sarkar Santo. I'm an undergraduate student in the 
                        Department of Information and Communication Technology at Bangladesh University of Professionals. 
                        I think teaching is a noble profession. I hope I can be one of them eventually. I'm also interested 
                        in start-ups. If I ever start one, it'll be named "Honululu".
                    </h3>
                </div>

                <div class="main-box">
                    <div class="box-img">
                        <img src="m.jpg" />
                    </div>
                    <div class="in-bxx">
                        <div class="in-text">
                            <h2>Mridula Mozid</h2>
                        </div>
                        <div class="in-icon">
                            <a href="https://www.facebook.com/MridulaMozid.Aunkita"
                               target="_blank"><i class="bx bxl-facebook-circle"></i
                                ></a>
                        </div>
                    </div>
                    <h3>
                        Hey! I'm Mridula Mozid. I'm an undergraduate student in the Department of 
                        Information and Communication Technology at Bangladesh University of 
                        Professionals. I aspire to be a full-stack developer.
                    </h3>
                </div>
            </div>
        </section>

        <!--contact section-->
        <section class="contact">
            <div class="contact-box">
                <h4>MY ACCOUNT</h4>
                <li><a href="#">My Account</a></li>
                <li><a href="#">CheckOut</a></li>
                <li><a href="#">Shopping Cart</a></li>
                <li><a href="#">Wishlist</a></li>
                <li><a href="#">Contact Us</a></li>
            </div>

            <div class="contact-box">
                <h4>QUICK LINKS</h4>
                <li><a href="#">Store Location</a></li>
                <li><a href="#">Orders Tracking</a></li>
                <li><a href="#">Size Guide</a></li>
                <li><a href="#">My Account</a></li>
                <li><a href="#">FAQs</a></li>
            </div>
            <div class="contact-box">
                <h4>INFORMATION</h4>
                <li><a href="#">Privacy Page</a></li>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Careers</a></li>
                <li><a href="#">Delivery Information</a></li>
                <li><a href="#">Terms & Conditions</a></li>
            </div>
            <div class="contact-box">
                <h4>CUSTOMER SERVICE</h4>
                <li><a href="#">Shipping Policy</a></li>
                <li><a href="#">Help & Contact Us</a></li>
                <li><a href="#">Returns & Refunds</a></li>
                <li><a href="#">Online Stores</a></li>
                <li><a href="#">Terms & Conditions</a></li>
            </div>
            <div class="contact-box">
                <h4>Crafty Cat</h4>
                <h5>Connect With Us</h5>
                <div class="social">
                    <a href=""><i class="bx bxl-facebook-circle"></i></a>
                    <a href=""><i class="bx bxl-instagram"></i></a>
                    <a href=""><i class="bx bxl-linkedin"></i></a>
                </div>
            </div>
        </section>
        <!--scroll-->
        <a href="#" class="scroll-top"><i class="bx bx-chevrons-up"></i></a>
        <div class="end-text">
            <p>© late 2023 BICE 2022(37,79,85). All Rights Reserved.</p>
        </div>

        <!--custom js link-->
        <script src="buyerIndex.js"></script>
    </body>
</html>
