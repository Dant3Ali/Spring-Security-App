package com.tech.lab4.dto;

import com.tech.lab4.entities.CatFriendsKey;
import com.tech.lab4.entities.Cat;
import lombok.Data;

@Data
public class CatFriendsDTO {
    private CatFriendsKey id;
    private Cat cat;
    private Cat friendCat;
}
