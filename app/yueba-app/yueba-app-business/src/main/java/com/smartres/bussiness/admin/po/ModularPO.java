package com.smartres.bussiness.admin.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_Modular")
public class ModularPO extends AbstractBasePO {
	//标识
	private String modularCode;
	
	// 模块名称
	private String modularName;
	
	//是否显示
	private int modularIsv;

	// 模块图片
	private String modularPic;
	
	public String getModularPic() {
		return modularPic;
	}

	public void setModularPic(String modularPic) {
		this.modularPic = modularPic;
	}

	public String getModularCode() {
		return modularCode;
	}

	public void setModularCode(String modularCode) {
		this.modularCode = modularCode;
	}

	public String getModularName() {
		return modularName;
	}

	public void setModularName(String modularName) {
		this.modularName = modularName;
	}

	public int getModularIsv() {
		return modularIsv;
	}

	public void setModularIsv(int modularIsv) {
		this.modularIsv = modularIsv;
	}

}
