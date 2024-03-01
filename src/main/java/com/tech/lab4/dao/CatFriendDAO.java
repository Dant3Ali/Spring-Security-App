package com.tech.lab4.dao;

import com.tech.lab4.entities.CatFriends;
import com.tech.lab4.entities.CatFriendsKey;
import com.tech.lab4.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatFriendDAO extends JpaRepository<CatFriends, CatFriendsKey> {

    @Query("SELECT cf.friendCat FROM CatFriends cf WHERE cf.cat.id = :catId")
    List<Cat> findFriendsByCatId(@Param("catId") Long catId);

    @Query("SELECT cf.cat FROM CatFriends cf WHERE cf.friendCat.id = :catId")
    List<Cat> findFriendByCatId(@Param("catId") Long catId);

    void deleteAllByCatId(Long catId);
}
