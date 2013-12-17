package com.ch018.library.controller;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Yurik Mikhaletskiy
 * Controller for Spring
 *
 */
@Controller
public class TestController {
	

    
	private int visitorCount = 0;
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("visitorCount", ++visitorCount);
		model.addAttribute("today", new java.util.Date().toString() );

		return "index";
	}

}
