package com.ch018.library.controller;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

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
	
}
