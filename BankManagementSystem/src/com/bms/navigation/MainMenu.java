package com.bms.navigation;

// Importing necessary packages
import com.bms.operations.CreateAccount;
import com.bms.security.Login;
import com.bms.security.ResetPassword;

import java.util.Scanner;

public class MainMenu {

    Scanner scanner;
    public static void mainMenu() throws Exception {

        System.out.println("""
                Enter 1 for create account
                Enter 2 for login
                Enter 3 for reset password
                """);

        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);
        scanner.close();

        switch (input) {
            case '1' -> CreateAccount.createAccount();
            case '2' -> Login.login();
            case '3' -> ResetPassword.resetPassword();
            default -> {
                System.out.println("Wrong Input!");
                mainMenu();
            }
        }
    }
}