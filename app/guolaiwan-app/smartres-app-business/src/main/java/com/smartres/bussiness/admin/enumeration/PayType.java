package com.smartres.bussiness.admin.enumeration;

public enum PayType {

	//默认渲染
	ALIPAY("支付宝"),
	
	//点击渲染
	WEICHAT("微信");
	
	private String name;
	
	private PayType(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public static PayType fromName(String name) throws Exception{
		if("支付宝".equals(name)){
			return ALIPAY;
		}else if("微信".equals(name)){
			return WEICHAT;
		}else {
			throw new Exception("错误的支付方式："+name);
		}
	}
	
	public static PayType fromString(String s) throws Exception{
		if("ALIPAY".equals(s)){
			return ALIPAY;
		}else if("WEICHAT".equals(s)){
			return WEICHAT;
		}else {
			throw new Exception("错误的支付方式："+s);
		}
	}
	
}
