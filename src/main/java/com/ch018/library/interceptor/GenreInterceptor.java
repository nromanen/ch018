package com.ch018.library.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import com.ch018.library.entity.Genre;
import com.ch018.library.service.LocalizationService;

/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
public class GenreInterceptor extends EmptyInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6425472886824039232L;
	@Autowired
	private LocalizationService localizationService;
	
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof Genre) {
			Genre genre = (Genre) entity;
			genre.setName(localizationService.getName(genre.getId(), LocaleContextHolder.getLocale().getLanguage()));
			return true;
	    }
		return false;
	}
}
