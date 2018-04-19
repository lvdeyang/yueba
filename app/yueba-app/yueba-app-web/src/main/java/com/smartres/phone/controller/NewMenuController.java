package com.smartres.phone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smartres.app.web.admin.vo.MerchantVO;
import com.smartres.app.web.admin.vo.ModularVO;
import com.smartres.app.web.admin.vo.ProductVO;
import com.smartres.bussiness.admin.dao.ModularDAO;
import com.smartres.bussiness.admin.dao.ProductDAO;
import com.smartres.bussiness.admin.dao.SysConfigDAO;
import com.smartres.bussiness.admin.po.MerchantPO;
import com.smartres.bussiness.admin.po.ModularPO;
import com.smartres.bussiness.admin.po.ProductPO;
import com.smartres.bussiness.admin.po.SysConfigPO;

import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/newmenu")
public class NewMenuController extends BaseController {
	@Autowired
	ModularDAO modularDao;
	@Autowired
	ProductDAO productDao;
	@Autowired
	SysConfigDAO sysConfigDAO;
	@RequestMapping(value = "/index")
	public ModelAndView getAll() {
		Map<String, Object> strMap = new HashMap<String, Object>();
		List<ModularPO> modularPOs=modularDao.findAll();
		List<ModularVO> modularVOs=new ArrayList<ModularVO>();
		SysConfigPO sysConfigPO = sysConfigDAO.getSysConfig();
		try {
			modularVOs = ModularVO.getConverter(ModularVO.class).convert(modularPOs, ModularVO.class);
			for (ModularVO modularVO : modularVOs) {
				List<ProductPO> productPOs=productDao.getProByModular(modularVO.getModularCode(),getLoginMerchantId());
			    List<ProductVO> productVOs=ProductVO.getConverter(ProductVO.class).convert(productPOs, ProductVO.class);
			    for (ProductVO productVO : productVOs) {
			    	productVO.setProductShowPic(sysConfigPO.getWebUrl()+productVO.getProductShowPic());
				}
			    modularVO.setProducts(productVOs);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		strMap.put("modularList", modularVOs);
		ModelAndView mv = new ModelAndView("menu");
		mv.addAllObjects(strMap);
		return mv;
	}
}
