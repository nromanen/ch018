package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.BooksInUse;

@Component
public class BooksInUseDAOImpl implements BooksInUseDAO {

	@Autowired
	SessionFactory sessionFactory;

	static Logger log = LogManager.getLogger(BooksInUseDAOImpl.class);

	@Override
	public void addBooksInUse(BooksInUse booksInUse) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(booksInUse);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public void removeBooksInUse(BooksInUse booksInUse) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BooksInUse> getAllBooksInUse() {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class).list());
		} catch (Exception e) {
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByPersonId(int personId) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("Person_id", personId)).list());
		} catch (Exception e) {
			System.out.println(e);// log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByBookId(int bookId) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("Books_id", bookId)).list());
		} catch (Exception e) {
			System.out.println(e);// log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByIssueDate(Date issueDate) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("issue_date", issueDate)).list());
		} catch (Exception e) {
			System.out.println(e);
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByReturnDate(Date returnDate) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("return_date", returnDate)).list());
		} catch (Exception e) {
			System.out.println(e);
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getInUse(boolean inUse) {
		// TODO Auto-generated method stub
		List<BooksInUse> booksInUses = new ArrayList<>();
		try {
			booksInUses.addAll(sessionFactory.getCurrentSession()
					.createCriteria(BooksInUse.class)
					.add(Restrictions.eq("inUse", inUse)).list());
		} catch (Exception e) {
			System.out.println(e);
			log.error(e);
		}
		return booksInUses;
	}

	@Override
	public void updateBooksInUse(int id, BooksInUse booksInUse) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(booksInUse);
			log.info("Updated booksInUse: " + booksInUse);
		} catch (Exception e) {
			log.error("Error insert " + e);
		}
	}

}
