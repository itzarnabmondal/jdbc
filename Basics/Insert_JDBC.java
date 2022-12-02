package com.Student;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

public class Insert_JDBC {
public static void main(String[] args) {
	 try {
		 	Class.forName("com.mysql.cj.jdbc.Driver");//registering my sql driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Debkanta@1998");//connection
			Statement stmt=con.createStatement();  //statement
			stmt.executeUpdate("insert into studentmanage values (1,'DEV',12345678,'Patuli')");//inserting data
			stmt.executeUpdate("insert into studentmanage values (2,'ARNAB',09876675,'US')");//inserting data
			stmt.executeUpdate("insert into studentmanage values (3,'SANKALPA',1200000,'ARGENTINA')");//inserting data


			System.out.println("inserted successfully");
			 con.close();
			 stmt.close();
	 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
}
}
