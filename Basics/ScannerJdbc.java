package com.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ScannerJdbc {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.println("enter student name");
		String sname=scan.next();
		System.out.println("enter address");
		String saddress=scan.next();
		System.out.println("enter id");
		int sid=scan.nextInt();
		System.out.println("enter phone number");
		int sphone=scan.nextInt();
		try {
			 	Class.forName("com.mysql.cj.jdbc.Driver");//registering my sql driver
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Debkanta@1998");//connection
				PreparedStatement stmt;
				//inserting data in database table
				String sql="insert into studentmanage values(?,?,?,?)";
				stmt=con.prepareStatement(sql);
				stmt.setString(2, sname);
				stmt.setInt(1,sid);
				stmt.setInt(3,sphone);
				stmt.setString(4, saddress);
				stmt.execute();
				System.out.println("inserted successfully");
		 }
catch(Exception e) {
	e.printStackTrace();
}
	}

}
