package com.example.uz_kassa.repository;

import com.example.uz_kassa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsernameAndActive(String username, boolean active);
    Optional<User> findByPhone(String phone);
    Optional<User> findByUsername(String username);
    Optional<User> findByPhoneOrUsername(String phone, String username);
    Optional<User> findByIdAndActive(Long id, Boolean active);


}
