package com.ch018.library.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
import com.ch018.library.validator.OrderValidator;

// TODO: author who?
/**
 * 
 * @author win7
 */
@Controller
public class OrderController {
    
    @Autowired
    private OrdersService orderService;
    
    @Autowired
    private WishListService wishListService;
    
    @Autowired
    private PersonService personService;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private BooksInUseService booksInUseService;
    
    @Autowired
    private OrderValidator orderValidator;
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/fail")
    public void fail(Model model){
    	
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/prepareorder", method = RequestMethod.GET)
    @ResponseBody
    public int prepareOrder(Model model, 
                                    @RequestParam("book") int bookId, 
                                    //@RequestParam("wish") int wishId, 
                                    Principal principal) {
        Person p = personService.getByEmail(principal.getName());
        if (booksInUseService.alreadyInUse(bookId, p.getId())) {
            return 0;
        }
        int personId = p.getId();
        int  uses = orderService.getOrdersByPersonId(personId).size();
        uses += booksInUseService.getByPersonId(personId).size();
        int j = p.getMultibookAllowed();
        if (j == uses) {
            return 2;
        } 
        if (orderService.orderExist(personId, bookId)) {
              return 3;
          }
        return 1;
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(Model model, 
            @RequestParam("book") int bookId, 
            //@RequestParam("wish") int wishId, 
            Principal principal) {
    	Person p = personService.getByEmail(principal.getName());
        Orders newOrder = new Orders();
        Book b = bookService.getBooksById(bookId);
        int term = 14;
        int available = b.getAvailable();
        if (available == 0) {
            Date date;
            date = booksInUseService.getMinByReturnDate(bookId);
            model.addAttribute("date", date);
        }
        if (available == 1) {
            Date date = orderService.minOrderDateOf(bookId);
            if(date == null){
            	model.addAttribute("term", term);
               } else {
                date.setDate(date.getDate() - 1);
               // date = Calendar.getInstance().getTime();
                model.addAttribute("orderDate", date.toString());
            }
        }
        if (available > 1) {
            model.addAttribute("term", term);
        }
        newOrder.setPerson(p);
        newOrder.setBook(b);
        model.addAttribute("order", newOrder);
        return "order";
   }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("order") Orders newOrder, 
                              BindingResult result, Model model) {
      	int bookId = newOrder.getBook().getId();
        int personId = newOrder.getPerson().getId();
        Calendar calendar = Calendar.getInstance();
        newOrder.setBook(bookService.getBooksById(newOrder.getBook().getId()));
        newOrder.setPerson(personService.getById(newOrder.getPerson().getId()));
        newOrder.setDate(calendar.getTime());
        orderService.addOrder(newOrder);
        if (wishListService.bookExistInWishList(newOrder.getBook().getId(), newOrder.getPerson().getId())) {
                    int id = wishListService.getWishWithoutId(bookId, personId).getId();
					wishListService.deleteWishById(id);
				}
        return "redirect:/userOrder";
    }
    
    @RequestMapping(value = "/userOrder", method = RequestMethod.GET)
    public Model showOrder(Principal principal, @ModelAttribute("editIssue") Orders newIssue, Model model) {
        //return new ModelAndView("userOrder", "showOrders", orderService.getOrdersByPersonId(personService.getByEmail(principal.getName()).getId()));
    	model.addAttribute("showOrders", orderService.getOrdersByPersonId(personService.getByEmail(principal.getName()).getId()));
    	return model;
    }
    
    @RequestMapping(value = "/userOrder", method = RequestMethod.POST)
    @ResponseBody
	public int editIssueDate(@Valid Orders editIssue, Model model,
			Principal principal, BindingResult result) {
    	orderValidator.validate(editIssue, result);
    	if (result.hasErrors()) {
    		model.addAttribute("showOrders", orderService.getOrdersByPersonId(personService.getByEmail(principal.getName()).getId()));
    		return 0;
    	}
    	Orders updateOrder = orderService.getById(editIssue.getId());
       	int available = updateOrder.getBook().getAvailable();
       	long  a = booksInUseService.getCountReturnBooksBeetweenDates(updateOrder.getIssueDate(), 
       			                                                    editIssue.getIssueDate(), 
       			                                                    updateOrder.getBook().getId());
       	long b = orderService.getCountOrdersBookBeetweenDates(updateOrder.getIssueDate(), 
       			                                                    editIssue.getIssueDate(), 
       			                                                    updateOrder.getBook().getId()) - 1;
       	int expectedAvailable = (int) ((int) a - b);
       	expectedAvailable = expectedAvailable + available;
        if (expectedAvailable > 1 ) {
        	updateOrder.setIssueDate(editIssue.getIssueDate());
            orderService.updateOrder(updateOrder);
            return 1;
        } else {
        	model.addAttribute("fail", "Try another date");
        	return 2;
        }
    }
    
    @RequestMapping(value = "/deleteorder", method = RequestMethod.GET)
    public String deleteOrder(@RequestParam("id") int id) {
    	orderService.deleteOrder(id);
    	return "redirect:/userOrder";
    }
    
    /**
     *      
     * Librarian
     */
	@RequestMapping(value = "/orders/{query}/{id}", method = RequestMethod.GET)
	public String showOrders(@PathVariable("id") Integer id,
			@PathVariable("query") String query, Model model) {
    	if (query.equals("book")) {
			Book book = bookService.getBooksByIdWithOrders(id);
			Set<Orders> orders = book.getOrders();
			model.addAttribute("orders", orders);
			model.addAttribute("book", book);
			return "orders";
		} else if (query.equals("user")) {
			Person person = personService.getByIdWithOrders(id);
			Set<Orders> orders = person.getOrders();
			model.addAttribute("orders", orders);
			model.addAttribute("person", person);
			return "orders";
		}
		return "404";
	}

	@RequestMapping(value = "/orders/delete{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteOrder(@PathVariable Integer id) {
		orderService.deleteOrder(id);
		return "order";
	}

	@RequestMapping(value = "/orders/issue{id}/{days}", method = RequestMethod.GET)
	@ResponseBody
	public String issueOrder(@PathVariable int id, @PathVariable int days) {
		booksInUseService.addBooksInUse(days, id);
		return "orders";
	}

}
