package com.ch018.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Edd Arazian
 */
enum Role {ADMINISTRATOR, LIBRARIAN, USER};

@Entity
@Table(name = "person")
public class Person implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "e_mail")
    private String email;
    
    @Column(name = "cellphone")
    private String cellphone;
    
    @Column(name = "role")
    private Role role;
    
    @Column(name = "confirmed")
    private boolean confirm;
    
    @Column(name = "sms")
    private boolean sms;
    
    @Column(name = "hash")
    private String password;
    
    @Column(name = "salt")
    private String salt;
    
    @Column(name = "timely_returns")
    private int timelyReturns;
    
    @Column(name = "untimely_returns")
    private int untimelyReturns;
    
    @Column(name = "multibookAllowed")
    private int multibookAllowed;
    
    @Column(name = "failedOrders")
    private int failedOrders;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person", cascade = CascadeType.ALL)
    private Set<BooksInUse> booksinuses = new HashSet<>();
     
    
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
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public boolean getConfirmed() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean getSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }

    public int getTimelyReturns() {
        return timelyReturns;
    }

    public void setTimelyReturns(int timelyReturns) {
        this.timelyReturns = timelyReturns;
    }
    
    public int getUntimelyReturns() {
        return untimelyReturns;
    }

    public void setUntimelyReturns(int untimelyReturns) {
        this.untimelyReturns = untimelyReturns;
    }
    
    public int getMultibookAllowed() {
        return multibookAllowed;
    }

    public void setMultibookAllowed(int multibookAllowed) {
        this.multibookAllowed = multibookAllowed;
    }
    
    public int getFailedOrders() {
        return failedOrders;
    }

    public void setFailedOrders(int failedOrders) {
        this.failedOrders = failedOrders;
    }
    
    
	public Set<BooksInUse> getBooksinuses() {
		return booksinuses;
	}
	
	public void setBooksinuses(Set<BooksInUse> booksinuses) {
		this.booksinuses = booksinuses;
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
