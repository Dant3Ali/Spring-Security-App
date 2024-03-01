package com.tech.lab4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "cat_friends")
public class CatFriends implements Serializable {
    @EmbeddedId
    private CatFriendsKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("catID")
    @JoinColumn(name = "cat_id")
    @JsonIgnore
    private Cat cat;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("friendID")
    @JoinColumn(name = "friend_id")
    @JsonIgnore
    private Cat friendCat;
}
