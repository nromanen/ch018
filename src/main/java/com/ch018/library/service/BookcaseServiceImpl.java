package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.DAO.BookcaseDAO;
import com.ch018.library.entity.Bookcase;

@Component
public class BookcaseServiceImpl implements BookcaseService {

	@Autowired
	BookcaseDAO bookcaseDAO;
	
	public void addBokcase(Bookcase bookcase) {
		// TODO Auto-generated method stub
		bookcaseDAO.addBokcase(bookcase);
	}

	@Override
	public void updateBookcase(int id, Bookcase bookcase) {
		// TODO Auto-generated method stub
		bookcaseDAO.updateBookcase(id, bookcase);
	}

	@Override
	public List<Bookcase> getAllBookcases() {
		// TODO Auto-generated method stub
		return bookcaseDAO.getAllBookcases();
	}

	@Override
	public Bookcase getBookcaseById(int id) {
		// TODO Auto-generated method stub
		return bookcaseDAO.getBookcaseById(id);
	}

	@Override
	public void deleteBookcase(Bookcase bookcase) {
		// TODO Auto-generated method stub
		bookcaseDAO.deleteBookcase(bookcase);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return bookcaseDAO.getCount();
	}

}
