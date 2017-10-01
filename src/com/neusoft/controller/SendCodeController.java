package com.neusoft.controller;
/***
 * version 20150817001
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.service.UserService;
import com.neusoft.service.UserServiceImpl;
import com.neusoft.service.bean.SmsLogBean;
import com.neusoft.web.sms.SmsToolBox;

@SuppressWarnings("serial")
public class SendCodeController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		String phone = request.getParameter("phone");
		String ip_addr = getIpAddr(request);
		UserService service = new UserServiceImpl(request);
		String r = service.checkPhoneAndIP(ip_addr, phone);
		Date date = new Date();
		Long timestamp = date.getTime();
		if(!r.equals("1")){
		}else{
			String type = request.getParameter("type");
			String codetype = "";
			String sendtype = "";
			if (type.equals("1")) {
				codetype = "registeCode";
				sendtype = "REGISTE";
			} else if (type.equals("2")) {
				codetype = "modifyPhoneCode";
				sendtype = "MODIFYACCOUNTINFO";
			} else if (type.equals("3")) {
				codetype = "resetPasswordCode";
				sendtype = "CHANGEPASSWORD";
			}
			int code = (int) (Math.random() * 100000 + 100000);
			//code = 111111;
			request.getSession().setAttribute(codetype, timestamp);
			//System.out.println(codetype + ":" + code);
			// 发短信
			String content = "";
			Long result = 0L;
			try {
				SmsToolBox box = new SmsToolBox();
				content = "验证码：" + code + ",南宁政务系统[工作人员不会向您索取，请勿泄露,5分钟之内有效]";
				//result = box.sendSmsTask(phone, content);
				//System.out.println("手机号" + phone + codetype + "发信息返回结果：" + result);
				System.out.println(content);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 记录日志
			SmsLogBean log = new SmsLogBean();
			log.setID(UUID.randomUUID().toString().replace("-", ""));
			log.setPhone(phone);
			log.setContent(content);
			log.setSenddate(new Timestamp(date.getTime()));
			log.setTimestamp(timestamp);
			log.setIp_addr(getIpAddr(request));
			log.setSend_type(sendtype);
			log.setRemark(result + "");
			log.setCode(code+"");
			service.recordSmsLog(log);
		}
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().write(r);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private String getIpAddr(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0
				|| "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

}
