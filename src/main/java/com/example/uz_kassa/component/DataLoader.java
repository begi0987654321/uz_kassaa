package com.example.uz_kassa.component;

import com.example.uz_kassa.entity.User;
import com.example.uz_kassa.enums.RoleName;
import com.example.uz_kassa.repository.RoleRepository;
import com.example.uz_kassa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            userRepository.save(new User("begzod",
                    passwordEncoder.encode("1234"),
                    "begzodbozorov@gmail.com",
                    "begzod",
                    "bozorov",
                    "+998977573538",
                    roleRepository.findAllByName(RoleName.ROLE_ADMIN)));
//            userRepository.save(new User(
//                    "superAdmin",
//                    passwordEncoder.encode("root123"),
//                    "Super Administrator",
//                    roleRepository.findAll()));

//            userRepository.save(new User(
//                    "admin",
//                    passwordEncoder.encode("admin1"),
//                    "Administrator",
//                    roleRepository.findAllByName(RoleName.ROLE_ADMIN)));
//
//            userRepository.save(new User(
//                    "moderator",
//                    passwordEncoder.encode("moder123"),
//                    "Moderator",
//                    roleRepository.findAllByName(RoleName.ROLE_MODERATOR)));
//
//            userRepository.save(new User(
//                    "user",
//                    passwordEncoder.encode("123"),
//                    "Begzod Bozorov",
//                    roleRepository.findAllByName(RoleName.ROLE_USER)));
        }
    }
}
