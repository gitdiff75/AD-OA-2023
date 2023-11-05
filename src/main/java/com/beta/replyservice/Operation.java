package com.beta.replyservice;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum Operation {
    REVERSE("1"),
    MD5("2");

    private final String rule;

    Operation(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    public String perform(String message) {
        switch (rule) {
            case "1":
                return reverse(message);
            case "2":
                return md5(message);
            default:
                throw new Error("Invalid operation");
        }
    }

    // Operations implementation
    private static String md5(String data) {
        // compute the data with md5 encoding
        // Get a MessageDigest object for the MD5 algorithm
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("MD5 algorithm not found in MessageDigest.getInstance");
        }
        // Calculate the hash
        byte[] messageDigest = md.digest(data.getBytes());

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    private static String reverse(String data) {
        return new StringBuilder(data).reverse().toString();
    }
}
