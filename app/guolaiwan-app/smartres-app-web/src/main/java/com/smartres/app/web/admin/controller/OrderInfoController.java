package com.smartres.app.web.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartres.app.web.admin.vo.ModularVO;
import com.smartres.app.web.admin.vo.OrderInfoVO;
import com.smartres.app.web.admin.vo.TableVO;
import com.smartres.app.web.admin.vo.TableWithOrderVO;
import com.smartres.bussiness.admin.dao.MerchantDAO;
import com.smartres.bussiness.admin.dao.ModularDAO;
import com.smartres.bussiness.admin.dao.OrderInfoDAO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.enumeration.OrderStateType;
import com.smartres.bussiness.admin.po.MerchantPO;
import com.smartres.bussiness.admin.po.ModularPO;
import com.smartres.bussiness.admin.po.OrderInfoPO;
import com.smartres.bussiness.admin.po.UserInfoPO;
import com.smartres.reserve.dao.TableDao;
import com.smartres.reserve.po.TablePo;
import com.smartres.vip.dao.ConfigDao;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/admin/orderinfo")
public class OrderInfoController extends BaseController{
	@Autowired
	private OrderInfoDAO conn_OrderInfo;
	@Autowired
	private UserInfoDAO conn_userInfo;
	@Autowired
	private TableDao conn_table;
	@Autowired
	private MerchantDAO conn_merchant;
	@Autowired
	private ConfigDao conn_config;
	
