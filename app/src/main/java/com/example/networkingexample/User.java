package com.example.networkingexample;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    public String id;
    public String email;
    public String password;
    public String name;
    public String major;

    public User()
    {

    }

    public User(String id, String email, String password, String name, String major)
    {
        this.id = id;
        this.email = email;
        this.password = generateHash(password);
        this.name = name;
        this.major = major;
    }

    private String generateHash(String password)
    {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(password.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }



}
