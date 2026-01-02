
package com.banking.controller;
import com.banking.dao.UserDAO;
import com.banking.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            UserDAO dao = new UserDAO();
            User user = dao.login(username, password);

            if(user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                res.sendRedirect("dashboard.jsp");
            } else {
                res.sendRedirect("index.jsp?error=1");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
