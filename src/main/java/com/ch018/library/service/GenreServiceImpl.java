package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.GenreDAO;
import com.ch018.library.entity.Genre;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreDAO genreDAO;
	
	@Autowired 
	private LocalizationService localizationService;
	
	@Transactional
	public void addGenre(Genre genre) {
		genreDAO.addGenre(genre);
	}

	@Override
	@Transactional
	public List<Genre> getAllGenres(String language) {
		List<Genre> genres = genreDAO.getAllGenres(language);
		return genres;
	}

	@Override
	@Transactional
	public Genre getGenreById(int id) {
		return genreDAO.getGenreById(id);
	}
}
