/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.WishList;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.WishListService;


@Controller
public class WishListController {
    
    @Autowired
    WishListService wish;
    OrdersService order;
    @RequestMapping(value="/wishList")
    public ModelAndView wishlist(){
       return new ModelAndView("wishList","wish",wish.getAllWishes());
    }
    
    
}
