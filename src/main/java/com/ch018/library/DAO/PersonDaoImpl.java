/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.DAO;

import com.ch018.library.util.HibernateUtil;
import com.ch018.library.entity.Person;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.Logger;
import org.hibernate.Session;
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

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Person person) {
		try {
			sessionFactory.getCurrentSession().save(person);
		} catch (Exception e) {
			System.out.println(e);// log.error(e);
		}
	}

	@Override
	public List<Person> getAll() {
		List<Person> persons = new ArrayList<>();
		try {
			persons = sessionFactory.getCurrentSession()
					.createCriteria(Person.class).list();
		} catch (Exception e) {
			System.out.println(e);// log.error(e);
		}
		return persons;
	}

	@Override
	public void delete(int id) {
		Person person = null;
		try {
			person = (Person) sessionFactory.getCurrentSession().get(
					Person.class, id);
		} catch (Exception e) {
			System.out.println(e);// log.error(e);
		}
	}

	@Override
	public void update(Person person) {
		try {
			sessionFactory.getCurrentSession().update(person);
		} catch (Exception e) {
			System.out.println(e);// log.error(e);
		}
	}

	@Override
	public Person getById(int id) {
		Person person = null;
		try {
			person = (Person) sessionFactory.getCurrentSession().get(
					Person.class, id);
		} catch (Exception e) {
			System.out.println(e);// log.error(e);
		}
		return person;
	}

	@Override
	public Person getByEmail(String email) {
		Person person = null;
		try {
			person = (Person) sessionFactory.getCurrentSession()
					.createCriteria(Person.class)
					.add(Restrictions.eq("e_mail", email));
		} catch (Exception e) {
			System.out.println(e);// log.error(e);
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
			System.out.println(e);// log.error(e);
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
			System.out.println(e);// log.error(e);
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
			System.out.println(e);// log.error(e);
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
			System.out.println(e);// log.error(e);
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
			System.out.println(e);// log.error(e);
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
			System.out.println(e);// log.error(e);
		}
		return persons;
	}

}