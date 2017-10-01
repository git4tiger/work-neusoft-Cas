package com.neusoft.cas.authentication.handler;

import com.neusoft.unieap.config.OrgnizationConfig;
import com.neusoft.unieap.service.orgnization.util.CrytogramUtil;
import com.neusoft.unieap.service.security.ui.jcaptcha.EAPCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.BadUsernameOrPasswordAuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public final class NeusoftAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler
{
  private String sql = null;

  public boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials) throws AuthenticationException
  {
    boolean ret = true;
    String username = credentials.getUsername();
    String password = credentials.getPassword();

    boolean checkCodeBoolean = true;
    boolean checkUserAndPassword = checkuser(username, password);

    if (checkUserAndPassword) {
      credentials.setUsername(username + "_" + password);
    }
    if (!checkCodeBoolean) {
      ret = false;
      throw new BadUsernameOrPasswordAuthenticationException();
    }if (!checkUserAndPassword) {
      ret = false;
      throw new BadUsernameOrPasswordAuthenticationException();
    }
    return ret;
  }

  private boolean checkuser(String username, String password)
  {
    try {
      if ((password != null) && (!"".equals(password)))
      {
        String truePassword = (String)getJdbcTemplate().queryForObject(this.sql, String.class, new Object[] { username });
        String inPassword = CrytogramUtil.encrypt(password, OrgnizationConfig.CRYPTOGRAM_ALGORITHM);
        if ((truePassword != null) && (truePassword.equals(inPassword))) {
          return true;
        }

      }

      return false; } catch (IncorrectResultSizeDataAccessException e) {
    }
    return false;
  }

  private boolean checkCode(String captchaId, String checkCode)
  {
    boolean isResponseCorrect = EAPCaptchaService.getInstance().validateResponseForID(captchaId, checkCode).booleanValue();
    return isResponseCorrect;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public static String DecodePasswd(String varCode) {
    String des = new String();
    String strKey = new String();
    if ((varCode == null) || (varCode.length() == 0))
    {
      return "";
    }
    strKey = "zxcvbnm,./asdfghjkl;'qwertyuiop[]\\1234567890-=` ZXCVBNM<>?:LKJHGFDSAQWERTYUIOP{}|+_)(*&^%$#@!~";
    if (varCode.length() % 2 == 1)
    {
      varCode = varCode + "?";
    }
    des = "";
    for (int n = 0; n <= varCode.length() / 2 - 1; n++)
    {
      char b = varCode.charAt(n * 2);

      int a = strKey.indexOf(varCode.charAt(n * 2 + 1));
      des = des + (char)(b ^ a);
    }
    int n = des.indexOf(1);
    if (n > 0)
    {
      return des.substring(0, n);
    }

    return des;
  }
  public static String replace(String inputstr, String oldsub, String newsub) {
    if (inputstr == null) {
      return null;
    }
    if ((oldsub == null) || (oldsub.equals(""))) {
      return inputstr;
    }
    if (newsub == null) {
      return inputstr;
    }
    String outputstr = "";
    int oldlen = oldsub.length();
    while (true) {
      int position = inputstr.indexOf(oldsub);
      if (position == -1) break;
      outputstr = outputstr + inputstr.substring(0, position) + newsub;
      inputstr = inputstr.substring(position + oldlen);
    }
    outputstr = outputstr + inputstr;
    return outputstr;
  }
}