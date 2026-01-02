<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.banking.model.User"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect("index.jsp"); return; }
%>
<!DOCTYPE html>
<html>
<head><title>Dashboard</title></head>
<body>
    <h1>Welcome, <%= user.getUsername() %></h1>
    <h3>Current Balance: $<%= user.getBalance() %></h3>
    
    <hr>
    <h3>Transfer Funds</h3>
    <form action="TransferServlet" method="post">
        Receiver Username: <input type="text" name="receiver" required><br>
        Amount: <input type="number" name="amount" step="0.01" required><br>
        <button type="submit">Transfer</button>
    </form>
    
    <p style="color:green">${param.msg}</p>
    <p style="color:red">${param.error}</p>
    
    <br><a href="index.jsp">Logout</a>
</body>
</html>
