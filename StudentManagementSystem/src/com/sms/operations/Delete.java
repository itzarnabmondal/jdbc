package com.sms.operations;

import com.sms.DbConnection;
import com.sms.main.Main;

import java.sql.Statement;

public final class Delete {
    public static void delete(String id) throws Exception {
        Statement statement = new DbConnection().connect().createStatement();
        int count=0;
        if (id.equalsIgnoreCase("all")) {
            count = statement.executeUpdate("DELETE FROM students");
            System.err.println(count+" row/s affected ");
        } else {
            try { count = statement.executeUpdate("DELETE FROM students WHERE id="+Integer.parseInt(id)); }
            catch (NumberFormatException i) {
                System.err.println("Please Enter valid id number: "); Main.start();
            }
        }
        if (count == 0) {
            System.out.println("Sorry! no result found of ID="+id);
        } else {
            System.out.println("ID : "+id+" Successfully deleted from database");
        }
    }
}