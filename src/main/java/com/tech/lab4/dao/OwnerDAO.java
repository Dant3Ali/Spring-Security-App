package com.tech.lab4.dao;

import com.tech.lab4.dto.OwnerDTO;
import com.tech.lab4.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Long> {
    Owner findOwnerByName(String name);
    boolean existsOwnerByName(String name);
}