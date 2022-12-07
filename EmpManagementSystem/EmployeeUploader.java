package dec_1st_lab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import dec_1st_lab.*;
class ID_Not_Found_Exception extends Exception{
	ID_Not_Found_Exception(String str){
		super(str);
	}
}
class InvalidSalaryException extends Exception{
	InvalidSalaryException(String str){
		super(str);
	}
}
public class EmployeeUploader {
	Scanner sc=new Scanner(System.in);
	//saving department details
	public void storeDepartmentDetails() throws SQLException{
		System.out.println("Enter department id : ");
		int dep_id=sc.nextInt();
		System.out.println("Enter department name : ");
		String dep_name=sc.next();
		System.out.println("Enter department head : ");
		String dep_head=sc.next();
		System.out.println("Enter department description : ");
		String dep_desc=sc.next();
		Connection conn=Helper.con();
		PreparedStatement stmt=conn.prepareStatement("insert into Department values(?,?,?,?)");
		stmt.setInt(1, dep_id);
		stmt.setString(2, dep_name);
		stmt.setString(3, dep_head);
		stmt.setString(4, dep_desc);
		stmt.executeUpdate();
		System.out.println("Data inserted successfully");
		
	}
	//storing employee data
	public void storeEmployeeDetails() throws SQLException,InvalidSalaryException{
		System.out.println("Enter employee id : ");
		int emp_id=sc.nextInt();
		System.out.println("Enter employee name : ");
		String emp_name=sc.next();
		System.out.println("Enter employee address : ");
		String emp_address=sc.next();
		System.out.println("Enter employee salary : ");
		double emp_sal=sc.nextDouble();
		System.out.println("Enter employee contact no : ");
		long emp_contact_no=sc.nextLong();
		System.out.println("Enter department id : ");
		int dep_id=sc.nextInt();
		if(emp_sal<1000) {
			throw new InvalidSalaryException("Salary can't be less than 1000");
		}else {
			Connection conn=Helper.con();
			PreparedStatement stmt=conn.prepareStatement("insert into Employee values(?,?,?,?,?,?)");
			stmt.setInt(1, emp_id);
			stmt.setString(2, emp_name);
			stmt.setString(3, emp_address);
			stmt.setDouble(4, emp_sal);
			stmt.setLong(5, emp_contact_no);
			stmt.setInt(6, dep_id);
			stmt.executeUpdate();
			System.out.println("Data inserted successfully");
		}		
	}
	//fetching data
	public void retrieveEmployeeDetails(int id) throws ID_Not_Found_Exception, SQLException{
		Connection conn=Helper.con();
		Statement stmt=conn.createStatement();
		//execute query fetch data from database
		ResultSet rs=stmt.executeQuery("select emp_id,emp_name,emp_contact_no,emp_address,dep_name,dep_head from Employee inner join Department on Employee.dep_id=Department.dep_id where emp_id="+id);
		if(!rs.next()) {
			throw new ID_Not_Found_Exception("Employee Id not Present");
		}else {
				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getLong(3)+","+rs.getString(4)+","+rs.getString(5)+
						","+rs.getString(6));
		}
	}
	//calculating PF
	public void calculatePF(int id) throws SQLException,ID_Not_Found_Exception {
		double salary=0;
		Connection conn=Helper.con();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select emp_sal from Employee where emp_id="+id);
		if(!rs.next()) {
			throw new ID_Not_Found_Exception("Employee Id not Present");
		}else{
			salary=rs.getDouble(1);
			if(salary>=1000 && salary<=10000) {
				System.out.println("Employee PF amount is : "+(salary*5)/100);
			}else if(salary>10000 && salary<=100000) {
				System.out.println("Employee PF amount is : "+(salary*6)/100);
			}else if(salary>100000) {
				System.out.println("Employee PF amount is : "+(salary*7)/100);
			}
		}
	}
}
