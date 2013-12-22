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

@Entity
@Table(name = "books")
public class Book implements Serializable {

	/**
	 * 
	 */
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
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "gid", nullable = false)
	public Genre getGenre() {
		return this.genre;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	@Column(name = "authors")
	public String getAuthors() {
		return authors;
	}

	@Column(name = "year_public")
	public int getYear() {
		return year;
	}

	@Column(name = "publication")
	public String getPublication() {
		return publication;
	}

	@Column(name = "pages")
	public int getPages() {
		return pages;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	@Column(name = "shelf")
	public int getShelf() {
		return shelf;
	}

	@Column(name = "bookcase")
	public int getBookcase() {
		return bookcase;
	}

	@Column(name = "term")
	public int getTerm() {
		return term;
	}

	/*
	 * @ManyToOne
	 * 
	 * @JoinTable(name="BooksInUse", joinColumns = @JoinColumn(name="Books_id"),
	 * inverseJoinColumns = @JoinColumn(name="Person_id")) public Person
	 * getPerson() { return person; }
	 */

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
	public Set<BooksInUse> getBooksinuse() {
		return booksinuses;
	}

	
        public void setBooksinuse(Set<BooksInUse> booksinuse) {
		this.booksinuses = booksinuse;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
        public Set<WishList> getWishList() {
                return wishList;
         }

        public void setWishList(Set<WishList> wishList) {
                this.wishList = wishList;
         }

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "book", cascade = CascadeType.ALL)
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
