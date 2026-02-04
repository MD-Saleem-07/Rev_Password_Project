package com.rev.main;

import java.util.Scanner;

import com.rev.exception.InvalidInputException;
import com.rev.model.PasswordEntry;
import com.rev.model.User;
import com.rev.service.PasswordService;
import com.rev.service.UserService;
import com.rev.util.PasswordUtil;
import com.rev.util.VerificationUtil;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        PasswordService passwordService = new PasswordService();

        while (true) {
            try {
                System.out.println("\n===== REV PASSWORD MANAGER =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Forgot Master Password");
                System.out.println("4. Exit");
                System.out.print("Choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                // ================= REGISTER =================
                if (choice == 1) {

                    User u = new User();

                    // USER ID LOOP
                    while (true) {
                        System.out.print("User ID: ");
                        String input = sc.nextLine();

                        try {
                            int uid = Integer.parseInt(input);
                            userService.validateUserId(uid);
                            u.setUserId(uid);
                            break; // success → exit loop
                        } catch (NumberFormatException e) {
                            System.out.println("❌ User ID must be a number");
                        } catch (InvalidInputException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                    }

                    // EMAIL LOOP
                    String email;

                    while (true) {
                        System.out.print("Email: ");
                        email = sc.nextLine();

                        try {
                            userService.validateEmail(email);
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                    }

                    System.out.print("Password: ");
                    String pass = sc.nextLine();

                    userService.login(email, pass);
                    System.out.println("✅ Login Successful");


                    System.out.print("Password: ");
                    u.setPassword(sc.nextLine());

                    System.out.print("Security Question: ");
                    u.setSecurityQuestion(sc.nextLine());

                    System.out.print("Security Answer: ");
                    u.setSecurityAnswer(sc.nextLine());

                    userService.register(u);
                    System.out.println("✅ Registration Successful");
                }

                // ================= LOGIN =================
                else if (choice == 2) {

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Password: ");
                    String pass = sc.nextLine();

                    userService.login(email, pass);
                    System.out.println("✅ Login Successful");

                    while (true) {
                        System.out.println("\n---- PASSWORD MENU ----");
                        System.out.println("1. Add Password");
                        System.out.println("2. View Passwords");
                        System.out.println("3. View Passwords (Master Check)");
                        System.out.println("4. Update Password");
                        System.out.println("5. Generate Password");
                        System.out.println("6. Delete Password");
                        System.out.println("7. Logout");

                        int ch = Integer.parseInt(sc.nextLine());

                        if (ch == 1) {

                            PasswordEntry p = new PasswordEntry();

                            // PASSWORD ID LOOP
                            while (true) {
                                try {
                                    System.out.print("Password ID: ");
                                    int pid = Integer.parseInt(sc.nextLine());
                                    passwordService.validatePasswordId(pid);
                                    p.setPasswordId(pid);
                                    break;
                                } catch (InvalidInputException e) {
                                    System.out.println("❌ " + e.getMessage());
                                }
                            }

                            System.out.print("User ID: ");
                            p.setUserId(Integer.parseInt(sc.nextLine()));

                            System.out.print("Account Name: ");
                            p.setAccountName(sc.nextLine());

                            System.out.print("Username: ");
                            p.setUsername(sc.nextLine());

                            System.out.print("Password: ");
                            p.setPassword(sc.nextLine());

                            passwordService.addPassword(p);
                            System.out.println("✅ Password Added");
                        }

                        else if (ch == 2) {
                            System.out.print("User ID: ");
                            passwordService.viewPasswords(
                                    Integer.parseInt(sc.nextLine()));
                        }

                        else if (ch == 3) {
                            System.out.print("User ID: ");
                            int uid = Integer.parseInt(sc.nextLine());

                            System.out.print("Master Password: ");
                            String mp = sc.nextLine();

                            userService.verifyMasterPassword(uid, mp);
                            passwordService.viewPasswords(uid);
                        }

                        else if (ch == 4) {
                            System.out.print("Password ID: ");
                            int id = Integer.parseInt(sc.nextLine());

                            System.out.print("New Password: ");
                            String np = sc.nextLine();

                            passwordService.updatePassword(id, np);
                            System.out.println("✅ Password Updated");
                        }

                        else if (ch == 5) {
                            System.out.print("Length: ");
                            System.out.println("Generated: " +
                                    PasswordUtil.generatePassword(
                                            Integer.parseInt(sc.nextLine())));
                        }

                        else if (ch == 6) {
                            System.out.print("Password ID: ");
                            passwordService.deletePassword(
                                    Integer.parseInt(sc.nextLine()));
                            System.out.println("✅ Deleted");
                        }

                        else if (ch == 7) break;
                    }
                }

                // ================= FORGOT MASTER PASSWORD =================
             // ===== FORGOT MASTER PASSWORD =====
                else if (choice == 3) {

                    String email;
                    String answer;
                    int enteredOtp;
                    int otp;

                    // 🔁 EMAIL LOOP
                    while (true) {
                        try {
                            System.out.print("Email: ");
                            email = sc.nextLine();
                            userService.validateEmailForForgot(email);
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                    }

                    // 🔁 SECURITY ANSWER LOOP
                    while (true) {
                        try {
                            System.out.print("Security Answer: ");
                            answer = sc.nextLine();
                            userService.validateSecurityAnswer(email, answer);
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                    }

                    otp = VerificationUtil.generateCode();
                    System.out.println("OTP: " + otp);

                    // 🔁 OTP LOOP
                    while (true) {
                        try {
                            System.out.print("Enter OTP: ");
                            enteredOtp = Integer.parseInt(sc.nextLine());
                            userService.validateOtp(enteredOtp, otp);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("❌ OTP must be a number");
                        } catch (InvalidInputException e) {
                            System.out.println("❌ " + e.getMessage());
                        }
                    }

                    System.out.print("New Password: ");
                    String newPass = sc.nextLine();

                    userService.forgotMasterPassword(
                            email, answer, enteredOtp, otp, newPass);

                    System.out.println("✅ Master Password Reset Successful");
                }


                else if (choice == 4) {
                    System.out.println("Thank you!");
                    break;
                }

            } catch (InvalidInputException e) {
                System.out.println("❌ " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❌ Invalid input");
            }
        }
    }
}
