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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@Entity
@Table(name = "genre")
public class Genre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5001085796621940917L;
	private int id;
	private String language;
	private String name;
	private Set<Book> books = new HashSet<>();
    //private Set<Localization> localizedGenres;

	public Genre() {

	}

	public Genre(String id) {
		this.id = Integer.parseInt(id);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gid", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	@Column(name = "name", unique = true)
	public String getName() {
		return name;
	}
	
	@Column(name = "language")
	public String getLanguage() {
		return language;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
	public Set<Book> getBooks() {
		return this.books;
	}
	
	/*@OneToMany(mappedBy="genre")
	public Set<Localization> getLocalizedGenres() {
		return localizedGenres;
	}*/
	

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	/*public void setLocalizedGenres(Set<Localization> localizedGenres) {
		this.localizedGenres = localizedGenres;
	}*/

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
		if (this.id == ((Genre) obj).getId())
			return true;
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
