package com.ch018.library.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(book);
	}

	@Override
	public void updateBook(int id, Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(Book.class).list();
	}

	@Override
	public Book getBooksById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByAuthors(String authors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCurrentCount(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}

}
