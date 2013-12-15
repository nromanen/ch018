package com.ch018.library.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="BooksInUse")
public class BooksInUse {
	private int id;
	private int personId;
	private int booksId;
	private Date issueDate;
	private Date returnDate;
	private boolean inUse;
	
	public BooksInUse() {
		
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	@Column(name="id")
	public int getId() {
		return id;
	}
	
	@Column(name="Person_id")
	public int getPersonId() {
		return personId;
	}
	
	@Column(name="Books_id")
	public int getBooksId() {
		return booksId;
	}
	
	@Column(name="issue_date")
	public Date getIssueDate() {
		return issueDate;
	}
	
	@Column(name="return_date")
	public Date getReturnDate() {
		return returnDate;
	}
	
	@Column(name="inUse")
	public boolean getInUse() {
		return inUse;
	}
	
}
