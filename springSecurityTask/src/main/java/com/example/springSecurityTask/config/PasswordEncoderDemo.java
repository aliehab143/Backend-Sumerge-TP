package com.example.springSecurityTask.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderDemo implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("\n=== PASSWORD ENCODER DEMO ===");
        
        // Your current setup (NoOpPasswordEncoder)
        var noOpEncoder = NoOpPasswordEncoder.getInstance();
        String providedPassword = "user123";  // What user types
        String storedPassword = "user123";    // What's in database
        
        boolean matches1 = noOpEncoder.matches(providedPassword, storedPassword);
        System.out.println("NoOpPasswordEncoder.matches('user123', 'user123') = " + matches1);
        System.out.println("  -> This is what Spring Security calls during authentication!");
        
        // What would happen with BCrypt
        var bcryptEncoder = new BCryptPasswordEncoder();
        String hashedPassword = "$2a$10$o6EgRvxRlpKNa4KhRQO.o.MZdhQGcq6PnzZ5K2gQrH7EKMqjOJNYO";
        
        boolean matches2 = bcryptEncoder.matches(providedPassword, hashedPassword);
        System.out.println("BCryptPasswordEncoder.matches('user123', 'hashed') = " + matches2);
        
        System.out.println("\n🔥 Spring Security REQUIRES a PasswordEncoder bean!");
        System.out.println("Without it, authentication would fail completely.\n");
    }
}
