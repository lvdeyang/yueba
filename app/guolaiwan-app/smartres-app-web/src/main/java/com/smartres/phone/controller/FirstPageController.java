package com.smartres.phone.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartres.app.web.admin.vo.MerchantVO;
import com.smartres.bussiness.admin.dao.MerchantDAO;
import com.smartres.bussiness.admin.dao.MessageDao;
import com.smartres.bussiness.admin.dao.SysConfigDAO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.po.MerchantPO;
import com.smartres.bussiness.admin.po.MessagePO;
import com.smartres.bussiness.admin.po.SysConfigPO;
import com.smartres.bussiness.admin.po.UserInfoPO;
import com.smartres.reserve.dao.TableDao;
import com.smartres.reserve.po.TablePo;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/first")
public class FirstPageController extends BaseController {
	@Autowired
	MerchantDAO merchantDao;
	@Autowired
	SysConfigDAO sysConfigDAO;
	@Autowired
	UserInfoDAO userInfoDAO;
	@Autowired
	TableDao tableDao;
	@Autowired
	MessageDao messageDao;
	@RequestMapping(value = "/show")
	public ModelAndView show(HttpServletRequest request) {
		Map<String, Object> strMap=new HashMap<String, Object>();
		
		
		ModelAndView mv = new ModelAndView("show");
		mv.addAllObjects(strMap);
		return mv;
	}
	
	
	@RequestMapping(value = "/index")
	public ModelAndView login(HttpServletRequest request) {
		Map<String, Object> strMap=new HashMap<String, Object>();
		MerchantPO merchant=merchantDao.get(getLoginMerchantId());
		MerchantVO merchantVO=new MerchantVO();
		try {
			merchantVO.set(merchant);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysConfigPO sysConfigPO=sysConfigDAO.getSysConfig();
		merchantVO.setShopPic(sysConfigPO.getWebUrl()+merchantVO.getShopPic());
		strMap.put("merchant", merchantVO);
		
		long tableId=getLoginTableId();
		long userId=getLoginUserId();
		
		UserInfoPO userInfoPO=userInfoDAO.get(userId);
		TablePo tablePo=tableDao.get(tableId);
		
		strMap.put("tableNum", tablePo.getNum());
		strMap.put("userName", userInfoPO.getUserNickname());
		
		ModelAndView mv = new ModelAndView("firstPage");
		mv.addAllObjects(strMap);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/keep", method= RequestMethod.POST)
	public String keep(HttpServletRequest request) throws Exception {
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/callServ", method= RequestMethod.POST)
	public String callServ(HttpServletRequest request) throws Exception {
		MessagePO messagePO=new MessagePO();
		messagePO.setMsg("呼叫服务员");
		TablePo tablePo=tableDao.get(getLoginTableId());
		messagePO.setTableId(tablePo.getId());
		messagePO.setTableNum(tablePo.getNum());
		messagePO.setDeal(0);
		messageDao.save(messagePO);
		return "success";
	}
	
}
