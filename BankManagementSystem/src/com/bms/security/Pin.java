package com.bms.security;

// Importing necessary packages
import com.bms.db.DbConnection;
import com.bms.operations.CreateAccount;
import com.bms.operations.CustomerInfo;
import com.bms.validation.FetchHash;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pin {
    private static int tempPin;
    public static void takeInput (String pin) {
        try {
            // Checking pin contains only number or not
            tempPin = Integer.parseInt(pin);
            if ((tempPin < 1000) || (tempPin > 999999)) {
                System.out.println("Oh No! Your pin length must be between 4-6 digits");
                System.out.println("Please try again: ");
                CreateAccount.enterPin();
            }else {
                // Pin converted into base64 and then the base 64 is converted into md5
                CustomerInfo.setPinHash(Security.getMd5(Security.getBase64(String.valueOf(tempPin))));
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong Input! You pin must only contains digits!\nPlease Try Again: ");
            CreateAccount.enterPin();
        } catch (Exception e) {
            System.out.println("Something Wrong happened! Please try again");
            CreateAccount.enterPin();
        }
    }

    public static boolean verifyPin(String pinhash, int accountNO) throws Exception {
        // it
        return pinhash.equalsIgnoreCase(FetchHash.fetchPinHash(accountNO));
    }

    public static boolean checkIsEnabled (int accountNo) throws Exception {
        Statement statement = DbConnection.connect().createStatement();
        ResultSet rs = statement.executeQuery("select ispinenabled from bankinfo where accountno="+accountNo);
        rs.next();
        return rs.getBoolean("ispinenabled");
    }
}
