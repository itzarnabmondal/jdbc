package com.bms.operations;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.main.Main;
import com.bms.navigation.LoggedInMenu;
import com.bms.navigation.MainMenu;
import com.bms.security.DestroySession;
import com.bms.security.Login;
import com.bms.security.Pin;
import com.bms.security.Security;
import com.bms.validation.Captcha;
import com.bms.validation.FetchHash;
import com.bms.validation.Validation;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteAccount {


    // Password counter variable
    private static byte invalidPasswordCounter = 0;

    // Method to delete account
    public static void deleteAccount() throws Exception {

        // Checking if customer has balance in his account
        if (Validation.balance(CustomerInfo.getAccountNo()) > 1) {
            System.err.println("You have Rs: "+Validation.balance(CustomerInfo.getAccountNo())+" left on your account!");
            System.out.println("Please Transfer or Withdraw you money before deleting account");
            LoggedInMenu.loggedInmenu();
        }
        // If Customer don't have balance calling delete method
        else {

            // Calling verify
            verify();
        }
    }

    public static void verify() throws Exception {
        System.out.println("Are you sure ? (Enter 'Y' for YES any other key for NO: )");
        char input = new Scanner(System.in).next().charAt(0);
        if (input == 'Y' || input == 'y') {

            // Calling Captcha
            Captcha.captcha();

            // Telling customer to enter password again
            enterPassword();

        }
        else {
            LoggedInMenu.loggedInmenu();
        }

    }



    private static void enterPassword() throws Exception {

        // Terminate condition for max password attempt
        if (invalidPasswordCounter >= 3) {
            System.out.println("Oh No! You reached max attempt");
            System.out.println("Bye :)");
            DestroySession.destroy();
            MainMenu.mainMenu();
        }

        // Taking password input and storing it into a variable
        System.out.println("Enter Your Password to verify it's you: ");
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
            System.out.println("Bye :)");
            DestroySession.destroy();
            MainMenu.mainMenu();
        }

        System.out.println("Enter Your Pin: ");
        CustomerInfo.setPinHash(Security.getMd5(Security.getBase64(new Scanner(System.in).next())));

        // Matching Pin hash with pin has in database
        if (Pin.verifyPin(CustomerInfo.getPinHash(), CustomerInfo.getAccountNo())) {

            // If all verification successfully calling delete
            delete();

        }
        else {
            System.out.println("Oh No! Wrong PIN, Pleases try Again: ");
            enterPin();
            invalidPasswordCounter++;
        }
    }

    // Delete method to delete account
    private static void delete() throws Exception {


        // Creating statement object and executing delete operation
        Statement st = DbConnection.connect().createStatement();
        byte count = (byte) st.executeUpdate("delete from bankinfo where accountno="+CustomerInfo.getAccountNo());

        // Showing result
        if (count > 0) {

            // Calling destroy method to clear all data stored on variables
            DestroySession.destroy();

            // Showing msg
            System.out.println("Your account successfully deleted from our database");
            System.out.println("GoodBye :) ");

            // Calling main menu
            MainMenu.mainMenu();
        }
    }
}
