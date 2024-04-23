package com.bms.utils;

import java.util.Scanner;

public class InputManager {
    private static Scanner scanner;

    // Private constructor to prevent instantiation
    private InputManager() {
    }

    // Initialize Scanner during application startup
    public static void init() {
        scanner = new Scanner(System.in);
    }

    // Get the singleton instance of Scanner
    public static Scanner getScanner() {
        if (scanner == null) {
            throw new IllegalStateException("Scanner has not been initialized. Call init() first.");
        }
        return scanner;
    }
}