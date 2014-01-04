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
    public void addWish(WishList wish);
    public void deleteWish(WishList wish);
    public void deleteWishById(int id);
    public Collection getAllWishes();
    public WishList getWishById(int id);
    public ArrayList<WishList> getWishesByPerson(int personId);
    public ArrayList<WishList> getWishesByPerson(String personEmail);
    public boolean bookExistInWishList(int bookId, int personId);
}
