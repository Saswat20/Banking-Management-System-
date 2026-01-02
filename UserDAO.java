
package com.banking.dao;
import com.banking.config.DBConnection;
import com.banking.model.User;
import java.sql.*;

public class UserDAO {

    public User login(String username, String password) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM users WHERE username=? AND password=?"
        );
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setName(rs.getString("name"));
            u.setUsername(rs.getString("username"));
            u.setBalance(rs.getDouble("balance"));
            return u;
        }
        return null;
    }

    public void transfer(String fromUser, String toUser, double amount) throws Exception {
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        PreparedStatement debit = con.prepareStatement(
            "UPDATE users SET balance = balance - ? WHERE username=?"
        );
        debit.setDouble(1, amount);
        debit.setString(2, fromUser);

        PreparedStatement credit = con.prepareStatement(
            "UPDATE users SET balance = balance + ? WHERE username=?"
        );
        credit.setDouble(1, amount);
        credit.setString(2, toUser);

        debit.executeUpdate();
        credit.executeUpdate();
        con.commit();
    }
}
