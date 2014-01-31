package com.ch018.library.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ch018.library.entity.Orders;
import com.ch018.library.service.PersonService;

@Component("orderValidator")
public class OrderValidator implements Validator {

	@Autowired
	private PersonService personService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Orders.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Orders order = (Orders) target;
			if (order.getIssueDate().before(new java.util.Date())) {
				errors.rejectValue("issueDate", "date.expired");
			} 
		
	}

}
