<%-- 
    Document   : newjsp
    Created on : Jul 18, 2022, 9:59:58 AM
    Author     : Tuan Be
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String us = (String) request.getAttribute("USERID");
            String ev = (String) request.getAttribute("EVTID");%>
        <h1><%=us%></h1>
        <h1><%=ev%></h1>
    </body>
</html>
