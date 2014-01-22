/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.validator;

import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author okryvortc
 */
@Component("accountValidator")
public class AccountValidation implements Validator {

    @Autowired
    PersonService personService;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.name", "name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required.surname", "surname is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellphone", "required.cellphone", "cellphone must contain only numbers");
       
    }
    
}
