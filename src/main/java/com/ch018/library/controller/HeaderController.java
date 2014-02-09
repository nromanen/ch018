package com.ch018.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Book;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.WishListService;

@Controller
public class HeaderController implements ViewPreparer {
	
	@Autowired
	private WishListService wishListService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private BooksInUseService booksInUseService;
	
	@Autowired
	private BookService bookService;

	@Override
	public void execute(Request tilesRequest, AttributeContext attributeContext) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    long countOrders = 0;
		long countBooks = 0;
		long countWish = 0;
	    
	    if (!name.equals("anonymousUser")) {
	    	countWish = wishListService.getCountByPerson(name);
	    	countOrders = ordersService.getCountOrdersByPerson(name);
	    	countBooks = booksInUseService.getCountBooksByPerson(name);
	    }
		attributeContext.putAttribute("countWish", new Attribute(countWish));
		attributeContext.putAttribute("countOrders", new Attribute(countOrders));
		attributeContext.putAttribute("countBooks", new Attribute(countBooks));

	}
	
	@RequestMapping(value = "/search/getBooks", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getBooks(@RequestParam(value="term") String bookName) {
		return simulateSearchResult(bookName);
	}
	
	private List<String> simulateSearchResult(String bookName) {
		List<String> result = new ArrayList<String>();
		List<Book> books = bookService.simpleSearch(bookName, 0, 0, "id");
		for (Book book : books) {
			result.add(book.getTitle());
		}
		return result;
	}
}
