package com.tech.lab4.services;

import com.tech.lab4.dto.CatDTO;
import com.tech.lab4.entities.Cat;

import java.util.Optional;

public class Convertors {
    public static CatDTO convertCatToCatDTO(Optional<Cat> cat){
        Cat newCat = cat.get();
        CatDTO catDTO = new CatDTO();
        catDTO.setBreed(newCat.getBreed());
        catDTO.setBirthdate(newCat.getBirthdate());
        catDTO.setName(newCat.getName());
        catDTO.setId(newCat.getId());
        catDTO.setOwner(newCat.getOwner());
        catDTO.setColor(newCat.getColor());
        catDTO.setFriendsList(newCat.getFriends());
        return catDTO;
    }

    public static CatDTO convertCatToCatDTO(Cat cat){
        CatDTO catDTO = new CatDTO();
        catDTO.setBreed(cat.getBreed());
        catDTO.setBirthdate(cat.getBirthdate());
        catDTO.setName(cat.getName());
        catDTO.setId(cat.getId());
        catDTO.setOwner(cat.getOwner());
        catDTO.setColor(cat.getColor());
        catDTO.setFriendsList(cat.getFriends());
        return catDTO;
    }
}
