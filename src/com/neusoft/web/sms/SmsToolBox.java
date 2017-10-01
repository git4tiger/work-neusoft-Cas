package com.neusoft.web.sms;

import java.util.Properties;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.tempuri.HySmsServiceSoap;

public class SmsToolBox
{
  private String userName;
  private String userPwd;
  private String clientUrl;

  public SmsToolBox()
  {
    GetPropertiesTool getPropertiesTool = GetPropertiesTool.getGetPropertiesTool();
    Properties prop = getPropertiesTool.getProperties();
    try
    {
      this.userName = prop.getProperty("sms.userName");
      this.userPwd = prop.getProperty("sms.userPwd");
      this.clientUrl = prop.getProperty("sms.clientUrl");
    }
    catch (Exception localException)
    {
    }
  }

  public long sendSmsTask(String telnoStr, String msg)
    throws Exception
  {
    JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
    bean.setAddress(this.clientUrl);
    bean.setServiceClass(HySmsServiceSoap.class);
    HySmsServiceSoap ws = (HySmsServiceSoap)bean.create();
    long re_code = ws.sendSmsTask(this.userName, this.userPwd, msg, telnoStr);
    return re_code;
  }

  public long sendSmsTaskTime(String telnoStr, String msg, String beginTime)
    throws Exception
  {
    JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
    bean.setAddress(this.clientUrl);
    bean.setServiceClass(HySmsServiceSoap.class);
    HySmsServiceSoap ws = (HySmsServiceSoap)bean.create();
    long re_code = ws.sendSmsTaskTime(this.userName, this.userPwd, beginTime, msg, telnoStr);
    return re_code;
  }

  public String getBatchResultNumber(int TaskID)
    throws Exception
  {
    JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
    bean.setAddress(this.clientUrl);
    bean.setServiceClass(HySmsServiceSoap.class);
    HySmsServiceSoap ws = (HySmsServiceSoap)bean.create();
    String re_code = ws.getSmsReport(this.userName, this.userPwd, TaskID);
    return re_code;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPwd() {
    return this.userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }

  public String getClientUrl() {
    return this.clientUrl;
  }

  public void setClientUrl(String clientUrl) {
    this.clientUrl = clientUrl;
  }
}