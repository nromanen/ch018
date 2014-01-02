/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.service;

import com.ch018.library.DAO.WishListDAO;
import com.ch018.library.entity.WishList;
import java.util.ArrayList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class WishListServiceImpl implements WishListService{
    
    @Autowired
    private WishListDAO wishlistDAO;
    
    @Override
    @Transactional
    public void addWish(WishList wish) {
        wishlistDAO.addWish(wish);
    }

    @Override
    @Transactional
    public void deleteWish(WishList wish) {
      wishlistDAO.deleteWish(wish);
    }

    @Override
    @Transactional
    public Collection getAllWishes() {
        return wishlistDAO.getAllWishes();
    }

    @Override
    @Transactional
    public WishList getWishById(int id) {
        return wishlistDAO.getWishById(id);
    }

    @Override
    @Transactional
    public ArrayList<WishList> getWishesByPerson(int personId) {
        return wishlistDAO.getWishesByPerson(personId);
    }

    @Override
    @Transactional
    public void deleteWishById(int id) {
           wishlistDAO.deleteWishById(id);
    }

    @Override
    @Transactional
    public boolean bookExistInWishList(int bookId, int personId) {
       return wishlistDAO.bookExistInWishList(bookId, personId);
    }
    
}
