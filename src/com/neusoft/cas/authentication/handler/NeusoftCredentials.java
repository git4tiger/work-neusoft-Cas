package com.neusoft.cas.authentication.handler;

import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

public class NeusoftCredentials extends UsernamePasswordCredentials
{
  private static final long serialVersionUID = -8343864967200862794L;
  private String checkCode;
  private String captchaId;

  public final String getCheckCode()
  {
    return this.checkCode;
  }

  public final void setCheckCode(String checkCode)
  {
    this.checkCode = checkCode;
  }

  public boolean equals(Object obj) {
    if ((obj == null) || (!obj.getClass().equals(getClass()))) {
      return false;
    }
    NeusoftCredentials c = (NeusoftCredentials)obj;

    return (getUsername().equals(c.getUsername())) && 
      (getPassword().equals(c.getPassword())) && 
      (this.checkCode.equals(c.getCheckCode()));
  }

  public int hashCode() {
    return this.checkCode.hashCode() ^ getUsername().hashCode() ^ 
      getPassword().hashCode();
  }

  public String getCaptchaId() {
    return this.captchaId;
  }

  public void setCaptchaId(String captchaId) {
    this.captchaId = captchaId;
  }
}