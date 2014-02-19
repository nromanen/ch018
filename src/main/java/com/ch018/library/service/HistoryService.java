package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.History;
import com.ch018.library.entity.Person;

public interface HistoryService {
	int newEntry(History history);
	List<History> getAllHistory();
	History getEntry(Person person, Book book);
}
