package com.smartres.bussiness.admin.enumeration;

public enum OrderSource {
	//网页
	WEBPAGE("网页"),

	//直播来源
	LIVE("直播");

	
	


	private String name;

	private OrderSource(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public static OrderSource fromName(String name) throws Exception{
		if("网页".equals(name)){
			return WEBPAGE;
		}else if("直播".equals(name)){
			return LIVE;
		
		}else {
			throw new Exception("错误的订单来源："+name);
		}
	}

	public static OrderSource fromString(String s) throws Exception{
		if("WEBPAGE".equals(s)){
			return WEBPAGE;
		}else if("LIVE".equals(s)){
			return LIVE;
		} 
		else
		{
			throw new Exception("错误的订单来源："+s);
		}
	}

}


