/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.util.HibernateUtil;
import com.ch018.library.entity.Person;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author Edd Arazian
 */
@Component
public class PersonDaoImpl implements PersonDao {

 
    
    @Override
    public void save(Person person) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(person);
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
    public List<Person> getAll() {
        
        Session session = null;
        List<Person> persons = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            persons = session.createCriteria(Person.class).list();
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

    @Override
    public void delete(int id) {
        Session session = null;
        Person person = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            person = (Person) session.get(Person.class, id);
            System.out.println(person);
            session.delete(person);
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
    public void update(Person person) {
        Session session = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(person);
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
    public Person getById(int id) {
        Session session = null;
        Person person = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            person = (Person) session.get(Person.class, id);
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
        return person;
    }

    @Override
    public Person getByEmail(String email) {
        Session session = null;
        Person person = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            person = (Person) session.createCriteria(Person.class).add(Restrictions.eq("email", email)).
                    list().get(0);
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
        return person;
       
    }

    @Override
    public List<Person> getByName(String name) {
        Session session = null;
        List<Person> persons = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            persons = session.createCriteria(Person.class).add(Restrictions.eq("name", name)).
                    list();
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

    @Override
    public List<Person> getBySurname(String surname) {
        Session session = null;
        List<Person> persons = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            persons = session.createCriteria(Person.class).add(Restrictions.eq("surname", surname)).
                    list();
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

    @Override
    public Person getByCellPhone(String cellphone) {
        Session session = null;
        Person person = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            person = (Person) session.createCriteria(Person.class).add(Restrictions.eq("cellphone", cellphone)).
                    list().get(0);
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
        return person;
    }

    @Override
    public List<Person> getByRole(String role) {
        Session session = null;
        List<Person> persons = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            persons = session.createCriteria(Person.class).add(Restrictions.eq("role", role)).
                    list();
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

    @Override
    public List<Person> getConfirmed() {
        Session session = null;
        List<Person> persons = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            persons = session.createCriteria(Person.class).add(Restrictions.eq("confirm", "1")).
                    list();
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

    @Override
    public List<Person> getSmsEnabled() {
        Session session = null;
        List<Person> persons = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            persons = session.createCriteria(Person.class).add(Restrictions.eq("sms", "1")).
                    list();
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
