package com.smartres.app.web.admin.vo;

import java.util.ArrayList;
import java.util.List;

public class ReverseVO {

	private long merchantId;
	private String merchantName;
	private List<TableVO> tableVOs=new ArrayList<TableVO>();
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public List<TableVO> getTableVOs() {
		return tableVOs;
	}
	public void setTableVOs(List<TableVO> tableVOs) {
		this.tableVOs = tableVOs;
	}
	
}
