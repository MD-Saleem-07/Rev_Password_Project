package com.rev.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.rev.dao.PasswordDao;
import com.rev.dao.UserDao;
import com.rev.model.PasswordEntry;
import com.rev.model.User;
import com.rev.util.PasswordUtil;
import com.rev.util.VerificationUtil;

public class MainApp {

    private static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDao userDao = new UserDao();
        PasswordDao passDao = new PasswordDao();

        while (true) {
            try {
                System.out.println("\n===== REV PASSWORD MANAGER =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Forgot  Master Password");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                // ================= REGISTER =================
                if (choice == 1) {

                    User u = new User();

                    // USER ID CHECK
                    while (true) {
                        System.out.print("User ID: ");
                        int uid = sc.nextInt();
                        sc.nextLine();

                        if (userDao.isUserIdExists(uid)) {
                            System.out.println("❌ User ID already exists. Try another.");
                        } else {
                            u.setUserId(uid);
                            break;
                        }
                    }

                    System.out.print("Name: ");
                    u.setName(sc.nextLine());

                    // EMAIL CHECK
                    String email;
                    while (true) {
                        System.out.print("Email: ");
                        email = sc.nextLine();

                        if (!isValidEmail(email)) {
                            System.out.println("❌ Invalid email format!");
                        } 
                        else if (userDao.isEmailExists(email)) {
                            System.out.println("❌ Email already registered!");
                        }
                        else {
                            break;
                        }
                    }
                    u.setEmail(email);

                    System.out.print("Master Password : ");
                    u.setPassword(sc.nextLine());

                    System.out.print("Security Question: ");
                    u.setSecurityQuestion(sc.nextLine());

                    System.out.print("Security Answer: ");
                    u.setSecurityAnswer(sc.nextLine());

                    if (userDao.registerUser(u))
                        System.out.println("✅ Registration Successful");
                    else
                        System.out.println("❌ Registration Failed");
                }

                // ================= LOGIN =================
                else if (choice == 2) {

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Master Password: ");
                    String password = sc.nextLine();

                    if (!userDao.login(email, password)) {
                        System.out.println("❌ Invalid Login");
                        continue;
                    }

                    System.out.println("✅ Login Successful");

                    while (true) {
                        try {
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
                            sc.nextLine();

                            if (ch == 1) {
                                PasswordEntry p = new PasswordEntry();

                                System.out.print("Password ID: ");
                                p.setPasswordId(sc.nextInt());
                                sc.nextLine();

                                System.out.print("User ID: ");
                                p.setUserId(sc.nextInt());
                                sc.nextLine();

                                System.out.print("Account Name: ");
                                p.setAccountName(sc.nextLine());

                                System.out.print("Username: ");
                                p.setUsername(sc.nextLine());

                                System.out.print("Password: ");
                                p.setPassword(sc.nextLine());

                                if (passDao.addPassword(p))
                                    System.out.println("✅ Password Added");
                                else
                                    System.out.println("❌ Password already exists");
                            }

                            else if (ch == 2) {
                                System.out.print("User ID: ");
                                passDao.viewPasswords(sc.nextInt());
                            }

                            else if (ch == 3) {
                                System.out.print("User ID: ");
                                int uid = sc.nextInt();

                                System.out.print("Master Password: ");
                                String mp = sc.next();

                                if (userDao.verifyMasterPassword(uid, mp))
                                    passDao.viewPasswords(uid);
                                else
                                    System.out.println("❌ Invalid Master Password");
                            }

                            else if (ch == 4) {
                                System.out.print("Password ID: ");
                                int pid = sc.nextInt();

                                System.out.print("New Password: ");
                                passDao.updatePassword(pid, sc.next());
                                System.out.println("✅ Password Updated");
                            }

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
                                    userDao.forgotPassword(mail, ans, sc.next());
                                    System.out.println("✅ Password Reset Successful");
                                } else {
                                    System.out.println("❌ Invalid Code");
                                }
                            }

                            else if (ch == 7) {
                                System.out.print("Enter length: ");
                                System.out.println("Generated Password: " +
                                        PasswordUtil.generatePassword(sc.nextInt()));
                            }

                            else if (ch == 8) {
                                System.out.print("Password ID: ");
                                passDao.deletePassword(sc.nextInt());
                                System.out.println("✅ Deleted");
                            }

                            else if (ch == 9) {
                                System.out.println("Logged out successfully.");
                                break;
                            }

                            else {
                                System.out.println("❌ Invalid option!");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("❌ Enter numbers only!");
                            sc.nextLine();
                        }
                    }
                }

                // ================= FORGOT PASSWORD =================
                else if (choice == 3) {

                    System.out.println("\n--- Forgot Master Password ---");

                    System.out.print("Enter Registered Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Security Answer: ");
                    String answer = sc.nextLine();

                    int code = VerificationUtil.generateCode();
                    System.out.println("Verification Code: " + code);

                    System.out.print("Enter Verification Code: ");
                    int inputCode = sc.nextInt();
                    sc.nextLine();

                    if (code == inputCode) {
                        System.out.print("Enter New Master Password: ");
                        String newPass = sc.nextLine();

                        if (userDao.forgotPassword(email, answer, newPass)) {
                            System.out.println("✅ Master Password Reset Successfully");
                        } else {
                            System.out.println("❌ Details do not match. Try again.");
                        }
                    } else {
                        System.out.println("❌ Invalid Verification Code");
                    }
                }

                else if (choice == 4) {
                    System.out.println("Thank you for using Rev Password Manager!");
                    break;
                }

                else {
                    System.out.println("❌ Invalid menu choice!");
                }

            } catch (InputMismatchException e) {
                System.out.println("❌ Please enter valid number!");
                sc.nextLine();
            }
        }
    }
}