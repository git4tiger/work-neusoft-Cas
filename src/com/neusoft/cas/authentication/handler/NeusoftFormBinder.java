package com.neusoft.cas.authentication.handler;

import javax.servlet.http.HttpServletRequest;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.web.bind.CredentialsBinder;

public class NeusoftFormBinder
  implements CredentialsBinder
{
  public void bind(HttpServletRequest request, Credentials credentials)
  {
  }

  public boolean supports(Class<?> clazz)
  {
    return (clazz != null) && (
      (clazz.equals(NeusoftCredentials.class)) || 
      (clazz
      .equals(NeusoftCredentials.class)));
  }
}