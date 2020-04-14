package com.ok.authorization;

import com.ok.authorization.model.Role;
import com.ok.authorization.model.User;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class Run {

    public static void main(String[] args) {
        Set<Role> roles = new HashSet<>();
        Role user = new Role();
        Role admin = new Role();
        user.setRole("USER");
        admin.setRole("ADMIN");
        roles.add(user);
        roles.add(admin);
        User username = new User();
        username.setRoles(roles);

        username.getRoles()
                .forEach(r -> {
                    if (r.getRole().equalsIgnoreCase("ADMIN")) {
                        System.out.println("ADMIN!");
                    } else {
                        System.out.println("USER!");
                    }
                });
    }
}