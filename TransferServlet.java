
package com.banking.controller;
import com.banking.dao.UserDAO;
import com.banking.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String toUser = req.getParameter("toUser");
        double amount = Double.parseDouble(req.getParameter("amount"));

        try {
            UserDAO dao = new UserDAO();
            dao.transfer(user.getUsername(), toUser, amount);
            res.sendRedirect("dashboard.jsp");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
