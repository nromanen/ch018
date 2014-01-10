/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.entity.Person;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public List<Person> getAll() {
		List<Person> persons = new ArrayList<>();
		try {
			persons = sessionFactory.getCurrentSession()
					.createCriteria(Person.class).list();
		} catch (Exception e) {
			log.error(e);
		}
		return persons;
	}

	@Override
	public int delete(int id) {
		int deleted = 0;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery("delete from Person where id=:id")
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
		String pasword = person.getPassword();
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
	public List<Person> getByName(String name) {
		List<Person> persons = null;
		try {
			persons = sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("name", name)).list();
		} catch (Exception e) {
			log.error(e);
		}
		return persons;
	}

	@Override
	public List<Person> getBySurname(String surname) {
		List<Person> persons = null;
		try {
			persons = sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("surname", surname)).list();
		} catch (Exception e) {
			log.error(e);
		}
		return persons;
	}

	@Override
	public Person getByCellPhone(String cellphone) {
		Person person = null;
		try {
			person = (Person) sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("cellphone", cellphone)).list().get(0);
		} catch (Exception e) {
			log.error(e);
		}
		return person;
	}

	@Override
	public List<Person> getByRole(String role) {
		List<Person> persons = null;
		try {
			persons = sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("role", role)).list();
		} catch (Exception e) {
			log.error(e);
		}
		return persons;
	}

	@Override
	public List<Person> getConfirmed() {
		List<Person> persons = null;
		try {
			persons = sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("confirm", "1")).list();
		} catch (Exception e) {
			log.error(e);
		}
		return persons;
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

}