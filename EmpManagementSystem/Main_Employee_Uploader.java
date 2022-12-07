package dec_1st_lab;

import java.util.Scanner;

public class Main_Employee_Uploader {

	public static void main(String[] args)  {
		EmployeeUploader emp=new EmployeeUploader();
		Scanner sc=new Scanner(System.in);
		int choice;
		int id;
		System.out.println("[1 -->Insert],[2-->Retrieve],[3-->Calculate PF]");
		try {
		choice=sc.nextInt();
			if(choice==1) {
					System.out.println("[Press 1 for Department],[Press 2 for Employee]");
					choice=sc.nextInt();
					if(choice==1) {
						emp.storeDepartmentDetails();
					}else if(choice==2) {
						emp.storeEmployeeDetails();
					}
			}else if (choice==2) {
					System.out.println("Enter id of the employye : ");
					id=sc.nextInt();
					emp.retrieveEmployeeDetails(id);
			}else if(choice==3) {
					System.out.println("Enter id of the employee : ");
					id=sc.nextInt();
					emp.calculatePF(id);
			}
		}catch(InvalidSalaryException e) {
			System.out.println(e.getMessage());
		}catch(ID_Not_Found_Exception e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}