package com.smartres.app.web.admin.vo;

import java.util.ArrayList;
import java.util.List;

public class TableWithOrderVO {
	private long id;
	private String num;
	private List<OrderInfoVO> orderInfoVOs=new ArrayList<OrderInfoVO>();
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public List<OrderInfoVO> getOrderInfoVOs() {
		return orderInfoVOs;
	}
	public void setOrderInfoVOs(List<OrderInfoVO> orderInfoVOs) {
		this.orderInfoVOs = orderInfoVOs;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

}
