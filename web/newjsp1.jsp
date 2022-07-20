<%-- 
    Document   : newjsp1
    Created on : Jul 20, 2022, 1:17:58 PM
    Author     : Tuan Be
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>QR Code in Java Servlet - viralpatel.net</title>
    </head>
    <body>
        <form action="GenerateQRCodeController" method="get">
            <p>Enter Text to create QR Code</p>
            <input type="text" name="qrtext" />
            <input type="submit" value="Generate QR Code" />
        </form>
    </body>
</html>
