package com.smartres.phone.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.smartres.bussiness.admin.dao.OrderInfoDAO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.enumeration.OrderStateType;
import com.smartres.bussiness.admin.po.OrderInfoPO;
import com.smartres.bussiness.admin.po.UserInfoPO;
import com.smartres.reserve.dao.TableDao;
import com.smartres.reserve.po.TablePo;
import com.smartres.vip.dao.ConfigDao;
import com.smartres.vip.po.ConfigPo;
import com.smartres.wx.util.HttpUtil;

import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/wx")
public class WxController extends BaseController {
	private String appid="wxc40369fd3ab2b254";
	private String appScret="19a82d83400da499ea7e85dd8391c356";
	private long merchantId=0;
	private long tableId=0;
	private String myKey="3640669552fdd20a83db3bf0650e18c9";
	@Autowired
	private OrderInfoDAO conn_order;
	@Autowired
	UserInfoDAO UserInfoDAO;
	@Autowired
	ConfigDao configDao;
	@Autowired 
	TableDao tableDao;
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) throws UnsupportedEncodingException {
		merchantId=Long.parseLong(request.getParameter("merchantId").toString());
		tableId=Long.parseLong(request.getParameter("tableId").toString());
		return "redirect:http://u.guolaiwan.net/wxApiGetInfo?u="+AESUtil.Encrypt("http://www.guolaiwan.net/smartres-app-web/wx/userInfo",myKey);
	}
	
	@RequestMapping(value = "/logintest")
	public String logintest(String code,HttpServletRequest request) {
		merchantId=2;
		tableId=1;
		return "redirect:/wx/userInfo?openid=werwerwer&nickname=fish";
	}
	
	
	@RequestMapping(value = "/userInfo")
	public String info(HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println(request.getQueryString());
		//openid=opVUYv_4GAy8GhpOWKMO2eqt-j0A
		
		
		UserInfoPO userInfoPO=new UserInfoPO();
		List<UserInfoPO> users=UserInfoDAO.findByField("userOpenID", request.getParameter("openid"));
		if(users.isEmpty()){
			userInfoPO.setMerchantId(merchantId);
			//nickname
			//headimg
			userInfoPO.setUserNickname(new String(request.getParameter("nickname").getBytes("iso-8859-1"), "utf-8"));
			userInfoPO.setUserOpenID(request.getParameter("openid"));
			userInfoPO.setUserHeadimg("");
			userInfoPO.setUserPassword("");
			userInfoPO.setUserPhone("");
			userInfoPO.setUserIntegral("0");
			UserInfoDAO.save(userInfoPO);
		}else{
			userInfoPO=users.get(0);
			userInfoPO.setUserNickname(new String(request.getParameter("nickname").getBytes("iso-8859-1"), "utf-8"));
			UserInfoDAO.update(userInfoPO);
			UserInfoDAO.flush();
		}
		
		
		
		request.getSession().setAttribute("userOpenId", userInfoPO.getUserOpenID());
		request.getSession().setAttribute("nickName", userInfoPO.getUserNickname());
		request.getSession().setAttribute("userMerchantId", merchantId);
		request.getSession().setAttribute("userId", userInfoPO.getId());
		request.getSession().setAttribute("tableId", tableId);
		
		TablePo tablePo=tableDao.get(tableId);
		if((tablePo.getStatus()==1||tablePo.getStatus()==2)&&tablePo.getUserId()!=userInfoPO.getId()){
			return "redirect:../wx/notime";
		}
		if(tablePo.getUserId()!=userInfoPO.getId()){
			tablePo.setUserId(userInfoPO.getId());
			tablePo.setStatus(1);
			tableDao.save(tablePo);
		}

		return "redirect:../first/show";
	}
	@RequestMapping(value = "/pay")
	public String pay(HttpServletRequest request,int useScore) throws UnsupportedEncodingException {
		long tableId=getLoginTableId();
		long userId=getLoginUserId();
		long merchantId=getLoginMerchantId();
		TablePo tablePo=tableDao.get(tableId);
		String orderNum=tablePo.getId()+"-"+merchantId;
		List<OrderInfoPO> orderInfoPOs=conn_order.getPhoneOrdersByPrint(OrderStateType.NOTPAY, tableId, merchantId);
		int count=0;
		for (OrderInfoPO orderInfoPO : orderInfoPOs) {
			count+=orderInfoPO.getProductPrice()/100;
		}
		if(tablePo.getStatus()==2){
			count-=tablePo.getReversePrice();
		}
		
		int dese=0;
		UserInfoPO userInfoPO=UserInfoDAO.get(getLoginUserId());
		ConfigPo configPo=configDao.getByField("merchantId", getLoginMerchantId());
		if(useScore==1){
			dese=(int) (Integer.parseInt(userInfoPO.getUserIntegral())*configPo.getOrderChangeRate());
		}
		if(count>dese){
			if(dese==0){
				int addScore=(int) (count*configPo.getChangeRate());
				userInfoPO.setUserIntegral(String.valueOf(Integer.parseInt(userInfoPO.getUserIntegral())+addScore));
			    UserInfoDAO.save(userInfoPO);
			}
			String para="";
			para+=orderNum;
			//para+="$"+(count-dese);
			para+="$1";
			para+="$http://www.guolaiwan.net/smartres-app-web/order/index";
			para+="$http://www.guolaiwan.net/smartres-app-web/wx/payBack?order="+orderNum;
			return "redirect:http://u.guolaiwan.net/wp/wxApiPay?rq="+AESUtil.Encrypt(para,myKey);
		}else{
			userInfoPO.setUserIntegral(String.valueOf((int)((dese-count)/configPo.getOrderChangeRate())));
			UserInfoDAO.save(userInfoPO);
			return "redirect:../order/index";
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/checkpay")
	public String checkPay(HttpServletRequest request) throws UnsupportedEncodingException {
		long tableId=getLoginTableId();
		long merchantId=getLoginMerchantId();
		List<OrderInfoPO> orderInfoPOs=conn_order.getPhoneOrdersByPrint(OrderStateType.NOTPAY, tableId, merchantId);
		if(orderInfoPOs.isEmpty()){
			return "fail";
		}else{
			return "success";
		}
	}
	
	
	
	
	
	@RequestMapping(value = "/payBack")
	public String payBack(HttpServletRequest request) throws UnsupportedEncodingException {
		System.out.println(request.getParameter("order"));
		String numString=request.getParameter("order");
		String[] numStrings=numString.split("-");
		long tempTableId=Long.parseLong(numStrings[0]);
		long tempMerchantId=Long.parseLong(numStrings[1]);
		List<OrderInfoPO> orderInfoPOs=conn_order.getPhoneOrdersByPrint(OrderStateType.NOTPAY, tempTableId, tempMerchantId);
		for (OrderInfoPO orderInfoPO : orderInfoPOs) {
			orderInfoPO.setOrderState(OrderStateType.PAYFINISH);
			conn_order.save(orderInfoPO);
		}
		
		return "redirect:../order/index";
	}
	
	@RequestMapping(value = "/notime")
	public String notime(HttpServletRequest request) {
		return "notime";
	}
	
	
}
