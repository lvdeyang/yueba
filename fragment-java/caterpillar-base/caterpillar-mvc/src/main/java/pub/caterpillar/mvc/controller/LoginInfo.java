package pub.caterpillar.mvc.controller;

import java.util.Date;

public class LoginInfo {
	// 管理员名称
	private String loginName;

	// 角色关联id
	private int roleId;

	// 城市代码
	private String cityCode;

	// 城市名
	private String cityName;
	
	//类型0管理员，1商户
	private int type;
	
	private long loginId;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}
	
	
}
