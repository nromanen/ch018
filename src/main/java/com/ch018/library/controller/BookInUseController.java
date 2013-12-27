/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.service.BooksInUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author okryvortc
 */

@Controller
public class BookInUseController {
    
    @Autowired
    BooksInUseService inUse;
    
    @RequestMapping(value = "/usersBooks")
    public ModelAndView showMyBooks(){
        return new ModelAndView("usersBooks","books",inUse.getByBookId(1));
    }
    
}
