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

@Entity
@Table(name = "genre")
public class Genre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5001085796621940917L;
	private int id;
	private String name;
	private Set<Book> books = new HashSet<>();

	public Genre() {

	}

	public Genre(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gid", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
	public Set<Book> getBooks() {
		return this.books;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this.name.equals(((Genre) obj).getName())) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public String toString() {
		return getName();
	}

}
