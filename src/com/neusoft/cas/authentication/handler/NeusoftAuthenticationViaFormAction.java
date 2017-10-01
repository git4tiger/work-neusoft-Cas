package com.neusoft.cas.authentication.handler;

import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.validation.UsernamePasswordCredentialsValidator;
import org.jasig.cas.web.flow.AuthenticationViaFormAction;
import org.springframework.util.Assert;

public class NeusoftAuthenticationViaFormAction extends AuthenticationViaFormAction
{
  protected void initAction()
  {
    if (getFormObjectClass() == null) {
      setFormObjectClass(NeusoftCredentials.class);
      setFormObjectName("credentials");
      setValidator(new UsernamePasswordCredentialsValidator());
    }
    Assert.isTrue(Credentials.class.isAssignableFrom(
      getFormObjectClass()), 
      "CommandClass must be of type Credentials.");
  }
}