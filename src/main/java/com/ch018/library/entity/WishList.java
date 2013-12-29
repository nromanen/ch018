/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.entity;

import javax.persistence.*;

@Entity
@Table(name = "wishlist")
public class WishList {
	private int id;
	private Person person;
	private Book book;

	public WishList(){
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Person_id")
	public Person getPerson() {
		return person;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "Books_id")
	public Book getBook() {
		return book;
	}

	@Override
	public String toString() {
		return getId() + ": " + getPerson() + " - " + getBook();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof WishList) return true;
		if (((WishList) obj).getBook().equals(this.getBook())
				&& ((WishList) obj).getPerson().equals(this.getPerson())) 
			return true;
		return false;
	}
}
