<%-- 
    Document   : searchPage
    Created on : Mar 3, 2021, 10:12:54 PM
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
        <c:set var="list" value="${sessionScope.listSearchByName}"/>
        <c:set var="list_2" value="${sessionScope.listSearchByCategory}"/>
        <c:set var="errorDate" value="${requestScope.errorDate}"/>


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
                                <input class="form-control me-2" type="text" placeholder="car name" aria-label="Search" name="txtSearch" id="nameSearch" value="${param.txtSearch}">
                                <input type="radio" name="a" value="category" style="margin-top: 25px"  onclick="myFunction_2()" id="categoryCheck"/>
                                <c:set var="listCategory" value="${sessionScope.LISTCATEGORY}" scope="session"/>
                                <select name="Category" id="category">
                                    <c:set var="categoryCurent" value="${sessionScope.CATEGORY}" scope="session"/>

                                    <c:forEach var="itemName" items="${listCategory}">
                                        <c:if test="${itemName == categoryCurent}">

                                            <option selected="selected">${itemName}</option>
                                        </c:if>
                                        <c:if test="${itemName != categoryCurent}">

                                            <option>${itemName}</option>
                                        </c:if>


                                    </c:forEach>



                                </select>



                                Pick up Date<input class="form-control me-2" type="date" placeholder="Pick up date" aria-label="Search" name="txtPickUpDate" style="margin-right: 2%" required="true" value="${param.txtPickUpDate}">
                                Return Date<input class="form-control me-2" type="date" placeholder="Return date" aria-label="Search" name="txtReturnDate" required="true" value="${param.txtReturnDate}">
                                <input class="form-control me-2" type="number" placeholder="amount" aria-label="amount" name="txtAmount" required="true" min="1" value="${param.txtAmount}">


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
            <c:if test="${not empty errorDate}">
                <font color="red">
                ${errorDate}
                </font>
            </c:if>
        </nav>





        <c:set var="actives" value="active"/>
        <c:if test="${not empty list}">
            <div class="row row-cols-1 row-cols-md-3 g-4 mt-4">

                <c:forEach var="dto" items="${list}">

                    <form action="AddItemTocartServlet">

                        <div class="col">
                            <div class="card h-100">
                                <img src="${dto.image}" class="card-img-top" alt="..." style="height: 350px">  
                                <div class="card-body">
                                    <h5 class="card-title">Name: ${dto.carName}</h5>
                                    <p class="card-text">Description: ${dto.description}</p>
                                    <p class="card-text">Color: ${dto.color}</p>
                                    <p class="card-text">Price: ${dto.price}₫</p>
                                    <p class="card-text">Quantity Remain: ${dto.quantityOfRemain}</p>
                                    <p class="card-text">Year of create: ${dto.yearOfCreate}</p>
                                    <p class="card-text">Rate: ${dto.rate} stars</p>
                                    <input type="hidden" name="carID" value="${dto.carID}" />
                                    <input type="hidden" name="carName" value="${dto.carName}" />
                                    <input type="hidden" name="image" value="${dto.image}" />
                                    <input type="hidden" name="price" value="${dto.price}" />
                                    <input type="hidden" name="quantityRemain" value="${dto.quantityOfRemain}" />
                                    <input type="hidden" name="categoryID" value="${dto.categoryID}" />


                                    <button class="btn btn-primary"  value="addItem" type="submit">Add to cart</button>
                                </div>
                            </div>
                        </div>

                    </form>

                </c:forEach>
                <c:set var="lastSearchValue" value="${param.txtSearch}" scope="session"/>
                <c:set var="lastSearchCategory" value="${param.Category}" scope="session"/>
                <c:set var="lastPickUpDate" value="${param.txtPickUpDate}" scope="session"/>
                <c:set var="lastReturnDate" value="${param.txtReturnDate}" scope="session"/>
                <c:set var="lastAmount" value="${param.txtAmount}" scope="session"/>
                <c:set var="lastRadioName" value="${param.a}" scope="session"/>
            </div>
        </c:if>

        <c:if test="${not empty list_2}">
            <script>
                document.getElementById('nameSearch').disabled = true;
            </script>
            <div class="row row-cols-1 row-cols-md-3 g-4 mt-4">

                <c:forEach var="dto" items="${list_2}">

                    <form action="AddItemTocartServlet">

                        <div class="col">
                            <div class="card h-100">
                                <img src="${dto.image}" class="card-img-top" alt="..." style="height: 350px">  
                                <div class="card-body">
                                    <h5 class="card-title">Name: ${dto.carName}</h5>
                                    <p class="card-text">Description: ${dto.description}</p>
                                    <p class="card-text">Color: ${dto.color}</p>
                                    <p class="card-text">Price: ${dto.price}₫</p>
                                    <p class="card-text">Quantity Remain: ${dto.quantityOfRemain}</p>
                                    <p class="card-text">Year of create: ${dto.yearOfCreate}</p>
                                    <p class="card-text">Rate: ${dto.rate} stars</p>
                                    <input type="hidden" name="carID" value="${dto.carID}" />
                                    <input type="hidden" name="carName" value="${dto.carName}" />
                                    <input type="hidden" name="image" value="${dto.image}" />
                                    <input type="hidden" name="price" value="${dto.price}" />
                                    <input type="hidden" name="quantityRemain" value="${dto.quantityOfRemain}" />
                                    <input type="hidden" name="categoryID" value="${dto.categoryID}" />


                                    <button class="btn btn-primary"  value="addItem" type="submit">Add to cart</button>
                                </div>
                            </div>
                        </div>

                    </form>

                </c:forEach>
                <c:set var="lastSearchValue" value="${param.txtSearch}" scope="session"/>
                <c:set var="lastSearchCategory" value="${param.Category}" scope="session"/>
                <c:set var="lastPickUpDate" value="${param.txtPickUpDate}" scope="session"/>
                <c:set var="lastReturnDate" value="${param.txtReturnDate}" scope="session"/>
                <c:set var="lastAmount" value="${param.txtAmount}" scope="session"/>
                <c:set var="lastRadioName" value="${param.a}" scope="session"/>
            </div>
        </c:if>

        <c:set var="pageNumber" value="${sessionScope.countPageSearchByName}"/>
        <c:set var="pageNumber_2" value="${sessionScope.countPageSearchByCategory}"/>
        <c:set var="currentIndex" value="${requestScope.INDEXCURRENT}"/>
        <c:if test="${not empty pageNumber}">
            <nav aria-label="Page navigation example" style="margin-left: 47%">
                <ul class="pagination mt-4 ml-5">

                    <c:forEach var="i" begin="1" end="${pageNumber}">    


                        <li class="page-item">
                            <a class="page-link" href="searchPage?a=name&txtSearch=${lastSearchValue}&txtPickUpDate=${lastPickUpDate}&txtReturnDate=${lastReturnDate}&txtAmount=${lastAmount}&index=${i}">${i}</a>
                        </li>


                        <%--<c:set var="currentIndex" value="${i}"/>--%>
                    </c:forEach>     

                </ul>
            </nav>
        </c:if>

        <c:if test="${not empty pageNumber_2}">
            <nav aria-label="Page navigation example" style="margin-left: 47%">
                <ul class="pagination mt-4 ml-5">

                    <c:forEach var="i" begin="1" end="${pageNumber_2}">    



                        <li class="page-item">
                            <a class="page-link" href="searchPage?a=category&Category=${lastSearchCategory}&txtPickUpDate=${lastPickUpDate}&txtReturnDate=${lastReturnDate}&txtAmount=${lastAmount}&index=${i}">${i}</a>
                        </li>



                        <%--<c:set var="currentIndex" value="${i}"/>--%>
                    </c:forEach>     

                </ul>
            </nav>
        </c:if>

    

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
