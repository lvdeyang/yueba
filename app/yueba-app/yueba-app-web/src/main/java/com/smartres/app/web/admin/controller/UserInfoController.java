package com.smartres.app.web.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartres.app.web.admin.vo.UserInfoVO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.po.ModularPO;
import com.smartres.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/userinfo")
public class UserInfoController extends BaseController{

	@Autowired
	private UserInfoDAO conn_UserInfo;
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getuserinfos(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		strMap.put("allcount", conn_UserInfo.GetCountByPage());
		
		ModelAndView mv = new ModelAndView("admin/userinfo/list",strMap);
		return mv;
	     }
	//添加页面
		@RequestMapping("/addv")
		public ModelAndView addUserInfo(){
			
			ModelAndView mv = new ModelAndView("admin/userinfo/add");
			return mv;
		}
		//异步读取
		@ResponseBody
		@RequestMapping(value="/userinfoList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
		public Map<String, Object> getList() throws Exception {
			List<UserInfoPO> userinfopo = conn_UserInfo.findAll();
			List<UserInfoVO> userinfovo = UserInfoVO.getConverter(UserInfoVO.class).convert(userinfopo, UserInfoVO.class);
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("list", userinfovo);
			return map;
		}
		//异步读取列表分页
				@ResponseBody
				@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
				public Map<String, Object> GetList(int pagecurr) throws Exception {
					List<UserInfoPO> userinfopo=conn_UserInfo.GetListbyPage(pagecurr, 5);
					List<UserInfoVO> userinfovo = UserInfoVO.getConverter(UserInfoVO.class).convert(userinfopo, UserInfoVO.class);
					Map<String, Object> map= new HashMap<String, Object>();
					map.put("alist", userinfovo);
					return map;
				
				}
		
				//删除数据
				@ResponseBody
				@RequestMapping(value="/del.do", method= RequestMethod.POST)
				public String del(HttpServletRequest request) throws Exception {
					String uuid =request.getParameter("uuid");
					UserInfoPO userinfo = conn_UserInfo.get(uuid);
					conn_UserInfo.delete(userinfo);
					return "success";
				}
}

