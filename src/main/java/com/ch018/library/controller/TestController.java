package com.ch018.library.controller;

import com.ch018.library.dao.PersonDao;
import com.ch018.library.dao.RatingDao;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Rating;
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
	
        @Autowired
        RatingDao pdao;
    
	private int visitorCount = 0;
	
	@RequestMapping("/")
	public String index(Model model) {
                Person p = new Person("johny@gmail.com");
                Rating rate = new Rating();
                rate.setPerson(p);
                rate.setGeneralRating(5F);
                pdao.save(rate);
                List<Rating> ratings = pdao.getAll();
                System.out.println(ratings);
                model.addAttribute("result", ratings);
		return "index";
	}

}
