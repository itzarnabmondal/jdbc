package com.bms.main;

import com.bms.navigation.MainMenu;
import com.bms.utils.InputManager;

public class Main {

    // Main Method
    public static void main(String[] args) throws Exception {

        InputManager.init(); // Initializing InputManager.

        welcome(); // To show welcome banner.

        MainMenu.mainMenu(); // Navigating to mainMenu
    }

    // Welcome method
    public static void welcome() {
        System.out.println("Welcome To,");
        System.out.println("""
                 __  __                ___            __ \s
                 \\ \\/ /__  __ ______  / _ )___ ____  / /__
                  \\  / _ \\/ // / __/ / _  / _ `/ _ \\/  '_/
                  /_/\\___/\\_,_/_/   /____/\\_,_/_//_/_/\\_\\\s
                                                         \s
                """);
    }
}