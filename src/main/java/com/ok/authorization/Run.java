package com.ok.authorization;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class Run {

    public static void main(String[] args) {
        Set<SomeClass> strings = new HashSet<>();
        strings.add(new SomeClass("ROLE_1"));
        strings.add(new SomeClass("ROLE_2"));
        strings.add(new SomeClass("ROLE_3"));

        for (int i = 0; i < strings.size(); i++) {
            System.out.println(strings.toArray()[i]);
        }

        for (SomeClass element : strings) {
            System.out.println(element);
        }




        SomeClass someClass = strings.stream().filter(r -> r.getRole().equalsIgnoreCase("role_1"))
                .findFirst().orElse(null);
        if (someClass != null) {
            System.out.println("Found! " + someClass.getRole());
        } else {
            System.out.println("No such role...");
        }
    }

    private static class SomeClass {
        private String role;

        public SomeClass() {
        }

        public SomeClass(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}