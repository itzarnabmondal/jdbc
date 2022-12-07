package com.bms.security;

// Importing necessary package
import com.bms.operations.CustomerInfo;

public class DestroySession {
    public static void destroy() throws Exception {
        CustomerInfo.setIsLoggedIn(false);
        CustomerInfo.setAccountNo(0);
        CustomerInfo.setName(null);
        CustomerInfo.setPhone(0L);
        CustomerInfo.setBalance(0);
        CustomerInfo.setPasswordHash(null);
        CustomerInfo.setBranch(null);
        CustomerInfo.setAccountType(null);
        CustomerInfo.setIfcsCode(null);
        CustomerInfo.setRecoveryKeyHash(null);
        CustomerInfo.setPinHash(null);
        CustomerInfo.setIsPinEnabled(false);
        System.out.println("You are logged out!");

    }
}
