package com.rev.service;

import com.rev.dao.UserDao;
import com.rev.exception.InvalidInputException;
import com.rev.model.User;

public class UserService {

    // ✅ SINGLE DAO (used everywhere)
    private UserDao userDao;

    // ✅ DEFAULT CONSTRUCTOR (PROJECT RUNTIME)
    public UserService() {
        this.userDao = new UserDao();
    }

    // ✅ CONSTRUCTOR INJECTION (MOCKITO / TESTS)
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    // ===== USER ID VALIDATION =====
    public void validateUserId(int userId) {
        if (userId <= 0)
            throw new InvalidInputException("User ID must be positive");

        if (userDao.isUserIdExists(userId))
            throw new InvalidInputException("User ID already exists");
    }

    // ===== EMAIL VALIDATION =====
    public void validateEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new InvalidInputException("Invalid email format");

        if (userDao.isEmailExists(email))
            throw new InvalidInputException("Email already exists");
    }

    // ===== REGISTER =====
    public void register(User user) {
        if (!userDao.registerUser(user))
            throw new InvalidInputException("Registration failed");
    }

    // ===== LOGIN (USED BY PROJECT & TESTS) =====
    public void login(String email, String password) {

        if (email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            throw new InvalidInputException("Invalid email or password");
        }

        if (!userDao.login(email, password)) {
            throw new InvalidInputException("Invalid email or password");
        }
    }


    // ===== MASTER PASSWORD CHECK =====
    public void verifyMasterPassword(int userId, String password) {
        if (!userDao.verifyMasterPassword(userId, password))
            throw new InvalidInputException("Invalid master password");
    }

    // ===== UPDATE PROFILE =====
    public void updateProfile(int userId, String name, String email) {
        if (!userDao.updateProfile(userId, name, email))
            throw new InvalidInputException("Profile update failed");
    }

    // ===== FORGOT PASSWORD (OLD FLOW) =====
    public void forgotMasterPassword(
            String email,
            String answer,
            int enteredOtp,
            int generatedOtp,
            String newPassword
    ) {

        if (enteredOtp != generatedOtp)
            throw new InvalidInputException("Invalid OTP");

        if (!userDao.forgotPassword(email, answer, newPassword))
            throw new InvalidInputException("Security answer incorrect");
    }

    // ================= VALIDATIONS =================

    public void validateEmailForForgot(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
            throw new InvalidInputException("Invalid email format");

        if (!userDao.isEmailExists(email))
            throw new InvalidInputException("Email does not exist");
    }

    public void validateSecurityAnswer(String email, String answer) {
        if (!userDao.isSecurityAnswerCorrect(email, answer))
            throw new InvalidInputException("Incorrect security answer");
    }

    public void validateOtp(int enteredOtp, int generatedOtp) {
        if (enteredOtp != generatedOtp)
            throw new InvalidInputException("Invalid OTP");
    }

    // ===== FORGOT PASSWORD (NEW CLEAN FLOW) =====
    public void forgotMasterPassword1(
            String email,
            String answer,
            int enteredOtp,
            int generatedOtp,
            String newPassword
    ) {

        validateEmailForForgot(email);
        validateSecurityAnswer(email, answer);
        validateOtp(enteredOtp, generatedOtp);

        if (!userDao.forgotPassword(email, answer, newPassword))
            throw new InvalidInputException("Password reset failed");
    }
}
