<%-- 
    Document   : homePage
    Created on : Feb 27, 2021, 1:23:45 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">



    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.fullName}"/>
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container-fluid">
                <a href="HomePageServlet">Car Rental</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">


                        <li>
                            <form class="d-flex" action="searchPage">
                                <input type="radio" name="a" value="name" style="margin-top: 25px" id="nameCheck" onclick="myFunction()" checked="checked"/>
                                <input class="form-control me-2" type="search" placeholder="car name" aria-label="Search" name="txtSearch" id="nameSearch">
                                <input type="radio" name="a" value="category" style="margin-top: 25px"  onclick="myFunction_2()" id="categoryCheck"/>
                                <c:set var="listCategory" value="${sessionScope.LISTCATEGORY}" scope="session"/>
                                <select name="Category" id="category">
                                    
                                    <c:forEach var="itemName" items="${listCategory}">
                                        <option>${itemName}</option>
                                    </c:forEach>

                                </select>

                                Pick up Date<input class="form-control me-2" type="date" placeholder="Pick up date" aria-label="Search" name="txtPickUpDate" style="margin-right: 2%" required="true">
                                Return Date<input class="form-control me-2" type="date" placeholder="Return date" aria-label="Search" name="txtReturnDate" required="true">
                                <input class="form-control me-2" type="number" placeholder="amount" aria-label="amount" name="txtAmount" required="true" min="1">


                                <button class="btn btn-outline-success" type="submit" name="btnAction" value="Search">Search</button>
                            </form>

                        </li>

                        <li>
                            <a class="btn btn-outline-success" href="${sessionScope.fullName != null ? "viewCart.jsp":"login.html"}" >Cart</a>
                        </li>


                        <li>

                            <c:if test="${empty fullname}">
                                <form action="loginHtml" method="post">
                                    <button type="submit" class="btn btn-danger ml-2">Login</button>
                                </form>
                            </c:if>

                        </li>

                        <c:if test="${not empty fullname}">
                            <font color="red">Welcome, ${fullname}</font>
                            <form action="Logout">
                                <button type="submit" class="btn btn-danger ml-2" name="btnAction" value="Logout">Logout</button>
                            </form>

                        </c:if>

                        <c:if test="${not empty fullname}">
                            <li>
                                <form action="OrderHistoryController">
                                    <button type="submit" class="btn btn-danger ml-2" name="btnAction" value="History">Order history</button>
                                </form>

                            </li>
                        </c:if>



                    </ul>



                </div>
            </div>
        </nav>







        <h2 style="margin-top: 8%;text-align: center">Connecting you to the biggest brands in car rental</h2>
        <script>

            if (document.getElementById("nameCheck").checked) {

                document.getElementById("category").disabled = true;
                document.getElementById("nameSearch").disabled = false;
            }

            function myFunction() {

                document.getElementById("category").disabled = true;
                document.getElementById("nameSearch").disabled = false;

            }
            function myFunction_2() {

                document.getElementById("nameSearch").disabled = true;
                document.getElementById("category").disabled = false;

            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
    </body>
</html>
