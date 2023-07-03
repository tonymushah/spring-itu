<%-- 
    Document   : counter
    Created on : 3 juil. 2023, 23:16:11
    Author     : tonymushah
--%>

<%@page import="models.Counter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scope Counter</title>
    </head>
    <body>
        <%
            Counter counter = (Counter) request.getAttribute("data");
        %>
        <h1>The number is : <%= counter.getNumber()%></h1>
        <form action="/testFramework/counter/decrement.do" >
            <input type="number" name="number" value="<%= counter.getLastDecrement()%>"/>
            <button>Decrement</button>
        </form>
        
        <form action="/testFramework/counter/increment.do">
            <input type="number" name="number" value="<%= counter.getLastIncrement()%>"/>
            <button>Increment</button>
        </form>
    </body>
</html>
