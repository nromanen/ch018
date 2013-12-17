package com.ch018.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch018.library.DAO.BooksInUseDAO;
import com.ch018.library.entity.BooksInUse;

@Service
public class BooksInUseServiceImpl implements BooksInUseService {

	@Autowired
	BooksInUseDAO booksInUseDAO;
	
	@Override
	public void addBooksInUse(BooksInUse booksInUse) {
		// TODO Auto-generated method stub
		booksInUseDAO.addBooksInUse(booksInUse);
	}

	@Override
	public void removeBooksInUse(BooksInUse booksInUse) {
		// TODO Auto-generated method stub
		booksInUseDAO.removeBooksInUse(booksInUse);
	}

	@Override
	public List<BooksInUse> getAllBooksInUse() {
		// TODO Auto-generated method stub
		return booksInUseDAO.getAllBooksInUse();
	}

	@Override
	public List<BooksInUse> getByPersonId(int personId) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByPersonId(personId);
	}

	@Override
	public List<BooksInUse> getByBookId(int bookId) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByBookId(bookId);
	}

	@Override
	public List<BooksInUse> getByIssueDate(Date issueDate) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByIssueDate(issueDate);
	}

	@Override
	public List<BooksInUse> getByReturnDate(Date returnDate) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getByReturnDate(returnDate);
	}

	@Override
	public List<BooksInUse> getInUse(boolean inUse) {
		// TODO Auto-generated method stub
		return booksInUseDAO.getInUse(inUse);
	}

}
