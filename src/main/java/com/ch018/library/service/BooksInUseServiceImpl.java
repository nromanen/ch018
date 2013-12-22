package com.ch018.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BooksInUseDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;

@Service
public class BooksInUseServiceImpl implements BooksInUseService {

	@Autowired
	private BooksInUseDAO booksInUseDAO;
	
	@Override
	@Transactional
	public void addBooksInUse(BooksInUse booksInUse) {
		// TODO Auto-generated method stub
		booksInUseDAO.addBooksInUse(booksInUse);
	}

	@Override
	@Transactional
	public void removeBooksInUse(BooksInUse booksInUse) {
		// TODO Auto-generated method stub
		booksInUseDAO.removeBooksInUse(booksInUse);
	}

	@Override
	@Transactional
	public List<BooksInUse> getAllBooksInUse() {
		// TODO Auto-generated method stub
		return booksInUseDAO.getAllBooksInUse();
	}

	@Override
	@Transactional
	public List<BooksInUse> getByPersonId(int personId) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByPersonId(personId);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByBookId(int bookId) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByBookId(bookId);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByIssueDate(Date issueDate) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByIssueDate(issueDate);
	}

	@Override
	@Transactional
	public List<BooksInUse> getByReturnDate(Date returnDate) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByReturnDate(returnDate);
	}

	@Override
	@Transactional
	public List<BooksInUse> getInUse(boolean inUse) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getInUse(inUse);
	}

	@Override
	@Transactional
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return booksInUseDAO.getAllBooks();
	}

}
