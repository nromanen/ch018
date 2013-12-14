package com.ch018.library.controller;

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
	
	@RequestMapping("/index.html")
	public String index(Model model) {
		model.addAttribute("visitorCount", visitorCount++);
		return "view/index.jsp";
	}

}
