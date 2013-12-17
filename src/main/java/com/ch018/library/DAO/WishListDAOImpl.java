/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.DAO;

import com.ch018.library.entity.WishList;
import com.ch018.library.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.Query;
import javax.swing.JOptionPane;

import com.ch018.library.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author win7
 */
public class WishListDAOImpl implements WishListDAO{

    static Logger log = LogManager.getLogger(GenreDAOImpl.class); 
    
    @Override
    public void addWish(WishList wish) {
      Session session = null;
      try{
          session = HibernateUtil.getSessionFactory().openSession();
          session.beginTransaction();
          session.save(wish);
          session.getTransaction().commit();
      }catch(Exception e){
          log.error(e);
      }finally{
          if(session!=null && session.isOpen())
                session.close();
      }
    }

    @Override
    public void deleteWish(WishList wish) {
        Session session = null;
       try{
           session = HibernateUtil.getSessionFactory().openSession();
           session.getTransaction().begin();
           session.delete(wish);
           session.getTransaction().commit();
           session.close();
       }catch(Exception e){
            log.error(e);
       }
    }

    @Override
    public Collection getAllWishes() {
       ArrayList<WishList> wish = new ArrayList<WishList>();
       Session session = null;
       try{
           session = HibernateUtil.getSessionFactory().openSession();
           wish.addAll(session.createCriteria(WishList.class).list());
       }catch(Exception e){
           log.error(e);
       }
       return wish;
    }

    @Override
    public WishList getWishById(int id) {
         WishList wish = new WishList();
         Session session = null;
         Query query = session.createQuery("from wishlist WHERE id=:id");
         try{
             session = HibernateUtil.getSessionFactory().openSession();   
             session.getTransaction().begin();
             query.setParameter("id", id);
             }catch(Exception e){
             log.error(e);
         }
      return wish;
    }
    
}
