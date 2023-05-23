<%-- 
    Document   : display_emp
    Created on : 25 avr. 2023, 08:38:55
    Author     : tonymushah
--%>
<%@page  import="models.Employee"%>
<%@page  import="etu001844.framework.FileUpload"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            Employee data = (Employee) request.getAttribute("data");
        %>
        <h2>Name : <%= data.getName() %></h2>
        <h2>Filename : <%= data.getFile() %></h2>
    </body>
</html>
