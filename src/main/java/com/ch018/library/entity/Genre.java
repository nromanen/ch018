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
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.ch018.library.service.LocalizationService;
/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@Entity
@Table(name = "genre")
public class Genre implements Serializable {
	
	@Autowired
	private LocalizationService localizationService;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5001085796621940917L;
	private int id;
	private String name;
	private Set<Book> books = new HashSet<>();
    private Set<Localization> localizations;

	public Genre() {

	}

	public Genre(String id) {
		this.id = Integer.parseInt(id);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	@Transient
	public String getName() {
		return this.name;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    public Set<Book> getBooks() {
            return this.books;
    }
	
	@OneToMany(fetch = FetchType.LAZY ,mappedBy="genre")
	public Set<Localization> getLocalizations() {
		return localizations;
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
	
	public void setLocalizations(Set<Localization> localizations) {
		this.localizations = localizations;
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
