package com.smartres.app.web.admin.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.smartres.bussiness.admin.po.AdminPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class AdminVO extends AbstractBaseVO<AdminVO, AdminPO> {

	// 管理员名称
	private String adminName;
	
	// 管理员密码
	private String password;
	
	// 创建日期   
	private String createdDate;
	
	// 角色关联id
	private int roleId;
	
	// 城市代码
	private String cityCode;
	
	// 城市名
	private String cityName;
		
	public String getCityCode() {
		return cityCode;
	}

	public AdminVO setCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}

	public String getCityName() {
		return cityName;
	}

	public AdminVO setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}

	public String getAdminName() {
		return adminName;
	}

	public AdminVO setAdminName(String adminName) {
		this.adminName = adminName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public AdminVO setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public AdminVO setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public int getRoleId() {
		return roleId;
	}

	public AdminVO setRoleId(int roleId) {
		this.roleId = roleId;
		return this;
	}


	@Override
	public AdminVO set(AdminPO entity) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
			.setUuid(entity.getUuid())
			.setUpdateTime(DateUtil.format(entity.getUpdateTime(), DateUtil.dateTimePattern))
			.setAdminName(entity.getAdminName())
			.setPassword(entity.getPassword())
			.setCreatedDate(formatter.format( entity.getCreatedDate()==null?new Date():entity.getCreatedDate()))
			.setRoleId(entity.getRoleId())
			.setCityCode(entity.getCityCode())
			.setCityName(entity.getCityName());
		return this;
	}
	
}
