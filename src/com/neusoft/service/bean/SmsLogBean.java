package com.neusoft.service.bean;
/**
 * version 20150817001
 */
import java.sql.Timestamp;



public class SmsLogBean {
	
	private String ID;
	private String phone;
	private String content;
	private Timestamp senddate;
	private Long timestamp;
	private String ip_addr;
	private String send_type;
	private String remark;
	private String code;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getSenddate() {
		return senddate;
	}
	public void setSenddate(Timestamp senddate) {
		this.senddate = senddate;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ipAddr) {
		ip_addr = ipAddr;
	}
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String sendType) {
		send_type = sendType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
