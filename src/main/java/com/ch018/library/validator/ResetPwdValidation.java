package com.ch018.library.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ch018.library.form.ResetPassword;
import com.ch018.library.service.PersonService;

@Component("resetValidator")
public class ResetPwdValidation implements Validator {
	
	@Autowired
	private PersonService personService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ResetPassword.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"required.password", "Field password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirm",
				"required.confirmPassword", "Field password is required.");
		ResetPassword resetPassword = (ResetPassword) target;

		if (!(resetPassword.getPassword().equals(resetPassword
				.getConfirm()))) {
			errors.rejectValue("password", "notmatch.password");
		}

		if (personService.getByKey(resetPassword.getKey()) == null) {
			errors.rejectValue("kay", "key.notfound");
		}

	}

}
