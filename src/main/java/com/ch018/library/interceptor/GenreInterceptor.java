package com.ch018.library.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.ch018.library.entity.Genre;
import com.ch018.library.service.LocalizationService;

public class GenreInterceptor extends EmptyInterceptor {
	
	@Autowired
	private LocalizationService localizationService;
	
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Genre) {
			Genre genre = (Genre) entity;
			genre.setName(localizationService.getName(genre.getId(), "en"));
			return true;
	    }
		return false;
	}
}
