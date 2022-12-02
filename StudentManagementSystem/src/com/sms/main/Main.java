package com.sms.main;

import com.sms.operations.*;

import java.util.Scanner;

public final class Main{
    public static void main(String ... args) throws Exception {
        System.out.println(":: Welcome to student management system ::");
        start();
    }
    public static void start() throws Exception {
        char input;
        System.out.println("""
                ENTER 1 FOR SHOW
                ENTER 2 FOR INSERT
                ENTER 3 FOR DELETE
                ENTER 4 FOR SEARCH
                ENTER 5 FOR UPDATE
                ENTER 0 FOR EXIT
                """);
        Scanner sc = new Scanner(System.in);
        input = sc.next().charAt(0);
        switch (input) {
            case '1' -> Show.show();
            case '2' -> Insert.insert();
            case '3' -> {
                System.out.println("Enter The Student id for delete or enter all for delete all students");
                String in = sc.next();
                Delete.delete(in);
            }
            case '4' -> {
                System.out.println("Enter student id: ");
                Search.search(sc.nextInt());
            }
            case '5' -> Update.update();
            case '0' -> System.exit(0);
            default -> {
                System.out.println("Wrong Input!");
                start();
            }
        }
    }
}
