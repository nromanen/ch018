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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@NamedQueries({
	@NamedQuery(
			name = "deleteOrder",
			query = "delete from Orders where id=:id"
			)
})
@Entity
@Table(name = "orders")
public class Orders {

	private int id;
	private Date date;
	private Date issueDate;
	private Person person;
	private Book book;


	public Orders() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	public Person getPerson() {
		return person;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "books_id")
	public Book getBook() {
		return book;
	}

	@Column(name = "order_date")
	public Date getDate() {
		return this.date;
	}

	
	@Column(name = "issue_date")
	@NotNull(message="{NotEmpty.order.issueDate}")
	public Date getIssueDate() {
		return this.issueDate;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}


	@Override
	public String toString() {
		return getId() + ": " + getPerson() 
                        + " ordered " + getBook() 
                        + ". Date: " + getDate();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Orders) return true;
		if (((Orders) obj).getBook().equals(this.getBook())
				&& ((Orders) obj).getPerson().equals(this.getPerson())
				&& ((Orders) obj).getDate() == this.getDate()) 
			return true;
		return false;
	}

}
