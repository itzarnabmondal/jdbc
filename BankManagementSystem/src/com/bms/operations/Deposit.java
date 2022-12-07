package com.bms.operations;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.navigation.LoggedInMenu;
import com.bms.security.Login;
import com.bms.validation.Validation;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Deposit {

    // Variable to temporary store deposit amount
    private static double tempAmount;

    // Method to deposit balance
    public static void deposit() throws Exception {

        // Checking customer logged in or not
        if (CustomerInfo.isIsLoggedIn()) {

            // calling Enter amount method
            enterAmount();

            // calling update amount method
            updateAmount();

        }

        // If not logged in
        else {
            System.out.println("Dear Customer, At first log in into your account: ");
            Login.login();
        }
    }

    // Method to take input
    private static void enterAmount() {

        System.out.println("Enter amount you want to add: ");

        // Handling Exception and trying to take input
        try {
             tempAmount = new Scanner(System.in).nextDouble();
        }
        catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Amount should be a numeric value!\nPlease try again: ");
            enterAmount();
        }
        catch (Exception e) {
            System.out.println("Oh No! Something wrong happened,\nPlease try again: ");
            enterAmount();
        }
    }

    private static void updateAmount() throws Exception {

        // Updating amount
        double oldBalance = Validation.balance(CustomerInfo.getAccountNo());
        double newBalance = Validation.balance(CustomerInfo.getAccountNo()) + tempAmount;

        // Creating statement object and applying balance update operation
        Statement st = DbConnection.connect().createStatement();
        byte count = (byte) st.executeUpdate("update bankinfo set balance="+newBalance+" where accountno="+CustomerInfo.getAccountNo());

        System.out.println("Count is: "+count);
        // Showing balance info
        if (count > 0) {
            System.out.println("Your old balance: "+oldBalance);
            System.out.println("amount added to account: "+tempAmount);
            System.out.println("Your updated balance is: "+newBalance);

            // Going back to logged in menu
            LoggedInMenu.loggedInmenu();
        }
        // If fails, showing error message
        else {
            System.out.println("Oh No! Something unexpected happened :(\nPlease try again ");
            enterAmount();
        }
    }
}