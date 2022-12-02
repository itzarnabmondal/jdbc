package com.sms;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    public Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Arnab@2003");
    }
}