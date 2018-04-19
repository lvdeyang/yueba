package com.smartres.app.web.admin.controller;

import java.util.Date;
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
import com.smartres.bussiness.admin.dao.AdminDAO;
import com.smartres.bussiness.admin.dao.RoleDAO;
import com.smartres.bussiness.admin.po.AdminPO;
import com.smartres.bussiness.admin.po.RolePO;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;


@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	@Autowired
	private AdminDAO conn_admin;
	@Autowired
	private RoleDAO conn_role;
	
	
	//查询列表页面刘立强新增测试
	//同时添加
	@RequestMapping(value="/adminInfo",method= RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		String username=request.getParameter("username");
	    
		if (username!=null) {
			username=encodeStr(username);
		}else {
			username="";
		}
		
		int allcount=conn_admin.GetCountByPage(username);//获取总数
		strMap.put("username",username);
	    strMap.put("allcount", allcount);
	    strMap.put("allpages", GetAllPages(allcount, 5));
		ModelAndView mv = new ModelAndView("admin/adminInfo/list",strMap);
		
		return mv;
	    }
	
	   //显示添加页面
		@RequestMapping("/addv")
		public String AddView(HttpServletRequest request,  HttpServletResponse response,Model model){
			List<RolePO> list=conn_role.findAll();
			model.addAttribute("list",list);
			return "admin/adminInfo/Add";
		}

		
		 //显示修改页面
		@RequestMapping(value="/updatev",method= RequestMethod.GET)
		public ModelAndView UpdateView(HttpServletRequest request){
			
			Map<String, Object> strMap=new HashMap<String, Object>();
			String uuid =request.getParameter("uuid");
			AdminPO admin = conn_admin.get(uuid);
			strMap.put("list", admin);
			List<RolePO> rolelist=conn_role.findAll();
			strMap.put("rlist", rolelist);
			ModelAndView mv = new ModelAndView("admin/adminInfo/Modify");
			mv.addAllObjects(strMap);
		
			return mv;
		}
		
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(int pagecurr,String username) throws Exception {
		List<AdminPO> listpo=conn_admin.GetListbyPage(pagecurr, 5);
		List<AdminVO> listvo = AdminVO.getConverter(AdminVO.class).convert(listpo, AdminVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}
	
	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		
 
		String userName =request.getParameter("adminName");
		String adminPwd=request.getParameter("adminpwd");
		int roleID=Integer.parseInt(request.getParameter("roleid"));
		String cityCode = request.getParameter("cityCode");
		String cityName = request.getParameter("cityName");
		
		AdminPO byField = conn_admin.getByField("adminName", userName);
		if(byField != null) {
			return "has";
		}
		
		AdminPO admin = new AdminPO();
		admin.setAdminName(userName);
		admin.setPassword(Sha1Util.getSha1(adminPwd));
		admin.setRoleId(roleID);
		admin.setCreatedDate(new Date());
		admin.setCityCode(cityCode);
		admin.setCityName(cityName);
		conn_admin.save(admin);
		
		return "success";
	}
	//删除数据
	@ResponseBody
	@RequestMapping(value="/del.do", method= RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		AdminPO admin = conn_admin.get(uuid);
		conn_admin.delete(admin);
		return "success";
	}
	//修改数据
	@ResponseBody
	@RequestMapping(value="/update.do", method= RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid =request.getParameter("uuid");
		AdminPO admin = conn_admin.get(uuid);
 
		String userName =request.getParameter("adminName");
		String adminPwd=request.getParameter("adminpwd");
		int roleID=Integer.parseInt(request.getParameter("roleid"));
		String cityCode = request.getParameter("cityCode");
		String cityName = request.getParameter("cityName");
		
		
		admin.setAdminName(userName);
		if(adminPwd.length()>0){
			admin.setPassword(Sha1Util.getSha1(adminPwd));
		}
		
		admin.setRoleId(roleID);
		admin.setCityCode(cityCode);
		admin.setCityName(cityName);
		admin.setUpdateTime(new Date());
		/*admin.setCreatedDate(new Date());*/
		
		
		conn_admin.update(admin);
		
		return "success";
	}
}
