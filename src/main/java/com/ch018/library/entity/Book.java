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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.ch018.library.util.IConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@NamedQueries({
	@NamedQuery(
			name = "deleteBook",
			query = "delete from Book where id=:id"
			)
})
@Entity
@Table(name = "books")
public class Book implements Serializable {
		
	private static final long serialVersionUID = 8488328581221067484L;
	private int id;
	private Genre genre;
	private String title;
	private String authors;
	private int year;
	private String publication;
	private int pages;
	private String description;
	private int shelf;
	private int bookcase;
	private int term;
	private String image;
	private int count;
	private int available;
	private float rating;
	private int numberOfEvaluations;

	private Set<BooksInUse> booksinuses = new HashSet<>();
	private Set<WishList> wishList = new HashSet<>();
	private Set<Orders> orders = new HashSet<>();


	public Book() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return this.id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "genre_id", nullable = false)
	@JsonIgnore
    public Genre getGenre() {
            return this.genre;
    }
	
	public void setGenre(Genre genre) {	
		this.genre = genre;
	}

	@Size(min = 0, max = IConstants.MAX_LENGTH_TITLE)
	@NotEmpty
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	@Size(min = 0, max = IConstants.MAX_LENGTH_AUTHORS)
	@NotEmpty
	@Column(name = "authors")
	public String getAuthors() {
		return authors;
	}

	@Min(value = IConstants.MIN_YEAR)
	@Max(value = IConstants.MAX_YEAR)
	@Column(name = "year_public", columnDefinition = "int default 0")
	public int getYear() {
		return year;
	}

	@Size(min = 0, max = IConstants.MAX_LENGTH_PUBLICATION)
	@NotEmpty
	@Column(name = "publication")
	public String getPublication() {
		return publication;
	}

	@Min(0)
	@Column(name = "pages", columnDefinition = "int default 0")
	public int getPages() {
		return pages;
	}

	@Column(name = "description")
	@Type(type="text")
	public String getDescription() {
		return description;
	}

	@Min(0)
	@Max(IConstants.MAX_SHELF)
	@Column(name = "shelf", columnDefinition = "int default 0")
	public int getShelf() {
		return shelf;
	}

	@Min(0)
	@Max(IConstants.MAX_BOOKCASE)
	@Column(name = "bookcase", columnDefinition = "int default 0")
	public int getBookcase() {
		return bookcase;
	}

	@Min(0)
	@Column(name = "term", columnDefinition = "int(11) default '14'")
	public int getTerm() {
		return term;
	}
	
	@Column(name = "image", columnDefinition = "varchar(255) default '/resources/img/books/default.gif'")
	public String getImage() {
		return image;
	}
	
	@Min(0)
	@Column(name = "count", columnDefinition = "int default 0")
	public int getCount() {
		return count;
	}
	
	@Min(0)
	@Column(name = "available", columnDefinition = "int default 0")
	public int getAvailable() {
		return available;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	@JsonIgnore
	public Set<BooksInUse> getBooksinuses() {
		return booksinuses;
	}

	public void setBooksinuses(Set<BooksInUse> booksinuses) {
		this.booksinuses = booksinuses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	@JsonIgnore
	public Set<WishList> getWishList() {
		return wishList;
	}

	public void setWishList(Set<WishList> wishList) {
		this.wishList = wishList;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	@JsonIgnore
	public Set<Orders> getOrders() {
		return orders;
	}
	
	@Column(name = "rating", scale = 5, columnDefinition = "float(6) default 0")
	public float getRating() {
		return rating;
	}
	
	@Column(name = "numberOfEvaluations", columnDefinition = "int default 0")
    public int getNumberOfEvaluations(){
	    return this.numberOfEvaluations;	
	}
	
	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBookcase(int bookcase) {
		this.bookcase = bookcase;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}

	public void setTerm(int term) {
		this.term = term;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setAvailable(int available) {
		this.available = available;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
    
	public void setNumberOfEvaluations(int number){
		this.numberOfEvaluations = number;
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
		if (this.title.equals(((Book) obj).getTitle())
				&& this.getAuthors().equals(((Book) obj).getAuthors())
				&& this.getPublication().equals(((Book) obj).getPublication())) {
			return true;
		}
		if (this.id == ((Book) obj).getId()) {
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
		return "Book[id: " + getId() + ", title: [" + getTitle() + "], Authors: [" + getAuthors() + "], Year: " + getYear() + "]";
	}

}
