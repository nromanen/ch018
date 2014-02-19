package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.HistoryDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.History;
import com.ch018.library.entity.Person;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	private HistoryDAO historyDAO;
	
	@Override
	@Transactional
	public int newEntry(History history) {
		return historyDAO.newEntry(history);
	}

	@Override
	@Transactional
	public List<History> getAllHistory() {
		return historyDAO.getAllHistory();
	}

	@Override
	public History getEntry(Person person, Book book) {
		return historyDAO.getEntry(person, book);
	}

}
