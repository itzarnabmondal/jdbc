package com.sms.operations;

import com.sms.DbConnection;
import com.sms.main.Main;

import java.sql.ResultSet;
import java.sql.Statement;

public final class Show {
    public static void show() throws Exception {
        Statement statement = new DbConnection().connect().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from students");
        if (!resultSet.next()) {
            System.out.println("Oh No! Table is empty :(");
        } else {
            do {
                System.out.println(resultSet.getInt(1) + " :: " + resultSet.getString(2) + " :: " + resultSet.getInt(3) + " :: " + resultSet.getString(4) + " :: " + resultSet.getInt(5));
            } while (resultSet.next());
            Main.start();
        }
    }
}