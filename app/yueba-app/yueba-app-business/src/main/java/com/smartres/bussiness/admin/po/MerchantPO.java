package com.smartres.bussiness.admin.po;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.smartres.bussiness.admin.enumeration.ShopAuditStateType;

import pub.caterpillar.orm.po.AbstractBasePO;

/**
 * 
 * <p>Title: Merchant</p>
 * <p>Description: 商户PO</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月27日 下午1:14:32
 */

@Entity
@Table(name = "t_sys_merchant")
public class MerchantPO extends AbstractBasePO {
	
	private static final long serialVersionUID = 1L;
	
	// 商户名称 1
	private String shopName;
	// 登录名称1
	private String shopLoginName;
	// 登录密码 
	private String shopLoginPwd;
	// 头像图片地址
	private String shopHeading;
	// 企业资质图片地址
	private String shopQualifications;
	// QQ
	private String shopQQ;
	// 联系电话1
	private String shopTel;
	// 商户地址1
	private String shopAddress;
	// 银行卡号
	private String shopBankId;
	// 开户行
	private String shopOpenBank;
	// 联系人1
	private String shopLinkperson;
	// 显示图片
	private String shopPic;
	// 商户多图片
	private String shopMpic;
	// 商户简介
	private String shopIntroduction;
	// 坐标经度
	private String shopLongitude;
	// 坐标纬度
	private String shopLatitude;
	// 账户付款
	private Long shopAllMoney;
	// 账户验单
	private Long shopActualMoney;
	// 审核状态1
	private ShopAuditStateType shopAuditState;
	// 审核意见1
	private String shopAuditopinion;
	// 板块名称1
	private String modularName;
	// 板块Code
	private String modularCode;
	// 板块分类1
	private String modularClass;
	// 板块分类Id
	private String modularClassId;
	
	// 城市标识
	private String cityCode;
	// 城市名
	private String cityName;

	
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopLoginName() {
		return shopLoginName;
	}
	public void setShopLoginName(String shopLoginName) {
		this.shopLoginName = shopLoginName;
	}
	public String getShopLoginPwd() {
		return shopLoginPwd;
	}
	public void setShopLoginPwd(String shopLoginPwd) {
		this.shopLoginPwd = shopLoginPwd;
	}
	public String getShopHeading() {
		return shopHeading;
	}
	public void setShopHeading(String shopHeading) {
		this.shopHeading = shopHeading;
	}
	public String getShopQualifications() {
		return shopQualifications;
	}
	public void setShopQualifications(String shopQualifications) {
		this.shopQualifications = shopQualifications;
	}
	public String getShopQQ() {
		return shopQQ;
	}
	public void setShopQQ(String shopQQ) {
		this.shopQQ = shopQQ;
	}
	public String getShopTel() {
		return shopTel;
	}
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopBankId() {
		return shopBankId;
	}
	public void setShopBankId(String shopBankId) {
		this.shopBankId = shopBankId;
	}
	
	public String getShopOpenBank() {
		return shopOpenBank;
	}
	public void setShopOpenBank(String shopOpenBank) {
		this.shopOpenBank = shopOpenBank;
	}
	public String getShopLinkperson() {
		return shopLinkperson;
	}
	public void setShopLinkperson(String shopLinkperson) {
		this.shopLinkperson = shopLinkperson;
	}
	public String getShopPic() {
		return shopPic;
	}
	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}
	public String getShopMpic() {
		return shopMpic;
	}
	public void setShopMpic(String shopMpic) {
		this.shopMpic = shopMpic;
	}
	public String getShopIntroduction() {
		return shopIntroduction;
	}
	public void setShopIntroduction(String shopIntroduction) {
		this.shopIntroduction = shopIntroduction;
	}
	public String getShopLongitude() {
		return shopLongitude;
	}
	public void setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
	}
	public String getShopLatitude() {
		return shopLatitude;
	}
	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	public Long getShopAllMoney() {
		return shopAllMoney;
	}
	public void setShopAllMoney(Long shopAllMoney) {
		this.shopAllMoney = shopAllMoney;
	}
	public Long getShopActualMoney() {
		return shopActualMoney;
	}
	public void setShopActualMoney(Long shopActualMoney) {
		this.shopActualMoney = shopActualMoney;
	}
	@Enumerated(EnumType.STRING)
	public ShopAuditStateType getShopAuditState() {
		return shopAuditState;
	}
	public void setShopAuditState(ShopAuditStateType shopAuditState) {
		this.shopAuditState = shopAuditState;
	}
	
	/*public String getShopAuditState() {
		return shopAuditState;
	}
	public void setShopAuditState(String shopAuditState) {
		this.shopAuditState = shopAuditState;
	}*/
	public String getShopAuditopinion() {
		return shopAuditopinion;
	}
	public void setShopAuditopinion(String shopAuditopinion) {
		this.shopAuditopinion = shopAuditopinion;
	}
	public String getModularName() {
		return modularName;
	}
	public void setModularName(String modularName) {
		this.modularName = modularName;
	}
	public String getModularCode() {
		return modularCode;
	}
	public void setModularCode(String modularCode) {
		this.modularCode = modularCode;
	}
	public String getModularClass() {
		return modularClass;
	}
	public void setModularClass(String modularClass) {
		this.modularClass = modularClass;
	}
	public String getModularClassId() {
		return modularClassId;
	}
	public void setModularClassId(String modularClassId) {
		this.modularClassId = modularClassId;
	}
	
}
