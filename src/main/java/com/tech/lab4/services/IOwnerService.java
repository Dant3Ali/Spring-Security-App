package com.tech.lab4.services;

import com.tech.lab4.exceptions.RecordNotFoundException;
import com.tech.lab4.dto.CatDTO;
import com.tech.lab4.dto.OwnerDTO;
import com.tech.lab4.entities.Owner;

import java.util.List;

public interface IOwnerService {
    List<OwnerDTO> getAllOwners();
    OwnerDTO createOrUpdateOwner(Owner entity);
    OwnerDTO getOwnerById(Long id) throws RecordNotFoundException;
    void deleteOwner(Long id) throws RecordNotFoundException;
    List<CatDTO> getAllOwnerCats(Long id);
    OwnerDTO findByOwnerName(String name);
    Owner findByOwnerNameDef(String name);
}
