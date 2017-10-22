<%-- 
    Document   : login
    Created on : 22-Oct-2017, 1:26:55 PM
    Author     : hanzh
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="username"%>
<%@attribute name="password"%>
<%@attribute name="checked"%>
<%@attribute name="loginMessage"%>

<%-- any content can be specified here e.g.: --%>
<h1>Login</h1>
<form action="login" method="post">
    <p>Username:<input type="text" name="username" value="${username}"></p>
    <p>Password:<input type="password" name="password" value="${password}"></p>
    <input type="submit" value="Login">
    <p><input type="checkbox" name="remember" value="true" ${checked}>Remember me</p>
</form>
${loginMessage}