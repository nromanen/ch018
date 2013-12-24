/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {

	private int id;
	private Date date;
	private Person person;
	private Book book;
        private Date issueDate;

	public Orders() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return this.id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Person_id")
	public Person getPerson() {
		return person;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Books_id")
	public Book getBook() {
		return book;
	}

	@Column(name = "order_date")
	public Date getOrderDate() {
		return this.date;
	}
        
         @Column(name = "issue_date")
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

	public void setOrderDate(Date date) {
		this.date = date;
	}
        
        public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
        }
        
	@Override
	public String toString() {
		return getId() + ": " + getPerson() + " ordered " + getBook() + ". Date: " + getOrderDate();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Orders) return true;
		if (((Orders) obj).getBook().equals(this.getBook())
				&& ((Orders) obj).getPerson().equals(this.getPerson())
				&& ((Orders) obj).getOrderDate() == this.getOrderDate()) 
			return true;
		return false;
	}

}
