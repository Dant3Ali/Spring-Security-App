package com.tech.lab4.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class CatFriendsKey implements Serializable {
    @Column(name = "cat_id")
    private Integer catID;
    @Column(name = "friend_id")
    private Integer friendID;
}
