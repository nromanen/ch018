package com.ch018.library.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Edd Arazian
 */
@Entity
@Table(name = "persons")
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "prole")
    @PrimaryKeyJoinColumn(name = "personrole")
    private int role;
    
    @Column(name = "cellphone")
    private String cellphone;
    
    @Column(name = "confirm")
    private boolean confirm;
    
    @Column(name = "sms")
    private boolean sms;
    
    @Column(name = "timelyReturn")
    private int timelyReturn;
    
    @Column(name = "untimelyReturn")
    private int untimekyReturn;
    
    @Column(name = "booksAllowed")
    private int booksAllowed;
    
    @Column(name = "failedOrders")
    private int failedOrders;
    
    @Column(name = "generalRatio")
    private float generalRatio;
    
    
    
    public Person() {
        
    }

    public Person(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
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
    
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Person))return false;
        Person otherPerson = (Person) other;
        return email.equals(otherPerson.getEmail());
    }
    
    @Override
    public String toString() {
        return this.id + " " + " " + email;
    }
    
}
