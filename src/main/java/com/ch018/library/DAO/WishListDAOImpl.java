/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.DAO;

import com.ch018.library.entity.WishList;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author win7
 */
@Component
public class WishListDAOImpl implements WishListDAO {

	static Logger log = LogManager.getLogger(WishListDAOImpl.class);

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addWish(WishList wish) {
		try {
			sessionFactory.getCurrentSession().save(wish);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public void deleteWish(WishList wish) {
		try {
			sessionFactory.getCurrentSession().delete(wish);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public Collection<WishList> getAllWishes() {
		ArrayList<WishList> wish = new ArrayList<WishList>();
		try {
			wish.addAll(sessionFactory.getCurrentSession()
					.createCriteria(WishList.class).list());
		} catch (Exception e) {
			log.error(e);
		}
		return wish;
	}

	@Override
	public WishList getWishById(int id) {
		WishList wish = new WishList();
		try {
			Query query = sessionFactory.getCurrentSession()
					.createQuery("from wishlist WHERE id=:id")
					.setParameter("id", id);
		} catch (Exception e) {
			log.error(e);
		}
		return wish;
	}

}
