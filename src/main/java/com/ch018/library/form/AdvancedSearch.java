package com.ch018.library.form;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
public class AdvancedSearch {
	
	private String title;
	private String authors;
	private String publication;
	private Integer genre;
	private Integer year;
	private boolean available;
	
	public AdvancedSearch() {

	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthors() {
		return this.authors;
	}
	
	public String getPublication() {
		return this.publication;
	}
	
	public Integer getGenre() {
		return this.genre;
	}
	
	public Integer getYear() {
		return this.year;
	}
	
	public boolean getAvailable() {
		return this.available;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	
	public void setPublication(String publication) {
		this.publication = publication;
	}
	
	public void setGenre(Integer genre) {
		this.genre = genre;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}

}