	//显示列表
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView getOrderInfos(HttpServletRequest request){
		Map<String, Object> strMap = new HashMap<String, Object>();
		
		strMap.put("allcount", conn_OrderInfo.GetCountByPage());
		if(getNewLoginInfo().getType()==0){
			strMap.put("tables",conn_table.findAll());
		}else{
			strMap.put("tables",conn_table.findAllTablesByMerchant(getNewLoginInfo().getLoginId()));
		}
		
		ModelAndView mv = new ModelAndView("admin/orderinfo/list",strMap);
		return mv;
	     }
	
	
	@ResponseBody
	@RequestMapping(value="/orderCount", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getOrderCount(HttpServletRequest request) throws Exception {
        long tableId=Long.parseLong(request.getParameter("tableId"));
        String dateString=request.getParameter("date");
        String[] dates=new String[2];
        if(dateString==null||dateString.isEmpty()){
        	dates[0]="";
        	dates[1]="";
        }else{
        	
        	dates=dateString.split(" - ");
        }
        
        
        int count= conn_OrderInfo.GetCountByTableAndDate(tableId,dates[0],dates[1]);

		
	
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("getcount", count);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/delOrder", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String delOrder(long orderId) throws Exception {
		conn_OrderInfo.delete(orderId);
		return "success";
	}
	
	//异步读取
	@ResponseBody
	@RequestMapping(value="/OrderInfoList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getList() throws Exception {
		List<OrderInfoPO> orderinfopo = conn_OrderInfo.findAll();
		List<OrderInfoVO> orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderinfopo, OrderInfoVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", orderinfovo);
		return map;
	}
	//异步读取列表分页
	@ResponseBody
	@RequestMapping(value="/list.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> GetList(int pagecurr,long tableId,String date) throws Exception {
		
		String[] dateStrs=new String[2];
        if(date==null||date.isEmpty()){
        	
        	dateStrs[0]="";
        	dateStrs[1]="";
        }else{
        	dateStrs=date.split(" - ");
        }
		List<OrderInfoPO> orderinfopo=new ArrayList<OrderInfoPO>();
		
		orderinfopo=conn_OrderInfo.GetListbyPageAndTableAndDate(pagecurr, 5,tableId,dateStrs[0],dateStrs[1]);
		

		
		List<OrderInfoVO> orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderinfopo, OrderInfoVO.class);
		for (OrderInfoVO orderInfoVO2 : orderinfovo) {
			orderInfoVO2.setProductPrice(orderInfoVO2.getProductPrice()/100);
			orderInfoVO2.setOrderAllMoney(orderInfoVO2.getProductPrice()+"");
			UserInfoPO userInfo=conn_userInfo.get(orderInfoVO2.getUserId());
			orderInfoVO2.setUserInfo(userInfo.getUserNickname());
		}
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("alist", orderinfovo);
		return map;
	}

	
	@ResponseBody
	@RequestMapping(value="/print", method= RequestMethod.POST)
	public String print(HttpServletRequest request,long tableId) throws Exception {
        List<OrderInfoPO> orderInfoPOs=conn_OrderInfo.getAllPrintOrder(getNewLoginInfo().getLoginId(),tableId);
        for (OrderInfoPO orderInfoPO : orderInfoPOs) {
        	orderInfoPO.setPrint(1);
		}
        conn_OrderInfo.saveAll(orderInfoPOs);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/payprint", method= RequestMethod.POST)
	public String payprint(HttpServletRequest request,long tableId) throws Exception {
        List<OrderInfoPO> orderInfoPOs=conn_OrderInfo.getAllPayPrintOrder(getNewLoginInfo().getLoginId(),tableId);
        for (OrderInfoPO orderInfoPO : orderInfoPOs) {
        	orderInfoPO.setPayPrint(1);
        	orderInfoPO.setTableId(0l);
		}
        TablePo tablePo=conn_table.get(tableId);
        tablePo.setStatus(0);
        tablePo.setUserId(0l);
        conn_table.save(tablePo);
        conn_OrderInfo.saveAll(orderInfoPOs);
		return "success";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getPrint", method= RequestMethod.POST)
	public Map<String, Object> getPrint(HttpServletRequest request,long tableId) throws Exception {
        List<OrderInfoPO> orderInfoPOs=conn_OrderInfo.getAllPrintOrder(getNewLoginInfo().getLoginId(),tableId);
        List<OrderInfoVO> orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
		for (OrderInfoVO orderInfoVO2 : orderinfovo) {
			orderInfoVO2.setProductPrice(orderInfoVO2.getProductPrice()/100);
			orderInfoVO2.setOrderAllMoney(orderInfoVO2.getProductPrice()+"");
		}
        Map<String, Object> map= new HashMap<String, Object>();
		map.put("orderList", orderinfovo);
		TablePo table=conn_table.get(tableId);
		TableVO tableVO=new TableVO();
		tableVO.set(table);
		map.put("table", tableVO);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/getPayPrint", method= RequestMethod.POST)
	public Map<String, Object> getPayPrint(HttpServletRequest request,long tableId) throws Exception {
        List<OrderInfoPO> orderInfoPOs=conn_OrderInfo.getAllPayPrintOrder(tableId,getNewLoginInfo().getLoginId());
        List<OrderInfoVO> orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
		for (OrderInfoVO orderInfoVO2 : orderinfovo) {
			orderInfoVO2.setProductPrice(orderInfoVO2.getProductPrice()/100);
			orderInfoVO2.setOrderAllMoney(orderInfoVO2.getProductPrice()+"");
		}
        Map<String, Object> map= new HashMap<String, Object>();
		map.put("orderList", orderinfovo);
		TablePo table=conn_table.get(tableId);
		TableVO tableVO=new TableVO();
		tableVO.set(table);
		map.put("table", tableVO);
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getAllPrint", method= RequestMethod.POST)
	public Map<String, Object> getAllPrint(HttpServletRequest request) throws Exception {
        List<TablePo> tablePos=conn_table.findTablesByMerchantAndStatus(getNewLoginInfo().getLoginId(), 0);
        List<TableWithOrderVO> tableVOs=new ArrayList<TableWithOrderVO>();
		for (TablePo tablePo : tablePos) {
			
			List<OrderInfoPO> orderInfoPOs=conn_OrderInfo.getAllPrintOrder(getNewLoginInfo().getLoginId(),tablePo.getId());
	        List<OrderInfoVO> orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
			for (OrderInfoVO orderInfoVO2 : orderinfovo) {
				orderInfoVO2.setProductPrice(orderInfoVO2.getProductPrice()/100);
				orderInfoVO2.setOrderAllMoney(orderInfoVO2.getProductPrice()+"");
			}
			if(!orderinfovo.isEmpty()){
				TableWithOrderVO tableWithOrderVO=new TableWithOrderVO();
				tableWithOrderVO.setId(tablePo.getId());
				tableWithOrderVO.setNum(tablePo.getNum());
				tableWithOrderVO.getOrderInfoVOs().addAll(orderinfovo);
				tableVOs.add(tableWithOrderVO);
			}
			
		}
        Map<String, Object> map= new HashMap<String, Object>();
		map.put("tableList", tableVOs);
		map.put("merchant", conn_merchant.get(getNewLoginInfo().getLoginId()).getShopName());
		map.put("cardDes", conn_config.findByMerchantId(getNewLoginInfo().getLoginId()).getCardDes());
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getAllPayPrint", method= RequestMethod.POST)
	public Map<String, Object> getAllPayPrint(HttpServletRequest request) throws Exception {
        List<TablePo> tablePos=conn_table.findTablesByMerchantAndStatus(getNewLoginInfo().getLoginId(), 0);
        List<TableWithOrderVO> tableVOs=new ArrayList<TableWithOrderVO>();
		for (TablePo tablePo : tablePos) {
			
			List<OrderInfoPO> orderInfoPOs=conn_OrderInfo.getAllPayPrintOrder(getNewLoginInfo().getLoginId(),tablePo.getId());
	        List<OrderInfoVO> orderinfovo = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
			for (OrderInfoVO orderInfoVO2 : orderinfovo) {
				orderInfoVO2.setProductPrice(orderInfoVO2.getProductPrice()/100);
				orderInfoVO2.setOrderAllMoney(orderInfoVO2.getProductPrice()+"");
			}
			if(!orderinfovo.isEmpty()){
				TableWithOrderVO tableWithOrderVO=new TableWithOrderVO();
				tableWithOrderVO.setId(tablePo.getId());
				tableWithOrderVO.setNum(tablePo.getNum());
				tableWithOrderVO.getOrderInfoVOs().addAll(orderinfovo);
				tableVOs.add(tableWithOrderVO);
			}
			
		}
        Map<String, Object> map= new HashMap<String, Object>();
		map.put("tableList", tableVOs);
		map.put("merchant", conn_merchant.get(getNewLoginInfo().getLoginId()).getShopName());
		map.put("cardDes", conn_config.findByMerchantId(getNewLoginInfo().getLoginId()).getCardDes());
		return map;
	}

}
