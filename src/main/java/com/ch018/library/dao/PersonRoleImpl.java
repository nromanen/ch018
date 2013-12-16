/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.dao;

import com.ch018.library.dao.util.HibernateUtil;
import com.ch018.library.entity.PersonRole;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Edd Arazian
 */
public class PersonRoleImpl implements PersonRoleDao {
    
    @Override
    public void save(PersonRole personRole) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(personRole);
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
    public List<PersonRole> getAll() {
        
        Session session = null;
        List<PersonRole> persons = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            persons = session.createCriteria(PersonRole.class).list();
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
        return persons;
    }
    
}
