package com.tech.lab4.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "owner",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owner_seq_gen")
    @SequenceGenerator(name = "owner_seq_gen", sequenceName = "Owner_seq", allocationSize = 1)

    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    private String password;
    private LocalDate birthdate;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonBackReference
    private List<Cat> cats;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "owner_roles",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    public Owner() {}

    public Owner(String ownerName, String password, LocalDate birthdate) {
        this.name = ownerName;
        this.password = password;
        this.birthdate = birthdate;
    }
}
