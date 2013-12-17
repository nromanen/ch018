/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.entity.Orders;
import java.util.Collection;

/**
 *
 * @author okryvortc
 */
public interface OrdersService {
    public void addOrder(Orders ord);
    public void deleteOrder(Orders ord);
    public Collection getOrdersByBooksId(int id);
    public Collection getOrdersByPersonId(int id);
    public Collection getAllOrders();    
}