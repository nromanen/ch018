package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.History;
import com.ch018.library.entity.Localization;
import com.ch018.library.entity.Person;

@Component
public class HistoryDAOImpl implements HistoryDAO {
	
	private static Logger log = LogManager.getLogger(WishListDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int newEntry(History history) {
		int result = 0;
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(history);
			result = 1;
		} catch (Exception e) {
			result = 0;
			log.error("Error insert entry: {0}", e.getMessage());
		}
		return result;
	}
	
	@Override
	public History getEntry(Person person, Book book) {
		History history = new History();
		try {
			history = (History) sessionFactory.getCurrentSession().createCriteria(History.class)
			.add(Restrictions.eq("person", person))
			.add(Restrictions.eq("book", book)).uniqueResult();

		} catch (Exception e) {
			log.error("Error get entry: }", e);
		}
		return history;
	}

	@Override
	public List<History> getAllHistory() {
		List<History> history = new ArrayList<>();
		try {
			history.addAll(sessionFactory.getCurrentSession()
					.createCriteria(History.class).list());
		} catch (Exception e) {
			log.error("Error get history: {0}", e.getMessage());
		}
		return history;
	}
	
	

}
