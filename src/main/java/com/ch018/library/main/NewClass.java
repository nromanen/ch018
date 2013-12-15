/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.main;

import com.ch018.library.dao.PersonDaoImpl;
import com.ch018.library.dao.RatingDaoImpl;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Rating;

/**
 *
 * @author admin
 */
public class NewClass {
    
    public static void main(String[] args) {
        
        PersonDaoImpl pdao = new PersonDaoImpl();
        RatingDaoImpl rdao = new RatingDaoImpl();
        
        Person p = new Person("gmail.com");
        Person p1 = new Person("mail.ru");
        
        Rating r = new Rating();
        
        r.setGeneralRating(5F);
        
        p.setRating(r);
        r.setPerson(p);
       
        
        //pdao.save(p);
        rdao.save(r);
        
        
        
        
    }
    
}
