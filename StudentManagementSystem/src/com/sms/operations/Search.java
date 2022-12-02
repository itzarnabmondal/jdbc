package com.sms.operations;

import com.sms.DbConnection;

import java.sql.ResultSet;
import java.sql.Statement;

public final class Search {
    public static boolean search (int id) throws Exception {
        Statement statement = new DbConnection().connect().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from students where id="+id);
        if (!resultSet.next()) {
            System.out.println("Sorry! No Result Found");
            return false;
        } else {
            do {
                System.out.println(resultSet.getInt(1) + " :: " + resultSet.getString(2) + " :: " + resultSet.getInt(3) + " :: " + resultSet.getString(4) + " :: " + resultSet.getInt(5));
                return true;
            } while (resultSet.next());
        }

    }
}
