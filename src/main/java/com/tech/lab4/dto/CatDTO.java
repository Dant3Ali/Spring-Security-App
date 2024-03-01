package com.tech.lab4.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tech.lab4.entities.CatFriends;
import com.tech.lab4.entities.Owner;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
public class CatDTO {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String breed;
    private String color;
    @JsonIgnore
    private Owner owner;
    @JsonIgnore
    private List<CatFriends> friendsList;
}
