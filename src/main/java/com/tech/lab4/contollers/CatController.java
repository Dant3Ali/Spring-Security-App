package com.tech.lab4.contollers;

import com.tech.lab4.services.ICatFriendsService;
import com.tech.lab4.services.ICatService;
import com.tech.lab4.dto.CatDTO;
import com.tech.lab4.entities.Cat;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
@RequiredArgsConstructor
public class CatController {

    private final ICatService catService;
    private final ICatFriendsService catFriendsService;

    @GetMapping
    public List<CatDTO> getCats() {
        return catService.getAllCats();
    }

    @GetMapping(value = "/friends/{id}")
    public List<CatDTO> friends(@PathVariable("id") Long id) {
        return catFriendsService.findAllCatFriendsById(id);
    }


    @GetMapping(value = "/color/{color}")
    public CatDTO getCatsWithColor(@PathVariable("color") String color) {
        return catService.getCatByColor(color);
    }

    @GetMapping(value = "/breed/{breed}")
    public CatDTO getCatsWithBreed(@PathVariable("breed") String breed) {
        return catService.getCatByBreed(breed);
    }

    @PostMapping
    public CatDTO create(@RequestBody Cat cat) {
        return catService.createOrUpdateCat(cat);
    }

    @GetMapping(value = "/{id}")
    public CatDTO get(@PathVariable("id") Long id) {
        return catService.getCatById(id);
    }

    @PutMapping
    public CatDTO update(@RequestBody Cat cat) {
        return catService.createOrUpdateCat(cat);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id){
        catService.deleteCat(id);
    }

    @Transactional
    @DeleteMapping(value = "/friends/{id}")
    public void deleteFriend(@PathVariable("id") Long id){
        catFriendsService.deleteFriends(id);
    }
}
