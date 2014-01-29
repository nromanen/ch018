package com.ch018.library.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;


/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

	public enum Role {
		ROLE_ADMINISTRATOR, ROLE_LIBRARIAN, ROLE_USER
	};
	private static final long serialVersionUID = 3607258059474732202L;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@NotEmpty(message = "{NotEmpty.Person.email}")
	@Email(message = "{Email.Person.email}")
	@Column(name = "e_mail", unique = true, nullable = false)
	private String email;

	@Column(name = "cellphone")
	private String cellphone;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "confirmed")
	private boolean confirm;
	
	@Column(name = "emailConfirmed")
	private boolean emailConfirmed;

	@Column(name = "sms")
	private boolean sms;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "verificationkey")
	private String verificationKey;

	@Column(name = "timely_returns")
	private int timelyReturns;

	@Column(name = "untimely_returns")
	private int untimelyReturns;
	
	@NotNull
	@Min(0)
	@Column(name = "multibookAllowed")
	private int multibookAllowed;

	@Column(name = "failedOrders")
	private int failedOrders;
	
	@Column(name = "rating")
	private double rating;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<BooksInUse> booksinuses = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<WishList> wishList = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<Orders> orders = new HashSet<>();
	
	public Person() {

	}

	public Person(Person person) {
		this.email = person.getEmail();
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

	public String getVerificationKey() {
		return verificationKey;
	}

	public void setVerificationKey(String verificationKey) {
		this.verificationKey = verificationKey;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public boolean getConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
	public boolean getEmailConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}
	

	public boolean getSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<BooksInUse> getBooksinuses() {
		return booksinuses;
	}

	public void setBooksinuses(Set<BooksInUse> booksinuses) {
		this.booksinuses = booksinuses;
	}
	
	public Set<WishList> getWishList() {
		return wishList;
	}

	public void setWishList(Set<WishList> wishList) {
		this.wishList = wishList;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}


	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Person))
			return false;
		Person otherPerson = (Person) other;
		return email.equals(otherPerson.getEmail());
	}

	@Override
	public String toString() {
		return this.id + " " + " " + email;
	}
	
	@Override
	public int hashCode() {
		return email.hashCode();
	}

}
