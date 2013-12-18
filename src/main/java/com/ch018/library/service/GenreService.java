package com.ch018.library.service;

import java.util.List;

import com.ch018.library.entity.Genre;

public interface GenreService {
	void addGenre(Genre genre);
	void updateGenre(int id, Genre genre);
	List<Genre> getAllGenres();
	Genre getGenreById(int id);
	void deleteGenre(Genre genre);
	int getCount();
}
