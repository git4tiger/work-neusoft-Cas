package com.neusoft.service;
/**
 * version 20160401001
 */
import com.neusoft.service.bean.SmsLogBean;
import com.neusoft.service.bean.UserAccountBean;

public interface UserService {
	/**
	 * 获取用户信息
	 * @param account
	 * @return
	 */
	public String getAccountInfo(String account);
	
	/**
	 * 注册
	 * @param account
	 * @return
	 */
	public boolean doRegiste(UserAccountBean account);
	
	/**
	 * 查询用户信息--修改
	 * @return
	 */
	public UserAccountBean getOneAccountInfo(String account);
	
	/**
	 * 修改用户信息
	 * @param account
	 * @return
	 */
	public boolean modifyAccount(UserAccountBean account);
	
	/**
	 * 手机号是否存在
	 * @param phone
	 * @return
	 */
	public boolean checkUserPhone(String phone);
	
	/**
	 * 重置密码
	 * @param phone
	 * @return
	 */
	public String resetPassword(String account,String phone,String password);
	
	/**
	 *  检查身份证号及手机号是否存在
	 * @param account
	 * @return
	 */
	public boolean checkAccountAndPhone(String account,String phone);
	
	/**
	 * 插入短信记录日志
	 * @param log
	 */
	public void recordSmsLog(SmsLogBean log);
	
	/**
	 * 发短信校验
	 * @param ip
	 * @param phone
	 * @return
	 */
	public String checkPhoneAndIP(String ip,String phone);
	
	/**
	 * 校验验证码
	 * @param phone
	 * @param type
	 * @return
	 */
	public String checkCode(String timestamp,String phone);
	
//	/**
//	 * 修改密码
//	 * @param account
//	 */
//	public boolean changePassword(UserAccountBean account);

}
