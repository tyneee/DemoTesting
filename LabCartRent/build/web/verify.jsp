<%-- 
    Document   : verify
    Created on : Mar 1, 2021, 3:57:58 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="errorsCode" value="${requestScope.errorCode}"/>
        Check your email and please input code verify: 
        <form action="verifyServlet" method="post">
            <input type="text" name="txtCode" value="" required="true"/>
            <input type="submit" value="Submit" /><br>
            <c:if test="${not empty errorsCode}">
                <font color="red">
                    ${errorsCode}
                </font>
            </c:if>


        </form>

    </body>
</html>
