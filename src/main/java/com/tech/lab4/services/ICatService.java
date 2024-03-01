package com.tech.lab4.services;

import com.tech.lab4.dto.CatDTO;
import com.tech.lab4.entities.Cat;
import com.tech.lab4.exceptions.RecordNotFoundException;

import java.util.List;

public interface ICatService {
    public List<CatDTO> getAllCats();
    public CatDTO createOrUpdateCat(Cat entity);
    public CatDTO getCatById(Long id) throws RecordNotFoundException;
    public void deleteCat(Long id) throws RecordNotFoundException;
    public CatDTO getCatByColor(String color);
    public CatDTO getCatByBreed(String breed);
}
