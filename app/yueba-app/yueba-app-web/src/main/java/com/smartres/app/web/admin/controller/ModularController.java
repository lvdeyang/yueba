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

import com.smartres.app.web.admin.vo.ModularVO;
import com.smartres.bussiness.admin.dao.ModularDAO;
import com.smartres.bussiness.admin.dao.SysConfigDAO;
import com.smartres.bussiness.admin.po.ModularPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/modular")
public class ModularController extends BaseController{
	
	
@Autowired
private ModularDAO conn_Modular;


@Autowired
private SysConfigDAO conn_sysConfig;
//显示列表
@RequestMapping(value="/list",method= RequestMethod.GET)
public ModelAndView getModulars(HttpServletRequest request){
	Map<String, Object> strMap = new HashMap<String, Object>();
	
	strMap.put("allcount", conn_Modular.GetCountByPage());
	
	ModelAndView mv = new ModelAndView("admin/modular/list",strMap);
	return mv;
     }
//添加页面
	@RequestMapping("/addv")
	public ModelAndView addModular(){
		
		ModelAndView mv = new ModelAndView("admin/modular/add");
		return mv;
	}
	//异步读取
	@ResponseBody
	@RequestMapping(value="/modularList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList() throws Exception {
		List<ModularPO> modularpo = conn_Modular.findAll();
		List<ModularVO> modularvo = ModularVO.getConverter(ModularVO.class).convert(modularpo, ModularVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", modularvo);
		return map;
	}
	//异步读取列表分页
		@ResponseBody
		@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
		public Map<String, Object> GetList(int pagecurr) throws Exception {
			List<ModularPO> modularpo=conn_Modular.GetListbyPage(pagecurr, 5);
			
			List<ModularVO> modularvo = ModularVO.getConverter(ModularVO.class).convert(modularpo, ModularVO.class);
			String weburl = conn_sysConfig.getSysConfig().getWebUrl();
			for (ModularVO mv : modularvo) {
				mv.setModularPic(weburl+mv.getModularPic());
			}
			Map<String, Object> map= new HashMap<String, Object>();
			map.put("alist", modularvo);
			return map;
		}
	//添加数据
		@ResponseBody
		@RequestMapping(value="/add.do", method= RequestMethod.POST)
		public String add(HttpServletRequest request) throws Exception {
	                    
			String modularCode = request.getParameter("modularCode");
			String modularName = request.getParameter("modularName");
			String modularPic  = request.getParameter("modularPic");
			int modularIsv =1;
			if(request.getParameter("modularIsv")==null)
				modularIsv=0;
			ModularPO modular = new ModularPO();
			modular.setModularCode(modularCode);
			modular.setModularName(modularName);
			modular.setModularIsv(modularIsv);
			modular.setModularPic(modularPic);
			
			conn_Modular.save(modular);
			
			return "success";
		}
		//删除数据
		@ResponseBody
		@RequestMapping(value="/del.do", method= RequestMethod.POST)
		public String del(HttpServletRequest request) throws Exception {
			String uuid =request.getParameter("uuid");
			ModularPO modular = conn_Modular.get(uuid);
			conn_Modular.delete(modular);
			return "success";
		}
		 //显示修改页面
		@RequestMapping(value="/updatev",method= RequestMethod.GET)
		public ModelAndView UpdateView(HttpServletRequest request) throws Exception{
			
			Map<String, Object> strMap=new HashMap<String, Object>();
			String uuid =request.getParameter("uuid");
			ModularPO modular = conn_Modular.get(uuid);
			ModularVO _modular = new ModularVO().set(modular);
			String weburl = conn_sysConfig.getSysConfig().getWebUrl();
			_modular.setModularPic(weburl+_modular.getModularPic());
			strMap.put("list", _modular);
			
			
			List<ModularPO> modularlist=conn_Modular.findAll();
			strMap.put("alist", modularlist);
			
			ModelAndView mv = new ModelAndView("admin/modular/Modify");
			mv.addAllObjects(strMap);
		
			return mv;
		}
		//修改数据
		@ResponseBody
		@RequestMapping(value="/update.do", method= RequestMethod.POST)
		public String update(HttpServletRequest request) throws Exception {
			String uuid =request.getParameter("uuid");
			ModularPO modular = conn_Modular.get(uuid);
	 
            
			String modularCode = request.getParameter("modularCode");
			String modularName = request.getParameter("modularName");
			String modularPic  = request.getParameter("modularPic");
			int modularIsv =1;
			if(request.getParameter("modularIsv")==null)
				modularIsv=0;
			
			modular.setModularCode(modularCode);
			modular.setModularName(modularName);
			modular.setModularIsv(modularIsv);
			modular.setModularPic(modularPic);
			conn_Modular.update(modular);
			
			return "success";
		}
		
		//显示列表
		@RequestMapping(value="/comSel",method= RequestMethod.GET)
		public ModelAndView getComSel(HttpServletRequest request){
			Map<String, Object> strMap = new HashMap<String, Object>();
			
			Object value=1;
			List<ModularPO> modularPolist=conn_Modular.findByField("modularIsv", value);
			
			
			StringBuilder sbHtml=new StringBuilder();
			//循环模块
			for (ModularPO modularPO : modularPolist) {
			    sbHtml.append("<div class=\"layui-colla-item child\" data-mcode='"+modularPO.getModularCode()+"' data-mname='"+modularPO.getModularName()+"'>");
			    sbHtml.append("<h2 class=\"layui-colla-title\">"+modularPO.getModularName()+"</h2>");
			    sbHtml.append(" </div>");
			}
		
			strMap.put("list", sbHtml.toString());
			ModelAndView mv = new ModelAndView("admin/modular/comSel",strMap);
			return mv;
		     }
		
	}
