package com.tech.lab4.dao;

import com.tech.lab4.entities.ERoles;
import com.tech.lab4.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDAO extends JpaRepository<Role, Integer> {
    Optional<Role> getRoleByName(ERoles name);
}
