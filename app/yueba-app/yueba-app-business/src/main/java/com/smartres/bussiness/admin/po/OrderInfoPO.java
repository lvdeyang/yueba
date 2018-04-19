package com.smartres.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.smartres.bussiness.admin.enumeration.OrderSource;
import com.smartres.bussiness.admin.enumeration.OrderStateType;
import com.smartres.bussiness.admin.enumeration.PayType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_Sys_OrderInfo")
public class OrderInfoPO extends AbstractBasePO{
	//订单号1
	private String orderNO;
	//验单码2
	private String ydNO;
	//下单时间3
	private Date createDate;
	//验单时间4
	private Date ydDate;
	//供应商ID5
	private long shopId;
	//供应商名称6
	private String shopName;
	//站点ID7
	private int siteCode;
	//站点名称8
	private String siteName;
	//产品ID9
	private long productId;
	//产品名称10
	private String productName;
	//产品数量11
	private long productNum;
	//商品单价12
	private long productPrice;
	//所属板块ID13
	private String bkCode;
	//所属板块名称14
	private String bkName;
	//套餐ID15
	private String attributeID;
    //套餐名称16
	private String attributeName;
	//会员ID17
	private long userId;
	//会员手机号18
	private String userTel;
	//会员信息19
	private long userInfo;
	//会员坐标经度20
	private String userlongitude;
    //会员坐标维度21
	private String userlatitude;
	//订单总金额22
	private long orderAllMoney;
	//提成比例23
	private long proportion;
	//提成方式24
	private int royaltyName;
	//积分数25
	
    private long integralNum;

	//订单佣金金额26
	private long proportionMoney;
	//支付金额27
	private long payMoney;
    //订单说明28
	private String orderRemark;

	//支付方式29
	private PayType payMode;

	//订单状态30
	private OrderStateType orderState;

	
	//商品图片31
    private String productPic;
    //有效期32
    private Date validityDate;
    //是否评价33
    private long commentIs;
    //预定日期34
    private Date orderBookDate;
    //收货地址35ID
    private long mailAddress;

    private long merchantId;
    
    private long tableId;
    
    private int confirm;//0未确认，1确认订单
    
    private int print;//0未打印，1已打印
    
    private int payPrint;//0未打印，1已打印
    
	public String getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}
	public String getYdNO() {
		return ydNO;
	}
	public void setYdNO(String ydNO) {
		this.ydNO = ydNO;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getYdDate() {
		return ydDate;
	}
	public void setYdDate(Date ydDate) {
		this.ydDate = ydDate;
	}
	
	public long getShopId() {
		return shopId;
	}
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(int siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getProductNum() {
		return productNum;
	}
	public void setProductNum(long productNum) {
		this.productNum = productNum;
	}
	public long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}
	public String getBkCode() {
		return bkCode;
	}
	public void setBkCode(String bkCode) {
		this.bkCode = bkCode;
	}
	public String getBkName() {
		return bkName;
	}
	public void setBkName(String bkName) {
		this.bkName = bkName;
	}
	public String getAttributeID() {
		return attributeID;
	}
	public void setAttributeID(String attributeID) {
		this.attributeID = attributeID;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public long getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(long userInfo) {
		this.userInfo = userInfo;
	}
	public String getUserlongitude() {
		return userlongitude;
	}
	public void setUserlongitude(String userlongitude) {
		this.userlongitude = userlongitude;
	}
	public String getUserlatitude() {
		return userlatitude;
	}
	public void setUserlatitude(String userlatitude) {
		this.userlatitude = userlatitude;
	}
	public long getOrderAllMoney() {
		return orderAllMoney;
	}
	public void setOrderAllMoney(long orderAllMoney) {
		this.orderAllMoney = orderAllMoney;
	}
	public long getProportion() {
		return proportion;
	}
	public void setProportion(long proportion) {
		this.proportion = proportion;
	}
	public int getRoyaltyName() {
		return royaltyName;
	}
	public void setRoyaltyName(int royaltyName) {
		this.royaltyName = royaltyName;
	}
	public long getIntegralNum() {
		return integralNum;
	}
	public void setIntegralNum(long integralNum) {
		this.integralNum = integralNum;
	}
	public long getProportionMoney() {
		return proportionMoney;
	}
	public void setProportionMoney(long proportionMoney) {
		this.proportionMoney = proportionMoney;
	}
	public long getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(long payMoney) {
		this.payMoney = payMoney;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	@Enumerated(EnumType.STRING)
	public PayType getPayMode() {
		return payMode;
	}
	public void setPayMode(PayType payMode) {
		this.payMode = payMode;
	}
	@Enumerated(EnumType.STRING)
	public OrderStateType getOrderState() {
		return orderState;
	}
	public void setOrderState(OrderStateType orderState) {
		this.orderState = orderState;
	}
	public String getProductPic() {
		return productPic;
	}
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
	public Date getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	public long getCommentIs() {
		return commentIs;
	}
	public void setCommentIs(long commentIs) {
		this.commentIs = commentIs;
	}
	public Date getOrderBookDate() {
		return orderBookDate;
	}
	public void setOrderBookDate(Date orderBookDate) {
		this.orderBookDate = orderBookDate;
	}
	public long getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(long mailAddress) {
		this.mailAddress = mailAddress;
	}
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}
	public long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public int getPrint() {
		return print;
	}
	public void setPrint(int print) {
		this.print = print;
	}
	public int getPayPrint() {
		return payPrint;
	}
	public void setPayPrint(int payPrint) {
		this.payPrint = payPrint;
	}
	
}


    

