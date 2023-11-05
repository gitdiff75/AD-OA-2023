package com.beta.replyservice;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * To introduce a new operation, follow these steps:
 * 1. Add a new enumeration constant in the Operation enum.
 * 2. In the perform method, add a new case in the switch statement that corresponds to the newly added enum constant.
 * 3. Ensure you define the functionality for this new case according to the requirements of the operation.
 */
public enum Operation {
    REVERSE("1"),
    MD5("2");

    private final String rule;

    static final Map<String, Operation> map;

    // Enum constructor is called when the enum constants are initialized
    static {
        map = Arrays.stream(Operation.values())
                .collect(Collectors.toMap(Operation::getRule, op -> op));
    }


    Operation(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }

    private boolean validateMessage(String message) {
        return message != null && message.matches("^[a-zA-Z0-9]*$") && message.length() <= 100;
    }

    public String perform(String message) {
        if (!validateMessage(message)) {
            throw new IllegalArgumentException("Invalid input");
        }
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
