<%-- 
    Document   : createNewAccount
    Created on : Feb 27, 2021, 10:46:54 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    </head>
    <body>
        <div>
            <h1> <a href="loginHtml" style="text-decoration: none;color: black;font-size: 40px">Login page</a></h1>
        </div>
        <c:set var="error" value="${requestScope.CreateErr}"/>
        <form action="createNewAccountServlet" method="POST">
            Email <input type="text" name="txtEmail" value="${param.txtEmail}" /><br>
            <c:if test="${not empty error.emailLengthErr}">
                <font color="red">

                ${error.emailLengthErr}<br>
                </font>
            </c:if>
            <c:if test="${not empty error.invalidFormatEmail}">
                <font color="red">

                ${error.invalidFormatEmail}<br>
                </font>
            </c:if>
            Full Name  <input type="text" name="txtName" value="${param.txtName}" /><br>
            <c:if test="${not empty error.nameLengthErr}">
                <font color="red">

                ${error.nameLengthErr}<br>
                </font>
            </c:if>
            Password  <input type="password" name="txtPass" value=""/><br>
            <c:if test="${not empty error.passwordLengthErr}">
                <font color="red">

                ${error.passwordLengthErr}<br>
                </font>
            </c:if>
            Confirm password  <input type="password" name="txtConfirmPass" value=""/><br>
            <c:if test="${not empty error.passwordNotMatchErr}">
                <font color="red">

                ${error.passwordNotMatchErr}<br>
                </font>
            </c:if>
            Phone  <input type="text" name="txtPhone" value="${param.txtPhone}" /><br>
            <c:if test="${not empty error.phoneLengthErr}">
                <font color="red">

                ${error.phoneLengthErr}<br>
                </font>
            </c:if>
            <c:if test="${not empty error.invalidFormatPhone}">
                <font color="red">

                ${error.invalidFormatPhone}<br>
                </font>
            </c:if>
            Address  <input type="text" name="txtAddress" value="${param.txtAddress}" /><br>
            <c:if test="${not empty error.addressLengthErr}">
                <font color="red">

                ${error.addressLengthErr}<br>
                </font>
            </c:if>

            <c:if test="${not empty error.existEmailErr}">
                <font color="red">

                ${error.existEmailErr}<br>
                </font>
            </c:if>
            <input type="submit" value="Create" />
            <button class="btn btn-info btn-md"><a href="login.jsp" style="color:white;text-decoration:none;">Back</a></button>
        </form>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
    </body>
</html>
