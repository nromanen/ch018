package com.ch018.library.controller;

import java.util.List;
import java.util.Locale;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import com.ch018.library.entity.Book;
import com.ch018.library.service.BookService;




public class RightSideController implements ViewPreparer{

	@Autowired
	private BookService bookService;
	
	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) {
		Locale locale = LocaleContextHolder.getLocale();
		
		List<Book> books = bookService.getBooksByRating();
		attributeContext.putAttribute("books", new Attribute(books));
		
		
	}

}
