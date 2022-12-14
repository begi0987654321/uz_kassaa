package com.example.uz_kassa.controllers;

import com.example.uz_kassa.config.SecurityConfig;
import com.example.uz_kassa.entity.User;
import com.example.uz_kassa.repository.MessageRepository;
import com.example.uz_kassa.repository.RoleRepository;
import com.example.uz_kassa.repository.UserRepository;
import com.example.uz_kassa.response.Response;
import com.example.uz_kassa.response.Status;
import com.example.uz_kassa.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/profile/")
public class UserController {
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final ObjectMapper objectMapper;
    private final UserServiceImpl userService;
    private final MessageRepository messageRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public UserController(UserRepository userRepository, SecurityConfig securityConfig, ObjectMapper objectMapper, UserServiceImpl userService, MessageRepository messageRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, JdbcTemplate jdbcTemplate ) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.messageRepository = messageRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jdbcTemplate = jdbcTemplate;

    }

    @GetMapping("data")
    public HttpEntity<?> getUserData() {
        Response response = new Response();
        Status status = null;
        ObjectNode data = objectMapper.createObjectNode();
        User currentUser = securityConfig.getCurrentUser();
        data.put("id", currentUser.getId());
        data.put("username", currentUser.getUsername());
        data.put("phone", currentUser.getPhone());
        data.put("firstName", currentUser.getFirstName());
        data.put("lastName", currentUser.getLastName());
        data.put("email", currentUser.getEmail());
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select su.sklad_id from sklad_users su where su.users_id=? order by su.sklad_id", currentUser.getId());
//        data.put("sklads",maps.toString());
        String s = jdbcTemplate.queryForObject("select  r.name from role r left join user_role ur on r.id = ur.role_id where ur.user_id=?", new Object[]{currentUser.getId()}, String.class);
        data.put("role", s);
        response.setMessage(messageRepository.findByCode(0));
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("list")
    public HttpEntity<?> getUsersList() {
        Response response = new Response();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select au.id, au.active, au.username, au.first_name, au.last_name, r.name as role_name, au.phone, au.email from app_user au left join user_role ur on au.id = ur.user_id left join role r on r.id = ur.role_id group by au.id, au.username, au.first_name, r.name, au.phone, au.email order by au.id");
        response.setMessage(messageRepository.findByCode(0));
        response.setData(maps);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("active")
    public HttpEntity<?> updateActive(@RequestBody ObjectNode objectNode) {
        Response response = new Response();
        long userId = objectNode.get("user_id").asLong();
        boolean active = objectNode.get("active").asBoolean();
        Optional<User> byId = userRepository.findById(userId);
        Long id = securityConfig.getCurrentUser().getId();
        if (byId.isPresent()) {
            if (byId.get().getId() != id) {
                User user = byId.get();
                user.setActive(active);
                userRepository.save(user);
                response.setMessage(messageRepository.findByCode(0));
            } else {
                response.setMessage(messageRepository.findByCode(1020));
            }
        } else {
            response.setMessage(messageRepository.findByCode(101));
        }
        return ResponseEntity.ok(response);
    }
}