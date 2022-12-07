package com.bms.security;

// Importing necessary packages
import com.bms.navigation.LoggedInMenu;
import com.bms.operations.CustomerInfo;
import com.bms.validation.AccountExistOrNot;
import com.bms.validation.FetchHash;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    private static byte invalidPasswordCounter = 0;
    public static void login() throws Exception {

        // Calling Enter account number method
        enterAccountNo();

        // Calling enter phone method
        enterPhoneNumber();

        // Checking Account with provided account no exist or not
        if (AccountExistOrNot.check(CustomerInfo.getAccountNo(), CustomerInfo.getPhone())) {
            // If exist, asking for password
            System.out.println("Enter Your Password: ");
            // Storing new password hash inside a variable
            enterPassword();
        }
        else {
            System.out.println("Oh No! No account exist with this details :( ");
            System.out.println("Please enter valid credentials: ");
            login();
        }
    }

    // Method to take Account name input
    public static void enterAccountNo() {

        try {
            System.out.println("Enter Your Account No: ");
            CustomerInfo.setAccountNo(Integer.parseInt(new Scanner(System.in).next()));
        }
        catch (NumberFormatException e) {
            System.out.println("Wrong Input! Account number can contains number only");
            enterAccountNo();
        }
        catch (Exception e) {
            System.out.println("Something wrong happened, Please try again");
            enterAccountNo();
        }
    }


    public static void enterPhoneNumber() {

        long tempPhone;

        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        System.out.println("Enter your 10 digit phone number: ");

        try {
            tempPhone = new Scanner(System.in).nextLong();
            Matcher m = p.matcher(String.valueOf(tempPhone));
            if (m.matches()) {
                CustomerInfo.setPhone(tempPhone);
            }else {
                System.out.println("Oh No! Phone number must be a 10 digit number");
                enterPhoneNumber();
            }
        }
        catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Oh No! Phone number must be a 10 digit number");
            enterPhoneNumber();
        }
        catch (Exception e) {
            System.out.println("Something Wrong happened! Please Try Again");
            enterPhoneNumber();
        }

    }

    // Taking Password input during login
    private static void enterPassword() throws Exception {

        // Terminate condition for max password attempt
        if (invalidPasswordCounter > 3) {
            System.out.println("Oh No! You reached max attempt");
            System.out.println("GoodBye :)");
            DestroySession.destroy();
            System.exit(0);
        }

        // Taking password input and storing it into a variable
        System.out.println("Enter Your Password: ");
        CustomerInfo.setPasswordHash(Security.getSha256(new Scanner(System.in).next()));

        // Checking, Password matching or not
        if (CustomerInfo.getPasswordHash().equalsIgnoreCase(FetchHash.fetchPasswordHash(CustomerInfo.getAccountNo()))) {

            // Code if password matching with database
            CustomerInfo.setPasswordHash(null);
            invalidPasswordCounter = 0; // resetting invalid password counter for pin

            // Checking Pin Enabled OR NOT
            if (Pin.checkIsEnabled(CustomerInfo.getAccountNo())) {

                // If pin enabled, Calling Enter Pin method
                enterPin();

            } else {

                // If pin not enabled then,
                System.out.println("You are successfully logged in :) ");
                CustomerInfo.setIsLoggedIn(true);
                LoggedInMenu.loggedInmenu();
            }

        }else {

            // If password not matching with database
            CustomerInfo.setIsLoggedIn(false);
            invalidPasswordCounter++;
            System.out.println("Oh No! Wrong password, Pleases try Again");
            enterPassword();
        }

    }

    // Method for take pin during login
    public static void enterPin() throws Exception {

        // Terminate condition for max PIN attempt
        if (invalidPasswordCounter > 3) {
            System.out.println("Oh No! You reached max attempt");
            System.out.println("Goodbye :)");
            DestroySession.destroy();
            System.exit(0);
        }

        System.out.println("Enter Your Pin: ");
        CustomerInfo.setPinHash(Security.getMd5(Security.getBase64(new Scanner(System.in).next())));

        // Matching Pin hash with pin has in database
        if (Pin.verifyPin(CustomerInfo.getPinHash(), CustomerInfo.getAccountNo())) {

            System.out.println("You are successfully logged in :)");
            CustomerInfo.setIsLoggedIn(true);
            CustomerInfo.setPinHash(null);
            LoggedInMenu.loggedInmenu();

        } else {

            System.out.println("Oh No! Wrong PIN, Pleases try Again: ");
            invalidPasswordCounter++;
        }
    }
}