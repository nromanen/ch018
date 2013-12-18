package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ch018.library.DAO.GenreDAO;
import com.ch018.library.entity.Genre;

@Component
public class GenreServiceImpl implements GenreService {

	@Autowired
	GenreDAO genreDAO;
	
	public void addGenre(Genre genre) {
		// TODO Auto-generated method stub
		genreDAO.addGenre(genre);
	}

	@Override
	public void updateGenre(int id, Genre genre) {
		// TODO Auto-generated method stub
		genreDAO.updateGenre(id, genre);
	}

	@Override
	public List<Genre> getAllGenres() {
		// TODO Auto-generated method stub
		return genreDAO.getAllGenres();
	}

	@Override
	public Genre getGenreById(int id) {
		// TODO Auto-generated method stub
		return genreDAO.getGenreById(id);
	}

	@Override
	public void deleteGenre(Genre genre) {
		// TODO Auto-generated method stub
		genreDAO.deleteGenre(genre);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return genreDAO.getCount();
	}

}
