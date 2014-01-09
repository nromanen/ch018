package com.ch018.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
	
	
	@RequestMapping("/home")    
    public String home() {    
     System.out.println("INSIDE HOME");  
         return "home";    
    }    
      
    @RequestMapping("/page")    
    public String page(@RequestParam(value="pageNo") String pageNo,HttpServletRequest request) {  
     System.out.println("PageNo: " + pageNo);  
     request.setAttribute("pageNo", pageNo);  
         return "page";    
    }    

}
