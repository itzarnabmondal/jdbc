package com.bms.operations;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.security.*;
import com.bms.security.RecoveryKey;
import com.bms.validation.Validation;

import javax.sound.midi.Soundbank;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.Scanner;

public class CreateAccount {

    // For storing inputs taken by scanner
    private static char input;

    // Method to create account
    public static void createAccount() throws Exception {

        // Calling enter name method
        enterName();

        // Selecting branch
        selectBranch();

        // Selecting account type
        selectAccountType();

        // Calling enter phone number method
        enterPhoneNumber();

        // Calling enter password method
        enterPassword();

        // Generating accountNo
        CustomerInfo.setAccountNo(new Random().nextInt(99999999));

        // Assign IFCS code
        IFCS.setIfcs(CustomerInfo.getBranch());

        // Generating recovery key
        String tempRecoveryKey = RecoveryKey.generateRecoveryKey();

        // Generating recovery key hash
        CustomerInfo.setRecoveryKeyHash(Security.getSha256(tempRecoveryKey));

        // Asking customer wants to create a pin or not
        System.out.println("""
                Do you want to create a PIN for additional security ?
                [Pin provides extra security, your have to enter your pin 
                before various purposes like Transfer money, Withdraw money etc. ] 
                If yes enter Y else enter any other character
                """);

        input = new Scanner(System.in).next().charAt(0);

        // Checking customer wants to create pin or not
        if (input == 'Y' || input == 'y') {
            enterPin();
            CustomerInfo.setIsPinEnabled(true);
        } else {
            CustomerInfo.setIsPinEnabled(false);
        }

        // Storing customer data into database
        PreparedStatement ps = DbConnection.connect().prepareStatement("insert into bankinfo values(?,?,?,?,?,?,?,?,?,?,?)");
        // Setting values
        ps.setInt(1, CustomerInfo.getAccountNo());
        ps.setString(2, CustomerInfo.getName());
        ps.setLong(3, CustomerInfo.getPhone());
        ps.setInt(4,0);
        ps.setString(5, CustomerInfo.getPasswordHash());
        ps.setString(6, CustomerInfo.getBranch());
        ps.setString(7, CustomerInfo.getAccountType());
        ps.setString(8,CustomerInfo.getIfcsCode());
        ps.setString(9, CustomerInfo.getRecoveryKeyHash());
        ps.setString(10, CustomerInfo.getPinHash());
        ps.setBoolean(11,CustomerInfo.isIsPinEnabled());
        ps.executeUpdate(); // For executing

        // Closing statement
        ps.close();

        // Account Created message
        System.out.println("""
                Account Successfully Created :)\n
                Use following credentials to login into your account\n
                Thank you for using our service :)
                """);
        System.out.println("Your Account Number is: "+ CustomerInfo.getAccountNo());
        System.out.println("Your IFCS code is: "+ CustomerInfo.getIfcsCode());
        System.out.println("Account recovery key: "+ tempRecoveryKey);
        System.out.println("NOTE: Save your recovery key in a safe place,\nIf you forgot password you need this to resetting your password");

        // Pausing screen
        System.out.println("Please Enter any key to continue :) ");
        char tempEntry = new Scanner(System.in).next().charAt(0);

        // Destroying session and telling user to login
        DestroySession.destroy();
        System.out.println("Please login your account :) ");
        Login.login();
    }

    // Enter name method
    public static void enterName() {
        System.out.println("Enter your full name: ");
        Validation.nameValidation(new Scanner(System.in).nextLine());
    }

    // Select Branch Method
    public static void selectBranch() {

        System.out.println("Enter branch name: ");
        System.out.println("""
                Select your account Branch
                Enter 1 for Kolkata branch
                Enter 2 for Mumbai branch
                Enter 3 for Hyderabad branch
                Enter 4 for Jaipur branch
                Enter 5 for Delhi Branch
                Enter 0 for close this program
                """);

        input = new Scanner(System.in).next().charAt(0);

        // Switch case for selecting
        switch (input) {
            case '1' -> CustomerInfo.setBranch("Kolkata");
            case '2' -> CustomerInfo.setBranch("Mumbai");
            case '3' -> CustomerInfo.setBranch("Hyderabad");
            case '4' -> CustomerInfo.setBranch("Jaipur");
            case '5' -> CustomerInfo.setBranch("Delhi");
            default -> {
                System.out.println("Wrong Input! Please Try again");
                selectBranch();
            }
        }
    }

    // Select account type method
    public static void selectAccountType() {

        System.out.println("""
                Select your account type
                Enter 1 for Current account
                Enter 2 for Savings account
                Enter 3 for Salary account
                Enter 4 for Fixed deposit account
                """);

        input = new Scanner(System.in).next().charAt(0);

        // Switch case for selecting
        switch (input) {
            case '1' -> CustomerInfo.setAccountType("Current account");
            case '2' -> CustomerInfo.setAccountType("Savings account");
            case '3' -> CustomerInfo.setAccountType("Salary account");
            case '4' -> CustomerInfo.setAccountType("Fixed deposit account");
            default -> {
                System.out.println("Wrong Input! Please Try again");
                selectAccountType();
            }
        }
    }

    // Enter Phone number method
    public static void enterPhoneNumber() {

        System.out.println("Enter your 10 digit phone number: ");
        // Passing input into phone validation method
        Validation.phoneValidation(new Scanner(System.in).next());
    }

    // Enter password method
    public static void enterPassword() throws Exception {

        System.out.println("Please Enter Your new password");
        System.out.println("""
                NOTE:
                * Password length must be between 8-20 characters.
                * Password must contains a lowercase letter, one digit,
                * one uppercase character and one spacial character.
                """);

        // Taking user input and passing into takeInput method of password class
        Password.takeInput(new Scanner(System.in).next());
    }

    // Enter PIN Method
    public static void enterPin() {

        System.out.println("""
                Your pin length should be 4-6 digits,
                No character or whitespace allowed!
                """);
        System.out.println("Enter your Pin");

        // Passing pin into take input method of pin class
        Pin.takeInput(new Scanner(System.in).next());
    }
}