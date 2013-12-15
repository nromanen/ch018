package com.ch018.library.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Bookcase;

@Repository
public class BookcaseDAOImpl implements BookcaseDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addBokcase(Bookcase bookcase) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(bookcase);
	}

	@Override
	public void updateBookcase(int id, Bookcase bookcase) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Bookcase> getAllBookcases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bookcase getBookcaseById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBookcase(Bookcase bookcase) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
