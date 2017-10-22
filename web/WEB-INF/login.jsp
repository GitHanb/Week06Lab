<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remember Me Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            <p>Username:<input type="text" name="username" value="${username}"></p>
            <p>Password:<input type="password" name="password" value="${password}"></p>
            <input type="submit" value="Login">
            <p><input type="checkbox" name="remember" value="true" ${checked}>Remember me</p>
        </form>
            ${loginMessage}
    </body>
</html>
