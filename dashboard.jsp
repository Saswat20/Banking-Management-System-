
<%@ page import="com.banking.model.User" %>
<%
User user = (User) session.getAttribute("user");
%>
<html>
<body>
<h2>Welcome <%= user.getName() %></h2>
<p>Balance: ₹<%= user.getBalance() %></p>

<form action="transfer" method="post">
To User: <input name="toUser"><br>
Amount: <input name="amount"><br>
<button type="submit">Transfer</button>
</form>
</body>
</html>
