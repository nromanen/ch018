package com.ch018.library.DAO;

import com.ch018.library.entity.Person;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Yurik Mikhaletskiy
 */
@Component
public interface PersonDao {
	void save(Person person);
	int delete(int id);
	void update(Person person);
	Person getById(int id);
	Person getByIdWithBooks(int id);
	Person getByIdWithOrders(int id);
	Person getByEmail(String email);
	Person getByKey(String key);
	List<Person> getSmsEnabled();
	long isExist(String email);
	long count();
	List<Person> getAll(int currentPos, int pageSize, String field);
}
