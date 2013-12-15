package com.ch018.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="books")
public class Book {
	
	private int id;
	private Bookcase bookcase;
	private String title;
	private String authors;
	private int year;
	private String publication;
	private int pages;
	private String description;
	private int shelf;
	private int term;
	private Person person;
	
	public Book() {
		
	}
	
	public Book(Book b) {
		title = b.getTitle();
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="id")
	public int getId() {
		return this.id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Bookcase_id")
	public Bookcase getBookase() {
		return bookcase;
	}
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	
	@Column(name="authors")
	public String getAuthors() {
		return authors;
	}
	
	@Column(name="year_public")
	public int getYear() {
		return year;
	}
	
	@Column(name="publication")
	public String getPublication() {
		return publication;
	}
	
	@Column(name="pages")
	public int getPages() {
		return pages;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	
	@Column(name="shelf")
	public int getShelf() {
		return shelf;
	}
	
	@Column(name="term")
	public int getTerm() {
		return term;
	}
	
	@ManyToOne
	@JoinTable(name="BooksInUse", joinColumns = @JoinColumn(name="Books_id"), inverseJoinColumns = @JoinColumn(name="Person_id"))
	public Person getPerson() {
		return person;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setBookase(Bookcase bookcase) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
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
		return getId() + " " + getTitle() + " " + getAuthors() + " " + getYear();
	}

	
}
