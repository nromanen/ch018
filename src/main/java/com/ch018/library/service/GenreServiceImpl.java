package com.ch018.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.GenreDAO;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;

@Component
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
		List<Localization> l = new ArrayList<>();
		for (Genre genre : genres) {
			l.addAll(genre.getLocalizations());
			for (Localization localization : l) {
				if (localization.getLanguage().equals(language)) {
					genre.setName(localization.getLocalizedName());
				}
			}
		}
		return genres;
	}

	@Override
	@Transactional
	public Genre getGenreById(int id) {
		return genreDAO.getGenreById(id);
	}
}
