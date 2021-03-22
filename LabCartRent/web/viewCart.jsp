

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <link href="css/homePage.css" rel="stylesheet">
    </head>
    <body>
        <c:set var="fullname" value="${sessionScope.fullName}"/>
        <c:if test="${empty fullname}">
            <c:redirect url="MoveToLoginPageController"/>
        </c:if>


        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a href="HomePageServlet">Car Rental</a>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                        <li>

                            <c:if test="${empty fullname}">
                                <a href="login.html"> <button type="button" class="btn btn-danger ml-2">Login</button></a>
                            </c:if>

                        </li>

                        <c:if test="${not empty fullname}">
                            <font color="red">Welcome, ${fullname}</font>
                            <form action="DispatchServlet">
                                <button type="submit" class="btn btn-danger ml-2" name="btnAction" value="Logout">Logout</button>
                            </form>

                        </c:if>

                        <c:if test="${not empty fullname}">
                            <li>
                                <form action="DispatchServlet">
                                    <button type="submit" class="btn btn-danger ml-2" name="btnAction" value="History">Order history</button>
                                </form>

                            </li>
                        </c:if>




                    </ul>

                </div>
            </div>
        </nav>

        <c:set var="CART" value="${sessionScope.CUSTCART}"/>

        <c:if test="${not empty sessionScope}">
            <c:set var="item" value="${CART.finalList}"/>
            <c:set var="item2" value="${CART.finalQuantity}"/>
            <c:set var="total" value="${0}" scope="session"/>
        </c:if>

        <c:if test="${not empty item}">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">OrderID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Image</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Pick up date</th>
                        <th scope="col">Return date</th>
                        <th scope="col">Total</th>
                        <th scope="col" class="actions">Action</th>
                    </tr>
                </thead>
                <tbody>

                    <c:if test="${not empty requestScope.error}">

                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>${requestScope.error}</strong> 
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>

                </c:if>
                <c:if test="${not empty requestScope.quantityError}">

                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>${requestScope.quantityError}</strong> 
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>

                </c:if>
                <c:forEach var="dto" items="${item}" varStatus="counter">
                    <form action="countQuantity">
                        <tr>
                            <th scope="row">${counter.count}</th>
                            <td>
                                ${dto.carName}
                                <input type="hidden" name="productID" value="${dto.carID}"/>
                            </td>
                            <td>  <img src="  ${dto.image}" class="card-img-top" alt="..." style="width:100px;height: 100px" ></td>

                            <td>


                                <span><button type="submit" id="countdown" class="btn btn-danger" name="btnAction" value="countDownQuantity" >-</button></span>${item2[counter.index]} <span><button type="submit" class="btn btn-success" name="btnAction" value="countUpQuantity">+</button></span> 

                                <input type="hidden" name="quantityIncart" value="${item2[counter.index]}" />


                            </td>

                            <td>
                                ${dto.pickUpDate}
                                <input type="hidden" name="txtpick" value="${dto.pickUpDate}" />
                            </td>

                            <td>
                                ${dto.returnDate}
                                <input type="hidden" name="txtreturn" value="${dto.returnDate}" />
                            </td>

                            <td>
                                ${item2[counter.index] * dto.price}
                                <c:set var="total" value="${total + item2[counter.index] * dto.price}" scope="session"/>
                            </td>





                            <td>
                                <button class="btn btn-danger delete " type="submit" value="Remove Selected Items" name="btnAction" onclick="return confirm('Ok to Remove?');">Delete</button>




                                <input type="hidden" name="chkItem" value="${dto.carID}" />     
                            </td>
                        </tr>
                    </form>
                </c:forEach>

            </tbody>
        </table>



        <c:set var="totals" value=" ${total}" scope="session"/>
        <c:if test="${ empty requestScope.applyCode}">
            <h2 style="color: red;text-align: center" id="totalPrice">Total: ${totals}</h2>
        </c:if> 
        <c:if test="${not empty requestScope.applyCode}">
            <h2 style="color: red;text-align: center" id="totalPrice">Total: ${sessionScope.newPrice}</h2>
        </c:if>   
        <form action="DiscountServlet">
            Input voucher: <input type="text" name="txtVoucher" value="${param.txtVoucher}" required="true"/>    
            <input type="submit" value="Submit" />
        </form>
        <c:if test="${not empty requestScope.ErrorsCode1}">
            <font color="red">
            ${requestScope.ErrorsCode1}

            </font>
        </c:if>
        <c:if test="${not empty requestScope.ErrorsCode2}">
            <font color="red">
            ${requestScope.ErrorsCode2}

            </font>
        </c:if>
        <c:if test="${not empty requestScope.applyCode}">
            <font color="red">
            ${requestScope.applyCode} ${sessionScope.percent}%

            </font>
        </c:if>

        <!--<button type="submit" class="btn btn-danger checkout" value="checkout" name="btnAction" style="text-align: center">Check Out</button>-->    
        <a href="ChekQuantityRemainController_2" class="btn btn-danger checkout">Check out</a>

    </c:if>

    <c:if test="${empty item}" >
        <h2>No Cart to view</h2>
    </c:if>

    <!--<h1 style="margin-top: 50px;text-align: center" >Checkout with Paypal</h1>-->

    <hr>       

    <c:if test="${not empty item}">
        <form action="chekQuantityRemainController_2" method="post">



            <input type="hidden" name="total" value="${total}" >




        </form>
    </c:if>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js" integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js" integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj" crossorigin="anonymous"></script>
</body>
</html>
