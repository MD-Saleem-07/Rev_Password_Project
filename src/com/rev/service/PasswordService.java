package com.rev.service;

import com.rev.dao.PasswordDao;
import com.rev.exception.InvalidInputException;
import com.rev.model.PasswordEntry;

public class PasswordService {

    private PasswordDao passwordDao = new PasswordDao();

    // ===== PASSWORD ID VALIDATION =====
    public void validatePasswordId(int passwordId) {

        if (passwordId <= 0)
            throw new InvalidInputException("Password ID must be positive");

        if (passwordDao.isPasswordIdExists(passwordId))
            throw new InvalidInputException("Password ID already exists");
    }

    // ===== ADD PASSWORD =====
    public void addPassword(PasswordEntry p) {

        if (!passwordDao.addPassword(p))
            throw new InvalidInputException("Failed to add password");
    }

    public void viewPasswords(int userId) {
        passwordDao.viewPasswords(userId);
    }

    public void updatePassword(int id, String pass) {

        if (!passwordDao.updatePassword(id, pass))
            throw new InvalidInputException("Password update failed");
    }

    public void deletePassword(int id) {

        if (!passwordDao.deletePassword(id))
            throw new InvalidInputException("Password delete failed");
    }
}
