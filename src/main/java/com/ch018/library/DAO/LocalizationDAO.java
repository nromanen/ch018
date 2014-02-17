package com.ch018.library.DAO;

import com.ch018.library.entity.Localization;

public interface LocalizationDAO {
	String getName(int id, String language);
	void addGenreLocalization(Localization localization);
}
