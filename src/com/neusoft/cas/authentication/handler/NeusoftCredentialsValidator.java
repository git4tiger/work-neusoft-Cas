package com.neusoft.cas.authentication.handler;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public final class NeusoftCredentialsValidator
  implements Validator
{
  public boolean supports(Class clazz)
  {
    return NeusoftCredentials.class.isAssignableFrom(clazz);
  }

  public void validate(Object o, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", 
      "required.username");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", 
      "required.password");
  }
}