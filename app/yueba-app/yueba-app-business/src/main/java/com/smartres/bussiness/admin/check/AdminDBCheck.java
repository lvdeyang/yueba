package com.smartres.bussiness.admin.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartres.bussiness.admin.dao.AdminDAO;
import com.smartres.bussiness.admin.exception.AdminNotFoundException;
import com.smartres.bussiness.admin.po.AdminPO;

@Service("com.smartres.bussiness.admin.check.AdminDBCheck")
public class AdminDBCheck {
	//Mr.Sun-1
	@Autowired
	private AdminDAO conn_admin;
	
	
//Mr.Sun-2
	//管理员是否存在
	public AdminPO adminExist(Long adminId) throws Exception{
		AdminPO admin = conn_admin.get(adminId);
		if(admin == null){
			throw new AdminNotFoundException(adminId);
		}
		return admin;
	}
	
	//管理员是否存在--重载
	public AdminPO adminExist(String adminId) throws Exception{
		return adminExist(Long.valueOf(adminId));
	}

	
	
}
