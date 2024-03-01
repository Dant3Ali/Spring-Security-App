package com.tech.lab4.services;

import com.tech.lab4.dao.OwnerDAO;
import com.tech.lab4.exceptions.RecordNotFoundException;
import com.tech.lab4.dto.CatDTO;
import com.tech.lab4.dto.OwnerDTO;
import com.tech.lab4.entities.Cat;
import com.tech.lab4.entities.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService implements IOwnerService, UserDetailsService {

    private final OwnerDAO ownerDAO;
    private final CatService catService;

    private OwnerDTO convertOwnerToDTO(Optional<Owner> owner){
        if (owner.isEmpty()){
            return null;
        }

        Owner newOwner = owner.get();
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setBirthdate(newOwner.getBirthdate());
        ownerDTO.setCats(newOwner.getCats());
        ownerDTO.setId(newOwner.getId());
        ownerDTO.setName(newOwner.getName());
        return ownerDTO;
    }

    private OwnerDTO convertOwnerToDTO(Owner owner){
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setBirthdate(owner.getBirthdate());
        ownerDTO.setCats(owner.getCats());
        ownerDTO.setId(owner.getId());
        ownerDTO.setName(owner.getName());
        return ownerDTO;
    }

    @Override
    public List<OwnerDTO> getAllOwners()
    {
        List<OwnerDTO> ownerList = ownerDAO.findAll().stream().map(this::convertOwnerToDTO).collect(Collectors.toList());

        if (!ownerList.isEmpty()){
            return ownerList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public OwnerDTO createOrUpdateOwner(Owner entity)
    {
        Optional<Owner> owner = ownerDAO.findById(entity.getId());

        if (owner.isPresent()){
            Owner newOwner = owner.get();
            newOwner.setId(entity.getId());
            newOwner.setBirthdate(entity.getBirthdate());
            newOwner.setName(entity.getName());
            newOwner.setCats(entity.getCats());

            newOwner = ownerDAO.save(newOwner);

            return convertOwnerToDTO(newOwner);
        } else {
            entity = ownerDAO.save(entity);

            return convertOwnerToDTO(entity);
        }
    }

    @Override
    public OwnerDTO getOwnerById(Long id) throws RecordNotFoundException
    {
        Optional<Owner> owner = ownerDAO.findById(id);

        if (owner.isPresent()){
            return convertOwnerToDTO(owner);
        } else {
            throw new RecordNotFoundException("No such owner in db for given id");
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteOwner(Long id) throws RecordNotFoundException
    {
        Optional<Owner> owner = ownerDAO.findById(id);

        if (owner.isPresent()){
            Owner newOwner = owner.get();
            List<Cat> cats = newOwner.getCats();
            cats.stream().map(Cat::getId).toList().forEach(catService::deleteCat);
            ownerDAO.deleteById(id);
        } else {
            throw new RecordNotFoundException("No such owner in db for given id");
        }
    }

    @Override
    public List<CatDTO> getAllOwnerCats(Long id){
        Optional<Owner> owner = ownerDAO.findById(id);

        return owner.map(value -> value.getCats().stream().map(Convertors::convertCatToCatDTO).collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = findByOwnerNameDef(username);
        if (owner == null){
            return null;
        }

        return OwnerDTO.build(owner);
    }

    @Override
    public OwnerDTO findByOwnerName(String name){
        return convertOwnerToDTO(ownerDAO.findOwnerByName(name));
    }

    @Override
    public Owner findByOwnerNameDef(String name){
        return ownerDAO.findOwnerByName(name);
    }
}