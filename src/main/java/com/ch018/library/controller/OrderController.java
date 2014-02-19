package com.ch018.library.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set; /*111111*/

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
import com.ch018.library.util.OrderTerm;
import com.ch018.library.util.UserOrderTermCalculate;
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
        //List<Orders> orders = new ArrayList<Orders>();
        int term = 14;
        int available = b.getAvailable();
        if (available == 0) {
            Date date = booksInUseService.getMinByReturnDate(bookId);
            model.addAttribute("date", date);
        }
        if (available == 1) {
            Date date= orderService.minOrderDateOf(bookId);
            if(date == null){
            	model.addAttribute("term", term);
               } else {
                date.setDate(date.getDate() - 1);
                model.addAttribute("orderDate", date.toString());
            }
        }
        if (available > 1) {
        	//orderService.getAllOrdersAfter(newOrder.getIssueDate());
        //	 if(available > orders.size()){
        	//	 model.addAttribute("term", term);
        	// }
        	 //if(available == orders.size()){
        	//	 Date date1 = 
        	 
            	model.addAttribute("term", term);
        }
        newOrder.setPerson(p);
        newOrder.setBook(b);
        model.addAttribute("order", newOrder);
        return "order";
   }
    
    @Secured({"ROLE_USER", "ROLE_LIBRARIAN" })
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public void fixAndSaveOrder(@ModelAttribute("order") Orders newOrder, 
                              BindingResult result, Model model) {
    	boolean aprovedOrder = false;
      	int bookId = newOrder.getBook().getId();
      	Book book = bookService.getBooksById(bookId);
        int personId = newOrder.getPerson().getId();
        int available = book.getAvailable();
        List<Orders> orders = new ArrayList<Orders>();
    	orders = orderService.getAllOrdersAfter(newOrder.getIssueDate(), bookId);
        if(available > orders.size()) {
           aprovedOrder=true;
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
            // if (orders.get(0).getIssueDate().after(expectedReturnDate))  {
            	// aprovedOrder = true; 
             //} else {
            	//     Map<Date, Date> resultResponse = new HashMap <Date,Date>();
            for(int i = 0; i<orders.size(); i++) {
                 if((orders.get(i).getIssueDate().after(expectedReturnDate)) && (!orders.get(i).getPreOrder())){
            	 aprovedOrder = true;
            	 Orders order = orders.get(i);
            	 order.setPreOrder(true);
            	 orderService.updateOrder(order);
            	 break;
            	     }
            }
                
        }
        if(aprovedOrder) {
        	newOrder.setBook(bookService.getBooksById(bookId));
        	newOrder.setPerson(personService.getById(personId));
        	newOrder.setDate(Calendar.getInstance().getTime());
        	orderService.addOrder(newOrder);
        }
    	//return null;
        //return orderService.createOrder(bookId, personId, newOrder);
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
		booksInUseService.addBooksInUse(days, id);
		return "orders";
	}

}
