package com.smartres.app.web.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.smartres.bussiness.admin.dao.AdminDAO;
import com.smartres.bussiness.admin.dao.MenuDAO;
import com.smartres.bussiness.admin.dao.RoleDAO;
import com.smartres.bussiness.admin.dao.RoleMenuDAO;
import com.smartres.bussiness.admin.dao.SysConfigDAO;
import com.smartres.bussiness.admin.po.AdminPO;
import com.smartres.bussiness.admin.po.MenuPO;
import com.smartres.bussiness.admin.po.RoleMenuPO;
import com.smartres.bussiness.admin.po.RolePO;
import com.smartres.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.commons.context.SpringContext;
import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.init.InitLoader;

public class ContextListener extends InitLoader{
	private AdminDAO adminDAO;
	@Override
	public void customInitialize() {
		adminDAO = SpringContext.getBean("com.smartres.bussiness.admin.dao.AdminDAO");
		if(adminDAO.findAll().isEmpty()){
			AdminPO adminPO=new AdminPO();
			adminPO.setAdminName("admin");
			adminPO.setPassword(Sha1Util.getSha1("admin"));
			adminDAO.save(adminPO);
		}
		
		
		System.out.println("context 初始化!");
	
	}
	
}
