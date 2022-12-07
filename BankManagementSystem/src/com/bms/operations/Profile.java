package com.bms.operations;

import com.bms.db.DbConnection;
import com.bms.navigation.LoggedInMenu;
import java.sql.ResultSet;
import java.sql.Statement;

public class Profile {
    public static void profile() throws Exception {
        // calling getProfileInfo method
        getProfile();
    }
    private static void getProfile() throws Exception {
        Statement st = DbConnection.connect().createStatement();
        ResultSet rs = st.executeQuery("select accountno,name,ph,balance,branch,accounttype,ifcs from bankinfo where accountno="+CustomerInfo.getAccountNo());
        if (rs.next()) {
            System.out.println("Account Holder: "+rs.getString("name"));
            System.out.println("Phone Number: "+rs.getLong("phone"));
            System.out.println("Account Number: "+rs.getInt("accountno"));
            System.out.println("Account Type: "+rs.getString("accounttype"));
            System.out.println("Branch: "+rs.getString("branch"));
            System.out.println("IFCS: "+rs.getString("ifcs"));
            System.out.println("Balance: "+rs.getDouble("balance"));
        }
        else {
            System.out.println("Oh No! Some unexpected error occurred :(");
            System.out.println("We're Sorry :0");
            LoggedInMenu.loggedInmenu();
        }
    }
}
