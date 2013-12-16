/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author Edd Arazian
 */
@Entity
@Table(name = "ratings")
public class Rating implements Serializable {
    
    @Id
    @GeneratedValue(generator = "selfgen")
    @GenericGenerator(name = "selfgen", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "person"))
    private int personid;
    
    //@OneToOne(mappedBy = "rating")
    @OneToOne
    @PrimaryKeyJoinColumn
    private Person person;
    
    @Column(name = "timelyreturn")
    private int timelyReturn;
    
    @Column(name = "untimelyreturn")
    private int untimekyReturn;
    
    @Column(name = "booksallowed")
    private int booksAllowed;
    
    @Column(name = "failedorders")
    private int failedOrders;
    
    @Column(name = "generalratio")
    private float generalRating;
    
    
    public Rating() {
        
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

   
    
    
    public int getTimelyReturn() {
        return timelyReturn;
    }

    public void setTimelyReturn(int timelyReturn) {
        this.timelyReturn = timelyReturn;
    }

    public int getUntimekyReturn() {
        return untimekyReturn;
    }

    public void setUntimekyReturn(int untimekyReturn) {
        this.untimekyReturn = untimekyReturn;
    }

    public int getBooksAllowed() {
        return booksAllowed;
    }

    public void setBooksAllowed(int booksAllowed) {
        this.booksAllowed = booksAllowed;
    }

    public int getFailedOrders() {
        return failedOrders;
    }

    public void setFailedOrders(int failedOrders) {
        this.failedOrders = failedOrders;
    }

    public float getGeneralRating() {
        return generalRating;
    }

    public void setGeneralRating(float generalRatio) {
        this.generalRating = generalRatio;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    
    
    
    
}
