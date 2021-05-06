package com.project.givemehand.utils;

import com.project.givemehand.models.entity.ERole;
import com.project.givemehand.models.entity.Role;
import com.project.givemehand.models.entity.User;
import com.project.givemehand.repository.RoleRepository;
import com.project.givemehand.repository.UserRepository;

import com.project.givemehand.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
/*
    @Autowired
    PasswordEncoder encoder; */

    @Autowired
    private UserService service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DB initializes...");
        List<Role> roles = roleRepository.findAll();
        List<User> users = userRepository.findAll();
       // User u = new User( "tina@GMAH.com","tinaGMAH");
        //userRepository.save(u);



        if(roles.isEmpty()){

            roleRepository.save(new Role(ERole.ROLE_PARTICULIER));
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
            System.out.println("--- Roles initialized");
        }

        if(users.isEmpty()){
            String password = "adminGMAH";
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            System.out.println("Password" + encodedPassword);
            User admin = new User( "admin@GMAH.com",encodedPassword);
             /* //      encoder.encode("adminGMAH"));*/
            Set<Role> newRole = new HashSet<>();
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            System.out.println("Role = " + adminRole.toString());
            newRole.add(adminRole);
            admin.setRoles(newRole);
            userRepository.save(admin);

            System.out.println("--- Admin user initialized");
        }


    }
}
