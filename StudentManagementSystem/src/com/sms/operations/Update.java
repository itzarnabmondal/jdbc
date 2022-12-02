package com.sms.operations;

import com.sms.DbConnection;
import com.sms.main.Main;
import com.sms.student.Student;

import java.sql.PreparedStatement;
import java.util.Scanner;


public class Update {
    public static void update() throws Exception {
        System.out.println("""
                :: Enter student id and column details as per order separated by single space ::
                   Example:  1 Alex 25 Tokyo 27337456 [ 1 is the id you want to update ]
                """);
        System.out.println("ID\tName\tAge\tCity\tPhoneNo");
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        sc = new Scanner(text);
        try {
            while (sc.hasNext()) {
                try {
                    Student.id = Integer.parseInt(sc.next());
                } catch (NumberFormatException e) {
                    System.err.println("Oh No! id should be an integer value");
                    update();
                }
                Student.name = sc.next();
                try {
                    Student.age = Integer.parseInt(sc.next());
                } catch (NumberFormatException e) {
                    System.err.println("Oh No! Age should be an integer value");
                    update();
                }
                Student.city = sc.next();
                try {
                    Student.ph = Integer.parseInt(sc.next());
                } catch (NumberFormatException e) {
                    System.out.println("Oh No! Phone should be an integer value");
                    update();
                }
            }
        } catch (Exception e) {
            System.out.println("Sorry Something wrong happened :( \nPlease try again:");
            update();
        }

        if (!Search.search(Student.id)) {

        }
        else {
                PreparedStatement preparedStatement = new DbConnection().connect().prepareStatement("""
                        update students set name=?, age=?, city=?, ph=? where id=?
                        """);
                preparedStatement.setString(1, Student.name);
                preparedStatement.setInt(2, Student.age);
                preparedStatement.setString(3, Student.city);
                preparedStatement.setInt(4, Student.ph);
                preparedStatement.setInt(5, Student.id);
                preparedStatement.executeUpdate();
                System.out.println("Operation successfully! updated Values:");
                Search.search(Student.id);
                Main.start();
        }

    }

}
