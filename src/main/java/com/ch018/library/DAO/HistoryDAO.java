package com.ch018.library.DAO;

import java.util.List;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.History;
import com.ch018.library.entity.Person;

public interface HistoryDAO {
	int newEntry(History history);
	List<History> getAllHistory();
	History getEntry(Person person, Book book);
	void removeHistory(History history);
}
