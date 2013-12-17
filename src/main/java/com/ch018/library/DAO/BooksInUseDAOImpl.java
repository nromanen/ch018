package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.util.HibernateUtil;

@Component
public class BooksInUseDAOImpl implements BooksInUseDAO {

	static Logger log = LogManager.getLogger(BooksInUseDAOImpl.class);
	
	@Override
	public void addBooksInUse(BooksInUse booksInUse) {
		// TODO Auto-generated method stub
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(booksInUse);
            session.getTransaction().commit();
        } catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
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
		Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            booksInUses.addAll(session.createCriteria(BooksInUse.class).list());
        } catch(Exception e){
            log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                log.error(e);
            }
        }
		return booksInUses;
	}

	@Override
	public List<BooksInUse> getByPersonId(int personId) {
		// TODO Auto-generated method stub
		Session session = null;
		List<BooksInUse> booksInUses = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            booksInUses.addAll(session.createCriteria(BooksInUse.class).add(Restrictions.eq("Person_id", personId)).
                    list());
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
        return booksInUses;
	}

	@Override
	public List<BooksInUse> getByBookId(int bookId) {
		// TODO Auto-generated method stub
		Session session = null;
		List<BooksInUse> booksInUses = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            booksInUses.addAll(session.createCriteria(BooksInUse.class).add(Restrictions.eq("Books_id", bookId)).
                    list());
        } catch(Exception e){
            System.out.println(e);//log.error(e);
        }finally{
            try{
                session.close();
            }catch(Exception e){
                //log.error(e);
            }
        }
        return booksInUses;
	}

	@Override
	public List<BooksInUse> getByIssueDate(Date issueDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BooksInUse> getByReturnDate(Date returnDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BooksInUse> getInUse(boolean inUse) {
		// TODO Auto-generated method stub
		return null;
	}

}
