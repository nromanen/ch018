/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.DAO.OrdersDAO;
import com.ch018.library.entity.Orders;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author okryvortc
 */

@Service
public class OrdersServiceImpl implements OrdersService{

    @Autowired
    OrdersDAO ordDAO;
    public void addOrder(Orders ord) {
       ordDAO.addOrder(ord); 
    }

    public void deleteOrder(Orders ord) {
        ordDAO.deleteOrder(ord);
    }

    public Collection getOrdersByBooksId(int id) {
        return ordDAO.getOrdersByBooksId(id);
    }

    public Collection getOrdersByPersonId(int id) {
        return ordDAO.getOrdersByPersonId(id);
    }

    public Collection getAllOrders() {
        return ordDAO.getAllOrders();
    }
    
}
