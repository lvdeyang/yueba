package com.smartres.app.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartres.app.web.admin.vo.AdminVO;
import com.smartres.bussiness.admin.po.AdminPO;
import com.smartres.vip.dao.ConfigDao;
import com.smartres.vip.po.ConfigPo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {
	@Autowired
	ConfigDao conn_config;
	// 显示添加页面
	@RequestMapping("/init")
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response, Model model) {

		
		Map<String, Object> strMap=new HashMap<String, Object>();
		ConfigPo configPo= conn_config.findByMerchantId(getNewLoginInfo().getLoginId());
		strMap.put("config", configPo);
		ModelAndView view=new ModelAndView("admin/config/setting",strMap);
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value="/save", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String saveConfig(double changeRate,double payChangeRate,String cardDes) throws Exception {
	
		ConfigPo configPo=conn_config.findByMerchantId(getNewLoginInfo().getLoginId());
		configPo.setChangeRate(changeRate);
		configPo.setOrderChangeRate(payChangeRate);
		configPo.setCardDes(cardDes);
		conn_config.save(configPo);
		return "success";
	}
}
