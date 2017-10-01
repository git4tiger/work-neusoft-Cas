package com.neusoft.cas.authentication.handler;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.CredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class NeusoftCredentialsToPrincipalResolver
  implements CredentialsToPrincipalResolver
{
  private final Log log = LogFactory.getLog(getClass());

  public Principal resolvePrincipal(Credentials credentials) {
    NeusoftCredentials neusoftCredentials = (NeusoftCredentials)credentials;
    if (this.log.isDebugEnabled()) {
      this.log.debug("Creating SimplePrincipal for [" + 
        neusoftCredentials.getUsername() + "]");
    }

    Map attributes = new HashMap();
    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

    String menuContent = "<ul style=\"list-style-type:none;margin:0;padding:0;\"><li><a onclick=\"changeRightIframeSrc('http://127.0.0.1:9003/ecdomain/ssoLogin.do?method=ssologin&menuSrc=siteopesonal.do?method=list')\">\uFFFD\u017B\uFFFD\uFFFD\uFFFD\u057E\uFFFD\uFFFD\uFFFD\uFFFD</a></li><li><a onclick=\"changeRightIframeSrc('http://127.0.0.1:9003/ecdomain/ssoLogin.do?method=ssologin&menuSrc=org_station.do?method=begin')\">\uFFFD\u017B\uFFFD\uFFFD\uFFFD\u03BB\uFFFD\uFFFD\uFFFD\uFFFD</a></li><li><a onclick=\"changeRightIframeSrc('http://127.0.0.1:9003/ecdomain/ssoLogin.do?method=ssologin&menuSrc=org_user.do?method=beginUser')\">\uFFFD\u017B\uFFFD\u04B5\uFFFD\uFFFD\uFFFD\u00FB\uFFFD</a></li><li><a onclick=\"changeRightIframeSrc('http://127.0.0.1:9003/ecdomain/ssoLogin.do?method=ssologin&menuSrc=ecplatform/content.do?parentID=0')\">\uFFFD\uFFFD\u057E\uFFFD\uFFFD\uFFFD\uFFFD</a></li><li><a onclick=\"changeRightIframeSrc('http://127.0.0.1:9003/ecdomain/ssoLogin.do?method=ssologin&menuSrc=sitelist.do?method=list')\">\uFFFD\uFFFD\u057E\uFFFD\uFFFD\u057E\uFFFD\uFFFD\uFFFD\uFFFD</a></li></ul>";

    attributes.put("menu", menuContent);
    return new SimplePrincipal(neusoftCredentials.getUsername(), attributes);
  }

  public boolean supports(Credentials credentials)
  {
    return (credentials != null) && 
      (NeusoftCredentials.class.isAssignableFrom(credentials
      .getClass()));
  }
}