package com.smartres.phone.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.smartres.app.web.admin.vo.OrderInfoVO;
import com.smartres.bussiness.admin.dao.OrderInfoDAO;
import com.smartres.bussiness.admin.dao.ProductDAO;
import com.smartres.bussiness.admin.dao.SysConfigDAO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.enumeration.OrderStateType;
import com.smartres.bussiness.admin.po.OrderInfoPO;
import com.smartres.bussiness.admin.po.ProductPO;
import com.smartres.bussiness.admin.po.SysConfigPO;
import com.smartres.bussiness.admin.po.UserInfoPO;
import com.smartres.reserve.dao.TableDao;
import com.smartres.reserve.po.TablePo;
import com.smartres.vip.dao.ConfigDao;
import com.smartres.vip.po.ConfigPo;

import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private ProductDAO productDao;
	@Autowired
	private OrderInfoDAO orderInfoDao;
	@Autowired
	private SysConfigDAO sysConfigDao;
	@Autowired
	private UserInfoDAO userinfoDao;
	@Autowired
	private ConfigDao configDao;
	@Autowired
	private TableDao tableDao;
	@RequestMapping(value = "/index")
	public ModelAndView getAll() {
		Map<String, Object> strMap=new HashMap<String, Object>();
		List<OrderInfoPO> orderInfoPOs= orderInfoDao.getPhoneOrders(OrderStateType.NOTPAY,getLoginTableId(),
				getLoginMerchantId());
		List<OrderInfoVO> orderInfoVOs=new ArrayList<OrderInfoVO>();
		try {
			orderInfoVOs = OrderInfoVO.getConverter(OrderInfoVO.class).convert(orderInfoPOs, OrderInfoVO.class);
		    SysConfigPO sysConfigPO=sysConfigDao.getSysConfig();
			for (OrderInfoVO orderInfoVO : orderInfoVOs) {
		    	orderInfoVO.setProductPic(sysConfigPO.getWebUrl()+orderInfoVO.getProductPic());
		    	orderInfoVO.setProductPrice(orderInfoVO.getProductPrice()/100);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		strMap.put("orders", orderInfoVOs);
		long[] result=getTotal(orderInfoPOs);
		strMap.put("total", result[0]/100);
		strMap.put("confirmTotal", result[1]/100);
		
		UserInfoPO userInfoPO=userinfoDao.get(getLoginUserId());
		int score=Integer.parseInt(userInfoPO.getUserIntegral());
		ConfigPo configPo=configDao.getByField("merchantId", getLoginMerchantId());
		int scoreChange=(int) (score*configPo.getOrderChangeRate());
		strMap.put("scoreChange", scoreChange);
		
		
		TablePo tablePo=tableDao.get(getLoginTableId());
		if(tablePo.getStatus()==2){
			strMap.put("reserveCount",tablePo.getReversePrice());
		}else{
			strMap.put("reserveCount",0);
		}
		
		ModelAndView mv = new ModelAndView("order");
		mv.addAllObjects(strMap);
		return mv;
	}
	
	private long[] getTotal(List<OrderInfoPO> orderInfoPOs){
		long[] result=new long[2];
		long total=0l;
		long confirmTotal=0l;
		for (OrderInfoPO orderInfoPO : orderInfoPOs) {
			total+=orderInfoPO.getProductPrice();
			if(orderInfoPO.getConfirm()==1){
				confirmTotal+=orderInfoPO.getProductPrice();
			}
		}
		result[0]=total;
		result[1]=confirmTotal;
		return result;
	}
   
	
	@ResponseBody
	@RequestMapping(value="/add", method= RequestMethod.POST)
	public String add(long proId,HttpServletRequest request) throws Exception {
        ProductPO productPO=productDao.get(proId);
        OrderInfoPO newOrder=new OrderInfoPO();
        newOrder.setCreateDate(new Date());
        newOrder.setMerchantId(productPO.getMerchantId());
        newOrder.setProductId(productPO.getId());
        newOrder.setProductName(productPO.getProductName());
        newOrder.setProductPic(productPO.getProductShowPic());
        newOrder.setProductNum(1);
        newOrder.setProductPrice(productPO.getProductPrice());
        newOrder.setOrderState(OrderStateType.NOTPAY);
        newOrder.setConfirm(0);
        newOrder.setUserId(getLoginUserId());
        newOrder.setTableId(getLoginTableId());
        newOrder.setPrint(0);
        newOrder.setPayPrint(0);
        orderInfoDao.save(newOrder);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method= RequestMethod.POST)
	public String delete(long orderId,HttpServletRequest request) throws Exception {
        orderInfoDao.delete(orderId);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping(value="/confirm", method= RequestMethod.POST)
	public String confirm(HttpServletRequest request) throws Exception {
        List<OrderInfoPO> orderInfoPOs=orderInfoDao.getPhoneOrders(OrderStateType.NOTPAY,getLoginTableId(),
				getLoginMerchantId());
        for (OrderInfoPO orderInfoPO : orderInfoPOs) {
        	orderInfoPO.setConfirm(1);
		}
        orderInfoDao.saveAll(orderInfoPOs);
        
		return "success";
	}
	
	
	
	
}
