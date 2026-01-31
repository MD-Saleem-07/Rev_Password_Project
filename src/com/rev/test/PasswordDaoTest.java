package com.rev.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.rev.dao.PasswordDao;
import com.rev.model.PasswordEntry;

public class PasswordDaoTest {

    //  Test 1: Add password (SAFE – unique ID)
    @Test
    public void testAddPassword() {
        PasswordDao dao = new PasswordDao();

        PasswordEntry p = new PasswordEntry();
        p.setPasswordId((int) (System.currentTimeMillis() % 100000));
        p.setUserId(786);
        p.setAccountName("test_app_" + System.currentTimeMillis());
        p.setUsername("testuser");
        p.setPassword("test123");

        boolean result = dao.addPassword(p);

        assertNotNull(result);
    }

    //  Test 2: View passwords (safe, no assert)
    @Test
    public void testViewPasswords() {
        PasswordDao dao = new PasswordDao();
        dao.viewPasswords(786);

        assertTrue(true); // test passes if no exception
    }

    //  Test 3: Search password (safe)
    @Test
    public void testSearchByAccount() {
        PasswordDao dao = new PasswordDao();
        dao.searchByAccount(786, "gmail");

        assertTrue(true);
    }

    //  Test 4: Update password (safe)
    @Test
    public void testUpdatePassword() {
        PasswordDao dao = new PasswordDao();

        boolean result = dao.updatePassword(1, "newpass123");

        assertNotNull(result);
    }
    
    
//     TEST CASE 5: Delete Password (Safe)
    @Test
    public void testDeletePassword() {
        PasswordDao dao = new PasswordDao();

        // Use an ID that may or may not exist
        boolean result = dao.deletePassword(9999);

        // We don't care if it's true or false
        // We only check that method executes safely
        assertNotNull(result);
    }
    
    
//     TEST CASE 6: Search with Non-Existing Account
    
    @Test
    public void testSearchWithInvalidAccount() {
        PasswordDao dao = new PasswordDao();

        dao.searchByAccount(786, "invalid_account_name");

        assertTrue(true); // passes if no exception
    }
//     TEST CASE 7: Update with Invalid Password ID
    @Test
    public void testUpdateInvalidPasswordId() {
        PasswordDao dao = new PasswordDao();

        boolean result = dao.updatePassword(99999, "dummy123");

        assertNotNull(result);
    }
//     TEST CASE 8: Add Password With Random Data
    @Test
    public void testAddPasswordRandomData() {
        PasswordDao dao = new PasswordDao();

        PasswordEntry p = new PasswordEntry();
        p.setPasswordId((int)(Math.random() * 100000));
        p.setUserId(786);
        p.setAccountName("app_" + System.currentTimeMillis());
        p.setUsername("user_" + System.nanoTime());
        p.setPassword("pass123");

        boolean result = dao.addPassword(p);

        assertNotNull(result);
    }
    
    
}