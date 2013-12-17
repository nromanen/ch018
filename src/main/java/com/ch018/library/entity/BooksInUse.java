package com.ch018.library.entity;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="booksinuse")
public class BooksInUse {
	private int buid;
	private Person person;
	private Book book;
	private Date issueDate;
	private Date returnDate;
	private boolean inUse;
	
	public BooksInUse() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="buid")
	public int getBuid() {
		return buid;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Person_id")
	public Person getPerson() {
		return person;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	public Book getBook() {
		return book;
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
	
	public void setBuid(int buid) {
		this.buid = buid;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}

	
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	
}
