package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Calendar;
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

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */

@Entity
@Table(name = "books")
public class Book implements Serializable {

	private static final int MIN_YEAR = 1800;
	private static final int MAX_LENGTH_TITLE = 200;
	private static final int MAX_LENGTH_AUTHORS = 200;
	private static final int MAX_LENGTH_PUBLICATION = 50;
	private static final int MAX_SHELF = 200;
	private static final int MAX_BOOKCASE = 200;
	private static final int MAX_YEAR = 2014;
	
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

	
	private Set<BooksInUse> booksinuses = new HashSet<>();
	private Set<WishList> wishList = new HashSet<>();
	private Set<Orders> orders = new HashSet<>();


	public Book() {

	}

	public Book(Book b) {
		title = b.getTitle();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "gid", nullable = false)
	public Genre getGenre() {
		return this.genre;
	}

	@Size(min = 0, max = MAX_LENGTH_TITLE)
	@NotEmpty
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	@Size(min = 0, max = MAX_LENGTH_AUTHORS)
	@NotEmpty
	@Column(name = "authors")
	public String getAuthors() {
		return authors;
	}

	@Min(value = MIN_YEAR)
	@Max(value = MAX_YEAR)
	@Column(name = "year_public")
	public int getYear() {
		return year;
	}

	@Size(min = 0, max = MAX_LENGTH_PUBLICATION)
	@NotEmpty
	@Column(name = "publication")
	public String getPublication() {
		return publication;
	}

	@Min(0)
	@Column(name = "pages")
	public int getPages() {
		return pages;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	@Min(0)
	@Max(MAX_SHELF)
	@Column(name = "shelf")
	public int getShelf() {
		return shelf;
	}

	@Min(0)
	@Max(MAX_BOOKCASE)
	@Column(name = "bookcase")
	public int getBookcase() {
		return bookcase;
	}

	@Min(0)
	@Column(name = "term", columnDefinition = "int(11) default '14'")
	public int getTerm() {
		return term;
	}
	
	@Column(name = "image", columnDefinition = "varchar(255) default 'http://placehold.it/120x150'")
	public String getImage() {
		return image;
	}
        
        @Column(name = "count")
        public int getCount(){
            return this.count;
        }
        
        @Column(name = "available")
        public int getAvailable(){
            return this.available;
        }
        
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<BooksInUse> getBooksinuses() {
		return booksinuses;
	}

	public void setBooksinuses(Set<BooksInUse> booksinuses) {
		this.booksinuses = booksinuses;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<WishList> getWishList() {
		return wishList;
	}

	public void setWishList(Set<WishList> wishList) {
		this.wishList = wishList;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Orders> getOrders() {
		return orders;
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

	public void setGenre(Genre genre) {
		this.genre = genre;
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
        
        public void setCount(int count){
            this.count=count;
        }
        
        public void setAvailable(int available){
            this.available=available;
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
		return false;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public String toString() {
		return getId() + " " + getTitle() + " " + getAuthors() + " "
				+ getYear();
	}

}
