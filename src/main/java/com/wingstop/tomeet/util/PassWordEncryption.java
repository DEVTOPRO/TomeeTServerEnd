package com.wingstop.tomeet.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassWordEncryption {
    public String getPasswordEncription(String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode(password);

        return hashedPassword;
    }

    public boolean getpasswordDecode(String password, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
        return isPasswordMatch;
    }
}
