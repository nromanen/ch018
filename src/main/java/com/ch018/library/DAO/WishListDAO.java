/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.DAO;

import com.ch018.library.entity.WishList;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;
/**
 *
 * @author win7
 */

@Component
public interface WishListDAO {
    public void addWish(WishList wish);
    public void deleteWish(WishList wish);
    public Collection getAllWishes();
    public WishList getWishById(int id);
}
