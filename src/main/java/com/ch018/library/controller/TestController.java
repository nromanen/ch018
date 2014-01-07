package com.ch018.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class TestController {
	
	@Controller
	public class HomeController {
		@RequestMapping(value = "/home", method = RequestMethod.GET)
		public String home() {
			return "tilestest/home";
		}
		
	}
}
