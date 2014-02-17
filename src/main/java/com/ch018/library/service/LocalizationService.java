package com.ch018.library.service;

import org.springframework.stereotype.Component;

import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;

@Component
public interface LocalizationService {
	void addGenreLocalization(Localization localization);
	String getName(int id, String language);
}
