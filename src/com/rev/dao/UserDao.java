package com.rev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rev.model.User;
import com.rev.util.DBUtil;
import com.rev.util.HashUtil;

public class UserDao {

    // Register User
    public boolean registerUser(User user) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4,HashUtil.hash(user.getPassword()));
            ps.setString(5, user.getSecurityQuestion());
            ps.setString(6, user.getSecurityAnswer());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Login User
    public boolean login(String email, String password) {
    	boolean status = false;
        try {
            Connection con = DBUtil.getConnection();

            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, HashUtil.hash(password));

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
            	status=true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    
    public boolean updateProfile(int userId, String name, String email) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "UPDATE users SET name=?, email=? WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean resetPassword(String email, String answer, String newPassword) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "UPDATE users SET password=? WHERE email=? AND security_answer=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.setString(3, answer);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean verifyMasterPassword(int userId, String password) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "SELECT * FROM users WHERE user_id=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    public boolean forgotPassword(String email, String answer, String newPassword) {
        try {
            Connection con = DBUtil.getConnection();

            String sql = "UPDATE users SET password=? WHERE email=? AND security_answer=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.setString(3, answer);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
    
    
    
}