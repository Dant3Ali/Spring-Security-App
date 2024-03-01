package com.tech.lab4.services;

import com.tech.lab4.dao.CatDAO;
import com.tech.lab4.exceptions.RecordNotFoundException;
import com.tech.lab4.dto.CatDTO;
import com.tech.lab4.entities.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(isolation = Isolation.SERIALIZABLE)
public class CatService implements ICatService {

    private final CatDAO catDAO;
    private final CatFriendsService catFriendsService;

    @Override
    public List<CatDTO> getAllCats()
    {
        List<CatDTO> catList = catDAO.findAll().stream().map(Convertors::convertCatToCatDTO).collect(Collectors.toList());

        if (!catList.isEmpty()){
            return catList;
        } else {
            return new ArrayList<CatDTO>();
        }
    }

    @Override
    public CatDTO createOrUpdateCat(Cat entity)
    {
        Optional<Cat> cat = catDAO.findById(entity.getId());

        if (cat.isPresent()){
            Cat newCat = cat.get();
            newCat.setId(entity.getId());
            newCat.setBreed(entity.getBreed());
            newCat.setBirthdate(entity.getBirthdate());
            newCat.setName(entity.getName());
            newCat.setColor(entity.getColor());
            newCat.setOwner(entity.getOwner());

            newCat = catDAO.save(newCat);

            return Convertors.convertCatToCatDTO(newCat);
        } else {
            entity = catDAO.save(entity);

            return Convertors.convertCatToCatDTO(entity);
        }
    }

    @Override
    public CatDTO getCatById(Long id) throws RecordNotFoundException
    {
        Optional<Cat> cat = catDAO.findById(id);

        if (cat.isPresent()){
            return Convertors.convertCatToCatDTO(cat);
        } else {
            throw new RecordNotFoundException("No such cat in db for given id");
        }
    }

    @Override
    public void deleteCat(Long id) throws RecordNotFoundException
    {
        Optional<Cat> cat = catDAO.findById(id);

        if (cat.isPresent()){
            catFriendsService.deleteFriends(id);
            catDAO.deleteById(id);
        } else {
            throw new RecordNotFoundException("No such cat in db for given id");
        }
    }

    @Override
    public CatDTO getCatByColor(String color){
        Optional<Cat> cat = catDAO.findCatByColor(color);

        if (cat.isPresent()){
            return Convertors.convertCatToCatDTO(cat);
        } else {
            throw new RuntimeException("no such cat with this color");
        }
    }

    @Override
    public CatDTO getCatByBreed(String breed){
        Optional<Cat> cat = catDAO.findCatByBreed(breed);

        if (cat.isPresent()){
            return Convertors.convertCatToCatDTO(cat);
        } else {
            throw new RuntimeException("no such cat with this breed");
        }
    }
}