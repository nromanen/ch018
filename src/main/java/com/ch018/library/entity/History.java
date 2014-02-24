package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "history")
public class History implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4746062485087388074L;
	private int id;
	private Person person;
	private Book book;
	private float mark;
	private String comment;
	private Date issueDate;

	public History() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	@Column(name = "comment")
	@Type(type="text")
	public String getComment() {
		return comment;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	public Book getBook() {
		return book;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return person;
	}
	
	@Column(name = "mark", columnDefinition = "float default -1")
	public float getMark() {
		return mark;
	}
	
	@Column(name = "issue_date")
	public Date getIssueDate() {
		return issueDate;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setMark(float mark) {
		this.mark = mark;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
