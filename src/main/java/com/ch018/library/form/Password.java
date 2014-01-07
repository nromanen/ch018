/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.form;


public class Password {
       
     private String password;
     private String confirmPassword;
     private String newPassword;
     
     public Password(){
         
     }
     
     public String getPassword(){
         return this.password;
     }
     
     public String getConfirmPassword(){
         return this.confirmPassword;
     }
     
     public String getNewPassword(){
         return this.newPassword;
     }
     
     public void setPassword(String pass){
         this.password=pass;
     }
     
     public void setConfirmPassword(String pass){
         this.confirmPassword=pass;
     }
     
     public void setNewPassword(String pass){
         this.newPassword=pass;
     }
}
