package com.smartres.app.web.admin.vo;

import java.util.List;

import com.smartres.bussiness.admin.po.ModularPO;
import com.smartres.bussiness.admin.po.ProductPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ModularVO extends AbstractBaseVO<ModularVO, ModularPO>{
	//标识
		private String modularCode;
		
		//模块名称
		private String modularName;
		
		//是否显示
		private int modularIsv;
		
		// 模块图片
		private String modularPic;
        
		public String getModularPic() {
			return modularPic;
		}

		public ModularVO setModularPic(String modularPic) {
			this.modularPic = modularPic;
			return this;
		}

		
		
		//guanl
		private List<ProductVO> products;
		
		


		public List<ProductVO> getProducts() {
			return products;
		}

		public void setProducts(List<ProductVO> products) {
			this.products = products;
		}

		public String getModularCode() {
			return modularCode;
		}

		public ModularVO setModularCode(String modularCode) {
			this.modularCode = modularCode;
			return this;
		}

		public String getModularName() {
			return modularName;
		}

		public ModularVO setModularName(String modularName) {
			this.modularName = modularName;
			return this;
		}

		public int getModularIsv() {
			return modularIsv;
		}

		public ModularVO setModularIsv(int modularIsv) {
			this.modularIsv = modularIsv;
			return this;
		}

		@Override
		public ModularVO set(ModularPO entity) throws Exception {
			
			this.setId(entity.getId())
				.setUuid(entity.getUuid())
				.setModularCode(entity.getModularCode())
				.setModularName(entity.getModularName())
				.setModularIsv(entity.getModularIsv())
				.setModularPic(entity.getModularPic());
				
			return this;
		}
}
