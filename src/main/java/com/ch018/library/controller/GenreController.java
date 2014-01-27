package com.ch018.library.controller;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;

import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Localization;
import com.ch018.library.service.GenreService;
/**
 * 
 * @author Yurik Mikhaletskiy
 *
 */
@Controller
public class GenreController implements ViewPreparer {
	
	@Autowired
	private GenreService genreService;

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) {
		Locale locale = LocaleContextHolder.getLocale();
		List<Genre> genres = genreService.getAllGenres(locale.getLanguage()); 		 // TODO: Change "EN"
		
		attributeContext.putAttribute("genres", new Attribute(genres));		
	}

}
