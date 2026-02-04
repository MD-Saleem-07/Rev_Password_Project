package com.rev.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.rev.model.User;
import com.rev.util.DBUtil;
import com.rev.util.HashUtil;

public class UserDao {

    private static Logger logger = Logger.getLogger(UserDao.class.getName());

    //  LOGGER SETUP 
    static {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdir(); // create logs folder
            }

            FileHandler fh = new FileHandler("logs/app.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);

        } catch (Exception e) {
            System.out.println(" Logging setup failed: " + e.getMessage());
        }
    }

    // ================= REGISTER =================
    public boolean registerUser(User user) {
        logger.info("Register method started");

        String sql = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, HashUtil.hash(user.getPassword()));
            ps.setString(5, user.getSecurityQuestion());
            ps.setString(6, user.getSecurityAnswer());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            logger.severe("Register failed: " + e.getMessage());
            return false;
        }
    }

    // ================= CHECK USER ID =================
    public boolean isUserIdExists(int userId) {
        String sql = "SELECT 1 FROM users WHERE user_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeQuery().next();

        } catch (Exception e) {
            return false;
        }
    }

    // ================= CHECK EMAIL =================
    public boolean isEmailExists(String email) {
        String sql = "SELECT 1 FROM users WHERE email=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            return ps.executeQuery().next();

        } catch (Exception e) {
            return false;
        }
    }

    // ================= LOGIN =================
    public boolean login(String email, String password) {
        logger.info("Login attempt for email: " + email);

        String sql = "SELECT * FROM users WHERE email=? AND password=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, HashUtil.hash(password));

            return ps.executeQuery().next();

        } catch (Exception e) {
            logger.severe("Login error: " + e.getMessage());
            return false;
        }
    }

    // ================= UPDATE PROFILE =================
    public boolean updateProfile(int userId, String name, String email) {

        String sql = "UPDATE users SET name=?, email=? WHERE user_id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    // ================= VERIFY MASTER PASSWORD =================
    public boolean verifyMasterPassword(int userId, String password) {

        String sql = "SELECT * FROM users WHERE user_id=? AND password=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, HashUtil.hash(password));

            return ps.executeQuery().next();

        } catch (Exception e) {
            return false;
        }
    }

    // ================= FORGOT PASSWORD =================
    public boolean forgotPassword(String email, String answer, String newPassword) {

        String sql = "UPDATE users SET password=? WHERE email=? AND security_answer=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, HashUtil.hash(newPassword)); // ✅ FIXED
            ps.setString(2, email);
            ps.setString(3, answer);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSecurityAnswerCorrect(String email, String answer) {

        String sql = "SELECT 1 FROM users WHERE email=? AND security_answer=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, answer);
            return ps.executeQuery().next();

        } catch (Exception e) {
            return false;
        }
    }

}