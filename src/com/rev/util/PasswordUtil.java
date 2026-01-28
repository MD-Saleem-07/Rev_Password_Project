package com.rev.util;

import java.util.Random;

public class PasswordUtil {

    public static String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#";
        StringBuilder pwd = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < length; i++) {
            pwd.append(chars.charAt(r.nextInt(chars.length())));
        }
        return pwd.toString();
    }
}