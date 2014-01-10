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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author win7
 */
@Component
public class WishListDAOImpl implements WishListDAO {

	private static Logger log = LogManager.getLogger(WishListDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

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
		WishList wish = null;
		try {
			wish = (WishList) sessionFactory.getCurrentSession().get(WishList.class,id);
                        
		} catch (Exception e) {
			log.error(e);
		}
		return wish;
	}

    @Override
    public ArrayList<WishList> getWishesByPerson(int personId) {
       ArrayList<WishList> wish = new ArrayList<WishList>();
       try{
           //Query query = );
           //query.setInteger("id", personId);
           
          wish.addAll(sessionFactory.getCurrentSession().createCriteria(WishList.class)
                                       .add(Restrictions.eq("person.id", personId)).list());
           
       }catch(Exception e){
           log.error(e);
           System.out.println(e);
       }
       return wish;
    }

    @Override
    public void deleteWishById(int id) {
      try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("delete from WishList where id=:id")
					.setInteger("id", id);
			int g = query.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
    }

    @Override
    public boolean bookExistInWishList(int bookId, int personId) {
        boolean exist=true;
        try{
             Query query = sessionFactory.getCurrentSession()
                          .createSQLQuery("SELECT * FROM wishlist WHERE Person_id=:person And Books_id=:book")
                          .setParameter("person", personId)
                          .setParameter("book", bookId);
           
                 if(query.list().isEmpty()){
                        exist=false;
                 }
        }catch(Exception e){
            log.error(e);
        }
        return exist;
    }

	@Override
	public ArrayList<WishList> getWishesByPerson(String personEmail) {
		ArrayList<WishList> wish = new ArrayList<WishList>();
	       try{
	           //Query query = );
	           //query.setInteger("id", personId);
	           
	          wish.addAll(sessionFactory.getCurrentSession().createCriteria(WishList.class)
	                                       .add(Restrictions.eq("person.email", personEmail)).list());
	           
	       }catch(Exception e){
	           log.error(e);
	           System.out.println(e);
	       }
	       return wish;
	}

}
