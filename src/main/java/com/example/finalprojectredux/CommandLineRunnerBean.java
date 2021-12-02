package com.example.finalprojectredux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void run(String... args) {
        User user = new User("user", "user@domain.com", "user", "user", "user", true);
        Role userRole = new Role("user", "ROLE_USER");
        userRepository.save(user);
        roleRepository.save(userRole);

        User admin = new User("super", "admin@domain.com", "super", "super", "hero", true);
        Role adminRole = new Role("super", "ROLE_ADMIN");
        Role adminRole2 = new Role("super", "ROLE_USER");
        userRepository.save(admin);
        roleRepository.save(adminRole);
        roleRepository.save(adminRole2);

    }
}
