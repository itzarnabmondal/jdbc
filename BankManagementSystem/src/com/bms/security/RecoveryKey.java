package com.bms.security;

public class RecoveryKey {
    private static byte keySize = 32;
    public static String generateRecoveryKey() {
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "0123456789" +
                "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(keySize);

        // generate a random number between 0 to AlphaNumericString variable length
        for (int i = 0; i < keySize; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}