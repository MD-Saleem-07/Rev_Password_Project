package com.rev.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.rev.dao.UserDao;
import com.rev.exception.InvalidInputException;
import com.rev.model.User;
import com.rev.service.UserService;

public class UserServiceLoginTest {

    private UserDao userDao;
    private UserService userService;

    @Before
    public void setUp() {
        userDao = mock(UserDao.class);
        userService = new UserService(userDao);
    }

    // ================= LOGIN TEST CASES =================

    @Test
    public void testLoginSuccess() {
        when(userDao.login("user@gmail.com", "123")).thenReturn(true);
        userService.login("user@gmail.com", "123");
    }

    @Test
    public void testLoginWrongPassword() {
        when(userDao.login("user@gmail.com", "wrong")).thenReturn(false);

        try {
            userService.login("user@gmail.com", "wrong");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid email or password", e.getMessage());
        }
    }

    @Test
    public void testLoginNullEmail() {
        try {
            userService.login(null, "123");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid email or password", e.getMessage());
        }
    }

    @Test
    public void testLoginEmptyPassword() {
        try {
            userService.login("user@gmail.com", "");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid email or password", e.getMessage());
        }
    }

    // ================= VALIDATE USER ID =================

    @Test
    public void testValidateUserIdSuccess() {
        when(userDao.isUserIdExists(10)).thenReturn(false);
        userService.validateUserId(10);
    }

    @Test
    public void testValidateUserIdAlreadyExists() {
        when(userDao.isUserIdExists(10)).thenReturn(true);

        try {
            userService.validateUserId(10);
            fail();
        } catch (InvalidInputException e) {
            assertEquals("User ID already exists", e.getMessage());
        }
    }

    @Test
    public void testValidateUserIdNegative() {
        try {
            userService.validateUserId(-1);
            fail();
        } catch (InvalidInputException e) {
            assertEquals("User ID must be positive", e.getMessage());
        }
    }

    // ================= VALIDATE EMAIL =================

    @Test
    public void testValidateEmailSuccess() {
        when(userDao.isEmailExists("user@gmail.com")).thenReturn(false);
        userService.validateEmail("user@gmail.com");
    }

    @Test
    public void testValidateEmailInvalidFormat() {
        try {
            userService.validateEmail("usergmail.com");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid email format", e.getMessage());
        }
    }

    @Test
    public void testValidateEmailAlreadyExists() {
        when(userDao.isEmailExists("user@gmail.com")).thenReturn(true);

        try {
            userService.validateEmail("user@gmail.com");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Email already exists", e.getMessage());
        }
    }

    // ================= REGISTER =================

    @Test
    public void testRegisterSuccess() {
        User user = new User();
        when(userDao.registerUser(user)).thenReturn(true);
        userService.register(user);
    }

    @Test
    public void testRegisterFailure() {
        User user = new User();
        when(userDao.registerUser(user)).thenReturn(false);

        try {
            userService.register(user);
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Registration failed", e.getMessage());
        }
    }

    // ================= VERIFY MASTER PASSWORD =================

    @Test
    public void testVerifyMasterPasswordSuccess() {
        when(userDao.verifyMasterPassword(1, "master123")).thenReturn(true);
        userService.verifyMasterPassword(1, "master123");
    }

    @Test
    public void testVerifyMasterPasswordFailure() {
        when(userDao.verifyMasterPassword(1, "wrong")).thenReturn(false);

        try {
            userService.verifyMasterPassword(1, "wrong");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid master password", e.getMessage());
        }
    }

    // ================= UPDATE PROFILE =================

    @Test
    public void testUpdateProfileSuccess() {
        when(userDao.updateProfile(1, "Saleem", "user@gmail.com"))
                .thenReturn(true);

        userService.updateProfile(1, "Saleem", "user@gmail.com");
    }

    @Test
    public void testUpdateProfileFailure() {
        when(userDao.updateProfile(1, "Saleem", "user@gmail.com"))
                .thenReturn(false);

        try {
            userService.updateProfile(1, "Saleem", "user@gmail.com");
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Profile update failed", e.getMessage());
        }
    }

    // ================= FORGOT PASSWORD (OLD FLOW) =================

    @Test
    public void testForgotMasterPasswordSuccess() {
        when(userDao.forgotPassword("user@gmail.com", "ans", "new123"))
                .thenReturn(true);

        userService.forgotMasterPassword(
                "user@gmail.com", "ans", 1111, 1111, "new123"
        );
    }

    @Test
    public void testForgotMasterPasswordInvalidOtp() {
        try {
            userService.forgotMasterPassword(
                    "user@gmail.com", "ans", 1111, 2222, "new123"
            );
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Invalid OTP", e.getMessage());
        }
    }

    // ================= FORGOT PASSWORD (NEW FLOW) =================

    @Test
    public void testForgotMasterPassword1Success() {
        when(userDao.isEmailExists("user@gmail.com")).thenReturn(true);
        when(userDao.isSecurityAnswerCorrect("user@gmail.com", "ans")).thenReturn(true);
        when(userDao.forgotPassword("user@gmail.com", "ans", "new123"))
                .thenReturn(true);

        userService.forgotMasterPassword1(
                "user@gmail.com", "ans", 1111, 1111, "new123"
        );
    }

    @Test
    public void testForgotMasterPassword1WrongAnswer() {
        when(userDao.isEmailExists("user@gmail.com")).thenReturn(true);
        when(userDao.isSecurityAnswerCorrect("user@gmail.com", "ans")).thenReturn(false);

        try {
            userService.forgotMasterPassword1(
                    "user@gmail.com", "ans", 1111, 1111, "new123"
            );
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Incorrect security answer", e.getMessage());
        }
    }
}
