package com.example.uz_kassa.repository;

import com.example.uz_kassa.entity.Role;
import com.example.uz_kassa.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByName(RoleName roleName);


}
