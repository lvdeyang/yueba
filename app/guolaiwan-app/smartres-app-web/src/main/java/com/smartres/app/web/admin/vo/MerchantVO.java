package com.smartres.app.web.admin.vo;

import com.smartres.bussiness.admin.po.MerchantPO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class MerchantVO extends AbstractBaseVO<MerchantVO, MerchantPO> {

	// 商户名称
	private String shopName;
	// 登录名称
	private String shopLoginName;
	// 登录密码 
	private String shopLoginPwd;
	// 头像图片地址
	private String shopHeading;
	// 企业资质
	private String shopQualifications;
	// QQ
	private String shopQQ;
	// 联系电话
	private String shopTel;
	// 商户地址
	private String shopAddress;
	// 银行卡号
	private String shopBankId;
	// 开户行
	private String shopOpenBank;
	// 联系人
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
	// 审核状态
	private String shopAuditState;
	// 审核意见
	private String shopAuditopinion;
	// 板块名称
	private String modularName;
	// 板块Code
	private String modularCode;
	// 板块分类
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

	public MerchantVO setCityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}

	public String getCityName() {
		return cityName;
	}

	public MerchantVO setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}

	public String getShopName() {
		return shopName;
	}

	public MerchantVO setShopName(String shopName) {
		this.shopName = shopName;
		return this;
	}

	public String getShopLoginName() {
		return shopLoginName;
	}

	public MerchantVO setShopLoginName(String shopLoginName) {
		this.shopLoginName = shopLoginName;
		return this;
	}

	public String getShopLoginPwd() {
		return shopLoginPwd;
	}

	public MerchantVO setShopLoginPwd(String shopLoginPwd) {
		this.shopLoginPwd = shopLoginPwd;
		return this;
	}

	public String getShopHeading() {
		return shopHeading;
	}

	public MerchantVO setShopHeading(String shopHeading) {
		this.shopHeading = shopHeading;
		return this;
	}

	public String getShopQualifications() {
		return shopQualifications;
	}

	public MerchantVO setShopQualifications(String shopQualifications) {
		this.shopQualifications = shopQualifications;
		return this;
	}

	public String getShopQQ() {
		return shopQQ;
	}

	public MerchantVO setShopQQ(String shopQQ) {
		this.shopQQ = shopQQ;
		return this;
	}

	public String getShopTel() {
		return shopTel;
	}

	public MerchantVO setShopTel(String shopTel) {
		this.shopTel = shopTel;
		return this;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public MerchantVO setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
		return this;
	}

	public String getShopBankId() {
		return shopBankId;
	}

	public MerchantVO setShopBankId(String shopBankId) {
		this.shopBankId = shopBankId;
		return this;
	}
	
	public String getShopOpenBank() {
		return shopOpenBank;
	}

	public MerchantVO setShopOpenBank(String shopOpenBank) {
		this.shopOpenBank = shopOpenBank;
		return this;
	}

	public String getShopLinkperson() {
		return shopLinkperson;
	}

	public MerchantVO setShopLinkperson(String shopLinkperson) {
		this.shopLinkperson = shopLinkperson;
		return this;
	}

	public String getShopPic() {
		return shopPic;
	}

	public MerchantVO setShopPic(String shopPic) {
		this.shopPic = shopPic;
		return this;
	}

	public String getShopMpic() {
		return shopMpic;
	}

	public MerchantVO setShopMpic(String shopMpic) {
		this.shopMpic = shopMpic;
		return this;
	}

	public String getShopIntroduction() {
		return shopIntroduction;
	}

	public MerchantVO setShopIntroduction(String shopIntroduction) {
		this.shopIntroduction = shopIntroduction;
		return this;
	}

	public String getShopLongitude() {
		return shopLongitude;
	}

	public MerchantVO setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
		return this;
	}

	public String getShopLatitude() {
		return shopLatitude;
	}

	public MerchantVO setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
		return this;
	}

	public Long getShopAllMoney() {
		return shopAllMoney;
	}

	public MerchantVO setShopAllMoney(Long shopAllMoney) {
		this.shopAllMoney = shopAllMoney;
		return this;
	}

	public Long getShopActualMoney() {
		return shopActualMoney;
	}

	public MerchantVO setShopActualMoney(Long shopActualMoney) {
		this.shopActualMoney = shopActualMoney;
		return this;
	}

	public String getShopAuditState() {
		return shopAuditState;
	}

	public MerchantVO setShopAuditState(String shopAuditState) {
		this.shopAuditState = shopAuditState;
		return this;
	}

	public String getShopAuditopinion() {
		return shopAuditopinion;
	}

	public MerchantVO setShopAuditopinion(String shopAuditopinion) {
		this.shopAuditopinion = shopAuditopinion;
		return this;
	}

	public String getModularName() {
		return modularName;
	}

	public MerchantVO setModularName(String modularName) {
		this.modularName = modularName;
		return this;
	}

	public String getModularCode() {
		return modularCode;
	}

	public MerchantVO setModularCode(String modularCode) {
		this.modularCode = modularCode;
		return this;
	}

	public String getModularClass() {
		return modularClass;
	}

	public MerchantVO setModularClass(String modularClass) {
		this.modularClass = modularClass;
		return this;
	}

	public String getModularClassId() {
		return modularClassId;
	}

	public MerchantVO setModularClassId(String modularClassId) {
		this.modularClassId = modularClassId;
		return this;
	}

	@Override
	public MerchantVO set(MerchantPO entity) throws Exception {
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setShopName(entity.getShopName())
		.setShopLoginName(entity.getShopLoginName())
		.setShopLoginPwd(entity.getShopLoginPwd())
		.setShopHeading(entity.getShopHeading())
		.setShopQualifications(entity.getShopQualifications())
		.setShopQQ(entity.getShopQQ())
		.setShopTel(entity.getShopTel())
		.setShopAddress(entity.getShopAddress())
		.setShopBankId(entity.getShopBankId())
		.setShopOpenBank(entity.getShopOpenBank())
		.setShopLinkperson(entity.getShopLinkperson())
		.setShopPic(entity.getShopPic())
		.setShopMpic(entity.getShopMpic())
		.setShopIntroduction(entity.getShopIntroduction())
		.setShopLongitude(entity.getShopLongitude())
		.setShopLatitude(entity.getShopLatitude())
		.setShopAllMoney(entity.getShopAllMoney())
		.setShopActualMoney(entity.getShopActualMoney())
		.setShopAuditState(entity.getShopAuditState().getName())
		.setShopAuditopinion(entity.getShopAuditopinion())
		.setModularName(entity.getModularName())
		.setModularCode(entity.getModularCode())
		.setModularClass(entity.getModularClass())
		.setModularClassId(entity.getModularClassId())
		.setCityCode(entity.getCityCode())
		.setCityName(entity.getCityName());
		return this;
	}

}
