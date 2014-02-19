package com.ch018.library.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ch018.library.entity.Genre;

public interface GenreService {
	void addGenre(Genre genre);
	Genre getGenreById(int id);
	List<Genre> getAllGenres(String language);
}
