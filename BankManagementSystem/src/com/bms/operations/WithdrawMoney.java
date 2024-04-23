package com.bms.operations;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.navigation.LoggedInMenu;
import com.bms.security.Login;
import com.bms.utils.InputManager;
import com.bms.validation.Validation;
import java.sql.Statement;
import java.util.InputMismatchException;

public class WithdrawMoney {
    private static double withdrawAmount;
    public static void withdrawMoney() throws Exception {
        if (CustomerInfo.isIsLoggedIn()) {
            // If customer already logged in calling enterAmount method
            enterAmount();

            // calling withdraw method
            withdraw();

        } else {
            System.out.println("Dear Customer, At first log in into your account: ");
            Login.login();
        }
    }
    private static void enterAmount() {

        System.out.println("Enter the amount you want to withdraw: ");
        try {
            withdrawAmount = InputManager.getScanner().nextDouble();
        }catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Amount should be a numeric value!\nPlease try again: ");
            enterAmount();
        }catch (Exception e) {
            System.out.println("Oh No! Something wrong happened,\nPlease try again: ");
            enterAmount();
        }
    }

    private static void withdraw() throws Exception {
        // checking balance
        if (withdrawAmount <= Validation.balance(CustomerInfo.getAccountNo())) {
            // If withdraw amount less than account balance
            double newBalance = Validation.balance(CustomerInfo.getAccountNo()) - withdrawAmount;
            Statement st = DbConnection.connect().createStatement();
            int count = st.executeUpdate("update bankinfo set balance="+newBalance+" where accountno="+CustomerInfo.getAccountNo());
            if (count > 0) {

                System.out.println("Withdrawal Request Successful :)");
                System.out.println("Your updated balance is: "+newBalance);

                // Going Back to logged in menu
                LoggedInMenu.loggedInmenu();

            } else {
                System.out.println("Oh No! Something unexpected happened :(\nPlease try again ");
                withdrawMoney();
            }
        }
        else {
            // If customer has less balance
            System.out.println("Oh No! You don't have sufficient balance :( ");
            System.out.println("Your existing account balance is: "+Validation.balance(CustomerInfo.getAccountNo()));
        }
    }
}
