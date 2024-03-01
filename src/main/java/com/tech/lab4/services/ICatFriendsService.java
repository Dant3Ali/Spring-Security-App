package com.tech.lab4.services;

import com.tech.lab4.dto.CatDTO;

import java.util.List;

public interface ICatFriendsService {
    public List<CatDTO> findAllCatFriendsById(Long id);
    public void deleteFriends(Long catID);
}
