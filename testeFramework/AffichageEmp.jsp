<%-- 
    Document   : AffichageEmp.jsp
    Created on : 28 mars 2023, 07:48:12
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= session.getAttribute("attribut") %></h1>
    </body>
</html>
