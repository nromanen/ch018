package com.ch018.library.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ch018.library.entity.Person;
import com.ch018.library.form.Password;
import com.ch018.library.form.Registration;
import com.ch018.library.form.ResetPassword;

public interface PersonService {
	void save(Person person);
    int delete(int id);
    void update(Person person);
    Person getById(int id);
    Person getByIdWithBooks(int id);
    Person getByIdWithOrders(int id);
    Person getByEmail(String email);
    Person getByKey(String key);
    List<Person> getSmsEnabled();
    Person updateAccProperties(Person person, Person updatedPerson, HttpServletRequest request);
    long isExist(String email);
    void librarianUpdatePerson(Person person, Person person2);
	void registrate(Registration registration, String message, HttpServletRequest request);
	void restorePassword(Person person, ResetPassword password);
	void librarianSavePerson(Person person, HttpServletRequest request);
	long getCount();
	List<Person> getAll(int currentPos, int pageSize, String field, boolean isAsc);
	void adminSavePerson(Person person, HttpServletRequest request);
	void adminUpdatePerson(Person person, Person person2);
	void updateEmail(Person pers, Person pers2, HttpServletRequest request);
	void remindPasswoed(Person person, String message,
			HttpServletRequest request);
	boolean updatePassword(Password password, Person person);
}
