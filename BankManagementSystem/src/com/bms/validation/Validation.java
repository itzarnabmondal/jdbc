package com.bms.validation;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.operations.CreateAccount;
import com.bms.operations.CustomerInfo;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    // Name Validation
    public static void nameValidation(String input) {
        boolean result;

        // Expression to validate name
        String expression = "^[a-zA-Z\\s]+";
        result = input.matches(expression);

        // Checking name is valid or not
        if (result) {
            CustomerInfo.setName(input);
        }

        // If name isn't valid
        else {
            System.out.println("Invalid Input! Name can only contains alphabet and a single space");
            CreateAccount.enterName();
        }
    }


    // Phone Validation
    public static void phoneValidation(String input) {
        boolean result;
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // The number should be of 10 digits.
        // Creating a Pattern class object
         Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression for which
        // object of Matcher class is created
        Matcher m = p.matcher(input);
        // Returning boolean value
        result = (m.matches());
        if (result) {
            try {
                CustomerInfo.setPhone(Long.parseLong(input));
            } catch (InputMismatchException e) {
                System.out.println("Oh No! Phone number must be a 10 digit number");
                CreateAccount.enterPhoneNumber();
            } catch (Exception e) {
                System.out.println("Something Wrong happened! Please Try Again");
                CreateAccount.enterPhoneNumber();
            }
        } else {
            System.out.println("Invalid Phone Number! Please Try Again :)");
            CreateAccount.enterPhoneNumber();
        }
    }

    // Balance Validation
    public static double balance(int accountNo) throws Exception {
        Statement statement = DbConnection.connect().createStatement();
        ResultSet rs = statement.executeQuery("select balance from bankinfo where accountno="+accountNo);
        rs.next();
        return rs.getDouble("balance");
    }
}
