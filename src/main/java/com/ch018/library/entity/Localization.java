package com.ch018.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "localization")
public class Localization {

    private int id;
    private String language;
    //private Genre genre;
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
    	return id;
    }
    
    public String getLanguage() {
		return language;
	}
    
/*    public Genre getGenre() {
    	return genre;
    }*/
    
    public void setId(int id) {
    	this.id = id;
    }
    
   /* public void setGenre(Genre genre) {
    	this.genre = genre;
    }*/
    
    public void setLanguage(String language) {
    	this.language = language;
    }
}
