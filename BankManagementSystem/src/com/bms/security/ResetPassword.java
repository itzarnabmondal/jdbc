package com.bms.security;

// Importing necessary packages
import com.bms.operations.CreateAccount;
import com.bms.operations.CustomerInfo;
import com.bms.utils.InputManager;
import com.bms.validation.AccountExistOrNot;
import com.bms.validation.Captcha;
import com.bms.validation.FetchHash;
import java.util.InputMismatchException;

public class ResetPassword {
    private static byte invalidKeyCounter = 0;
    public static void resetPassword() throws Exception {
        DestroySession.destroy();

        // Calling enter account no method
        enterAccountNo();

    }

    // Taking account no
    public static void enterAccountNo() {
        try {
            System.out.println("Enter Your Account No: ");
            CustomerInfo.setAccountNo(Integer.parseInt(InputManager.getScanner().next()));
        } catch (NumberFormatException e) {
            System.out.println("Wrong Input! Account number can contains number only");
            enterAccountNo();
        }
    }

    public static void enterPhoneNumber() {
        System.out.println("Enter Registered phone number: ");
        try {
            CustomerInfo.setPhone(InputManager.getScanner().nextLong());
        }catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Wrong Input! Enter your 10 digit phone number: ");
            enterPhoneNumber();
        }
    }

    private static void verify() throws Exception {
        if (AccountExistOrNot.check(CustomerInfo.getAccountNo(), CustomerInfo.getPhone())) {
            // if caustomer present in database asking for enter amount
            Captcha.captcha();

        } else {
            System.out.println("No account found with these details :( ");
            System.out.println("Please enter correct details");
            enterAccountNo();
        }
    }

    public static void enterRecoveryKey() throws Exception {
        System.out.println("Enter 32 characters recovery key: ");
        CustomerInfo.setRecoveryKeyHash(Security.getSha256(InputManager.getScanner().next()));
        if (CustomerInfo.getRecoveryKeyHash().equalsIgnoreCase(FetchHash.fetchRecoveryKeyHash(CustomerInfo.getAccountNo()))) {
            CreateAccount.enterPassword();
            // Updating passwordhash in database
            Password.updatePassword();
        }
        else {
            invalidKeyCounter++;
            System.out.println("Oh No! This recovery key isn't valid\nPlease try again");
        }

    }
}
