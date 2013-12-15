/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Generated;

/**
 *
 * @author Edd Arazian
 */
@Entity
@Table(name = "ratings")
public class Rating implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Person userId;
    
    @Column(name = "timelyreturn")
    private int timelyReturn;
    
    @Column(name = "untimelyreturn")
    private int untimekyReturn;
    
    @Column(name = "booksallowed")
    private int booksAllowed;
    
    @Column(name = "failedorders")
    private int failedOrders;
    
    @Column(name = "generalratio")
    private float generalRatio;
    
    public Rating() {
        
    }

    public Person getUserId() {
        return userId;
    }

    public void setUserId(Person userId) {
        this.userId = userId;
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

    public float getGeneralRatio() {
        return generalRatio;
    }

    public void setGeneralRatio(float generalRatio) {
        this.generalRatio = generalRatio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
