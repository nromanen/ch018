package com.ch018.library.service;

import java.util.List;

import com.ch018.library.entity.Person;

public interface PersonService {
	void save(Person person);
    int delete(int id);
    void update(Person person);
    List<Person> getAll();
    Person getById(int id);
    Person getByEmail(String email);
    List<Person> getByName(String name);
    List<Person> getBySurname(String surname);
    Person getByCellPhone(String cellphone);
    List<Person> getByRole(String role);
    List<Person> getConfirmed();
    List<Person> getSmsEnabled();
    Person updateAccProperties(Person person, Person updatedPerson);
}
