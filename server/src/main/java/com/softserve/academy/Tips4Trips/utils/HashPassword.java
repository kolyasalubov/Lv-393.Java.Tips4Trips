package com.softserve.academy.Tips4Trips.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
    private static final String SALT = "some_random_salt";

    public static String hashString(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            String passWithSalt = password + SALT;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < passHash.length; i++) {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
