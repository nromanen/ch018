/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="orders")
public class Orders {
    
    private int id;
    private int idBooks;
    private int idPerson;
    private Date date;
    
    public Orders(){
        
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    public int getId(){
        return this.id;
    }
    
    @Column(name="Books_id")
    public int getIdBooks(){
        return this.idBooks;
    }
    
    @Column(name="Person_id")
    public int getIdPerson(){
        return this.idPerson;
    }
    
    @Column(name="order_date")
    public Date getOrderDate(){
        return this.date;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public void setIdBooks(int idBook){
        this.idBooks=idBook;
    }
    
    public void setIdPerson(int idPerson){
        this.idPerson=idPerson;
    }
    
    public void setOrderDate(Date date){
        this.date=date;
    }
    
    @Override
    public String toString(){
        return getId()+" "+getIdBooks()+" "+getIdPerson()+" "+getOrderDate();
    }
    
    @Override
    public boolean equals(Object obj){
     if (this == obj) 
         return true;
     if (obj instanceof Orders){
               if ( ((Orders)obj).getIdBooks()==this.getIdBooks() &&
                   ((Orders)obj).getIdPerson()==this.getIdPerson() && 
                   ((Orders)obj).getOrderDate()== this.getOrderDate()) {  return true;
                                                                         }
               else {
                     return false;
                     }
           
           } else {
                    return false;
                   }
    }

}
