<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sait" uri="/WEB-INF/tlds/sait.tld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <sait:debug debug="${param.debug!=null}">
            Remote Host: ${pageContext.request.remoteHost}<br>
            Session ID: ${pageContext.session.id}
        </sait:debug>
        <h1>Home Page</h1>
        <p>Hello,${username}</p>
        <p><a href="login?action=logout" >Logout</a></p>
    </body>
</html>
