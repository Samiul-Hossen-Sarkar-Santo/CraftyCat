package com.craftycat.normalClasses;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHashing {
    
    private static String salt = getSalt();
    
    private static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String getSaltValue() {
        return salt;
    }

    public static String hashingThePassword(String passFromWeb) {
        String passwordToHash = passFromWeb;

        String hashedPassword = hashPassword(passwordToHash, salt);

        System.out.println("Original Password: " + passwordToHash);
        System.out.println("Salt: " + salt);
        System.out.println("Hashed Password: " + hashedPassword);
        
        return hashedPassword;
    }
    
    public static String hashingThePassword(String passFromWeb, String saltFromDB) {
        String passwordToHash = passFromWeb;
        String salt = saltFromDB;

        String hashedPassword = hashPassword(passwordToHash, salt);

        System.out.println("Original Password: " + passwordToHash);
        System.out.println("Salt from DB: " + salt);
        System.out.println("Hashed Password: " + hashedPassword);
        
        return hashedPassword;
    }

    private static String hashPassword(String password, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());

            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
