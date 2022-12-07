package com.bms.navigation;

// Importing necessary packages
import com.bms.operations.*;
import com.bms.security.DestroySession;

import java.util.Scanner;

public class LoggedInMenu {

    public static void loggedInmenu() throws Exception {

        if (CustomerInfo.isIsLoggedIn()) {

            System.out.println("""
                Enter 1 for Check Balance
                Enter 2 for Deposit Money
                Enter 3 for Withdraw Money
                Enter 4 for Transfer Money
                Enter 5 to view you profile
                Enter 6 for delete account
                Enter 0 for go back to main menu
                """);

            char input = new Scanner(System.in).next().charAt(0);

            switch (input) {
                case '1' -> CheckBalance.checkBalance();
                case '2' -> Deposit.deposit();
                case '3' -> WithdrawMoney.withdrawMoney();
                case '4' -> TransferMoney.transferMoney();
                case '5' -> Profile.profile();
                case '6' -> DeleteAccount.deleteAccount();
                case '0' -> {
                    DestroySession.destroy();
                    MainMenu.mainMenu();
                }
            }
        }

    }
}
