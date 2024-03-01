package com.tech.lab4.dao;

import com.tech.lab4.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatDAO extends JpaRepository<Cat, Long> {

    Optional<Cat> findCatByBreed(String breed);
    Optional<Cat> findCatByColor(String color);
}

