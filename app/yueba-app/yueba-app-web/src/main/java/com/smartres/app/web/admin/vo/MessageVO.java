package com.smartres.app.web.admin.vo;

import com.smartres.bussiness.admin.po.MenuPO;
import com.smartres.bussiness.admin.po.MessagePO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MessageVO extends AbstractBaseVO<MessageVO, MessagePO> {

	
	private String msg;
	private String tableNum;
	private long tableId;
	private int deal;//0未处理1已处理
	
	
	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getTableNum() {
		return tableNum;
	}


	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}


	public long getTableId() {
		return tableId;
	}


	public void setTableId(long tableId) {
		this.tableId = tableId;
	}


	public int getDeal() {
		return deal;
	}


	public void setDeal(int deal) {
		this.deal = deal;
	}


	@Override
	public MessageVO set(MessagePO entity) throws Exception {
		// TODO Auto-generated method stub
		this.setDeal(entity.getDeal());
		this.setTableId(entity.getTableId());
		this.setTableNum(entity.getTableNum());
		this.setMsg(entity.getMsg());
		return this;
	}

}
