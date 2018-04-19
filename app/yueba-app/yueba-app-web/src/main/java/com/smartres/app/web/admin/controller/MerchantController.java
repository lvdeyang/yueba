package com.smartres.app.web.admin.controller;

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

import com.smartres.app.web.admin.vo.MerchantVO;
import com.smartres.bussiness.admin.dao.MerchantDAO;
import com.smartres.bussiness.admin.dao.ModularDAO;
import com.smartres.bussiness.admin.dao.SysConfigDAO;
import com.smartres.bussiness.admin.dao.UserInfoDAO;
import com.smartres.bussiness.admin.enumeration.ShopAuditStateType;
import com.smartres.bussiness.admin.po.MerchantPO;
import com.smartres.bussiness.admin.po.SysConfigPO;
import com.smartres.bussiness.admin.po.UserInfoPO;
import com.smartres.vip.dao.ConfigDao;
import com.smartres.vip.po.ConfigPo;

import pub.caterpillar.commons.util.binary.Sha1Util;
import pub.caterpillar.mvc.controller.BaseController;

/**
 * 
 * <p>
 * Title: MerchantController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: guolaiwan
 * </p>
 * 
 * @author Mr.Sun
 * @date 2017年11月28日 上午9:12:37
 */

@Controller
@RequestMapping("/admin/merchant")
public class MerchantController extends BaseController {

	@Autowired
	private MerchantDAO conn_merchant;
	
	@Autowired
	private UserInfoDAO conn_userinfo;

    @Autowired
    private SysConfigDAO conn_sys;

	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private ConfigDao conn_config;

