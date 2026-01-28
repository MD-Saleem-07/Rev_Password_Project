package com.rev.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rev.dao.UserDao;
import com.rev.model.User;
import com.rev.util.HashUtil;

public class UserDaoTest {

    @Test
    public void testLogin() {

        UserDao dao = new UserDao();

        // Existing user in DB
        String email = "chapati@gmail.com";
        String password = "786";   // plain password

        boolean result = dao.login(email, password);

        assertTrue(result);
    }
    
    @Test
    public void testRegister() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@gmail.com");
        user.setPassword(HashUtil.hash("123"));
        user.setSecurityQuestion("pet");
        user.setSecurityAnswer("dog");

        UserDao dao = new UserDao();
        boolean result = dao.registerUser(user);

        assertTrue(result);
    }
}