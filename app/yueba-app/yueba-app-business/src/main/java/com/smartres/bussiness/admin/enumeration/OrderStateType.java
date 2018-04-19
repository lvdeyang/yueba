package com.smartres.bussiness.admin.enumeration;

public enum OrderStateType {

	//商品类型
	NOTPAY("未付款"),
	
	//支付成功
	PAYSUCCESS("支付成功"),
	
	//支付完成
	PAYFINISH("支付完成"),

	//已验单
	TESTED("已验单"),

	//数量过滤
	REFUNDING("申请退款"),

	//数量过滤
	REFUNDFAIL("退款失败"),

	//数量过滤
	REFUNDED("退款成功");



	private String name;

	private OrderStateType(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public static OrderStateType fromName(String name) throws Exception{
		if("未付款".equals(name)){
			return NOTPAY;
		}else if("支付成功".equals(name)){
			return PAYSUCCESS;
		}else if("支付完成".equals(name)){
			return PAYFINISH;
		}else if("已验单".equals(name)){
			return TESTED;
		}else if("申请退款".equals(name)){
			return REFUNDING;
		}else if("退款失败".equals(name)){
			return REFUNDFAIL;
		}else if("退款成功".equals(name)){
			return REFUNDED;
		}else {
			throw new Exception("错误的订单状态："+name);
		}
	}

	public static OrderStateType fromString(String s) throws Exception{
		if("NOTPAY".equals(s)){
			return NOTPAY;
		}else if("PAYSUCCESS".equals(s)){
			return PAYSUCCESS;
		}else if("PAYFINISH".equals(s)){
			return PAYFINISH;
		}else if("TESTED".equals(s)){
			return TESTED;
		}else if("REFUNDING".equals(s)){
			return REFUNDING;
		}else if("REFUNDFAIL".equals(s)){
			return REFUNDFAIL;
		}else if("REFUNDED".equals(s)){
			return REFUNDED;
		}else {
			throw new Exception("错误的订单状态："+s);
		}
	}

}
