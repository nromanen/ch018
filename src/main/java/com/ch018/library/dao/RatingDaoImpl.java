/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.dao;

import com.ch018.library.dao.util.HibernateUtil;
import com.ch018.library.entity.Rating;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository
public class RatingDaoImpl implements RatingDao {

    @Override
    public void save(Rating rating) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(rating);
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
    }

    @Override
    public void delete(int id) {
        Session session = null;
        Rating rating = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            rating = (Rating) session.get(Rating.class, id);
            session.delete(rating);
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
    }

    @Override
    public void update(Rating rating) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(rating);
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
    }

    @Override
    public List<Rating> getAll() {
       Session session = null;
       List<Rating> ratings = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ratings = session.createCriteria(Rating.class).list();
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
        return ratings;
    }

    @Override
    public List<Rating> getEq(float rating) {
        Session session = null;
       List<Rating> ratings = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ratings = session.createCriteria(Rating.class).add(Restrictions.
                    eq("generalRating", rating)).list();
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
        return ratings;
    }

    @Override
    public List<Rating> getLt(float rating) {
        Session session = null;
       List<Rating> ratings = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ratings = session.createCriteria(Rating.class).add(Restrictions.
                    lt("generalRating", rating)).list();
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
        return ratings;
    }

    @Override
    public List<Rating> getGt(float rating) {
        Session session = null;
       List<Rating> ratings = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ratings = session.createCriteria(Rating.class).add(Restrictions.
                    gt("generalRating", rating)).list();
            session.getTransaction().commit();
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
        return ratings;
    }
    
    
}
