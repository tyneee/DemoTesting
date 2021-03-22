<%-- 
    Document   : login
    Created on : Feb 27, 2021, 11:58:05 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script type="text/javascript">
            var onloadCallback = function () {
                grecaptcha.render('html_element', {
                    'sitekey': '6LfGx2kaAAAAAE_zmitNO_Va6RO6gNAyZeKmPmdR'
                });
            };
        </script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    </head>
    <body>
        <a href="HomePageServlet">Car Rental</a>
        <c:set var="errorsRecaptcha" value="${requestScope.errorsRecaptcha}"/>
        <c:set var="ErrorVerify" value="${requestScope.ErrorVerify}"/>
        <c:set var="ErrorLogin" value="${requestScope.ErrorLogin}"/>
        <form action="loginServlet" method="post">
            <h1>Login Page</h1>
            Email <input type="text" name="txtEmail" value="" required="true"/> <br/>
            Password <input type="password" name="txtPassword" value="" required="true"/> <br/>

            <div id="html_element"></div>
            <br>
            <c:if test="${not empty errorsRecaptcha}">
                <font color="red">
                ${errorsRecaptcha} <br/>
                </font>
            </c:if>
            <c:if test="${not empty ErrorLogin}">
                <font color="red">
                ${ErrorLogin} <br/>
                </font>
            </c:if>
            <c:if test="${not empty ErrorVerify}">
                <font color="red">
                ${ErrorVerify} <br/>
                </font>
            </c:if>
            <input type="submit" value="Login" />
            <input  type="reset"  value="Reset" />

        </form>
        <form action="createNewAccount" method="post">
            <button type="submit" class="btn btn-danger ml-2">Create New Account</button>
        </form>
        <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
                async defer>
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js" integrity="sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js" integrity="sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG" crossorigin="anonymous"></script>
    </body>
</html>
