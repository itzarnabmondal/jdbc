package com.bms.operations;

public class CustomerInfo {

    // Variables to store customer information
    private static int accountNo;
    private static String name;
    private static Long phone;
    private static double balance;
    private static String passwordHash;
    private static String branch;
    private static String accountType;
    private static String ifcsCode;
    private static String recoveryKeyHash;
    private static String pinHash = null;
    private static boolean isPinEnabled = false;

    // Session Only
    private static boolean isLoggedIn = false;



    // Getters and Setters to do operations on private variables

    public static int getAccountNo() {
        return accountNo;
    }

    public static void setAccountNo(int accountNo) {
        CustomerInfo.accountNo = accountNo;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        CustomerInfo.name = name;
    }

    public static Long getPhone() {
        return phone;
    }

    public static void setPhone(Long phone) {
        CustomerInfo.phone = phone;
    }

    public static double getBalance() {
        return balance;
    }

    public static void setBalance(double balance) {
        CustomerInfo.balance = balance;
    }

    public static String getPasswordHash() {
        return passwordHash;
    }

    public static void setPasswordHash(String passwordHash) {
        CustomerInfo.passwordHash = passwordHash;
    }

    public static String getBranch() {
        return branch;
    }

    public static void setBranch(String branch) {
        CustomerInfo.branch = branch;
    }

    public static String getAccountType() {
        return accountType;
    }

    public static void setAccountType(String accountType) {
        CustomerInfo.accountType = accountType;
    }

    public static String getIfcsCode() {
        return ifcsCode;
    }

    public static void setIfcsCode(String ifcsCode) {
        CustomerInfo.ifcsCode = ifcsCode;
    }

    public static String getRecoveryKeyHash() {
        return recoveryKeyHash;
    }

    public static void setRecoveryKeyHash(String recoveryKeyHash) {
        CustomerInfo.recoveryKeyHash = recoveryKeyHash;
    }

    public static String getPinHash() {
        return pinHash;
    }

    public static void setPinHash(String pinHash) {
        CustomerInfo.pinHash = pinHash;
    }

    public static boolean isIsPinEnabled() {
        return isPinEnabled;
    }

    public static void setIsPinEnabled(boolean isPinEnabled) {
        CustomerInfo.isPinEnabled = isPinEnabled;
    }

    public static boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public static void setIsLoggedIn(boolean isLoggedIn) {
        CustomerInfo.isLoggedIn = isLoggedIn;
    }
}