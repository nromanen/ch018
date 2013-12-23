/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import com.ch018.library.service.OrdersService;
import com.ch018.library.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author win7
 */
@Controller
public class OrderController {
    
    @Autowired
    OrdersService order;
    
    @Autowired
    WishListService wish;
    
    @RequestMapping(value="/order", method=RequestMethod.GET)
    public ModelAndView createOrder(@RequestParam("id") int id, Model model){
        //model.addAllAttributes(wish.getWishesByPerson(id));
        return new ModelAndView("order", "newOrder", wish.getWishesByPerson(id));
    }
    

}
