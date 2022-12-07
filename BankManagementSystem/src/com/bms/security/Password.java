package com.bms.security;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.operations.CreateAccount;
import com.bms.operations.CustomerInfo;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password {

    // It takes a string as input
    public static void takeInput(String input) throws Exception {

        // Checking, entered password valid or not by passing it into check password method
        if (checkPassword(input)) {
            CustomerInfo.setPasswordHash(Security.getSha256(input));
        }

        // If password entered password don't meets requirements it again tells to enter password
        else {
            System.out.println("Sorry! your password doesn't meet the requirements\nPlease Try Again :) ");
            CreateAccount.enterPassword();
        }
    }

    // Method to check password is valid or not
    private static boolean checkPassword(String password) {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,32}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty return false
        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        // Return if the password matched the ReGex
        return m.matches();
    }

    // Update password
    public static void updatePassword() throws Exception {

        // Creating statement object
        Statement st = DbConnection.connect().createStatement();

        // Executing update operation in database
        byte count = (byte) st.executeUpdate("update bankinfo set passwordhash="+CustomerInfo.getPasswordHash()+"where accountno="+CustomerInfo.getAccountNo());

        // checks operation successfully or not
        if (count > 0)
            System.out.println("Password Successfully Updated :) ");
        else
            System.out.println("Something wrong happened :( ");
    }
}