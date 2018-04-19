package com.smartres.app.web.admin.vo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.smartres.bussiness.admin.po.PicturePO;
import com.smartres.bussiness.admin.po.ProductPO;

import pub.caterpillar.mvc.converter.AbstractBaseVO;

public class ProductVO extends AbstractBaseVO<ProductVO, ProductPO> {


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
	private String productMerchantJson;

	//商品名称
	private String productName;

	//产品副标题
	private String productSubtitle;

	//开始时间
	private String productBeginDate;

	//结束时间
	private String productEnddate;

	//有效期
	private String productEctivedate;

	//原价
	private String productOldPrice;

	//现价
	private String productPrice;

	//提成模式（0：佣金1：比例）
	private int productCommissionCode;

	//提成金额（佣金：分。比例百分比：10，20。。）
	private long productCommissionPrice;

	//前端显示图片（路径）
	private String productShowPic;

	//产品多图显示（路径）Json形式
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
	private String productAuditstatus;

	//审核意见
	private String productAuditAdvice;

	//库存
	private long productStock;

	//是否限购（0，1）
	private int productLimitType;

	//限购数量
	private long productLimitNum;

	//产品介绍
	private String productIntroduce;

	//兑换积分
	private long productntegral;

	//是否显示
	private int productIsShow;

	//城市编码
	private String productCityCode;

	//城市名称
	private String productCityName;
	

	public String getProductModularCode() {
		return productModularCode;
	}





	public ProductVO setProductModularCode(String productModularCode) {
		this.productModularCode = productModularCode;
		return this;
	}





	public String getProductModularCodeName() {
		return productModularCodeName;
	}





	public ProductVO setProductModularCodeName(String productModularCodeName) {
		this.productModularCodeName = productModularCodeName;
		return this;
	}





	public String getProductClassCode() {
		return productClassCode;
	}





	public ProductVO setProductClassCode(String productClassCode) {
		this.productClassCode = productClassCode;
		return this;
	}





	public String getProductClassName() {
		return productClassName;
	}





	public ProductVO setProductClassName(String productClassName) {
		this.productClassName = productClassName;
		return this;
	}












	public String getProductMerchantID() {
		return productMerchantID;
	}





	public ProductVO setProductMerchantID(String productMerchantID) {
		this.productMerchantID = productMerchantID;
		return this;
	}





	public String getProductMerchantName() {
		return productMerchantName;
	}





	public ProductVO setProductMerchantName(String productMerchantName) {
		this.productMerchantName = productMerchantName;
		return this;
	}





	public String getProductMerchantJson() {
		return productMerchantJson;
	}





	public ProductVO setProductMerchantJson(String productMerchantJson) {
		this.productMerchantJson = productMerchantJson;
		return this;
	}





	public String getProductName() {
		return productName;
	}





	public ProductVO setProductName(String productName) {
		this.productName = productName;
		return this;
	}





	public String getProductSubtitle() {
		return productSubtitle;
	}





	public ProductVO setProductSubtitle(String productSubtitle) {
		this.productSubtitle = productSubtitle;
		return this;
	}








	public String getProductBeginDate() {
		return productBeginDate;
	}





	public ProductVO setProductBeginDate(String productBeginDate) {
		this.productBeginDate = productBeginDate;
		return this;
	}





	public String getProductEnddate() {
		return productEnddate;
	}





	public ProductVO setProductEnddate(String productEnddate) {
		this.productEnddate = productEnddate;
		return this;
	}





	public String getProductEctivedate() {
		return productEctivedate;
	}





	public ProductVO setProductEctivedate(String productEctivedate) {
		this.productEctivedate = productEctivedate;
		return this;
	}





	public String getProductOldPrice() {
		return productOldPrice;
	}





	public ProductVO setProductOldPrice(String productOldPrice) {
		this.productOldPrice = productOldPrice;
		return this;
	}





	public String getProductPrice() {
		return productPrice;
	}





	public ProductVO setProductPrice(String productPrice) {
		this.productPrice = productPrice;
		return this;
	}






	public int getProductCommissionCode() {
		return productCommissionCode;
	}





	public ProductVO setProductCommissionCode(int productCommissionCode) {
		this.productCommissionCode = productCommissionCode;
		return this;
	}





	public long getProductCommissionPrice() {
		return productCommissionPrice;
	}





	public ProductVO setProductCommissionPrice(long productCommissionPrice) {
		this.productCommissionPrice = productCommissionPrice;
		return this;
	}





	public String getProductShowPic() {
		return productShowPic;
	}





	public ProductVO setProductShowPic(String productShowPic) {
		this.productShowPic = productShowPic;
		return this;
	}





	public String getProductMorePic() {
		return productMorePic;
	}





	public ProductVO setProductMorePic(String productMorePic) {
		this.productMorePic = productMorePic;
		return this;
	}





	public int getProductIndexRecommend() {
		return productIndexRecommend;
	}





	public ProductVO setProductIndexRecommend(int productIndexRecommend) {
		this.productIndexRecommend = productIndexRecommend;
		return this;
	}





