package com.tech.lab4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String breed;
    private String color;
    @ManyToOne()
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Owner owner;
    @OneToMany(mappedBy = "cat", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CatFriends> friends;
    public Cat() {}
}
