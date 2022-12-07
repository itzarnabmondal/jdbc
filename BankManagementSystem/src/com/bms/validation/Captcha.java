package com.bms.validation;

// Importing necessary packages
import java.util.Random;
import java.util.Scanner;

public class Captcha {

    // Captcha
    public static void captcha() {

        int input = 0;

        // Generating two random value
        int x = new Random().nextInt(10);
        int y = new Random().nextInt(10);

        // Multiplying value
        int z = x * y;

        // Telling user to solve the multiplying
        System.out.println("prove you are a human: ");
        System.out.println("What is the result of "+ x +" * "+ y +" ?");

        // Handling exception
        try {
            input = new Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input!\nPlease try again: ");
            captcha();
        }
        if (z == input) {
            System.out.println("Verification successful :)");
        } else {
            System.out.println("Wrong Input!\nPlease try again: ");
            captcha();
        }
    }
}