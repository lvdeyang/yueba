package com.smartres.app.web.index.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.smartres.app.web.admin.vo.ReportVO;
import com.smartres.bussiness.admin.dao.OrderInfoDAO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.po.OrderInfoPO;
import com.smartres.reserve.po.OrderReportDTO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/welcome")
public class WelcomeController extends BaseController {
	@Autowired
	private OrderInfoDAO conn_order;
	@Autowired
	private UserInfoDAO conn_user;
	@RequestMapping(value="/init",method= RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		try {
			if(getNewLoginInfo().getType()==0){
				strMap.put("orderCount",conn_order.countTodayOrders(0));
				strMap.put("userCount", conn_user.countByMerchant(0));
			}else{
				strMap.put("orderCount",conn_order.countTodayOrders(getNewLoginInfo().getLoginId()));
				strMap.put("userCount", conn_user.countByMerchant(getNewLoginInfo().getLoginId()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<ReportVO> reportVOs=new ArrayList<ReportVO>();
		for(int i=0;i<7;i++){
			Date now=new Date();
			String currDate= DateUtil.dateAddday(-i);
			String[] currDates=currDate.split(" ");
			String startDate=currDates[0]+" 00:00:00";
			String endDate=currDates[0]+" 23:59:59";
			try {
				Date startDated=DateUtil.parse(startDate);
				Date endDated=DateUtil.parse(endDate);
				ReportVO reportVO=new ReportVO();
				reportVO.setTitle(currDates[0]);
				if(getNewLoginInfo().getType()==0){
					reportVO.setValue(conn_order.countOrdersBydate(0,startDated,endDated));
				}else{
					reportVO.setValue(conn_order.countOrdersBydate(getNewLoginInfo().getLoginId(),startDated,endDated));
				}
				reportVOs.add(reportVO);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		strMap.put("orderReports", reportVOs);
		List<ReportVO> proReportList=new ArrayList<ReportVO>();
		List<OrderReportDTO> orderReportDTOs=new ArrayList<OrderReportDTO>();
		if(getNewLoginInfo().getType()==0){
			orderReportDTOs=conn_order.queryByReport(0);
		}else{
			orderReportDTOs=conn_order.queryByReport(getNewLoginInfo().getLoginId());
		}
		for (OrderReportDTO orderReportDTO : orderReportDTOs) {
			ReportVO vo=new ReportVO();
			vo.setTitle(orderReportDTO.getProductName());
			vo.setValue(orderReportDTO.getAllNum());
			proReportList.add(vo);
		}
		strMap.put("proReports", proReportList);
		ModelAndView mv = new ModelAndView("admin/welcome",strMap);
		return mv;
	}
}
