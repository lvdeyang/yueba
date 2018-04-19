package com.smartres.vip.po;

import javax.persistence.Entity;
import javax.persistence.Table;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_smart_config")
public class ConfigPo extends AbstractBasePO{
	private double changeRate;
	private double orderChangeRate;
    private long merchantId;
    private String cardDes;
    
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public double getChangeRate() {
		return changeRate;
	}
	public void setChangeRate(double changeRate) {
		this.changeRate = changeRate;
	}
	public double getOrderChangeRate() {
		return orderChangeRate;
	}
	public void setOrderChangeRate(double orderChangeRate) {
		this.orderChangeRate = orderChangeRate;
	}
	public String getCardDes() {
		return cardDes;
	}
	public void setCardDes(String cardDes) {
		this.cardDes = cardDes;
	}
	
	

}
