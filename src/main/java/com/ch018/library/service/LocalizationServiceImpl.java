package com.ch018.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.LocalizationDAO;
import com.ch018.library.entity.Localization;

@Component
public class LocalizationServiceImpl implements LocalizationService {
	
	@Autowired
	private LocalizationDAO localizationDAO;
	
	@Override
	@Transactional
	public void addGenreLocalization(Localization localization) {
		localizationDAO.addGenreLocalization(localization);
	}

	@Override
	@Transactional
	public String getName(int id, String language) {
		return localizationDAO.getName(id, language);
	}

}
