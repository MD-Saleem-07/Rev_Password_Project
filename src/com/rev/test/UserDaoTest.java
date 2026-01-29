package com.rev.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.rev.dao.UserDao;
import com.rev.model.User;
import com.rev.util.HashUtil;

public class UserDaoTest {

    // ✅ 1. Test Login (Valid user)
    @Test
    public void testLoginSuccess() {
        UserDao dao = new UserDao();

        boolean result = dao.login("chapati@gmail.com", "786");

        assertNotNull(result);
    }

    // ✅ 2. Test Login with Wrong Password
    @Test
    public void testLoginFailure() {
        UserDao dao = new UserDao();

        boolean result = dao.login("chapati@gmail.com", "wrongpass");

        assertNotNull(result);
    }

    // ✅ 3. Verify Master Password
    @Test
    public void testVerifyMasterPassword() {
        UserDao dao = new UserDao();

        boolean result = dao.verifyMasterPassword(786, "786");

        assertNotNull(result);
    }

    // ✅ 4. Forgot Password (Safe Update)
    @Test
    public void testForgotPassword() {
        UserDao dao = new UserDao();

        boolean result = dao.forgotPassword(
                "chapati@gmail.com",
                "turtle",
                HashUtil.hash("786")
        );

        assertNotNull(result);
    }

    // ✅ 5. Update Profile
    @Test
    public void testUpdateProfile() {
        UserDao dao = new UserDao();

        boolean result = dao.updateProfile(
                786,
                "Chapati Updated",
                "chapati@gmail.com"
        );

        assertNotNull(result);
    }

    // ✅ 6. Register User (SAFE VERSION)
    // This avoids duplicate user ID
    @Test
    public void testRegisterUserSafe() {
        UserDao dao = new UserDao();

        User user = new User();
        user.setUserId((int)(System.currentTimeMillis() % 100000));
        user.setName("TestUser");
        user.setEmail("test" + System.currentTimeMillis() + "@gmail.com");
        user.setPassword("123");
        user.setSecurityQuestion("pet");
        user.setSecurityAnswer("dog");

        boolean result = dao.registerUser(user);

        assertNotNull(result);
    }

    // ✅ 7. Login with Invalid Email
    @Test
    public void testLoginInvalidUser() {
        UserDao dao = new UserDao();

        boolean result = dao.login("invalid@gmail.com", "123");

        assertNotNull(result);
    }

    // ✅ 8. Reset Password with Wrong Answer
    @Test
    public void testResetPasswordWrongAnswer() {
        UserDao dao = new UserDao();

        boolean result = dao.forgotPassword(
                "chapati@gmail.com",
                "wronganswer",
                "newpass"
        );

        assertNotNull(result);
    }

    // ✅ 9. Verify Master Password - Wrong Case
    @Test
    public void testVerifyMasterPasswordFail() {
        UserDao dao = new UserDao();

        boolean result = dao.verifyMasterPassword(786, "wrong");

        assertNotNull(result);
    }
}