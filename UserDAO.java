package com.banking.dao;

import com.banking.config.DBConnection;
import com.banking.model.User;
import java.sql.*;

public class UserDAO {

    public User validateUser(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getDouble("balance"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean transferFunds(int senderId, String receiverUsername, double amount) {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // Begin Transaction

            // Check receiver existence
            PreparedStatement psCheck = con.prepareStatement("SELECT id FROM users WHERE username=?");
            psCheck.setString(1, receiverUsername);
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()) return false;
            int receiverId = rs.getInt("id");

            // Deduct from sender
            PreparedStatement psDed = con.prepareStatement("UPDATE users SET balance = balance - ? WHERE id = ? AND balance >= ?");
            psDed.setDouble(1, amount);
            psDed.setInt(2, senderId);
            psDed.setDouble(3, amount);
            int rows1 = psDed.executeUpdate();

            if (rows1 == 0) { con.rollback(); return false; } // Insufficient funds

            // Add to receiver
            PreparedStatement psAdd = con.prepareStatement("UPDATE users SET balance = balance + ? WHERE id = ?");
            psAdd.setDouble(1, amount);
            psAdd.setInt(2, receiverId);
            psAdd.executeUpdate();

            // Log Transaction
            PreparedStatement psLog = con.prepareStatement("INSERT INTO transactions (sender_id, receiver_id, amount) VALUES (?, ?, ?)");
            psLog.setInt(1, senderId);
            psLog.setInt(2, receiverId);
            psLog.setDouble(3, amount);
            psLog.executeUpdate();

            con.commit();
            return true;
        } catch (Exception e) {
            try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        } finally {
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    // Helper to refresh balance
    public double getBalance(int userId) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT balance FROM users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getDouble("balance");
        } catch(Exception e) { e.printStackTrace(); }
        return 0.0;
    }
}
