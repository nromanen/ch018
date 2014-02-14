/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ch018.library.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ch018.library.form.Password;

/**
 *
 * @author win7
 */

@Component("changePasswordValid")
public class ChangePasswordValid implements Validator {

    @Override
    public boolean supports(Class<?> type) {
        return Password.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
                "password", "required.password", "this filed is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
                "confirmPassword", "required.confirmPassword", "this filed is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, 
                "newPassword", "required.newPassword", "this filed is required");
        Password pass = (Password) o;
        
        if(!pass.getNewPassword().equals(pass.getConfirmPassword()))
            errors.rejectValue("newPassword", "notmatch");
    }
    
}
