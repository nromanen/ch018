package com.ch018.library.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.Person;

/**
 * 
 * @author Yurik Mikhaletskiy
 */
@Component
public class PersonDaoImpl implements PersonDao {
	
	private static Logger log = LogManager.getLogger(GenreDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Person person) {
		try {
			sessionFactory.getCurrentSession().save(person);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public int delete(int id) {
		int deleted = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession().getNamedQuery("deletePerson")
					.setInteger("id", id);
			deleted = query.executeUpdate();
		} catch (Exception e) {
			log.error(e);
			deleted = 0;
		}
		return deleted;
	}

	@Override
	public void update(Person person) {
		try {
			sessionFactory.getCurrentSession().update(person);
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public Person getById(int id) {
		Person person = null;
		try {
			person = (Person) sessionFactory.getCurrentSession().get(
					Person.class, id);
		} catch (Exception e) {
			log.error(e);
		}
		return person;
	}
	
	@Override
	public Person getByIdWithBooks(int id) {
		Session session = null;
		Person person = null;
		try {
			session = sessionFactory.getCurrentSession();
			person = (Person) session.get(Person.class, id);
		} catch (Exception e) {
			log.error(e);
		}
		Hibernate.initialize(person.getBooksinuses());
		session.clear();
		return person;
	}
	
	@Override
	public Person getByIdWithOrders(int id) {
		Session session = null;
		Person person = null;
		try {
			session = sessionFactory.getCurrentSession();
			person = (Person) session.get(Person.class, id);
		} catch (Exception e) {
			log.error(e);
		}
		Hibernate.initialize(person.getOrders());
		session.clear();
		return person;
	}

	@Override
	public Person getByEmail(String email) {
		Person person = null;
		try {
			person = (Person) sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("email", email)).list().get(0);
		} catch (Exception e) {
			log.error(e);
		}
		return person;

	}
	
	@Override
	public Person getByKey(String key) {
		Person person = null;
		try {
			person = (Person) sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("verificationKey", key)).list().get(0);
		} catch (Exception e) {
			log.error(e);
		}
		return person;
	}

	@Override
	public List<Person> getSmsEnabled() {
		List<Person> persons = null;
		try {
			persons = sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("sms", "1")).list();
		} catch (Exception e) {
			log.error(e);
		}
		return persons;
	}
	
	@Override
	public long isExist(String email) {
		long count = 0;
		try {
			
			count = (long) sessionFactory
					.getCurrentSession()
					.createQuery("select count(P) from Person P where lower(P.email) = lower(:email)")
					.setString("email", email).iterate().next();
		} catch (Exception e) {
			log.error(e);
			count = 0;
		}

		return count;
	}
	
	@Override
	public long count() {
		long count = 0;
		try {
			count = (long) sessionFactory.getCurrentSession().createCriteria(Person.class).setProjection(Projections.rowCount()).uniqueResult();
		} catch (Exception e) {
			log.error("Error getting count of persons: " + e.getMessage());
		}
		return count;
	}
	
	@Override
	public List<Person> getAll(int currentPos, int pageSize, String field) {
		List<Person> persons = new ArrayList<>();
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("from Person P order by P." + field + " asc");
			if (pageSize != 0) {
				query.setMaxResults(pageSize).setFirstResult(currentPos);
			}
			persons.addAll(query.list());
		} catch (Exception e) {
			log.error("Error getting all persons: " + e.getMessage());
		}
		return persons;
	}

}