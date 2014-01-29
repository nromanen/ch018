package com.ch018.library.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ch018.library.entity.Person;
import com.ch018.library.form.Registration;
import com.ch018.library.service.PersonService;

@Component("personValidator")
public class PersonValidation implements Validator {
	
	@Autowired
	private PersonService personService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Registration.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Person person = (Person) target;
		if (personService.isExist(person.getEmail()) > 0 && person.getId() == 0) {
			errors.rejectValue("email", "exist.email", "exist.email");
		}
	}

}
