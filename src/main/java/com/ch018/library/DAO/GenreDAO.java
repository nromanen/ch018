package com.ch018.library.DAO;

import java.util.List;

import com.ch018.library.entity.Genre;

public interface GenreDAO {
	void addGenre(Genre genre);

	void updateGenre(int id, Genre genre);

	List<Genre> getAllGenres();

	Genre getGenreById(int id);

	Genre getGenreByName(String name);

	void deleteGenre(Genre genre);

	int getCount();
}
