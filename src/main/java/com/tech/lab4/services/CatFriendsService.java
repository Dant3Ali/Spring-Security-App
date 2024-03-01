package com.tech.lab4.services;

import com.tech.lab4.dao.CatFriendDAO;
import com.tech.lab4.dto.CatDTO;
import com.tech.lab4.entities.Cat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatFriendsService implements ICatFriendsService{

    private final CatFriendDAO cfDao;
    @Override
    public List<CatDTO> findAllCatFriendsById(Long id) {
        return cfDao.findFriendsByCatId(id).stream().map(Convertors::convertCatToCatDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteFriends(Long catID) {
        cfDao.findFriendByCatId(catID).stream().map(Cat::getId).toList().forEach(cfDao::deleteAllByCatId);
        cfDao.deleteAllByCatId(catID);
    }
}
