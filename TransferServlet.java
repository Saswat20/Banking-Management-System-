package com.banking.controller;

import com.banking.dao.UserDAO;
import com.banking.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) { response.sendRedirect("index.jsp"); return; }
        
        String receiver = request.getParameter("receiver");
        double amount = Double.parseDouble(request.getParameter("amount"));
        
        UserDAO dao = new UserDAO();
        boolean success = dao.transferFunds(user.getId(), receiver, amount);
        
        if (success) {
            // Update session balance
            user = new User(user.getId(), user.getUsername(), dao.getBalance(user.getId()));
            session.setAttribute("user", user);
            response.sendRedirect("dashboard.jsp?msg=Transfer Successful");
        } else {
            response.sendRedirect("dashboard.jsp?error=Transfer Failed");
        }
    }
}
