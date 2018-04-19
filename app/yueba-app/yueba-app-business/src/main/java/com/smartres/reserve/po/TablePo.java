package com.smartres.reserve.po;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import pub.caterpillar.orm.po.AbstractBasePO;
@Entity
@Table(name = "t_smart_table")
public class TablePo extends AbstractBasePO{
	private String num;
	private int count;
	private int status;//0表示未用，1表示占用,2表示预定
    private long userId;
    private long merchantId;
    private long reversePrice; 

   
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getReversePrice() {
		return reversePrice;
	}
	public void setReversePrice(long reversePrice) {
		this.reversePrice = reversePrice;
	}
	

}
