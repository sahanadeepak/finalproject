<%-- 
    Document   : Login
    Created on : 12 Feb, 2016, 1:09:20 PM
    Author     : sumit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Here</h1>
        
        <form action="./login" method="post">
            <table>
                <tr>
                    <td>Email</td><td><input type="text" name="email" id="email"/></td>
                </tr>
                <tr>
                    <td>Pass</td><td><input type="password" name="pass" id="pass"/></td>
                </tr>
                <tr>
                    <td></td><td><input type="submit" name="submit" value="Login"/></td>
                </tr>
            </table>
        </form>
        
        
    </body>
</html>
