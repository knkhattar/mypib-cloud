package com.kkcom.mypib.login.pres.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.kkcom.mypib.login.svc.model.Login;

public class LoginValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Login.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"password.required");
	}

}
