package com.ch018.library.DAO;

import java.util.List;

import com.ch018.library.entity.Bookcase;

public interface BookcaseDAO {
	void addBokcase(Bookcase bookcase);
	void updateBookcase(int id, Bookcase bookcase);
	List<Bookcase> getAllBookcases();
	Bookcase getBookcaseById(int id);
	void deleteBookcase(Bookcase bookcase);
	int getCount();
}
