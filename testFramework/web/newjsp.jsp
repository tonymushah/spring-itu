<%-- 
    Document   : newjsp
    Created on : 4 avr. 2023, 09:19:55
    Author     : tonymushah
--%>

<%@page import="java.util.List"%>
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
            List<String> noms = (List<String>) request.getAttribute("noms");
        %>
        <ul>
            <%
                for(String nom : noms){
                    %>
                    <li><%= nom%></li>
                    <%
                }
            %>
        </ul>
    </body>
</html>
