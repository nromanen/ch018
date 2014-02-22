package com.ch018.library.entity;

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

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@NamedQueries({
	@NamedQuery(
			name = "deleteBookInUse",
			query = "delete from BooksInUse where buid=:id"
			)
})
@Entity
@Table(name = "booksinuse")
public class BooksInUse {
	private int buid;
	private Person person;
	private Book book;
	private Date issueDate;
	private Date returnDate;
	private float mark;

	public BooksInUse() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getBuid() {
		return buid;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return person;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	public Book getBook() {
		return book;
	}

	@Column(name = "issue_date")
	public Date getIssueDate() {
		return issueDate;
	}

	@Column(name = "return_date")
	public Date getReturnDate() {
		return returnDate;
	}
	
	@Column(name = "mark", columnDefinition = "float default 0")
    public float getMark() {
		return this.mark;
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
	
	public void setMark(float mark) {
		this.mark = mark;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.book.equals(((BooksInUse)obj).book) 
				|| this.book.equals(((BooksInUse)obj).person)) {
			return true;
		}
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "BooksInUse[" + this.book + ":" + this.person + "]";
	}

}
