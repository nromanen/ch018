package com.ch018.library.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.History;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookService;
import com.ch018.library.service.BooksInUseService;
import com.ch018.library.service.HistoryService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
import com.ch018.library.util.JsonResponse;
import com.ch018.library.util.OrderTerm;
import com.ch018.library.validator.OrderValidator;

/**
 * 
 * @author okryvortc
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
    
    @Autowired
    private HistoryService historyService;
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/fail")
    public void fail(Model model) {
    	
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/prepareorder", method = RequestMethod.GET)
    @ResponseBody
    public int prepareOrder(Model model, 
                            @RequestParam("book") int bookId, 
                            Principal principal) {
    	Person pers = personService.getByEmail(principal.getName());
    	return orderService.prepareOrder(bookId, pers);
    }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(Model model, 
            @RequestParam("book") int bookId, 
                      Principal principal) {
    	Person p = personService.getByEmail(principal.getName());
        Orders newOrder = new Orders();
        Book b = bookService.getBooksById(bookId);
        int available = b.getAvailable();
        if (available == 0) {
            Date date = booksInUseService.getMinByReturnDate(bookId);
            model.addAttribute("date", date);
        }
        newOrder.setPerson(p);
        newOrder.setBook(b);
        model.addAttribute("order", newOrder);
        return "order";
   }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse fixAndSaveOrder(@ModelAttribute("order") Orders newOrder, 
                              BindingResult result, Model model) {
    
    	JsonResponse response = new JsonResponse();
    	boolean aprovedOrder = false;
      	int bookId = newOrder.getBook().getId();
      	Book book = bookService.getBooksById(bookId);
        int personId = newOrder.getPerson().getId();
    	if(orderService.orderExist(personId, bookId)) {
    		response.setStatus("ORDERED");
    		return response;
    	}
        int available = book.getAvailable();
        if (available == 1) {
        	Date date= orderService.minOrderDateOf(bookId);
        	if(date == null) {
        		orderService.createOrder(bookId, personId, newOrder);
        	    response.setStatus("SUCCESS");	
        		return response;
        	} 
        }
        List<Orders> orders = new ArrayList<Orders>();
    	//orders = orderService.getAllOrdersAfter(newOrder.getIssueDate(), bookId);
        orders = orderService.getAllOrdersAfter(Calendar.getInstance().getTime(), bookId);
        if(available > orders.size()) {
           aprovedOrder=true;
           orderService.createOrder(bookId, personId, newOrder);
           response.setStatus("SUCCESS");
           return response;
        }
        if(available <= orders.size()) {
            Calendar c = Calendar.getInstance();
            Calendar d = Calendar.getInstance();
            c.setTime(newOrder.getIssueDate());
            d.setTime(orders.get(0).getIssueDate());
            c.set(Calendar.MILLISECOND, 0);
            c.set(Calendar.SECOND, 0);
            c.add(Calendar.DATE, newOrder.getTerm());
            Date expectedReturnDate = c.getTime();
            for(int i = 0; i<orders.size(); i++) {
				if ((orders.get(i).getIssueDate().after(expectedReturnDate))
						&& (!orders.get(i).getPreOrder())) {
					aprovedOrder = true;
					Orders order = orders.get(i);
					order.setPreOrder(true);
					orderService.updateOrder(order);
					break;
				}
            }
        }
        if (aprovedOrder) {
        	orderService.createOrder(bookId, personId, newOrder);
        	response.setStatus("SUCCESS");
            return response;
        } else {
        	SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
        	Calendar firstReturnDate = Calendar.getInstance();
        	firstReturnDate.setTime(orders.get(0).getIssueDate());
        	firstReturnDate.add(Calendar.DATE, orders.get(0).getTerm());
        	if (available == 1 && orders!=null) {
        		response.setResult(fmt.format(firstReturnDate.getTime()));
        	}
        	response.setStatus("FAIL");
        	return response;
        }
    }
    
    @RequestMapping(value = "/userOrder", method = RequestMethod.GET)
    public Model showOrder(Principal principal, @ModelAttribute("editIssue") Orders newIssue, Model model) {
    	model.addAttribute("showOrders", orderService.getOrdersByPersonId(personService.getByEmail(principal.getName()).getId()));
    	return model;
    }
    
    @RequestMapping(value = "/userOrder", method = RequestMethod.POST)
    @ResponseBody
	public int editIssueDate(@RequestParam("issueDate") String issueDate,
			                 @RequestParam("id") Integer id, 
			                 Model model,
			                 Principal principal) throws ParseException {
    	 return orderService.updateissueDate(id, issueDate);
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
			int orderTerm = OrderTerm.calculate(book);
			model.addAttribute("maxterm", orderTerm);
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
		Orders orders = orderService.getById(id);
		booksInUseService.addBooksInUse(days, orders);
		History history = new History();
		history.setBook(orders.getBook());
		history.setPerson(orders.getPerson());
		history.setIssueDate(new Date());
		historyService.newEntry(history);
		return "orders";
	}

}
