<%-- 
    Document   : login
    Created on : 2-Oct-2017, 11:55:02 AM
    Author     : 636334
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Remember Me Login Page</h1>
        <div>
            <form action="login" method="post">
                Username: <input type="text" name="username" value="${user.username}"><br>
                Password: <input type="text" name="password" value="${user.password}"><br>
                <input type="submit" value="Login">  
            </form><br>
            <input type="checkbox" name="remember" value="true">Remember me<br><br>
            ${Message}
        </div>
    </body>
</html>
