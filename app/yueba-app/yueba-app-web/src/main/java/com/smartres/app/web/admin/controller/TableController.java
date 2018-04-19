package com.smartres.app.web.admin.controller;

import java.util.ArrayList;
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
import com.smartres.app.web.admin.vo.OrderInfoVO;
import com.smartres.app.web.admin.vo.TableVO;
import com.smartres.app.web.admin.vo.UserInfoVO;
import com.smartres.bussiness.admin.dao.OrderInfoDAO;
import com.smartres.bussiness.admin.dao.SysConfigDAO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.enumeration.OrderStateType;
import com.smartres.bussiness.admin.po.AdminPO;
import com.smartres.bussiness.admin.po.OrderInfoPO;
import com.smartres.bussiness.admin.po.RolePO;
import com.smartres.bussiness.admin.po.SysConfigPO;
import com.smartres.bussiness.admin.po.UserInfoPO;
import com.smartres.reserve.dao.TableDao;
import com.smartres.reserve.po.TablePo;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/table")
public class TableController extends BaseController {
	@Autowired
	TableDao conn_table;
	@Autowired
	UserInfoDAO conn_userInfo;
	@Autowired
	OrderInfoDAO conn_orderInfo;
	@Autowired
	SysConfigDAO conn_sysConfig;

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int allcount = 0;
		if(getNewLoginInfo().getType()==0){
			allcount=conn_table.findAll().size();
		}else{
			allcount=conn_table.countTablesByMerchant(getNewLoginInfo().getLoginId());
		}
		strMap.put("allcount", allcount);
		strMap.put("allpages", GetAllPages(allcount, 5));
		ModelAndView mv = new ModelAndView("admin/table/list", strMap);
		return mv;
	}
	
	
	@RequestMapping(value = "/newinfo", method = RequestMethod.GET)
	public ModelAndView newInfo(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<TablePo> tablePos=new ArrayList<TablePo>();
		SysConfigPO sysConfigPO=conn_sysConfig.getSysConfig();
		if(getNewLoginInfo().getType()!=0){
			tablePos=conn_table.findAllTablesByMerchant(getNewLoginInfo().getLoginId());
		}
		List<TableVO> tableVOs=new ArrayList<TableVO>();
		try {
			tableVOs = TableVO.getConverter(TableVO.class).convert(tablePos, TableVO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (TableVO tableVO : tableVOs) {
			UserInfoPO userInfoPO=conn_userInfo.get(tableVO.getUserId());
			if(userInfoPO!=null){
				tableVO.setUserName(userInfoPO.getUserNickname());
			}else{
				tableVO.setUserName("-");
			}
			/*List<OrderInfoPO> orderInfoPOs=conn_orderInfo.GetConfirmListbyTable(tableVO.getId());
			List<OrderInfoVO> orderInfoVOs=new ArrayList<OrderInfoVO>();
			try {
				orderInfoVOs=OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
		    for (OrderInfoVO orderInfoVO2 : orderInfoVOs) {
				orderInfoVO2.setProductPrice(orderInfoVO2.getProductPrice()/100);
				orderInfoVO2.setOrderAllMoney(orderInfoVO2.getProductPrice()+"");
				UserInfoPO userInfo=conn_userInfo.get(orderInfoVO2.getUserId());
				orderInfoVO2.setUserInfo(userInfo.getUserNickname());
				orderInfoVO2.setProductPic(sysConfigPO.getWebUrl()+orderInfoVO2.getProductPic());
			    tableVO.getOrderInfoVOs().add(orderInfoVO2);
		    }*/
			
		}
		
		strMap.put("tables", tableVOs);
		ModelAndView mv = new ModelAndView("admin/table/newList", strMap);
		return mv;
	}

	
	@RequestMapping(value = "/orderInfo", method = RequestMethod.GET)
	public ModelAndView order(HttpServletRequest request,long tableId) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		
	    List<OrderInfoPO> orderInfoPOs=conn_orderInfo.GetConfirmListbyTable(tableId);
	    List<OrderInfoVO> orderinfovo=new ArrayList<OrderInfoVO>();
		try {
			orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysConfigPO sysConfigPO=conn_sysConfig.getSysConfig();
		int total=0;
	    for (OrderInfoVO orderInfoVO2 : orderinfovo) {
			orderInfoVO2.setProductPrice(orderInfoVO2.getProductPrice()/100);
			orderInfoVO2.setOrderAllMoney(orderInfoVO2.getProductPrice()+"");
			total+=orderInfoVO2.getProductPrice();
			UserInfoPO userInfo=conn_userInfo.get(orderInfoVO2.getUserId());
			orderInfoVO2.setUserInfo(userInfo.getUserNickname());
			orderInfoVO2.setProductPic(sysConfigPO.getWebUrl()+orderInfoVO2.getProductPic());
		}
		strMap.put("orders", orderinfovo);
		strMap.put("count", orderInfoPOs.size());
		strMap.put("total", total);
		strMap.put("tableId", tableId);
		ModelAndView mv = new ModelAndView("admin/table/orderList", strMap);
		return mv;
	}
	
	
	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(int pagecurr, String username) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		List<TablePo> tableList=new ArrayList<TablePo>();
		if(getNewLoginInfo().getType()==0){
			tableList = conn_table.findTablesByPage(pagecurr, 5);
		}else{
			tableList = conn_table.findTablesByMerchant(getNewLoginInfo().getLoginId(),pagecurr, 5);
		}
		List<TableVO> tableVOs = TableVO.getConverter(TableVO.class).convert(tableList, TableVO.class);
		for (TableVO tableVO : tableVOs) {
			UserInfoPO userInfoPO=conn_userInfo.get(tableVO.getUserId());
			if(userInfoPO!=null){
				tableVO.setUserName(userInfoPO.getUserNickname());
			}else{
				tableVO.setUserName("-");
			}
			tableVO.setWaitingForPrint("否");
			tableVO.setWaitingForPay("否");
			tableVO.setWaitingForPayPrint("否");
			List<OrderInfoPO> orderInfoPOs=conn_orderInfo.GetConfirmListbyTable(tableVO.getId());
			int total=0;
			for (OrderInfoPO orderInfoPO : orderInfoPOs) {
				total+=orderInfoPO.getProductPrice()/100;
				if(orderInfoPO.getOrderState().equals(OrderStateType.NOTPAY)&&orderInfoPO.getPrint()==0){
					tableVO.setWaitingForPrint("是");
				}
				else if(orderInfoPO.getOrderState().equals(OrderStateType.NOTPAY)&&orderInfoPO.getPrint()==1){
					tableVO.setWaitingForPay("是");
				}else if(orderInfoPO.getPayPrint()==0){
					tableVO.setWaitingForPay("是");
				}
			}
		    tableVO.setTotal(total);
			
		}
		
	    
		
		map.put("list", tableVOs);
		return map;
	}

	// 显示添加页面
	@RequestMapping("/initadd")
	public String initAdd(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "admin/table/add";
	}

	
	@ResponseBody
	@RequestMapping(value = "/setnouse", method = RequestMethod.POST)
	public String setnouser(HttpServletRequest request) throws Exception {
		String tableIdStr=request.getParameter("tableId");
		TablePo tablePo=conn_table.get(Long.parseLong(tableIdStr));
		tablePo.setStatus(0);
		tablePo.setUserId(0l);
		conn_table.save(tablePo);
		List<OrderInfoPO> orderInfoPOs=conn_orderInfo.GetConfirmListbyTable(Long.parseLong(tableIdStr));
		conn_orderInfo.deleteAll(orderInfoPOs);
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/orderList", method = RequestMethod.POST)
	public Map<String, Object> orderList(HttpServletRequest request) throws Exception {
        Map<String, Object> strMap = new HashMap<String, Object>();
		String tableIdStr=request.getParameter("tableId");
	    List<OrderInfoPO> orderInfoPOs=conn_orderInfo.GetConfirmListbyTable(Long.parseLong(tableIdStr));
	    List<OrderInfoVO> orderinfovo=new ArrayList<OrderInfoVO>();
		try {
			orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SysConfigPO sysConfigPO=conn_sysConfig.getSysConfig();
	    for (OrderInfoVO orderInfoVO2 : orderinfovo) {
			orderInfoVO2.setProductPrice(orderInfoVO2.getProductPrice()/100);
			orderInfoVO2.setOrderAllMoney(orderInfoVO2.getProductPrice()+"");
			UserInfoPO userInfo=conn_userInfo.get(orderInfoVO2.getUserId());
			orderInfoVO2.setUserInfo(userInfo.getUserNickname());
			orderInfoVO2.setProductPic(sysConfigPO.getWebUrl()+orderInfoVO2.getProductPic());
		}
		strMap.put("orders", orderinfovo);
		return strMap;
	}
	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		String num =request.getParameter("num");
		String count=request.getParameter("count");
		String reversePrice=request.getParameter("reversePrice");
		TablePo table=new TablePo();
		table.setCount(Integer.parseInt(count));
		table.setNum(num);
		table.setStatus(0);
		table.setReversePrice(Long.parseLong(reversePrice));
		if(getNewLoginInfo().getType()==0){
			table.setMerchantId(0);
		}else{
			table.setMerchantId(getNewLoginInfo().getLoginId());
		}
		conn_table.save(table);
		return "success";
	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String id =request.getParameter("id");
        conn_table.delete(Long.parseLong(id));		
		return "success";
	}

}
