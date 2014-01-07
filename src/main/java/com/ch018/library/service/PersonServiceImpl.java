package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	
	@Override
	@Transactional
	public void save(Person person) {
		// TODO Auto-generated method stub
		personDao.save(person);
	}

	@Override
	@Transactional
	public int delete(int id) {
		// TODO Auto-generated method stub
		return personDao.delete(id);
	}

	@Override
	@Transactional
	public void update(Person person) {
		// TODO Auto-generated method stub
		personDao.update(person);
	}

	@Override
	@Transactional
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return personDao.getAll();
	}

	@Override
	@Transactional
	public Person getById(int id) {
		// TODO Auto-generated method stub
		return personDao.getById(id);
	}

	@Override
	@Transactional
	public Person getByEmail(String email) {
		// TODO Auto-generated method stub
		return personDao.getByEmail(email);
	}

	@Override
	@Transactional
	public List<Person> getByName(String name) {
		// TODO Auto-generated method stub
		return personDao.getByName(name);
	}

	@Override
	@Transactional
	public List<Person> getBySurname(String surname) {
		// TODO Auto-generated method stub
		return personDao.getBySurname(surname);
	}

	@Override
	@Transactional
	public Person getByCellPhone(String cellphone) {
		// TODO Auto-generated method stub
		return personDao.getByCellPhone(cellphone);
	}

	@Override
	@Transactional
	public List<Person> getByRole(String role) {
		// TODO Auto-generated method stub
		return personDao.getByRole(role);
	}

	@Override
	@Transactional
	public List<Person> getConfirmed() {
		// TODO Auto-generated method stub
		return personDao.getConfirmed();
	}

	@Override
	@Transactional
	public List<Person> getSmsEnabled() {
		// TODO Auto-generated method stub
		return personDao.getSmsEnabled();
	}

    @Override
    public Person updateAccProperties(Person person, Person updatedPerson) {
        if(!updatedPerson.getName().isEmpty())
                         if((person.getName()==null)||(!person.getName().equals(updatedPerson.getName()))){
                             person.setName(updatedPerson.getName());
                         } 
        if(!updatedPerson.getSurname().isEmpty())
                         if((person.getSurname()==null)||(!person.getSurname().equals(updatedPerson.getSurname()))){
                             person.setSurname(updatedPerson.getSurname());
                         } 
        if(!updatedPerson.getCellphone().isEmpty())
                         if((person.getCellphone()==null)||(!person.getCellphone().equals(updatedPerson.getCellphone()))){
                             person.setCellphone(updatedPerson.getCellphone());
                         }
        if(!updatedPerson.getEmail().isEmpty())
                         if(!person.getEmail().equals(updatedPerson)){
                             person.setEmail(updatedPerson.getEmail());
                         }
        if(person.getSms()!=updatedPerson.getSms()){
            person.setSms(updatedPerson.getSms());
        }
        return person;
    }

      

}
