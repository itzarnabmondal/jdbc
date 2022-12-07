package com.bms.validation;

// Importing necessary packages
import com.bms.db.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountExistOrNot {

    // Method to check account exist or not
    public static boolean check(int accountNo, long phoneNo) throws Exception {

        // Creating prepared statement and executing query
        PreparedStatement ps = DbConnection.connect().prepareStatement("""
                select * from bankinfo where accountno=? and ph=?
                """);
        ps.setInt(1, accountNo);
        ps.setLong(2, phoneNo);
        ResultSet resultSet = ps.executeQuery();

        // Returning boolean result
        return resultSet.next();
    }

}
