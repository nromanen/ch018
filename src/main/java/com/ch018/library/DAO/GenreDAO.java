package com.ch018.library.DAO;

import java.util.List;

import com.ch018.library.entity.Genre;

public interface GenreDAO {
	void addGenre(Genre genre);
	List<Genre> getAllGenres();
	Genre getGenreById(int id);
	Genre getGenreByIdWithBooks(int id);
	Genre getGenreByName(String name);
}