	// 商户列表页面
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String cityCode = getNewLoginInfo().getCityCode();
		String[] names = {"cityCode"};
		String[] values = {cityCode};
		int allcount = conn_merchant.getCountByPageA(names,values);
		strMap.put("allcount", allcount);
		strMap.put("allpages", GetAllPages(allcount, 5));
		ModelAndView mv = new ModelAndView("admin/merchant/list", strMap);
		return mv;
	}

	// 异步读取列表分页
	@ResponseBody
	@RequestMapping(value = "/list.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getList(int pagecurr, String name) throws Exception {
		String cityCode = getNewLoginInfo().getCityCode();
		String[] names = { "cityCode" };
		String[] values = { cityCode };
		List<MerchantPO> listpo = conn_merchant.getListByPageA(names, values, pagecurr, 10);
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}

	// 添加商户弹出窗口
	@RequestMapping("/addv")
	public String AddView() {

		return "admin/merchant/add";
	}

	// 添加数据
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {

		String shopName = request.getParameter("shopName");
		String shopLoginName = request.getParameter("shopLoginName");
		String shopLoginPwd = request.getParameter("shopLoginPwd");
		String shopHeading = request.getParameter("shopHeading");
		String shopQualifications = request.getParameter("shopQualifications");
		String shopQQ = request.getParameter("shopQQ");
		String shopTel = request.getParameter("shopTel");
		String shopAddress = request.getParameter("shopAddress");
		String shopBankId = request.getParameter("shopBankId");
		String shopOpenBank = request.getParameter("shopOpenBank");
		String shopLinkperson = request.getParameter("shopLinkperson");
		String shopPic = request.getParameter("shopPic");
		String shopMpic = request.getParameter("shopMpic");
		String shopIntroduction = request.getParameter("shopIntroduction");
		String shopLongitude = request.getParameter("shopLongitude");
		String shopLatitude = request.getParameter("shopLatitude");
		String modularName = request.getParameter("modularName");
		String modularCode = request.getParameter("modularCode");
		String modularClass = request.getParameter("modularClass");
		String modularClassId = request.getParameter("modularClassId");
		String shopAuditstates = request.getParameter("shopAuditstates");
		String cityCode = getNewLoginInfo().getCityCode();
		String cityName = getNewLoginInfo().getCityName();

		MerchantPO merchant = new MerchantPO();
		MerchantPO check = conn_merchant.getByField("shopLoginName", shopLoginName);
		if (check != null) {
			return "has";
		}
		
		merchant.setShopName(shopName);
		merchant.setShopLoginName(shopLoginName);
		merchant.setShopLoginPwd(Sha1Util.getSha1(shopLoginPwd));
		merchant.setShopHeading(shopHeading);
		merchant.setShopQualifications(shopQualifications);
		merchant.setShopQQ(shopQQ);
		merchant.setShopTel(shopTel);
		merchant.setShopAddress(shopAddress);
		merchant.setShopBankId(shopBankId);
		merchant.setShopOpenBank(shopOpenBank);
		merchant.setShopLinkperson(shopLinkperson);
		merchant.setShopPic(shopPic);
		merchant.setShopMpic(shopMpic);
		merchant.setShopIntroduction(shopIntroduction);
		merchant.setShopLongitude(shopLongitude);
		merchant.setShopLatitude(shopLatitude);
		merchant.setShopAuditState(ShopAuditStateType.fromString(shopAuditstates));
		merchant.setModularName(modularName);
		merchant.setModularCode(modularCode);
		merchant.setModularClass(modularClass);
		merchant.setModularClassId(modularClassId);
		merchant.setCityCode(cityCode);
		merchant.setCityName(cityName);

		conn_merchant.save(merchant);

		ConfigPo configPo=new ConfigPo();
		configPo.setOrderChangeRate(0d);
		configPo.setChangeRate(0d);
		configPo.setMerchantId(merchant.getId());
		conn_config.save(configPo);
		return "success";
	}

	// 验证登录名是否重复
	@ResponseBody
	@RequestMapping(value = "/verifyLoginName.do", method = RequestMethod.POST)
	public String IsLoginName(HttpServletRequest request) throws Exception {
		String shopLoginName = request.getParameter("shopLoginName");

		MerchantPO merchant = new MerchantPO();
		MerchantPO check = conn_merchant.getByField("shopLoginName", shopLoginName);
		if (check != null) {
			return "has";
		}

		return "success";
	}

	// 修改页面弹出窗口
	@RequestMapping(value = "/updatev", method = RequestMethod.GET)
	public ModelAndView updateView(HttpServletRequest request) {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);

		/*
		 * if(merchant.getShopAuditState() == ShopAuditStateType.T) {
		 * 
		 * }
		 */
		strMap.put("list", merchant);

		ModelAndView mv = new ModelAndView("admin/merchant/modify");
		mv.addAllObjects(strMap);

		return mv;
	}

	// 修改数据
	@ResponseBody
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String update(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);
		/*
		 * if(merchant.getShopAuditState() == ShopAuditStateType.T) { return
		 * "unable"; }
		 */
		String shopName = request.getParameter("shopName");
		String shopLoginName = request.getParameter("shopLoginName");
		String shopLoginPwd = request.getParameter("shopLoginPwd");
		String shopHeading = request.getParameter("shopHeading");
		String shopQualifications = request.getParameter("shopQualifications");
		String shopQQ = request.getParameter("shopQQ");
		String shopTel = request.getParameter("shopTel");
		String shopAddress = request.getParameter("shopAddress");
		String shopBankId = request.getParameter("shopBankId");
		String shopOpenBank = request.getParameter("shopOpenBank");
		String shopLinkperson = request.getParameter("shopLinkperson");
		String shopPic = request.getParameter("shopPic");
		String shopMpic = request.getParameter("shopMpic");
		String shopIntroduction = request.getParameter("shopIntroduction");
		String shopLongitude = request.getParameter("shopLongitude");
		String shopLatitude = request.getParameter("shopLatitude");
		String modularName = request.getParameter("modularName");
		String modularCode = request.getParameter("modularCode");
		String modularClass = request.getParameter("modularClass");
		String modularClassId = request.getParameter("modularClassId");
		String shopLoginPwdj = Sha1Util.getSha1(shopLoginPwd);
		String shopAuditstates = request.getParameter("shopAuditstates");
		
		
		merchant.setShopName(shopName);
		merchant.setShopLoginName(shopLoginName);
		merchant.setShopLoginPwd(shopLoginPwdj);
		merchant.setShopHeading(shopHeading);
		merchant.setShopQualifications(shopQualifications);
		merchant.setShopQQ(shopQQ);
		merchant.setShopTel(shopTel);
		merchant.setShopAddress(shopAddress);
		merchant.setShopBankId(shopBankId);
		merchant.setShopOpenBank(shopOpenBank);
		merchant.setShopLinkperson(shopLinkperson);
		merchant.setShopPic(shopPic);
		merchant.setShopMpic(shopMpic);
		merchant.setShopIntroduction(shopIntroduction);
		merchant.setShopLongitude(shopLongitude);
		merchant.setShopLatitude(shopLatitude);
		merchant.setModularName(modularName);
		merchant.setModularCode(modularCode);
		merchant.setModularClass(modularClass);
		merchant.setModularClassId(modularClassId);
		merchant.setShopAuditState(ShopAuditStateType.fromString(shopAuditstates));
		conn_merchant.update(merchant);

		return "success";
	}

	// 删除数据
	@ResponseBody
	@RequestMapping(value = "/del.do", method = RequestMethod.POST)
	public String del(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);
		conn_config.deleteByMerchantId(merchant.getId());
		conn_merchant.delete(merchant);
		return "success";
	}

	// 待审核商家列表页面
	@RequestMapping(value = "/checklist", method = RequestMethod.GET)
	public ModelAndView check(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int allcount = conn_merchant.countByField("shopAuditState", ShopAuditStateType.D);

		strMap.put("allcount", allcount);
		strMap.put("allpages", GetAllPages(allcount, 5));
		ModelAndView mv = new ModelAndView("admin/merchant/checklist", strMap);
		return mv;
	}

	// 待审核商家列表
	@ResponseBody
	@RequestMapping(value = "/checklist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getCheckList(int pagecurr) throws Exception {

		List<MerchantPO> listpo = conn_merchant.getFieldListbyPage(ShopAuditStateType.D, pagecurr, 5);
		// List<MerchantPO> listpo=conn_merchant.getListbyPage(pagecurr, 5);

		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}

	// 审核通过列表页面
	@RequestMapping(value = "/passlist", method = RequestMethod.GET)
	public ModelAndView checkc(HttpServletRequest request) {
		Map<String, Object> strMap = new HashMap<String, Object>();
		int checkcount = conn_merchant.countByField("shopAuditState", ShopAuditStateType.T);
		strMap.put("checkcount", checkcount);
		strMap.put("allpages", GetAllPages(checkcount, 5));
		ModelAndView mv = new ModelAndView("admin/merchant/passlist", strMap);
		return mv;
	}

	// 分城市查询
	@ResponseBody
	@RequestMapping(value = "/getMerByCity.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> countByCityp(HttpServletRequest request) throws Exception {
		String shopAuditState = request.getParameter("shopAuditState");
		String cityCode = request.getParameter("cityCode");
		int pagecurr = Integer.parseInt(request.getParameter("pagecurr"));
		Map<String, Object> newmap = new HashMap<String, Object>();

		newmap.put("cityCode", cityCode);
		newmap.put("shopAuditState", ShopAuditStateType.fromString(shopAuditState));

		List<MerchantPO> listpo = conn_merchant.getListByPageE(newmap, pagecurr, 10);
		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("getlist", listvo);
		return map;
	}

	// 分城市計數 getCountByCity.do
	@ResponseBody
	@RequestMapping(value = "/getCountByCity.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> countByCitypC(HttpServletRequest request) throws Exception {
		String cityCode = request.getParameter("cityCode");
		String shopAuditState = request.getParameter("shopAuditState");
		Map<String, Object> newmap = new HashMap<String, Object>();
		if (!cityCode.equals("-1")) {
			newmap.put("cityCode", cityCode);
		}
		newmap.put("shopAuditState", ShopAuditStateType.fromString(shopAuditState));
		int count = conn_merchant.getCountByCity(newmap);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("getcount", count);
		return map;
	}

	// 审核通过商家列表
	@ResponseBody
	@RequestMapping(value = "/passlist.do", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Map<String, Object> getCheckListc(int pagecurr) throws Exception {

		List<MerchantPO> listpo = conn_merchant.getFieldListbyPage(ShopAuditStateType.T, pagecurr, 5);
		// List<MerchantPO> listpo=conn_merchant.getListbyPage(pagecurr, 5);

		List<MerchantVO> listvo = MerchantVO.getConverter(MerchantVO.class).convert(listpo, MerchantVO.class);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}

	// 审核商家详情
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ModelAndView checkView(HttpServletRequest request) {

		Map<String, Object> strMap = new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		MerchantVO vo=new MerchantVO();
		try {
			vo.set(merchant);
			if(sysConfigPO!=null){
				vo.setShopHeading(sysConfigPO.getWebUrl()+vo.getShopHeading());
				vo.setShopQualifications(sysConfigPO.getWebUrl()+vo.getShopQualifications());
				vo.setShopPic(sysConfigPO.getWebUrl()+vo.getShopPic());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		strMap.put("list", vo);

		ModelAndView mv = new ModelAndView("admin/merchant/check");
		mv.addAllObjects(strMap);

		return mv;
	}

	// 审核结论
	@ResponseBody
	@RequestMapping(value = "/checkResult.do", method = RequestMethod.POST)
	public String checkResult(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		MerchantPO merchant = conn_merchant.get(uuid);

		String shopAuditState = request.getParameter("shopAuditState");
		String shopAuditopinion = request.getParameter("shopAuditopinion");

		merchant.setShopAuditState(ShopAuditStateType.fromString(shopAuditState));
		merchant.setShopAuditopinion(shopAuditopinion);

		conn_merchant.update(merchant);

		return "success";
	}

	@RequestMapping(value = "/sellist", method = RequestMethod.GET)
	public ModelAndView selhome(HttpServletRequest request) {
		String mcname = request.getParameter("mcname");
		String mcuuid = request.getParameter("mcuuid");
		Map<String, Object> strMap = new HashMap<String, Object>();
		int count = conn_merchant.getCountByPage();
		strMap.put("count", count);
		strMap.put("mcuuid", mcuuid);
		strMap.put("mcname", mcname);
		ModelAndView mv = new ModelAndView("admin/merchant/selectlist", strMap);
		return mv;
	}

	// 详情页面
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public ModelAndView info(HttpServletRequest request) {
		String uuid = request.getParameter("uuid");
		Map<String, Object> strMap = new HashMap<String, Object>();
		MerchantPO merchant = conn_merchant.get(uuid);
		SysConfigPO sysConfigPO=conn_sys.findAll().isEmpty()?null:conn_sys.findAll().get(0);
		
		MerchantVO vo=new MerchantVO();
		try {
			vo.set(merchant);
			if(sysConfigPO!=null){
				vo.setShopHeading(sysConfigPO.getWebUrl()+vo.getShopHeading());
				vo.setShopQualifications(sysConfigPO.getWebUrl()+vo.getShopQualifications());
				vo.setShopPic(sysConfigPO.getWebUrl()+vo.getShopPic());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		strMap.put("merchant", vo);
		ModelAndView mv = new ModelAndView("admin/merchant/info");
		mv.addAllObjects(strMap);
		return mv;
	}

}
