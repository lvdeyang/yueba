package com.smartres.bussiness.admin.po;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_userinfo")
public class UserInfoPO extends AbstractBasePO {
	
	//用户手机号
    private String userPhone;
    
    //用户密码
    private String userPassword;
    
    //openID
    private String userOpenID;
    //积分数
    private String userIntegral;
    //用户头像
    private String userHeadimg;
    //昵称
    private String  userNickname;
   
    private long merchantId;
	
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserOpenID() {
		return userOpenID;
	}
	public void setUserOpenID(String userOpenID) {
		this.userOpenID = userOpenID;
	}
	public String getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(String userIntegral) {
		this.userIntegral = userIntegral;
	}
	public String getUserHeadimg() {
		return userHeadimg;
	}
	public void setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
}
	