package com.smartres.bussiness.admin.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.smartres.bussiness.admin.enumeration.ShopAuditStateType;

import pub.caterpillar.orm.po.AbstractBasePO;

@Entity
@Table(name = "t_sys_modular_product")
public class ProductPO extends AbstractBasePO {

	private static final long serialVersionUID = 1L;

	//绑定模块
	private String productModularCode;

	//绑定模块名称
	private String productModularCodeName;

	//分类标识
	private String productClassCode; 

	//分类名称
	private String productClassName;

	//商户ID
	private String productMerchantID;
	
	//商户名称
	private String productMerchantName;

	//传入参数
	@Column(name = "productMerchantJson",length=4000)
	private String productMerchantJson;

	//商品名称
	private String productName;

	//产品副标题
	private String productSubtitle;

	//开始时间
	private Date productBeginDate;

	//结束时间
	private Date productEnddate;

	//有效期
	private Date productEctivedate;

	//原价(分)s
	private long productOldPrice;

	//现价（分）
	private long productPrice;

	//提成模式（0：佣金1：比例）
	private int productCommissionCode;

	//提成金额（佣金：分。比例百分比：10，20。。）
	private long productCommissionPrice;

	//前端显示图片（路径）
	private String productShowPic;

	//产品多图显示（路径）,
	private String productMorePic;

	//首页/平台推荐（0，1）
	private int productIndexRecommend; 

	//列表/右侧推荐（0，1）
	private int productListRecommend;

	//浏览量
	private long productShowNum;

	//销量
	private long productSaleNum;

	//排序
	private long productSort;

	//审核状态(初审：T复审：R)
	private ShopAuditStateType productAuditstatus;

	//审核意见
	private String productAuditAdvice;

	//库存
	private long productStock;

	//是否限购（0，1）
	private int productLimitType;

	//限购数量
	private long productLimitNum;

	//产品介绍 || 描述 forPhoneInterfac
	private String productIntroduce;

	//兑换积分
	private long productntegral;

	//是否显示
	private int productIsShow;
	
	//城市编码
	private String productCityCode;
	
	//城市名称
	private String productCityName;
	
	
	private long merchantId;

	public String getProductModularCode() {
		return productModularCode;
	}

	public void setProductModularCode(String productModularCode) {
		this.productModularCode = productModularCode;
	}

	public String getProductModularCodeName() {
		return productModularCodeName;
	}

	public void setProductModularCodeName(String productModularCodeName) {
		this.productModularCodeName = productModularCodeName;
	}

	public String getProductClassCode() {
		return productClassCode;
	}

	public void setProductClassCode(String productClassCode) {
		this.productClassCode = productClassCode;
	}

	public String getProductClassName() {
		return productClassName;
	}

	public void setProductClassName(String productClassName) {
		this.productClassName = productClassName;
	}

	public String getProductMerchantID() {
		return productMerchantID;
	}

	public void setProductMerchantID(String productMerchantID) {
		this.productMerchantID = productMerchantID;
	}

	public String getProductMerchantName() {
		return productMerchantName;
	}

	public void setProductMerchantName(String productMerchantName) {
		this.productMerchantName = productMerchantName;
	}

	public String getProductMerchantJson() {
		return productMerchantJson;
	}

	public void setProductMerchantJson(String productMerchantJson) {
		this.productMerchantJson = productMerchantJson;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSubtitle() {
		return productSubtitle;
	}

	public void setProductSubtitle(String productSubtitle) {
		this.productSubtitle = productSubtitle;
	}

	public Date getProductBeginDate() {
		return productBeginDate;
	}

	public void setProductBeginDate(Date productBeginDate) {
		this.productBeginDate = productBeginDate;
	}

	public Date getProductEnddate() {
		return productEnddate;
	}
	
	public Date getProductEctivedate() {
		return productEctivedate;
	}

	public void setProductEctivedate(Date productEctivedate) {
		this.productEctivedate = productEctivedate;
	}

	public void setProductEnddate(Date productEnddate) {
		this.productEnddate = productEnddate;
	}

	public long getProductOldPrice() {
		return productOldPrice;
	}

	public void setProductOldPrice(long productOldPrice) {
		this.productOldPrice = productOldPrice;
	}

	public long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(long productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductCommissionCode() {
		return productCommissionCode;
	}

	public void setProductCommissionCode(int productCommissionCode) {
		this.productCommissionCode = productCommissionCode;
	}

	public long getProductCommissionPrice() {
		return productCommissionPrice;
	}

	public void setProductCommissionPrice(long productCommissionPrice) {
		this.productCommissionPrice = productCommissionPrice;
	}

	public String getProductShowPic() {
		return productShowPic;
	}

	public void setProductShowPic(String productShowPic) {
		this.productShowPic = productShowPic;
	}

	public String getProductMorePic() {
		return productMorePic;
	}

	public void setProductMorePic(String productMorePic) {
		this.productMorePic = productMorePic;
	}

	public int getProductIndexRecommend() {
		return productIndexRecommend;
	}

	public void setProductIndexRecommend(int productIndexRecommend) {
		this.productIndexRecommend = productIndexRecommend;
	}

	public int getProductListRecommend() {
		return productListRecommend;
	}

	public void setProductListRecommend(int productListRecommend) {
		this.productListRecommend = productListRecommend;
	}

	

	
	public long getProductShowNum() {
		return productShowNum;
	}

	public void setProductShowNum(long productShowNum) {
		this.productShowNum = productShowNum;
	}

	public long getProductSaleNum() {
		return productSaleNum;
	}

	public void setProductSaleNum(long productSaleNum) {
		this.productSaleNum = productSaleNum;
	}

	public long getProductSort() {
		return productSort;
	}

	public void setProductSort(long productSort) {
		this.productSort = productSort;
	}

	public void setProductntegral(long productntegral) {
		this.productntegral = productntegral;
	}

	@Enumerated(EnumType.STRING)
	public ShopAuditStateType getProductAuditstatus() {
		return productAuditstatus;
	}

	public void setProductAuditstatus(ShopAuditStateType productAuditstatus) {
		this.productAuditstatus = productAuditstatus;
	}

	public String getProductAuditAdvice() {
		return productAuditAdvice;
	}

	public void setProductAuditAdvice(String productAuditAdvice) {
		this.productAuditAdvice = productAuditAdvice;
	}

	public long getProductStock() {
		return productStock;
	}

	public void setProductStock(long productStock) {
		this.productStock = productStock;
	}

	public int getProductLimitType() {
		return productLimitType;
	}

	public void setProductLimitType(int productLimitType) {
		this.productLimitType = productLimitType;
	}

	public long getProductLimitNum() {
		return productLimitNum;
	}

	public void setProductLimitNum(long productLimitNum) {
		this.productLimitNum = productLimitNum;
	}

	public String getProductIntroduce() {
		return productIntroduce;
	}

	public void setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
	}

	

	public long getProductntegral() {
		return productntegral;
	}

	public int getProductIsShow() {
		return productIsShow;
	}

	public void setProductIsShow(int productIsShow) {
		this.productIsShow = productIsShow;
	}

	public String getProductCityCode() {
		return productCityCode;
	}

	public void setProductCityCode(String productCityCode) {
		this.productCityCode = productCityCode;
	}

	public String getProductCityName() {
		return productCityName;
	}

	public void setProductCityName(String productCityName) {
		this.productCityName = productCityName;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
	}

	
}
