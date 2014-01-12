package com.ch018.library.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ch018.library.form.Registration;
import com.ch018.library.service.PersonService;

@Component("registrationValidator")
public class RegistrationValidation implements Validator {
	
	@Autowired
	private PersonService personService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Registration.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Field password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"required.confirmPassword", "Field password is required.");
		Registration registration = (Registration) target;

		if (!(registration.getPassword().equals(registration
				.getConfirmPassword()))) {
			errors.rejectValue("password", "notmatch.password");
		}

		if (personService.isExist(registration.getEmail()) > 0) {
			errors.rejectValue("email", "exist.email");
		}

	}

}
