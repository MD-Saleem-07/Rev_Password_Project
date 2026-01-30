package com.rev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.rev.model.PasswordEntry;
import com.rev.util.DBUtil;

public class PasswordDao {

    // Add Password
	public boolean addPassword(PasswordEntry p) {
	    String sql = "INSERT INTO passwords VALUES (?, ?, ?, ?, ?)";

	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, p.getPasswordId());
	        ps.setInt(2, p.getUserId());
	        ps.setString(3, p.getAccountName());
	        ps.setString(4, p.getUsername());
	        ps.setString(5, p.getPassword());

	        return ps.executeUpdate() > 0;

	    } catch (SQLIntegrityConstraintViolationException e) {
	        System.out.println("❌ Password ID already exists!");
	        return false;

	    } catch (Exception e) {
	        System.out.println("❌ Error while adding password.");
	        return false;
	    }
	}
    // View Passwords
    public void viewPasswords(int userId) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "SELECT * FROM passwords WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- SAVED PASSWORDS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1));
                System.out.println("Account: " + rs.getString(3));
                System.out.println("Username: " + rs.getString(4));
                System.out.println("Password: " + rs.getString(5));
                System.out.println("-----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Password
    public boolean deletePassword(int pid) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "DELETE FROM passwords WHERE password_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, pid);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    public void searchByAccount(int userId, String account) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "SELECT * FROM passwords WHERE user_id=? AND account_name=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, account);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Account: " + rs.getString("account_name"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean updatePassword(int passwordId, String newPassword) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "UPDATE passwords SET password=? WHERE password_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, newPassword);
            ps.setInt(2, passwordId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	
}