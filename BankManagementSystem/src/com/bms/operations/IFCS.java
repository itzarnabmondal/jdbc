package com.bms.operations;

public class IFCS {

    // enum to store IFCS information
    private enum ifcs {
        KOL752345,
        MUM673564,
        HYD253647,
        JAI536484,
        DEL563457,
    }

    // Method to set ifcs code
    public static void setIfcs(String branch) {

        // Checking branch name and assigning ifcs
        switch (branch) {
            case "Kolkata" -> CustomerInfo.setIfcsCode(ifcs.KOL752345.name());
            case "Mumbai" -> CustomerInfo.setIfcsCode(ifcs.MUM673564.name());
            case "Hyderabad" -> CustomerInfo.setIfcsCode(ifcs.HYD253647.name());
            case "Jaipur" -> CustomerInfo.setIfcsCode(ifcs.JAI536484.name());
            case "Delhi" -> CustomerInfo.setIfcsCode(ifcs.DEL563457.name());
            default -> {
                // for any internal errors
                System.err.println("Something wrong happened!\n We're Sorry :(");
                System.exit(0);
            }
        }
    }
}