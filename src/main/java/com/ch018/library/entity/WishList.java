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
	private int booksID;
	private int personId;

	public WishList() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBooksId(int booksId) {
		this.booksID = booksId;
	}

	public void setPersonId(int pId) {
		this.personId = pId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	@Column(name = "Books_id")
	public int getBooksId() {
		return this.booksID;
	}

	@Column(name = "person_id")
	public int getPersonId() {
		return this.personId;
	}

	@Override
	public String toString() {
		return this.getId() + " " + this.getBooksId() + " "
				+ this.getPersonId();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof WishList) {
			if (((WishList) obj).getBooksId() == this.getBooksId()
					&& ((WishList) obj).getPersonId() == this.getPersonId()) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}
}
