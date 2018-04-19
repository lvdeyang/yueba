package com.smartres.phone.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/person")
public class PersonnalController extends BaseController {
	@Autowired 
	UserInfoDAO uesrInfoDao;
	@RequestMapping(value = "/index")
	public ModelAndView getAll() {
		Map<String, Object> strMap = new HashMap<String, Object>();
		UserInfoPO userInfoPO=uesrInfoDao.get(getLoginUserId());
		strMap.put("user", userInfoPO);
		ModelAndView mv = new ModelAndView("personal");
		mv.addAllObjects(strMap);
		return mv;
	}
}
