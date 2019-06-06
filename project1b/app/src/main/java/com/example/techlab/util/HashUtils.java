package com.example.techlab.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtils {

    public static byte[] generateHash(String password,String algorithm,byte[] salt){
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.reset();
            digest.update(salt);
            byte[] hash = digest.digest(password.getBytes());
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] createSalt() {
        byte[] salt = new byte[5];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }
}
