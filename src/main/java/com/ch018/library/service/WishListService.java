/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.service;

import com.ch018.library.entity.WishList;

import java.util.ArrayList;
import java.util.Collection;



public interface WishListService {
    void addWish(WishList wish);
    void deleteWish(WishList wish);
    void deleteWishById(int id);
    Collection getAllWishes();
    WishList getWishById(int id);
    ArrayList<WishList> getWishesByPerson(int personId);
    ArrayList<WishList> getWishesByPerson(String personEmail);
    boolean bookExistInWishList(int bookId, int personId);
    WishList getWishWithoutId(int bookId, int personId);
    long getCountByPerson(String name);
}
