package com.ch018.library.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ch018.library.form.Registration;

@Component
public class RegistrationValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Registration.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "Field name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required.requiredPassword", "Field name is required.");
		Registration registration = (Registration) target;
		
		if (!(registration.getPassword().equals(registration.getConfirmPassword()))) {
			errors.rejectValue("password", "notmath.password");
		}
		
	}

}
