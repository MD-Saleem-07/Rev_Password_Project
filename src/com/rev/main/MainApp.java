package com.rev.main;

import java.util.Scanner;

import com.rev.dao.PasswordDao;
import com.rev.dao.UserDao;
import com.rev.model.PasswordEntry;
import com.rev.model.User;
import com.rev.util.PasswordUtil;
import com.rev.util.VerificationUtil;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDao userDao = new UserDao();
        PasswordDao passDao = new PasswordDao();

        while (true) {

            System.out.println("\n===== REV PASSWORD MANAGER =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            // ---------------- REGISTER ----------------
            if (choice == 1) {
                User u = new User();

                System.out.print("User ID: ");
                u.setUserId(sc.nextInt());

                System.out.print("Name: ");
                u.setName(sc.next());

                System.out.print("Email: ");
                u.setEmail(sc.next());

                System.out.print("Password: ");
                u.setPassword(sc.next());

                System.out.print("Security Question: ");
                u.setSecurityQuestion(sc.next());

                System.out.print("Security Answer: ");
                u.setSecurityAnswer(sc.next());

                if (userDao.registerUser(u))
                    System.out.println("✅ Registration Successful");
                else
                    System.out.println("❌ Registration Failed");
            }

            // ---------------- LOGIN ----------------
            else if (choice == 2) {

                System.out.print("Email: ");
                String email = sc.next();

                System.out.print("Password: ");
                String password = sc.next();

                if (userDao.login(email, password)) {

                    System.out.println("✅ Login Successful");

                    while (true) {
                        System.out.println("\n---- PASSWORD MENU ----");
                        System.out.println("1. Add Password");
                        System.out.println("2. View Passwords");
                        System.out.println("3. View Password (Master Check)");
                        System.out.println("4. Update Password");
                        System.out.println("5. Update Profile");
                        System.out.println("6. Forgot Password");
                        System.out.println("7. Generate Password");
                        System.out.println("8. Delete Password");
                        System.out.println("9. Logout");

                        int ch = sc.nextInt();

                        // ADD PASSWORD
                        if (ch == 1) {
                            PasswordEntry p = new PasswordEntry();

                            System.out.print("Password ID: ");
                            p.setPasswordId(sc.nextInt());

                            System.out.print("User ID: ");
                            p.setUserId(sc.nextInt());

                            System.out.print("Account Name: ");
                            p.setAccountName(sc.next());

                            System.out.print("Username: ");
                            p.setUsername(sc.next());

                            System.out.print("Password: ");
                            p.setPassword(sc.next());

                            passDao.addPassword(p);
                            System.out.println("✅ Password Added");
                        }

                        // VIEW PASSWORDS
                        else if (ch == 2) {
                            System.out.print("User ID: ");
                            passDao.viewPasswords(sc.nextInt());
                        }

                        // VIEW PASSWORD WITH MASTER CHECK
                        else if (ch == 3) {
                            System.out.print("User ID: ");
                            int uid = sc.nextInt();

                            System.out.print("Enter Master Password: ");
                            String mp = sc.next();

                            if (userDao.verifyMasterPassword(uid, mp)) {
                                passDao.viewPasswords(uid);
                            } else {
                                System.out.println("❌ Invalid Master Password");
                            }
                        }

                        // UPDATE PASSWORD
                        else if (ch == 4) {
                            System.out.print("Password ID: ");
                            int pid = sc.nextInt();

                            System.out.print("New Password: ");
                            String np = sc.next();

                            passDao.updatePassword(pid, np);
                            System.out.println("✅ Password Updated");
                        }

                        // UPDATE PROFILE
                        else if (ch == 5) {
                            System.out.print("User ID: ");
                            int uid = sc.nextInt();

                            System.out.print("New Name: ");
                            String name = sc.next();

                            System.out.print("New Email: ");
                            String mail = sc.next();

                            userDao.updateProfile(uid, name, mail);
                            System.out.println("✅ Profile Updated");
                        }

                        // FORGOT PASSWORD
                        else if (ch == 6) {
                            System.out.print("Email: ");
                            String mail = sc.next();

                            System.out.print("Security Answer: ");
                            String ans = sc.next();

                            int code = VerificationUtil.generateCode();
                            System.out.println("Verification Code: " + code);

                            System.out.print("Enter Code: ");
                            int userCode = sc.nextInt();

                            if (code == userCode) {
                                System.out.print("New Password: ");
                                String np = sc.next();
                                userDao.forgotPassword(mail, ans, np);
                                System.out.println("✅ Password Reset Successful");
                            } else {
                                System.out.println("❌ Invalid Code");
                            }
                        }

                        // GENERATE PASSWORD
                        else if (ch == 7) {
                            System.out.print("Enter length: ");
                            int len = sc.nextInt();
                            System.out.println("Generated Password: "
                                    + PasswordUtil.generatePassword(len));
                        }

                        // DELETE PASSWORD
                        else if (ch == 8) {
                            System.out.print("Password ID: ");
                            passDao.deletePassword(sc.nextInt());
                            System.out.println("✅ Deleted");
                        }

                        // LOGOUT
                        else {
                            System.out.println("Logged out successfully.");
                            break;
                        }
                    }
                } else {
                    System.out.println("❌ Invalid Login");
                }
            }

            else {
                System.out.println("Thank You!");
                break;
            }
        }
    }
}