package com.smartres.app.web.admin.vo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.smartres.bussiness.admin.po.OrderInfoPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class OrderInfoVO extends AbstractBaseVO<OrderInfoVO,OrderInfoPO>{

	//订单号
	private String orderNO;
	//验单码
	private String ydNO;
	//下单时间
	private String createDate;
	//验单时间
	private String ydDate;
	//供应商ID
	private long shopId;
	//供应商名称
	private String shopName;
	//站点ID
	private int siteCode;
	//站点名称
	private String siteName;
	//产品ID
	private long productId;
	//产品名称
	private String productName;
	//产品数量
	private long productNum;
	//商品单价
	private long productPrice;
	//所属板块ID
	private String bkCode;
	//所属板块名称
	private String bkName;
	//套餐ID
	private String attributeID;
	//套餐名称
	private String attributeName;
	//会员ID
	private long userId;
	//会员手机号
	private String userTel;
	//会员信息
	private String userInfo;
	//会员坐标经度
	private String userlongitude;
	//会员坐标维度
	private String userlatitude;
	//订单总金额
	private String orderAllMoney;
	//提成方式
	private int royaltyName;
	//提成比例
	private long proportion;
	//积分数
	private long integralNum;

	//订单佣金金额
	private String proportionMoney;
	//支付金额
	private String payMoney;
	//订单说明
	private String orderRemark;

	//支付方式
	private String payMode;

	//订单状态
	private String orderState;


	//商品图片
	private String productPic;
	//有效期
	private String validityDate;
	//是否评价
	private long commentIs;
	//预定日期
	private String orderBookDate;
	//收货地址
	private long mailAddress;
	
	private long merchantId;
	    
	private long tableId;
	    
	private int confirm;//0未确认，1确认订单

	public String getOrderNO() {
		return orderNO;
	}

	public OrderInfoVO setOrderNO(String orderNO) {
		this.orderNO = orderNO;
		return this;
	}

	public String getYdNO() {
		return ydNO;
	}

	public OrderInfoVO setYdNO(String ydNO) {
		this.ydNO = ydNO;
		return this;
	}

	public String getCreateDate() {
		return createDate;
	}

	public OrderInfoVO setCreateDate(String createDate) {
		this.createDate = createDate;
		return this;
	}

	public String getYdDate() {
		return ydDate;
	}

	public OrderInfoVO setYdDate(String ydDate) {
		this.ydDate = ydDate;
		return this;
	}

	public long getShopId() {
		return shopId;
	}

	public OrderInfoVO setShopId(long shopId) {
		this.shopId = shopId;
		return this;
	}

	public String getShopName() {
		return shopName;
	}

	public OrderInfoVO setShopName(String shopName) {
		this.shopName = shopName;
		return this;
	}

	public int getSiteCode() {
		return siteCode;
	}

	public OrderInfoVO setSiteCode(int siteCode) {
		this.siteCode = siteCode;
		return this;
	}

	public String getSiteName() {
		return siteName;
	}

	public OrderInfoVO setSiteName(String siteName) {
		this.siteName = siteName;
		return this;
	}


	public long getProductId() {
		return productId;
	}

	public OrderInfoVO setProductId(long productId) {
		this.productId = productId;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public OrderInfoVO setProductName(String productName) {
		this.productName = productName;
		return this;
	}

	public long getProductNum() {
		return productNum;
	}

	public OrderInfoVO setProductNum(long productNum) {
		this.productNum = productNum;
		return this;
	}

	public long getProductPrice() {
		return productPrice;
	}

	public OrderInfoVO setProductPrice(long productPrice) {
		this.productPrice = productPrice;
		return this;
	}

	public String getBkCode() {
		return bkCode;
	}

	public OrderInfoVO setBkCode(String bkCode) {
		this.bkCode = bkCode;
		return this;
	}

	public String getBkName() {
		return bkName;
	}

	public OrderInfoVO setBkName(String bkName) {
		this.bkName = bkName;
		return this;
	}

	public String getAttributeID() {
		return attributeID;
	}


	public OrderInfoVO setAttributeID(String attributeID) {
		this.attributeID = attributeID;
		return this;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public OrderInfoVO setAttributeName(String attributeName) {
		this.attributeName = attributeName;
		return this;
	}

	public long getUserId() {
		return userId;
	}

	public OrderInfoVO setUserId(long userId) {
		this.userId = userId;
		return this;
	}

	public String getUserTel() {
		return userTel;
	}

	public OrderInfoVO setUserTel(String userTel) {
		this.userTel = userTel;
		return this;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public OrderInfoVO setUserInfo(String userInfo) {
		this.userInfo = userInfo;
		return this;
	}

	public String getUserlongitude() {
		return userlongitude;
	}

	public OrderInfoVO setUserlongitude(String userlongitude) {
		this.userlongitude = userlongitude;
		return this;
	}

	public String getUserlatitude() {
		return userlatitude;
	}

	public OrderInfoVO setUserlatitude(String userlatitude) {
		this.userlatitude = userlatitude;
		return this;
	}



	public long getProportion() {
		return proportion;
	}

	public OrderInfoVO setProportion(long proportion) {
		this.proportion = proportion;
		return this;
	}


	public int getRoyaltyName() {
		return royaltyName;
	}

	public OrderInfoVO setRoyaltyName(int royaltyName) {
		this.royaltyName = royaltyName;
		return this;
	}

	public long getIntegralNum() {
		return integralNum;
	}

	public OrderInfoVO setIntegralNum(long integralNum) {
		this.integralNum = integralNum;
		return this;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public OrderInfoVO setValidityDate(String validityDate) {
		this.validityDate = validityDate;
		return this;
	}

	public long getCommentIs() {
		return commentIs;
	}

	public OrderInfoVO setCommentIs(long commentIs) {
		this.commentIs = commentIs;
		return this;
	}

	public String getOrderBookDate() {
		return orderBookDate;
	}

	public OrderInfoVO setOrderBookDate(String orderBookDate) {
		this.orderBookDate = orderBookDate;
		return this;
	}






	public String getOrderRemark() {
		return orderRemark;
	}

	public OrderInfoVO setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
		return this;
	}

	public String getPayMode() {
		return payMode;
	}

	public OrderInfoVO setPayMode(String payMode) {
		this.payMode = payMode;
		return this;
	}

	public String getOrderState() {
		return orderState;
	}

	public OrderInfoVO setOrderState(String orderState) {
		this.orderState = orderState;
		return this;
	}

	public String getProductPic() {
		return productPic;
	}

	public OrderInfoVO setProductPic(String productPic) {
		this.productPic = productPic;
		return this;
	}


	public long getMailAddress() {
		return mailAddress;
	}

	public OrderInfoVO setMailAddress(long mailAddress) {
		this.mailAddress = mailAddress;
		return this;
	}


	public String getProportionMoney() {
		return proportionMoney;
	}

	public OrderInfoVO setProportionMoney(String proportionMoney) {
		this.proportionMoney = proportionMoney;
		return this;
	}

	public String getOrderAllMoney() {
		return orderAllMoney;
	}

	public OrderInfoVO setOrderAllMoney(String orderAllMoney) {
		this.orderAllMoney = orderAllMoney;
		return this;
	}

	public String getPayMoney() {
		return payMoney;
	}

	public OrderInfoVO setPayMoney(String payMoney) {
		this.payMoney = payMoney;
		return this;
	}
	




	@Override
	public OrderInfoVO set(OrderInfoPO entity)throws Exception{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		DecimalFormat def = new DecimalFormat("0.00");
		String ydDate =entity.getYdDate()==null?"":df.format(entity.getYdDate());
		String validityDate =entity.getValidityDate()==null?"":df.format(entity.getValidityDate());
		String OrderBookDate =entity.getOrderBookDate()==null?"":df.format(entity.getOrderBookDate());
		String payMode = entity.getPayMode()==null?"":entity.getPayMode().getName();
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setOrderNO(entity.getOrderNO())
		.setYdNO(entity.getYdNO())
		.setCreateDate(df.format(entity.getCreateDate()))
		.setYdDate(ydDate)
		.setShopId(entity.getShopId())
		.setShopName(entity.getShopName())
		.setSiteCode(entity.getSiteCode())
		.setSiteName(entity.getSiteName())
		.setProductId(entity.getProductId())
		.setProductName(entity.getProductName())
		.setProductNum(entity.getProductNum())
		.setProductPrice(entity.getProductPrice())
		.setBkCode(entity.getBkCode())
		.setBkName(entity.getBkName())
		.setAttributeID(entity.getAttributeID())
		.setAttributeName(entity.getAttributeName())
		.setUserId(entity.getUserId())
		.setUserTel(entity.getUserTel())
		.setUserlongitude(entity.getUserlongitude())
		.setUserlatitude(entity.getUserlatitude())
		.setOrderAllMoney(def.format((double)(entity.getOrderAllMoney()/100)))
		.setRoyaltyName(entity.getRoyaltyName())
		.setIntegralNum(entity.getIntegralNum())
		.setValidityDate(validityDate)
		.setCommentIs(entity.getCommentIs())
		.setOrderBookDate(OrderBookDate)
		.setProportion(entity.getProportion())
		.setProportionMoney(def.format((double)(entity.getProportionMoney()/100)))
		.setPayMoney(def.format((double)(entity.getPayMoney()/100)))
		.setOrderRemark(entity.getOrderRemark())
		.setPayMode(payMode)
		.setOrderState(entity.getOrderState().getName())
		.setTableId(entity.getTableId())
		.setMerchantId(entity.getMerchantId())
		.setConfirm(entity.getConfirm())
        .setProductPic(entity.getProductPic())
		.setMailAddress(entity.getMailAddress());
		return this;

	}

	public long getMerchantId() {
		return merchantId;
	}

	public OrderInfoVO setMerchantId(long merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	public long getTableId() {
		return tableId;
	}

	public OrderInfoVO setTableId(long tableId) {
		this.tableId = tableId;
		return this;
	}

	public int getConfirm() {
		return confirm;
	}

	public OrderInfoVO setConfirm(int confirm) {
		this.confirm = confirm;
		return this;
	}
	
	
}


