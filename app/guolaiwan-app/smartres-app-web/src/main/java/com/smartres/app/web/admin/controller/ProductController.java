package com.smartres.app.web.admin.controller;

import java.text.SimpleDateFormat;
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

import com.alibaba.fastjson.JSONObject;
import com.smartres.app.web.admin.vo.ProductVO;
import com.smartres.bussiness.admin.dao.MerchantDAO;
import com.smartres.bussiness.admin.dao.ModularDAO;
import com.smartres.bussiness.admin.dao.PictureDAO;
import com.smartres.bussiness.admin.dao.ProductDAO;
import com.smartres.bussiness.admin.dao.SysConfigDAO;
import com.smartres.bussiness.admin.enumeration.ShopAuditStateType;
import com.smartres.bussiness.admin.po.MerchantPO;

import com.smartres.bussiness.admin.po.ModularPO;
import com.smartres.bussiness.admin.po.PicturePO;
import com.smartres.bussiness.admin.po.ProductPO;
import com.smartres.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.controller.LoginInfo;


@Controller
@RequestMapping("/admin/product")
public class ProductController extends BaseController{

	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private MerchantDAO conn_merchant;

	@Autowired
	private ModularDAO conn_modular;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	//列表页面
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request){
		Map<String, Object> strMap=new HashMap<String, Object>();
		String cityCode = getNewLoginInfo().getCityCode();
		String[] names = {"productCityCode"};
		String[] values = {cityCode};
		int count = 0;
		
		LoginInfo loginInfo=getNewLoginInfo();
		if(loginInfo.getType()==0){
			count = conn_product.getCountByPageA(names,values);
		}else{
			count= conn_product.getCountByPageA(names,values,loginInfo.getLoginId());
		}
		
		
		List<ModularPO> modulars = conn_modular.findAll();

		strMap.put("count",count);
		strMap.put("modulars",modulars);
		strMap.put("cityCode",cityCode);


		ModelAndView mv = new ModelAndView("admin/product/list",strMap);
		return mv;
	}

	//增加页面
	@RequestMapping("/addv")
	public ModelAndView addProduct(){

		ModelAndView mv = new ModelAndView("admin/product/add");
		return mv;
	}


	//修改页面
	@RequestMapping(value="/infov",method= RequestMethod.GET)
	public ModelAndView productInfo(HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid =request.getParameter("uuid");
		ProductPO productp = conn_product.get(uuid);
		ProductVO productV = new ProductVO().set(productp);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		String beginDate = sdf.format(productp.getProductBeginDate());
		String ectiveDate = sdf.format(productp.getProductEctivedate());
		String endDate = sdf.format(productp.getProductEnddate());
		ModelAndView mv = new ModelAndView("admin/product/modify");
		strMap.put("product", productV);
		strMap.put("beginDate", beginDate);
		strMap.put("ectiveDate", ectiveDate);
		strMap.put("endDate", endDate);
		String[] pic = productp.getProductMorePic().split(",");
		for(int i = 1; i<=pic.length;i++){
			String imgstr = "img"+i;
			strMap.put(imgstr, pic[i-1]);
		}
		strMap.put("sysConfig", sysConfig);
		
		mv.addAllObjects(strMap);
		return mv;
	}

	//获取的数据城市
	@ResponseBody
	@RequestMapping(value="/proList.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getProList(int pagecurr) throws Exception {
		String cityCode = getNewLoginInfo().getCityCode();
		String[] names = {"productCityCode"};
		String[] values = {cityCode};
		List<ProductPO> listpo = new ArrayList<ProductPO>();
		LoginInfo loginInfo=getNewLoginInfo();
		if(loginInfo.getType()==0){
			listpo = conn_product.getListByPageA(names,values,pagecurr,10);
		}else{
			listpo = conn_product.getListByPageA(names,values,pagecurr,10,loginInfo.getLoginId());
		}
		

		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}



	//添加数据
	@ResponseBody
	@RequestMapping(value="/add.do", method= RequestMethod.POST)
	public String add(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		ProductPO product = new ProductPO();
		//名称、副标题、板块、分类、时间
		String modularCode = request.getParameter("modularCode");
		String modularName = request.getParameter("modularName");
		String productName = request.getParameter("productName");
		String modularClassId = request.getParameter("modularClassId");
		String modularClass = request.getParameter("modularClass");
		String productSubtitle = request.getParameter("productSubtitle");
		String productBeginDate = request.getParameter("productBeginDate");
		String productEnddate = request.getParameter("productEnddate");
		String productIntroduce = request.getParameter("productIntroduce");  
		String productEctivedate = request.getParameter("productEctivedate"); 

		String productMerchantID = request.getParameter("productMerchantID");  
		String productMerchantName = request.getParameter("productMerchantName");
		//提成
		int productCommissionCode = Integer.parseInt(request.getParameter("productCommissionCode")); 
		long productCommissionPrice =(long) (Double.parseDouble(request.getParameter("productCommissionPrice"))*100); 
		//浏览量、排序、销量、积分
		if(!(request.getParameter("productShowNum")==""||request.getParameter("productShowNum")==null)){
			long productShowNum = Long.parseLong(request.getParameter("productShowNum"));
			product.setProductShowNum(productShowNum);
		};
		if(!(request.getParameter("productSort")==""||request.getParameter("productSort")==null)){
			long productSort = Long.parseLong(request.getParameter("productSort"));
			product.setProductSort(productSort);;
		};
		if(!(request.getParameter("productSaleNum")==""||request.getParameter("productSaleNum")==null)){
			long productSaleNum = Long.parseLong(request.getParameter("productSaleNum"));
			product.setProductSaleNum(productSaleNum);;
		};
		if(!(request.getParameter("productntegral")==""||request.getParameter("productntegral")==null)){
			long productntegral = Long.parseLong(request.getParameter("productntegral"));
			product.setProductntegral(productntegral);
		};
		//原价、现价、库存
		long productOldPrice =(long) (Double.parseDouble(request.getParameter("productOldPrice"))*100);
		long productPrice =(long) (Double.parseDouble(request.getParameter("productPrice"))*100);
		long productStock = Long.parseLong(request.getParameter("productStock"));

		//显示、推荐
		int productIsShow =1;
		if(request.getParameter("productIsShow")==null)
			productIsShow=0;

		int productIndexRecommend =1;
		if(request.getParameter("like[productIndexRecommend]")==null)
			productIndexRecommend=0;

		int productListRecommend =1;
		if(request.getParameter("like[productListRecommend]")==null)
			productListRecommend=0;
		//限购
		int productLimitType;
		if(request.getParameter("productLimitType")==null){
			productLimitType=0;
		}else{
			productLimitType=1;
			long productLimitNum;
			if(request.getParameter("productLimitNum")==null){
				productLimitNum=0;
			}else{
				productLimitNum = Integer.parseInt(request.getParameter("productLimitNum"));
			}
			product.setProductLimitNum(productLimitNum);
		}
		product.setProductLimitType(productLimitType);
		//图片
		String shopMpic =request.getParameter("shopMpic");
		String shopPic =request.getParameter("shopPic");

		//审核状态
		String productAuditstates = request.getParameter("productAuditstates");

		//前端显示图片（路径）
		product.setProductShowPic(shopPic);
		//产品多图显示（路径）,
		product.setProductMorePic(shopMpic);
		//传入参数
		MerchantPO merchant = conn_merchant.get(productMerchantID);
		String productMerchantJson = JSONObject.toJSONString(merchant);
		product.setProductMerchantJson(productMerchantJson);;
		//商户ID
		product.setProductMerchantID(productMerchantID);
		product.setMerchantId(merchant.getId());
		//商户名称
		product.setProductMerchantName(productMerchantName);
		//提成模式（0：佣金1：比例）productCommissionCode
		product.setProductCommissionCode(productCommissionCode);
		//提成金额（佣金：分。比例百分比：10，20。。） productCommissionPrice
		product.setProductCommissionPrice(productCommissionPrice);
		//库存
		product.setProductStock(productStock);
		//商品名称
		product.setProductName(productName);
		//板块
		product.setProductModularCode(modularCode);
		product.setProductModularCodeName(modularName);
		product.setProductClassCode(modularClassId);
		product.setProductClassName(modularClass);
		product.setProductSubtitle(productSubtitle);
		product.setProductBeginDate(sdf.parse(productBeginDate));
		product.setProductEnddate(sdf.parse(productEnddate));
		product.setProductEctivedate(sdf.parse(productEctivedate));
		product.setProductIntroduce(productIntroduce);
		product.setProductIsShow(productIsShow);
		product.setProductIndexRecommend(productIndexRecommend);
		product.setProductListRecommend(productListRecommend);
		product.setProductOldPrice(productOldPrice);
		product.setProductPrice(productPrice);
		product.setUpdateTime(d);

		//审核
		product.setProductAuditstatus(ShopAuditStateType.fromName(productAuditstates));

		//城市
		product.setProductCityCode(getNewLoginInfo().getCityCode());
		product.setProductCityName(getNewLoginInfo().getCityName());

		//product.setMerchantId(getNewLoginInfo().getLoginId());

		conn_product.save(product);
		return "success";
	}

	//修改数据
	@ResponseBody
	@RequestMapping(value="/modify.do", method= RequestMethod.POST)
	public String modify(HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ProductPO product = conn_product.get(request.getParameter("uuid"));
		//名称、副标题、
		String productName = request.getParameter("productName");
		String productSubtitle = request.getParameter("productSubtitle");
		//板块、分类、
		String modularCode = request.getParameter("modularCode");
		String modularName = request.getParameter("modularName");
		String modularClassId = request.getParameter("modularClassId");
		String modularClass = request.getParameter("modularClass");

		//商家
		String productMerchantID = request.getParameter("productMerchantID");  
		String productMerchantName = request.getParameter("productMerchantName");

		//原价、现价、库存
		long productOldPrice =(long) (Double.parseDouble(request.getParameter("productOldPrice"))*100);
		long productPrice =(long) (Double.parseDouble(request.getParameter("productPrice"))*100);
		long productStock = Long.parseLong(request.getParameter("productStock"));

		//时间
		String productBeginDate = request.getParameter("productBeginDate");
		String productEnddate = request.getParameter("productEnddate");
		String productEctivedate = request.getParameter("productEctivedate"); 

		//图片
		String shopMpic =request.getParameter("shopMpic");
		String shopPic =request.getParameter("shopPic");
		//提成
		int productCommissionCode = Integer.parseInt(request.getParameter("productCommissionCode")); 
		long productCommissionPrice =(long) (Double.parseDouble(request.getParameter("productCommissionPrice"))*100);

		//显示、推荐
		int productIsShow =1;
		if(request.getParameter("productIsShow")==null)
			productIsShow=0;
		int productIndexRecommend =1;
		if(request.getParameter("like[productIndexRecommend]")==null)
			productIndexRecommend=0;
		int productListRecommend =1;
		if(request.getParameter("like[productListRecommend]")==null)
			productListRecommend=0;

		//限购
		int productLimitType;
		if(request.getParameter("productLimitType")==null){
			productLimitType=0;
		}else{
			productLimitType=1;
			long productLimitNum;
			if(request.getParameter("productLimitNum")==null){
				productLimitNum=0;
			}else{
				productLimitNum = Integer.parseInt(request.getParameter("productLimitNum"));
			}
			product.setProductLimitNum(productLimitNum);
		}
		product.setProductLimitType(productLimitType);

		//浏览量、排序、销量、积分
		if(!(request.getParameter("productShowNum")==""||request.getParameter("productShowNum")==null)){
			long productShowNum = Long.parseLong(request.getParameter("productShowNum"));
			product.setProductShowNum(productShowNum);
		};
		if(!(request.getParameter("productSort")==""||request.getParameter("productSort")==null)){
			long productSort = Long.parseLong(request.getParameter("productSort"));
			product.setProductSort(productSort);;
		};
		if(!(request.getParameter("productSaleNum")==""||request.getParameter("productSaleNum")==null)){
			long productSaleNum = Long.parseLong(request.getParameter("productSaleNum"));
			product.setProductSaleNum(productSaleNum);;
		};
		if(!(request.getParameter("productntegral")==""||request.getParameter("productntegral")==null)){
			long productntegral = Long.parseLong(request.getParameter("productntegral"));
			product.setProductntegral(productntegral);
		};

		//介绍
		String productIntroduce = request.getParameter("productIntroduce");

		//传入参数
		//商户ID
		MerchantPO merchant = conn_merchant.get(productMerchantID);
		String productMerchantJson = JSONObject.toJSONString(merchant);

		//审核状态
		String productAuditstates = request.getParameter("productAuditstates");


		//商品、副标题
		product.setProductName(productName);
		product.setProductSubtitle(productSubtitle);

		//板块
		product.setProductModularCode(modularCode);
		product.setProductModularCodeName(modularName);
		product.setProductClassCode(modularClassId);
		product.setProductClassName(modularClass);

		//商家
		product.setProductMerchantID(productMerchantID);
		product.setProductMerchantName(productMerchantName);
		product.setProductMerchantJson(productMerchantJson);

		//原价、现价
		product.setProductOldPrice(productOldPrice);
		product.setProductPrice(productPrice);
		product.setProductStock(productStock);

		//时间
		product.setProductBeginDate(sdf.parse(productBeginDate));
		product.setProductEnddate(sdf.parse(productEnddate));
		product.setProductEctivedate(sdf.parse(productEctivedate));

		//图片
		product.setProductShowPic(shopPic);
		product.setProductMorePic(shopMpic);

		//提成
		product.setProductCommissionCode(productCommissionCode);
		product.setProductCommissionPrice(productCommissionPrice);

		//显示推荐
		product.setProductIsShow(productIsShow);
		product.setProductIndexRecommend(productIndexRecommend);
		product.setProductListRecommend(productListRecommend);

		//介绍
		product.setProductIntroduce(productIntroduce);

		//审核
		product.setProductAuditstatus(ShopAuditStateType.fromString(productAuditstates));


		product.setMerchantId(getNewLoginInfo().getLoginId());
		conn_product.saveOrUpdate(product);
		return "success";
	}


	//模块加载类
	@ResponseBody
	@RequestMapping(value="/getProBymc.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getProBymc(String modularCode) throws Exception {
		Map<String, Object> map= new HashMap<String, Object>();
		return map;
	}

	//待审核列表
	@RequestMapping("/checklist")
	public ModelAndView auditProduct(){
		Map<String, Object> strMap=new HashMap<String, Object>();
		
		int acount = 0;
		
		LoginInfo loginInfo=getNewLoginInfo();
		if(loginInfo.getType()==0){
			acount = conn_product.countByField("productAuditstatus", ShopAuditStateType.D);
		}else{
			acount = conn_product.countByMerchant(ShopAuditStateType.D,loginInfo.getLoginId());
		}
		List<ModularPO> modulars = conn_modular.findAll();
		strMap.put("modulars",modulars);
		strMap.put("acount",acount);
		ModelAndView mv = new ModelAndView("admin/product/checklist");
		mv.addAllObjects(strMap);
		return mv;
	}



	//获取待审核的全部数据
	@ResponseBody
	@RequestMapping(value="/checklist.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> auditList(int pagecurr) throws Exception {
		List<ProductPO> listpo = new ArrayList<ProductPO>();
		
		LoginInfo loginInfo=getNewLoginInfo();
		if(loginInfo.getType()==0){
			listpo = conn_product.getProByaud(ShopAuditStateType.D,pagecurr,10);
		}else{
			listpo = conn_product.getProByaud(ShopAuditStateType.D,pagecurr,10,loginInfo.getLoginId());
		}
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("list", listvo);
		return map;
	}


	//审核通过列表
	@RequestMapping("/passlist")
	public ModelAndView passList(){
		Map<String, Object> strMap=new HashMap<String, Object>();
		int pcount = 0;
		
		LoginInfo loginInfo=getNewLoginInfo();
		if(loginInfo.getType()==0){
			pcount = conn_product.countByField("productAuditstatus", ShopAuditStateType.T);
		}else{
			pcount = conn_product.countByMerchant(ShopAuditStateType.T,loginInfo.getLoginId());
		}
		
		List<ModularPO> modulars = conn_modular.findAll();
		strMap.put("modulars",modulars);
		strMap.put("pcount",pcount);
		ModelAndView mv = new ModelAndView("admin/product/passlist");
		mv.addAllObjects(strMap);
		return mv;
	}



	//获取审核通过的全部数据
	@ResponseBody
	@RequestMapping(value="/passlist.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> getPassList(int pagecurr) throws Exception {
		List<ProductPO> listpo = conn_product.getProByaud(ShopAuditStateType.T,pagecurr,10);
		
		LoginInfo loginInfo=getNewLoginInfo();
		if(loginInfo.getType()==0){
			listpo = conn_product.getProByaud(ShopAuditStateType.T,pagecurr,10);
		}else{
			listpo = conn_product.getProByaud(ShopAuditStateType.T,pagecurr,10,loginInfo.getLoginId());
		}
		
		
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("passlist", listvo);
		return map;
	}

	//审核页面
	@RequestMapping(value="/check" ,method= RequestMethod.GET)
	public ModelAndView check(HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SysConfigPO sysConfig =  conn_sysConfig.getSysConfig();
		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		ProductPO product = conn_product.get(uuid);
		ProductVO productV = new ProductVO().set(product);
		String beginDate = sdf.format(product.getProductBeginDate());
		String ectiveDate = sdf.format(product.getProductEctivedate());
		String endDate = sdf.format(product.getProductEnddate());
		strMap.put("product",productV);
		strMap.put("beginDate",beginDate);
		strMap.put("ectiveDate",ectiveDate);
		strMap.put("endDate",endDate);
		strMap.put("sysConfig",sysConfig);
		ModelAndView mv = new ModelAndView("admin/product/check");
		mv.addAllObjects(strMap);
		return mv;
	}

	//审核按钮 
	@ResponseBody
	@RequestMapping(value="/audit.do", method= RequestMethod.POST)
	public String audit(HttpServletRequest request) throws Exception {
		ProductPO prodcut = conn_product.get(request.getParameter("uuid"));
		String pass = request.getParameter("pass");
		String advice = request.getParameter("advice");
		if(pass.equals("yes")){
			prodcut.setProductAuditstatus(ShopAuditStateType.T);
		}
		if(pass.equals("no")){
			prodcut.setProductAuditstatus(ShopAuditStateType.N);
		}
		prodcut.setProductAuditAdvice(advice);
		conn_product.saveOrUpdate(prodcut);
		return "success";
	}

	//详情页面
	@RequestMapping(value="/info" ,method= RequestMethod.GET)
	public ModelAndView info(HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> strMap=new HashMap<String, Object>();
		String uuid = request.getParameter("uuid");
		ProductPO product = conn_product.get(uuid);
		ProductVO productV = new ProductVO().set(product);
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();
		String beginDate = sdf.format(product.getProductBeginDate());
		String ectiveDate = sdf.format(product.getProductEctivedate());
		String endDate = sdf.format(product.getProductEnddate());
		strMap.put("product",productV);
		strMap.put("beginDate",beginDate);
		strMap.put("ectiveDate",ectiveDate);//sysConfig
		strMap.put("endDate",endDate);
		strMap.put("sysConfig",sysConfig);
		ModelAndView mv = new ModelAndView("admin/product/info");
		mv.addAllObjects(strMap);
		return mv;
	}

	//查询
	@ResponseBody
	@RequestMapping(value="/getProductBy.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> countByCityp(HttpServletRequest request) throws Exception {
		Map<String, Object> newmap= new HashMap<String, Object>();
		String productAuditstates = request.getParameter("productAuditstates");
		String cityCode = request.getParameter("cityCode");
		String productName = request.getParameter("productName");
		String shopName = request.getParameter("shopName");
		String modularCode = request.getParameter("modularCode");
		String classCode = request.getParameter("classCode");

		if(cityCode!=null&&cityCode.length()>0){
			newmap.put("productCityCode", cityCode);
		}
		if(productName!=null&&productName.length()>0){
			newmap.put("productName", productName);
		}
		if(shopName!=null&&shopName.length()>0){
			newmap.put("productMerchantName", shopName);
		}
		if(modularCode!=null&&modularCode.length()>0){
			newmap.put("productModularCode", modularCode);
		}
		if(classCode!=null&&classCode.length()>0){
			newmap.put("productClassCode", classCode);
		}
		if(productAuditstates!=null&&productAuditstates!=""){
			newmap.put("productAuditstatus", ShopAuditStateType.fromString(productAuditstates));
		}

		int pagecurr = Integer.parseInt(request.getParameter("pagecurr"));


		List<ProductPO> listpo = conn_product.getListByPageE(newmap,pagecurr,10);
		
		LoginInfo loginInfo=getNewLoginInfo();
		if(loginInfo.getType()==0){
			listpo = conn_product.getListByPageE(newmap,pagecurr,10);
		}else{
			listpo = conn_product.getListByPageE(newmap,pagecurr,10,loginInfo.getLoginId());
		}
		
		
		List<ProductVO> listvo = ProductVO.getConverter(ProductVO.class).convert(listpo, ProductVO.class);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("getlist", listvo);
		return map;
	}

	//查询个数
	@ResponseBody
	@RequestMapping(value="/getProductByC.do", method= RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Map<String, Object> countByCitypC(HttpServletRequest request) throws Exception {
		Map<String, Object> newmap= new HashMap<String, Object>();
		String productAuditstates = request.getParameter("productAuditstates");
		String cityCode = request.getParameter("cityCode");
		String productName = request.getParameter("productName");
		String shopName = request.getParameter("shopName");
		String modularCode = request.getParameter("modularCode");
		String classCode = request.getParameter("classCode");

		if(cityCode!=null&&cityCode.length()>0){
			newmap.put("productCityCode", cityCode);
		}
		if(productName!=null&&productName.length()>0){
			newmap.put("productName", productName);
		}
		if(shopName!=null&&shopName.length()>0){
			newmap.put("productMerchantName", shopName);
		}
		if(modularCode!=null&&modularCode.length()>0){
			newmap.put("productModularCode", modularCode);
		}
		if(classCode!=null&&classCode.length()>0){
			newmap.put("productClassCode", classCode);
		}
		if(productAuditstates!=null&&productAuditstates.length()>0){
			newmap.put("productAuditstatus", ShopAuditStateType.fromString(productAuditstates));
		}
		int count = conn_product.getCountByPageE(newmap);
		
		LoginInfo loginInfo=getNewLoginInfo();
		if(loginInfo.getType()==0){
			count = conn_product.getCountByPageE(newmap);
		}else{
			count = conn_product.getCountByPageE(newmap,loginInfo.getLoginId());
		}
		
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("getcount", count);
		return map;
	}
	
	
	//删除产品
	@ResponseBody
	@RequestMapping(value="prodel.do", method= RequestMethod.POST)
	public String proDel(HttpServletRequest request) throws Exception {
		String uuid = request.getParameter("uuid");
		conn_product.deleteByUuid(uuid);
		return "success";
	}



}
