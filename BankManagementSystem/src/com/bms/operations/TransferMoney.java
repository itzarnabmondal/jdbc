package com.bms.operations;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.security.Login;
import com.bms.validation.AccountExistOrNot;
import com.bms.validation.Captcha;
import com.bms.validation.Validation;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TransferMoney {

    // Some private variables to store some required data
    private static int receiverAccountNo;
    private static long receiverPhoneNumber;
    private static double transferAmount;

    // transfer money method
    public static void transferMoney() throws Exception {

        // Checking customer logged in or not
        if (CustomerInfo.isIsLoggedIn()) {
            // calling takeRe
            takeReceiverDetails();
        }
        else {
            System.out.println("Dear Customer, At first log in into your account: ");
            Login.login();
        }
    }

    private static void takeReceiverDetails() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter receiver's account no: ");

        try {
            receiverAccountNo = sc.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input! Account number is a 8 digits number\nPlease try again: ");
            takeReceiverDetails();
        }
        catch (Exception e) {
            System.out.println("Something wrong happened!\nPlease Try again: ");
            takeReceiverDetails();
        }

        // Taking phone number
        System.out.println("Enter receiver's phone no: ");
        try {
            receiverPhoneNumber = sc.nextLong();
        }
        catch (InputMismatchException e) {
            System.out.println("Wrong input! Enter 10 digits phone number\nPlease try again: ");
            takeReceiverDetails();
        }
        catch (Exception e) {
            System.out.println("Something wrong happened!\nPlease Try again: ");
            takeReceiverDetails();
        }

        // Calling verify method to check customer exist or not
        verify();
    }

    private static void verify() throws Exception {


        // Calling captcha
        Captcha.captcha();

        if (AccountExistOrNot.check(receiverAccountNo, receiverPhoneNumber)) {
            // if caustomer present in database asking for enter amount
            enterAmount();
        } else {
            System.out.println("No account found with these details :( ");
            System.out.println("Please enter correct details");
            takeReceiverDetails();
        }

        // calling enter ammount method
        enterAmount();

    }

    // Enter amount method
    private static void enterAmount() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount you want to withdraw: ");
        try {
            transferAmount = sc.nextDouble();
        }
        catch (NumberFormatException e) {
            System.out.println("Amount should be a numeric value!\nPlease try again: ");
            enterAmount();
        }
        catch (Exception e) {
            System.out.println("Oh No! Something wrong happened,\nPlease try again: ");
            enterAmount();
        }
        sc.close();

        // calling transfer
        transfer();
    }


    private static void transfer() throws Exception {

        if (transferAmount <= Validation.balance(CustomerInfo.getAccountNo())) {
            // If withdraw amount less than account balance
            double oldBalance = Validation.balance(CustomerInfo.getAccountNo());
            double newBalance = Validation.balance(CustomerInfo.getAccountNo()) - transferAmount;

            Statement st = DbConnection.connect().createStatement();
            int count = st.executeUpdate("update customer set accbal="+(Validation.balance(receiverAccountNo) + transferAmount)+" where accountno="+receiverAccountNo);

            if (count > 0) {
                System.out.println("amount added to account");
                System.out.println("Your updated balance is: "+newBalance);
            }
            else {
                System.out.println("Oh No! Something unexpected happened :(\nPlease try again ");

            }
        }
    }
}
