package com.smartres.app.web.admin.vo;

import java.util.ArrayList;
import java.util.List;
import com.smartres.reserve.po.TablePo;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class TableVO extends AbstractBaseVO<TableVO, TablePo> {
	private String num;
	private int count;
	private long userId;
	private String userName;
	private String status;
	private long total=0;
	private String waitingForPrint;
	private String waitingForPay;
	private String waitingForPayPrint;
    private long reversePrice;
    private List<OrderInfoVO> orderInfoVOs=new ArrayList<OrderInfoVO>();
	
	

	public List<OrderInfoVO> getOrderInfoVOs() {
		return orderInfoVOs;
	}



	public void setOrderInfoVOs(List<OrderInfoVO> orderInfoVOs) {
		this.orderInfoVOs = orderInfoVOs;
	}



	public String getWaitingForPrint() {
		return waitingForPrint;
	}



	public void setWaitingForPrint(String waitingForPrint) {
		this.waitingForPrint = waitingForPrint;
	}



	public String getWaitingForPay() {
		return waitingForPay;
	}



	public void setWaitingForPay(String waitingForPay) {
		this.waitingForPay = waitingForPay;
	}



	public long getTotal() {
		return total;
	}



	public void setTotal(long total) {
		this.total = total;
	}




	public String getNum() {
		return num;
	}



	public void setNum(String num) {
		this.num = num;
	}



	public int getCount() {
		return count;
	}



	public void setCount(int count) {
		this.count = count;
	}



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public TableVO set(TablePo entity) throws Exception {
		// TODO Auto-generated method stub
		this.setCount(entity.getCount());
		this.setNum(entity.getNum());
		this.setId(entity.getId());
		if(entity.getStatus()==0){
			this.setStatus("未使用");
		}else if(entity.getStatus()==1){
			this.setStatus("使用中");
		}else{
			this.setStatus("已预定");
		}
		this.setUserId(entity.getUserId());
		this.setReversePrice(entity.getReversePrice());
		return this;
	}



	public String getWaitingForPayPrint() {
		return waitingForPayPrint;
	}



	public void setWaitingForPayPrint(String waitingForPayPrint) {
		this.waitingForPayPrint = waitingForPayPrint;
	}



	public long getReversePrice() {
		return reversePrice;
	}



	public void setReversePrice(long reversePrice) {
		this.reversePrice = reversePrice;
	}

}