	public int getProductListRecommend() {
		return productListRecommend;
	}





	public ProductVO setProductListRecommend(int productListRecommend) {
		this.productListRecommend = productListRecommend;
		return this;
	}



	public long getProductShowNum() {
		return productShowNum;
	}





	public ProductVO setProductShowNum(long productShowNum) {
		this.productShowNum = productShowNum;
		return this;
	}





	public long getProductSaleNum() {
		return productSaleNum;
	}





	public ProductVO setProductSaleNum(long productSaleNum) {
		this.productSaleNum = productSaleNum;
		return this;
	}





	public long getProductSort() {
		return productSort;
	}





	public ProductVO setProductSort(long productSort) {
		this.productSort = productSort;
		return this;
	}





	public ProductVO setProductntegral(long productntegral) {
		this.productntegral = productntegral;
		return this;
	}





	public long getProductntegral() {
		return productntegral;
	}





	public String getProductAuditstatus() {
		return productAuditstatus;
	}





	public ProductVO setProductAuditstatus(String productAuditstatus) {
		this.productAuditstatus = productAuditstatus;
		return this;
	}





	public String getProductAuditAdvice() {
		return productAuditAdvice;
	}





	public ProductVO setProductAuditAdvice(String productAuditAdvice) {
		this.productAuditAdvice = productAuditAdvice;
		return this;
	}





	public long getProductStock() {
		return productStock;
	}





	public ProductVO setProductStock(long productStock) {
		this.productStock = productStock;
		return this;
	}





	public int getProductLimitType() {
		return productLimitType;
	}





	public ProductVO setProductLimitType(int productLimitType) {
		this.productLimitType = productLimitType;
		return this;
	}





	public long getProductLimitNum() {
		return productLimitNum;
	}





	public ProductVO setProductLimitNum(long productLimitNum) {
		this.productLimitNum = productLimitNum;
		return this;
	}





	public String getProductIntroduce() {
		return productIntroduce;
	}





	public ProductVO setProductIntroduce(String productIntroduce) {
		this.productIntroduce = productIntroduce;
		return this;
	}




	




	public int getProductIsShow() {
		return productIsShow;
	}





	public ProductVO setProductIsShow(int productIsShow) {
		this.productIsShow = productIsShow;
		return this;
	}


	


	public String getProductCityCode() {
		return productCityCode;
	}





	public ProductVO setProductCityCode(String productCityCode) {
		this.productCityCode = productCityCode;
		return this;
	}

	
	








	public String getProductCityName() {
		return productCityName;
	}





	public ProductVO setProductCityName(String productCityName) {
		this.productCityName = productCityName;
		return this;
	}






	@Override
	public ProductVO set(ProductPO entity) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setId(entity.getId())
		.setUuid(entity.getUuid())
		.setUpdateTime(df.format(entity.getUpdateTime()))
		.setProductModularCode(entity.getProductModularCode())
		.setProductModularCodeName(entity.getProductModularCodeName())
		.setProductClassCode(entity.getProductClassCode())
		.setProductClassName(entity.getProductClassName())
		.setProductMerchantID(entity.getProductMerchantID())
		.setProductMerchantName((entity.getProductMerchantName()))
		.setProductMerchantJson(entity.getProductMerchantJson())
		.setProductName(entity.getProductName())
		.setProductSubtitle(entity.getProductSubtitle())
		.setProductBeginDate(df.format(entity.getProductBeginDate()))
		.setProductEnddate(df.format(entity.getProductEnddate()))
		.setProductEctivedate(df.format(entity.getProductEctivedate()))
		.setProductOldPrice(new DecimalFormat("0.00").format(((double)entity.getProductOldPrice()/100)))
		.setProductPrice(new DecimalFormat("0.00").format(((double)entity.getProductPrice()/100)))
		.setProductCommissionCode(entity.getProductCommissionCode())
		.setProductCommissionPrice(entity.getProductCommissionPrice())
		.setProductShowPic(entity.getProductShowPic())
		.setProductMorePic(entity.getProductMorePic())
		.setProductIndexRecommend(entity.getProductIndexRecommend())
		.setProductListRecommend(entity.getProductListRecommend())
		.setProductShowNum(entity.getProductShowNum())
		.setProductSaleNum(entity.getProductSaleNum())
		.setProductSort(entity.getProductSort())
		.setProductAuditstatus(entity.getProductAuditstatus().getName())
		.setProductAuditAdvice(entity.getProductAuditAdvice())
		.setProductStock(entity.getProductStock())
		.setProductLimitType(entity.getProductLimitType())
		.setProductLimitNum(entity.getProductLimitNum())
		.setProductIntroduce(entity.getProductIntroduce())
		.setProductntegral(entity.getProductntegral())
		.setProductIsShow(entity.getProductIsShow())
		.setProductCityCode(entity.getProductCityCode())
		.setProductCityName(entity.getProductCityName());
		return this;
	}

}
