package com.smartres.app.web.admin.vo;

import java.text.SimpleDateFormat;

import com.smartres.bussiness.admin.po.AdminPO;
import com.smartres.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class UserInfoVO extends AbstractBaseVO<UserInfoVO,UserInfoPO>{
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
	public String getUserPhone() {
		return userPhone;
	}
	public UserInfoVO setUserPhone(String userPhone) {
		this.userPhone = userPhone;
		return this;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public UserInfoVO setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}
	public String getUserOpenID() {
		return userOpenID;
	}
	public UserInfoVO setUserOpenID(String userOpenID) {
		this.userOpenID = userOpenID;
		return this;
	}
	public String getUserIntegral() {
		return userIntegral;
	}
	public UserInfoVO setUserIntegral(String userIntegral) {
		this.userIntegral = userIntegral;
		return this;
	}
	public String getUserHeadimg() {
		return userHeadimg;
	}
	public UserInfoVO setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
		return this;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public UserInfoVO setUserNickname(String userNickname) {
		this.userNickname = userNickname;
		return this;
	}
	
	
	@Override
	public UserInfoVO set(UserInfoPO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUserPhone(entity.getUserPhone())
			.setUserPassword(entity.getUserPassword())
			.setUserOpenID(entity.getUserOpenID())
			.setUserIntegral(entity.getUserIntegral())
			.setUserHeadimg(entity.getUserHeadimg())
			.setUserNickname(entity.getUserNickname());
		
			
			
		return this;
	}
}
