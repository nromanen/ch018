package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Genre;


@Component
public interface GenreService {
	void addGenre(Genre genre);
	//List<Genre> getAllGenres();
	Genre getGenreById(int id);
	Genre getGenreByIdWithBooks(int id);
	Genre getGenreByName(String name);
	List<Genre> getAllGenres(String language);
}
