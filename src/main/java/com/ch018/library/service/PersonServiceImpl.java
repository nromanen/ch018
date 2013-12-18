package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDao personDao;
	
	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		personDao.save(person);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		personDao.delete(id);
	}

	@Override
	public void update(Person person) {
		// TODO Auto-generated method stub
		personDao.update(person);
	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return personDao.getAll();
	}

	@Override
	public Person getById(int id) {
		// TODO Auto-generated method stub
		return personDao.getById(id);
	}

	@Override
	public Person getByEmail(String email) {
		// TODO Auto-generated method stub
		return personDao.getByEmail(email);
	}

	@Override
	public List<Person> getByName(String name) {
		// TODO Auto-generated method stub
		return personDao.getByName(name);
	}

	@Override
	public List<Person> getBySurname(String surname) {
		// TODO Auto-generated method stub
		return personDao.getBySurname(surname);
	}

	@Override
	public Person getByCellPhone(String cellphone) {
		// TODO Auto-generated method stub
		return personDao.getByCellPhone(cellphone);
	}

	@Override
	public List<Person> getByRole(String role) {
		// TODO Auto-generated method stub
		return personDao.getByRole(role);
	}

	@Override
	public List<Person> getConfirmed() {
		// TODO Auto-generated method stub
		return personDao.getConfirmed();
	}

	@Override
	public List<Person> getSmsEnabled() {
		// TODO Auto-generated method stub
		return personDao.getSmsEnabled();
	}

}
