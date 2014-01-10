package com.ch018.library.controller;

import java.util.List;

import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ch018.library.entity.Genre;
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
		List<Genre> genres = genreService.getAllGenres();
		attributeContext.putAttribute("genres", new Attribute(genres));		
	}

}
