/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Orders;
import com.ch018.library.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author okryvortc
 */
public class OrdersDAOImpl implements OrdersDAO{

    static Logger log = LogManager.getLogger(GenreDAOImpl.class);   
    
    public void addOrder(Orders ord) {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(ord);
            session.getTransaction().commit();
        }catch(Exception e){
            log.error(e);
        }
    }

    public void deleteOrder(Orders ord) {
        Session session = null;
        try{
             session = HibernateUtil.getSessionFactory().openSession();
             session.beginTransaction();
             session.delete(ord);
        }catch(Exception e){
            log.error(e);
        }
    }

    public Collection getOrdersByBooksId(int id) {
        Session session = null;
        ArrayList<Orders> ordList = new ArrayList<Orders>();
        Query query = session.createQuery("from orders where Books_id=:id");
        query.setParameter("id", id);
        try{
            session = HibernateUtil.getSessionFactory().openSession();
           
            ordList.addAll(query.list());
            //ordList.addAll(session.createCriteria(Orders.class).list());
        }catch(Exception e){
            log.error(e);
        }
        
        
        return ordList;
    }

    public Collection getOrdersByPersonId(int id) {
       Session session = null;
       ArrayList<Orders> result = new ArrayList<Orders>();
       try{
           session = HibernateUtil.getSessionFactory().openSession();
           Query query = session.createQuery("from orders where Person_id=:id");
           query.setParameter("id", id);
           result.addAll(query.list());
       }catch(Exception e){
           log.error(e);
       }
       return result;
    }

    public Collection getAllOrders() {
       Session session = null;
       ArrayList<Orders> result = new ArrayList<Orders>();
       try{
           session = HibernateUtil.getSessionFactory().openSession();
           result.addAll(session.createCriteria(Orders.class).list());
       }catch(Exception e){
           log.error(e);
       }
       
       return result;
    }
    
}
