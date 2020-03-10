package com.ok.authorization;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Run {
    public static void main(String[] args) {
        String admin = new BCryptPasswordEncoder().encode("admin");
        String user = new BCryptPasswordEncoder().encode("user");
        System.out.println("A: " + admin);
        System.out.println("U: " + user);
    }
}