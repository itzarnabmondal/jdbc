package com.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC_TestCase {

	public static void main(String[] args) {
		 try {
			 	Class.forName("com.mysql.cj.jdbc.Driver");//registering my sql driver
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Debkanta@1998");//connection
				Statement stmt=con.createStatement(); 
				ResultSet rs=stmt.executeQuery("select * from studentmanage");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));			
	}
				con.close();
		 }
catch(Exception e) {
	e.printStackTrace();
}
	}

}
