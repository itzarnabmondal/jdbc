package com.sms.operations;

import com.sms.DbConnection;
import com.sms.main.Main;
import com.sms.student.Student;
import java.sql.PreparedStatement;
import java.util.Scanner;

public final class Insert  {
    static boolean isShowed=false;
    public static void insert() throws Exception {
        if(!isShowed) { banner(); }
        System.out.println("ID\tName\tAge\tCity\tPhoneNo");
        String[] text = new String[100];
        Scanner sc = new Scanner(System.in);
        int i = 0, counter = 0; String line;
        while (!(line = sc.nextLine()).equals(":w")){
            text[i] = line;
            i++;
        }
       for (i=0; text[i] != null; i++) {
           sc = new Scanner(text[i]);
           while (sc.hasNext()) {
                try {
                    Student.id = Integer.parseInt(sc.next());
                    if (Search.search(Student.id)) {
                        System.out.println("Oh No! This Entry already exist :( \n"+"Enter new id: ");
                        insert();
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Oh No! id should be an integer value"); insert();
                }
               Student.name = sc.next();
               try {
                   Student.age = Integer.parseInt(sc.next());
               } catch (NumberFormatException e) {
                   System.err.println("Oh No! Age should be an integer value"); insert();
               }
               Student.city = sc.next();
               try {
                   Student.ph = Integer.parseInt(sc.next());
               } catch (NumberFormatException e) {
                   System.out.println("Oh No! Phone should be an integer value"); insert();
               }
               System.out.println(Student.id+" :: " + Student.name+" :: "+ Student.age+" :: "+Student.city+" :: "+Student.ph);
               PreparedStatement preparedStatement = new DbConnection().connect().prepareStatement("insert into students values(?,?,?,?,?)");
               preparedStatement.setInt(1, Student.id);
               preparedStatement.setString(2, Student.name);
               preparedStatement.setInt(3, Student.age);
               preparedStatement.setString(4, Student.city);
               preparedStatement.setInt(5, Student.ph);
               int count = preparedStatement.executeUpdate();
               if (count > 0) {
                   counter++;
               }
           }
       }
        System.out.println(counter + " row/s affected");
        Main.start();
    }
    static void banner() {
        System.out.println("""
                :: Enter column details as per order separated by single space ::
                   You Can Insert multiple rows just enter new rows values by
                   pressing enter and then enter :q for complete .............
                   ************ Example:  1 Alex 25 Tokyo 27337456 ************
                """);
        isShowed = true;
    }
}