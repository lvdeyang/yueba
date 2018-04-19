package com.smartres.app.web.index.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartres.app.web.admin.vo.MessageVO;
import com.smartres.app.web.admin.vo.ProductVO;
import com.smartres.bussiness.admin.dao.AdminDAO;
import com.smartres.bussiness.admin.dao.MenuDAO;
import com.smartres.bussiness.admin.dao.MerchantDAO;
import com.smartres.bussiness.admin.dao.MessageDao;
import com.smartres.bussiness.admin.dao.RoleMenuDAO;
import com.smartres.bussiness.admin.po.AdminPO;
import com.smartres.bussiness.admin.po.MenuPO;
import com.smartres.bussiness.admin.po.MerchantPO;
import com.smartres.bussiness.admin.po.MessagePO;
import com.smartres.bussiness.admin.po.RoleMenuPO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private AdminDAO conn_admin;
	
	@Autowired
	private RoleMenuDAO conn_rolemenu;
	
	@Autowired
	private MenuDAO conn_menu;
	
	@Autowired
	private MerchantDAO conn_merchant;
	
	@Autowired
	private MessageDao conn_msg;
	
	@RequestMapping("/admin/my")
	public ModelAndView home(HttpServletRequest request){
		
		
		
		Map<String, Object> strMap=new HashMap<String, Object>();
		
		
		String field ="roleId";
		
		Object value=getNewLoginInfo().getRoleId();
		
		StringBuilder sbHtml=new StringBuilder();
		List<RoleMenuPO> findByField = conn_rolemenu.findByField(field, value);
		
		//父类
		List<RoleMenuPO> flist=search(0,findByField);
		for (RoleMenuPO roleMenuPO : flist) {
			
			MenuPO mpo=conn_menu.getByField("id",Long.parseLong(String.valueOf(roleMenuPO.getMenuId())));
			sbHtml.append("<li class=\"layui-nav-item\"> <a class=\"javascript:;\" href=\"javascript:;\"> <i class=\"layui-icon\" style=\"top: 3px;\">&#xe62d;</i><cite>"+mpo.getName()+"</cite> </a>");
			sbHtml.append("<dl class=\"layui-nav-child\">");
			//子类
			List<RoleMenuPO> clist=search(roleMenuPO.getMenuId(),findByField);
			for (RoleMenuPO roleMenuPO2 : clist) {
				
				
				mpo=conn_menu.getByField("id",Long.parseLong(String.valueOf(roleMenuPO2.getMenuId())));
				
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();

				if(mpo.getShowis()==1)
				{
				sbHtml.append("<dd class=\"\"> <a href=\"javascript:;\" _href=\""+basePath+"/"+mpo.getUrl()+"\"> <cite>"+mpo.getName()+"</cite> </a> </dd>");
				}
			}
			sbHtml.append("</dl>");
			sbHtml.append("</li>");
		}
		strMap.put("menulist", sbHtml.toString());
		strMap.put("loginName", getNewLoginInfo().getLoginName());
		strMap.put("type", getNewLoginInfo().getType());
		ModelAndView mv = new ModelAndView("admin/MyJsp",strMap);
		return mv;
	}
	
	
	
	@RequestMapping("/admin/timeout")
	public ModelAndView timeout(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/timeoutjsp",strMap);
		return mv;
	}
	
	//获取父类
	public List<RoleMenuPO> search(int parentId,List<RoleMenuPO> list){
				
				List<RoleMenuPO> results = new ArrayList<>();
				   for(int i=0;i<list.size();i++){
					   if(((list.get(i))).getMenuFid()==parentId)
						   results.add(list.get(i));
				}
					   return results;
	}
			
	public List<RoleMenuPO> searchQ(int parentId,List<RoleMenuPO> list){
		
		List<RoleMenuPO> results = new ArrayList<>();
		   for(int i=0;i<list.size();i++){
			   if(((list.get(i))).getMenuFid()!=parentId)
				   results.add(list.get(i));
		}
			   return results;
}
	
	// Hello World!
	@RequestMapping("/admin/welcome")
	public ModelAndView welcome(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("admin/welcome");
		
		return mv;
	}
	
	@RequestMapping("/login/myjsps")
	public ModelAndView myjspS(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("admin/login");
		
		return mv;
	}
	
	    //登录
		@ResponseBody
		@RequestMapping(value="/login/login.do", method= RequestMethod.POST)
		public String add(HttpServletRequest request) throws Exception {
			System.out.println("登录");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			AdminPO loginpo = conn_admin.getByField("adminName", username);
			MerchantPO merchantPO=conn_merchant.getByField("shopLoginName",username);
			int roleid = 1;
			if(loginpo != null) {
				if(!loginpo.getPassword().equals(Sha1Util.getSha1(password))) {
					return "error";
				}else{
					roleid=loginpo.getRoleId();
					request.getSession().setAttribute("loginName", loginpo.getAdminName());
					request.getSession().setAttribute("cityCode", loginpo.getCityCode());
					request.getSession().setAttribute("cityName", loginpo.getCityName());
					request.getSession().setAttribute("type", 0);
					request.getSession().setAttribute("loginId", 0l);
				}
			} else if(merchantPO!=null){
				if(!merchantPO.getShopLoginPwd().equals(Sha1Util.getSha1(password))) {
					return "error";
				}else{
					roleid=2;
					request.getSession().setAttribute("loginName", merchantPO.getShopLoginName());
					request.getSession().setAttribute("cityCode", merchantPO.getCityCode());
					request.getSession().setAttribute("cityName", merchantPO.getCityName());
					request.getSession().setAttribute("type", 1);
					request.getSession().setAttribute("loginId", merchantPO.getId());
				}
			}
			else {
				return "error";
			}
			
			 
			
			List<String> strUrls=new ArrayList<String>();
			//权限绑定
			String field ="roleId";
			Object value = roleid;
			List<RoleMenuPO> findByField = conn_rolemenu.findByField(field, value);
			List<RoleMenuPO> flist=searchQ(0,findByField);
			
			for (RoleMenuPO roleMenuPO : flist) {
				MenuPO mpo=conn_menu.getByField("id",Long.parseLong(String.valueOf(roleMenuPO.getMenuId())));
				strUrls.add(mpo.getUrl());
			}
			strUrls.add("/admin/my");
		    strUrls.add("/login/error");
		    strUrls.add("/do/logout");
		    strUrls.add("/admin/modular/comSel");
		    strUrls.add("/admin/picture/sellist");
		    strUrls.add("/admin/merchant/sellist");
		    
			request.getSession().setAttribute("roleid", roleid);
			request.getSession().setAttribute("strUrls", strUrls);
		
			return "success";
		}
		

		@RequestMapping("/do/logout")
		public ModelAndView doLogout(HttpServletRequest request){
			
			HttpSession session = request.getSession(true); 
			
			session.removeAttribute("roleid");
			
			ModelAndView mv = new ModelAndView("admin/login");
			
			return mv;
		}
		
		@RequestMapping("/login/error")
		public ModelAndView myerror(HttpServletRequest request){
			
			
			ModelAndView mv = new ModelAndView("admin/error");
			
			return mv;
		}
		
		@ResponseBody
		@RequestMapping(value = "/msg",method= RequestMethod.POST)
		public Map<String, Object> getMsg(HttpServletRequest request) throws UnsupportedEncodingException {
			Map<String, Object> map= new HashMap<String, Object>();
			List<MessagePO> messagePOs=conn_msg.findByField("deal", 0);
			for (MessagePO messagePO : messagePOs) {
				messagePO.setDeal(1);
				conn_msg.update(messagePO);
				conn_msg.flush();
			}
			List<MessageVO> messageVOs=new ArrayList<MessageVO>();
			try {
				messageVOs = MessageVO.getConverter(MessageVO.class).convert(messagePOs, MessageVO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("msgList", messageVOs);
			
			return map;

		}
		
		
}

