package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.GenreDAO;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;

@Component
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreDAO genreDAO;
	
	@Transactional
	public void addGenre(Genre genre) {
		// TODO Auto-generated method stub
		genreDAO.addGenre(genre);
	}

	@Override
	@Transactional
	public List<Genre> getAllGenres(String language) {
		// TODO Auto-generated method stub
		return genreDAO.getAllGenres(language);
	}

	@Override
	@Transactional
	public Genre getGenreById(int id) {
		// TODO Auto-generated method stub
		return genreDAO.getGenreById(id);
	}
	
	/**
	 * Lazy initialization
	 */
	@Override
	@Transactional
	public Genre getGenreByIdWithBooks(int id) {
		// TODO Auto-generated method stub
		return genreDAO.getGenreByIdWithBooks(id);
	}

	@Override
	@Transactional
	public Genre getGenreByName(String name) {
		// TODO Auto-generated method stub
		return genreDAO.getGenreByName(name);
	}
	
	@Override
	@Transactional
	public Genre getGenreByBook(Book book, String locale) {
		// TODO Auto-generated method stub
		return genreDAO.getGenreByBook(book, locale);
	}

}
