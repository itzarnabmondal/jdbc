package com.bms.validation;

// Importing necessary packages
import com.bms.db.DbConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchHash {

    // Method to fetch password hash
    public static String fetchPasswordHash(int accountNo) throws Exception {

        // Creating statement object and executing query
        Statement statement = DbConnection.connect().createStatement();
        ResultSet rs = statement.executeQuery("select passwordhash from bankinfo where accountno="+accountNo);
        rs.next();

        // Returning result
        return rs.getString("passwordhash");
    }

    // Method to fetch password hash
    public static String fetchRecoveryKeyHash(int accountNo) throws Exception {

        // Creating statement object and executing query
        Statement statement = DbConnection.connect().createStatement();
        ResultSet rs = statement.executeQuery("select recoverykeyhash from bankinfo where accountno="+accountNo);
        rs.next();

        // Returning result
        return rs.getString("recoverykeyhash");
    }

    // Method to fetch password hash
    public static String fetchPinHash(int accountNo) throws Exception {

        // Creating statement object and executing query
        Statement statement = DbConnection.connect().createStatement();
        ResultSet rs = statement.executeQuery("select pinhash from bankinfo where accountno="+accountNo);
        rs.next();

        // Returning result
        return rs.getString("pinhash");

    }

}