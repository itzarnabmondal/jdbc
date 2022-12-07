package com.bms.operations;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.navigation.LoggedInMenu;
import com.bms.security.Login;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckBalance {

    // Method to print balance of current customer
    public static void checkBalance() throws Exception {

        // Checking is customer logged in or not
        if (CustomerInfo.isIsLoggedIn()) {

            // Creating statement and executing query
            Statement statement = DbConnection.connect().createStatement();
            ResultSet rs = statement.executeQuery("select balance from bankinfo where accountno="+CustomerInfo.getAccountNo());
            rs.next();

            // Displaying result
            System.out.println("Your Current Balance Is: "+rs.getDouble("balance"));

            // Going back to logged in menu
            LoggedInMenu.loggedInmenu();
        }

        // If not logged in
        else {
            System.out.println("Dear Customer, At first log in into your account: ");
            Login.login();
        }
    }
}
