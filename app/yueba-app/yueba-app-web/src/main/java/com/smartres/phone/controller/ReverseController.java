package com.smartres.phone.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import com.beust.jcommander.Strings;
import com.smartres.app.web.admin.vo.ModularVO;
import com.smartres.app.web.admin.vo.ProductVO;
import com.smartres.app.web.admin.vo.ReverseVO;
import com.smartres.app.web.admin.vo.TableVO;
import com.smartres.bussiness.admin.dao.MerchantDAO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.po.MerchantPO;
import com.smartres.bussiness.admin.po.MessagePO;
import com.smartres.bussiness.admin.po.ModularPO;
import com.smartres.bussiness.admin.po.ProductPO;
import com.smartres.bussiness.admin.po.SysConfigPO;
import com.smartres.bussiness.admin.po.UserInfoPO;
import com.smartres.reserve.dao.TableDao;
import com.smartres.reserve.po.TablePo;

import pub.caterpillar.mvc.controller.BaseController;
@Controller
@RequestMapping("/reverse")
public class ReverseController extends BaseController {
	@Autowired
	MerchantDAO conn_merchant;
	@Autowired
	TableDao conn_table;
	@Autowired
	UserInfoDAO userInfoDAO;
	private String myKey="3640669552fdd20a83db3bf0650e18c9";
	
	
	@RequestMapping(value = "/index")
	public ModelAndView getAll(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String nums=request.getParameter("nums");
		String name="";
		try {
			name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] numstrs = new String[2];
		if(nums!=null&&!nums.isEmpty()){
			numstrs=nums.split("-");
		}else {
			numstrs[0]="";
			numstrs[1]="";
		}
		List<ReverseVO> reverseVOs=new ArrayList<ReverseVO>(); 
		List<MerchantPO> merchantPOs=conn_merchant.findByName(name);
		for (MerchantPO merchantPO : merchantPOs) {
			ReverseVO vo=new ReverseVO();
			vo.setMerchantId(merchantPO.getId());
			vo.setMerchantName(merchantPO.getShopName());
			List<TablePo> tablePos=conn_table.findTablesByMerchantAndNum(numstrs[0],numstrs[1],merchantPO.getId());
			try {
				List<TableVO> tableVOs=TableVO.getConverter(TableVO.class).convert(tablePos, TableVO.class);
			    if(!tableVOs.isEmpty()){
			    	vo.getTableVOs().addAll(tableVOs);
				    reverseVOs.add(vo);
			    }
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		strMap.put("reserves", reverseVOs);
		strMap.put("nums", nums);
		strMap.put("name", name);
		ModelAndView mv = new ModelAndView("reserve");
		mv.addAllObjects(strMap);
		return mv;
	}
	
	
	@RequestMapping(value="/myreserve", method= RequestMethod.GET)
	public ModelAndView myreserve(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String tableIdStr=request.getParameter("tableId");
		if(tableIdStr!=null){
			TablePo tablePo1=conn_table.get(Long.parseLong(tableIdStr));
			tablePo1.setStatus(2);
			tablePo1.setUserId(getLoginUserId());
			conn_table.save(tablePo1);
		}
		
		
		
		List<TablePo> tablePos=conn_table.findTablesByUserAndStatus(getLoginUserId());
		List<ReverseVO> reverseVOs=new ArrayList<ReverseVO>(); 
		for (TablePo tablePo : tablePos) {
			
			MerchantPO merchantPO=conn_merchant.get(tablePo.getMerchantId());
			ReverseVO vo=null;
			for (ReverseVO reverseVO : reverseVOs) {
				if(reverseVO.getMerchantId()==merchantPO.getId().longValue()){
					vo=reverseVO;
					break;
				}
			}
			if(vo==null){
				vo=new ReverseVO();
				vo.setMerchantId(merchantPO.getId());
				vo.setMerchantName(merchantPO.getShopName());
			}
			
			TableVO tableVO=new TableVO();
			tableVO.set(tablePo);
		    vo.getTableVOs().add(tableVO);
			reverseVOs.add(vo);
			strMap.put("reserves", reverseVOs);
		}
		
		ModelAndView mv = new ModelAndView("myreserve");
		mv.addAllObjects(strMap);
		
		return mv;
	}
	
	@RequestMapping(value="/payreserve", method= RequestMethod.GET)
	public String payreserve(HttpServletRequest request,long tableId) throws Exception {
		//return "redirect:myreserve?tableId="+tableId;
		String para="";
		para+=tableId;
		para+="$1";
		para+="$http://www.guolaiwan.net/smartres-app-web/reverse/index";
		para+="$http://www.guolaiwan.net/smartres-app-web/reverse/myreserve?tableId="+tableId;
		return "redirect:http://u.guolaiwan.net/wp/wxApiPay?rq="+AESUtil.Encrypt(para,myKey);
	}
	

	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) throws UnsupportedEncodingException {
		return "redirect:http://u.guolaiwan.net/wxApiGetInfo?u="+AESUtil.Encrypt("http://www.guolaiwan.net/smartres-app-web/reverse/userInfo",myKey);
	}
	
	@RequestMapping(value = "/logintest")
	public String logintest(String code,HttpServletRequest request) {
		return "redirect:/reverse/userInfo?openid=werwerwer&nickname=fish";
	}
	
	
	@RequestMapping(value = "/userInfo")
	public String info(HttpServletRequest request) throws UnsupportedEncodingException {

		UserInfoPO userInfoPO=new UserInfoPO();
		List<UserInfoPO> users=userInfoDAO.findByField("userOpenID", request.getParameter("openid"));
		if(users.isEmpty()){
			userInfoPO.setUserNickname(new String(request.getParameter("nickname").getBytes("iso-8859-1"), "utf-8"));
			userInfoPO.setUserOpenID(request.getParameter("openid"));
			userInfoPO.setUserHeadimg("");
			userInfoPO.setUserPassword("");
			userInfoPO.setUserPhone("");
			userInfoPO.setUserIntegral("0");
			userInfoDAO.save(userInfoPO);
		}else{
			userInfoPO=users.get(0);
			userInfoPO.setUserNickname(new String(request.getParameter("nickname").getBytes("iso-8859-1"), "utf-8"));
			userInfoDAO.update(userInfoPO);
			userInfoDAO.flush();
		}
		
		request.getSession().setAttribute("userOpenId", userInfoPO.getUserOpenID());
		request.getSession().setAttribute("nickName", userInfoPO.getUserNickname());
		request.getSession().setAttribute("userId", userInfoPO.getId());
		return "redirect:index";
	}
	
	
}
