package com.tech.lab4.contollers;

import com.tech.lab4.dto.CatDTO;
import com.tech.lab4.dto.OwnerDTO;
import com.tech.lab4.entities.Owner;
import com.tech.lab4.services.IOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final IOwnerService ownerService;

    @GetMapping
    public List<OwnerDTO> getOwners() {
        return ownerService.getAllOwners();
    }

    @PostMapping
    public OwnerDTO create(@RequestBody Owner owner) {
        return ownerService.createOrUpdateOwner(owner);
    }

    @GetMapping(value = "/{id}")
    public OwnerDTO get(@PathVariable("id") Long id) {
        return ownerService.getOwnerById(id);
    }

    @PutMapping
    public OwnerDTO update(@RequestBody Owner owner) {
        return ownerService.createOrUpdateOwner(owner);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id){
        ownerService.deleteOwner(id);
    }

    @GetMapping(value = "/cats")
    public List<CatDTO> getAllCatsByOwnerId(@PathVariable("id") Long id){
        return ownerService.getAllOwnerCats(id);
    }
}

