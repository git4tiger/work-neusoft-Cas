package com.neusoft.service;
/**
 * version 20160401001
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.StringUtils;

import com.neusoft.DBUtil;
import com.neusoft.service.bean.SmsLogBean;
import com.neusoft.service.bean.UserAccountBean;
import com.neusoft.unieap.config.OrgnizationConfig;
import com.neusoft.unieap.service.orgnization.util.CrytogramUtil;


public class UserServiceImpl implements UserService{
	

	public UserServiceImpl(HttpServletRequest request) {
		super();
		DBUtil.setURL(request);
	}

	/**
	 * 查询用户信息
	 */
	@Override
	public String getAccountInfo(String account) {
		String result = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT ID,ACCOUNT,NAME,PASSWORD,MOBILE_TELEPHONE,LAST_UPDATE_DATE FROM UP_ORG_USER_SSO WHERE ACCOUNT=?");
		ResultSet rs = null;
		PreparedStatement pstm = null;
		Connection con = DBUtil.getConnection();
		try {
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, account);
			rs = pstm.executeQuery();
			UserAccountBean bean= new UserAccountBean();
			while(rs.next()){
				bean.setId(rs.getString("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setCardid(rs.getString("ACCOUNT"));
				bean.setPhone(rs.getString("MOBILE_TELEPHONE"));
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(rs.getString("LAST_UPDATE_DATE"));
				bean.setPassword(rs.getString("PASSWORD"));
				bean.setTimestamp(sdf1.format(date));
			}
			if(!StringUtils.isEmpty(bean.getId())){
				JSONObject json = JSONObject.fromObject(bean);
				result = json.toString();  
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, rs);
		}
		return result;
	}

	/**
	 * 用户注册
	 */
	@Override
	public boolean doRegiste(UserAccountBean account) {
		boolean isSuccess = false;
		Connection con = DBUtil.getConnection();
		PreparedStatement pstm = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("INSERT INTO UP_ORG_USER_SSO(ID,ACCOUNT,NAME,PASSWORD,ACCOUNT_ENABLED,ACCOUNT_LOCKED,MOBILE_TELEPHONE,LAST_UPDATE_DATE,TYPE) VALUES (?,?,?,?,'T','F',?,SYSDATE,'SSOUSER')");
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1,account.getId());
			pstm.setString(2,account.getCardid());
			pstm.setString(3,account.getName());
			pstm.setString(4, account.getPassword());
			pstm.setString(5, account.getPhone());
			pstm.executeUpdate();
			isSuccess = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, null);
		}
		
		return isSuccess;
	}

	/**
	 * 查询用户信息
	 */
	@Override
	public UserAccountBean getOneAccountInfo(String account) {
		UserAccountBean bean=null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT ID,ACCOUNT,NAME,MOBILE_TELEPHONE,PASSWORD,LAST_UPDATE_DATE FROM UP_ORG_USER_SSO WHERE ACCOUNT=?");
		ResultSet rs = null;
		PreparedStatement pstm = null;
		Connection con = DBUtil.getConnection();
		try {
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, account);
			rs = pstm.executeQuery();
			while(rs.next()){
				bean =  new UserAccountBean();
				bean.setId(rs.getString("ID"));
				bean.setName(rs.getString("NAME"));
				bean.setCardid(rs.getString("ACCOUNT"));
				bean.setPhone(rs.getString("MOBILE_TELEPHONE"));
				bean.setTimestamp(rs.getString("LAST_UPDATE_DATE"));
				bean.setPassword(rs.getString("PASSWORD"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, rs);
		}
		return bean;
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public boolean modifyAccount(UserAccountBean account) {
		Connection con = DBUtil.getConnection();
		boolean isSuccess = false;
		StringBuffer buffer = new StringBuffer();
		buffer.append("UPDATE UP_ORG_USER_SSO SET MOBILE_TELEPHONE=?,LAST_UPDATE_DATE = sysdate WHERE ID=? AND ACCOUNT = ?");
		PreparedStatement pstm = null;
		try {
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, account.getPhone());
			pstm.setString(2, account.getId());
			pstm.setString(3, account.getCardid());
			pstm.executeUpdate();
			pstm.close();
			con.close();
			isSuccess = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, null);
		}
		return isSuccess;
	}

	/**
	 * 手机号是否存在
	 */
	@Override
	public boolean checkUserPhone(String phone) {
		Connection con = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		boolean isExist = false;
		if(!StringUtils.isEmpty(phone)){
			try {
				
				StringBuffer buffer = new StringBuffer();
				buffer.append("SELECT COUNT(ID) AS NUM FROM UP_ORG_USER_SSO WHERE MOBILE_TELEPHONE=?");
				pstm = con.prepareStatement(buffer.toString());
				pstm.setString(1, phone);
				rs = pstm.executeQuery();
				while(rs.next()){
					int num = rs.getInt(1);
					if(num>0){
						isExist = true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(con, pstm, rs);
			}
		}
		return isExist;
	}

	/**
	 * 重置密码
	 */
	@Override
	public String resetPassword(String account, String phone,String password) {
		String message = ""; 
		Connection con = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT COUNT(ID) AS NUM FROM UP_ORG_USER_SSO WHERE ACCOUNT=? AND MOBILE_TELEPHONE=?");
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, account);
			pstm.setString(2, phone);
			rs = pstm.executeQuery();
			while(rs.next()){
				int num = rs.getInt(1);
				if(num==1){
					//重置密码
					boolean isSuccess = doResetPassword(account,phone,password);
					if(isSuccess){
						message = "密码设置成功";
					}else{
						message = "密码设置失败，请稍后再试";
					}
				}else if(num>1){
					message = "系统存在多条记录";
				}else{
					message = "根据此身份证号及手机号码系统查不到相关信息";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, rs);
		}
		
		return message;
	}
	
	/**
	 * 重置密码
	 * @param account
	 * @param phone
	 * @return
	 */
	public boolean doResetPassword(String account,String phone,String password){
		boolean isSuccess = false;
		Connection con = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("UPDATE UP_ORG_USER_SSO SET PASSWORD = ?,LAST_UPDATE_DATE = sysdate WHERE ACCOUNT = ? AND MOBILE_TELEPHONE = ?");
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, CrytogramUtil.encrypt(password, OrgnizationConfig.CRYPTOGRAM_ALGORITHM));
			pstm.setString(2, account);
			pstm.setString(3, phone);
			pstm.executeUpdate();
			isSuccess = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, rs);
		}
		return isSuccess;
	}

	/**
	 * 校验身份证号及手机是否存在
	 */
	@Override
	public boolean checkAccountAndPhone(String account, String phone) {
		boolean isExist = false;
		Connection con = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT COUNT(ID) AS NUM FROM UP_ORG_USER_SSO WHERE ACCOUNT=? AND MOBILE_TELEPHONE=?");
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, account);
			pstm.setString(2, phone);
			rs = pstm.executeQuery();
			while(rs.next()){
			   int num = rs.getInt(1);
			   if(num==1){
				   isExist = true;
			   }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, rs);
		}
		
		return isExist;
	}

	/**
	 * 发送短信日志
	 */
	@Override
	public void recordSmsLog(SmsLogBean log) {
		Connection con = DBUtil.getConnection();
		PreparedStatement pstm = null;
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("INSERT INTO SMS_LOG VALUES (?,?,?,SYSDATE,?,?,?,?,?)");
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, log.getID());
			pstm.setString(2, log.getPhone());
			pstm.setString(3, log.getContent());
			//pstm.setTimestamp(4, log.getSenddate());
			pstm.setLong(4, log.getTimestamp());
			pstm.setString(5, log.getIp_addr());
			pstm.setString(6, log.getSend_type());
			pstm.setString(7, log.getRemark());
			pstm.setString(8, log.getCode());
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, null);
		}
		
	}

	/**
	 * 短信校验
	 * 同一个IP或电话，1分钟内发超过10条，定位过于频繁
	 */
	@Override
	public String checkPhoneAndIP(String ip, String phone) {
		String message = "1";
		Connection con = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT COUNT(ID) AS NUM FROM SMS_LOG WHERE SENDDATE >= (sysdate - 1/24/60) AND  (PHONE=? OR IP_ADDR=?)");
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, phone);
			pstm.setString(2, ip);
			rs = pstm.executeQuery();
			while(rs.next()){
			   int num = rs.getInt(1);
			   if(num>10){ 
				   message = "系统检测您发短信过于频繁，请稍后再试";
			   }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, rs);
		}
		
		return message;
	}

	/**
	 * 校验验证码
	 */
	@Override
	public String checkCode(String timestamp,String phone) {
		String code = "";
		Connection con = DBUtil.getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT CODE FROM sms_log WHERE timestmap=? AND phone=? AND SENDDATE >= (sysdate - 5/24/60)"); //查询是否在五分钟内
			pstm = con.prepareStatement(buffer.toString());
			pstm.setString(1, timestamp);
			pstm.setString(2, phone);
			rs = pstm.executeQuery();
			while(rs.next()){
				code = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, pstm, rs);
		}
		
		return code;
	}

//	/**
//	 * 修改密码
//	 */
//	@Override
//	public boolean changePassword(UserAccountBean account) {
//		boolean isSuccess = false;
//		Connection con = DBUtil.getConnection();
//		ResultSet rs = null;
//		PreparedStatement pstm = null;
//		try {
//			StringBuffer buffer = new StringBuffer();
//			buffer.append("UPDATE UP_ORG_USER_SSO SET PASSWORD = ?,LAST_UPDATE_DATE = sysdate WHERE ACCOUNT = ? AND MOBILE_TELEPHONE = ?");
//			pstm = con.prepareStatement(buffer.toString());
//			pstm.setString(1, account.getPassword());
//			pstm.setString(2, account.getCardid());
//			pstm.setString(3, account.getPhone());
//			pstm.executeUpdate();
//			isSuccess = true;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			DBUtil.closeAll(con, pstm, rs);
//		}
//		return isSuccess;
//	}
}
