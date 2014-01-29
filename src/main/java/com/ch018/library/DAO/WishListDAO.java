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
