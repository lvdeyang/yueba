package com.smartres.bussiness.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.smartres.bussiness.admin.enumeration.ShopAuditStateType;
import com.smartres.bussiness.admin.po.ProductPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * Don't try so hard!
 * <p>Title: MenuDAO</p>
 * <p>Description: 权限DAO</p>
 * <p>Company: guolaiwan</p> 
 * @author zichao
 * @date 2017年11月20日 上午9:11:36
 */
@Repository("com.smartres.bussiness.admin.dao.ProductDAO")
public class ProductDAO extends AbstractBaseDao<ProductPO> {

	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	public int  getCountByPage(long merchantId) {
		CountHql cHql=this.newCountHql();
		cHql.andBy("merchantId",Condition.eq,merchantId);
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	//分页
	public List<ProductPO> getListByPage(int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	
	public List<ProductPO> getListByPage(int pageNum,int pageSize,long merchantId) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("merchantId",Condition.eq,merchantId);
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}

	//通用带参分页
	public List<ProductPO> getListByPageA(String[] names,String[] values,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		for(int i=0;i<names.length;i++){
			hql.andBy(names[i],  Condition.eq, values[i]);
		}
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	
	public List<ProductPO> getListByPageA(String[] names,String[] values,int pageNum,int pageSize,long merchantId) {
		QueryHql hql = this.newQueryHql();

		for(int i=0;i<names.length;i++){
			hql.andBy(names[i],  Condition.eq, values[i]);
		}
		hql.andBy("merchantId",Condition.eq,merchantId);
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	

	//通用带参总数
	public int  getCountByPageA(String[] names,String[] values){
		CountHql cHql=this.newCountHql();
		for(int i=0;i<names.length;i++){
			cHql.andBy(names[i],  Condition.eq, values[i]);
		}
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	public int  getCountByPageA(String[] names,String[] values,long merchantId){
		CountHql cHql=this.newCountHql();
		for(int i=0;i<names.length;i++){
			cHql.andBy(names[i],  Condition.eq, values[i]);
		}
		cHql.andBy("merchantId",Condition.eq,merchantId);
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	

	//通过模块和类获取商品
	public List<ProductPO> getProBymc(char modular,String classes){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productModularCode", Condition.eq, modular);
		hql.andBy("productClassCode", Condition.eq, classes);
		List<ProductPO> products = findByHql(hql);
		if(products==null || products.size()<=0) return null;
		return products;
	}
	
	public List<ProductPO> getProBymc(char modular,String classes,long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productModularCode", Condition.eq, modular);
		hql.andBy("productClassCode", Condition.eq, classes);
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<ProductPO> products = findByHql(hql);
		if(products==null || products.size()<=0) return null;
		return products;
	}
	
	public List<ProductPO> getProByModular(String modular,long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productModularCode", Condition.eq, modular);
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<ProductPO> products = findByHql(hql);
		if(products==null || products.size()<=0) return null;
		return products;
	}
	
	//通过模块和类获取商品
	public List<ProductPO> getProByaud(ShopAuditStateType d,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productAuditstatus", Condition.eq, d);
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	public List<ProductPO> getProByaud(ShopAuditStateType d,int pageNum,int pageSize,long merchantId){
		QueryHql hql = this.newQueryHql();
		hql.andBy("productAuditstatus", Condition.eq, d);
		hql.andBy("merchantId",Condition.eq,merchantId);
		hql.orderBy("updateTime", true);
		
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	
	

	//通用带参分页,审核状态
	public List<ProductPO> getListByPageE(Map<String,Object> map,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey()=="productName"||entry.getKey()=="productMerchantName"){
				hql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else{
				hql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	
	public List<ProductPO> getListByPageE(Map<String,Object> map,int pageNum,int pageSize,long merchantId) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey()=="productName"||entry.getKey()=="productMerchantName"){
				hql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else{
				hql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
		hql.andBy("merchantId",Condition.eq,merchantId);
		hql.orderBy("updateTime", true);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	

	//通用带参
	public int  getCountByPageE(Map<String,Object> map){
		CountHql cHql=this.newCountHql();

		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey()=="productName"||entry.getKey()=="productMerchantName"){
				cHql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else{
				cHql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}

		}
		
		int allcount=this.countByHql(cHql);
		return allcount;
	}

	public int  getCountByPageE(Map<String,Object> map,long merchantId){
		CountHql cHql=this.newCountHql();

		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey()=="productName"||entry.getKey()=="productMerchantName"){
				cHql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else{
				cHql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}

		}
		cHql.andBy("merchantId",Condition.eq,merchantId);
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	
	// 首页搜索
	public List<ProductPO> productSearch(String pcName, int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("productName",  Condition.lk, pcName);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	//通用带参分页,审核状态
	public List<ProductPO> getListByPageB(Map<String,Object> map,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey()=="productName"){
				hql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else if(entry.getKey()=="minProductPrice"){
				hql.andBy("productPrice",  Condition.ge, entry.getValue());
			}else if(entry.getKey()=="maxProductPrice"){
				hql.andBy("productPrice",  Condition.le, entry.getValue());
			}else{
				hql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
	
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}

	public List<ProductPO> getListByPageB(Map<String,Object> map,int pageNum,int pageSize,long merchantId) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey()=="productName"){
				hql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else if(entry.getKey()=="minProductPrice"){
				hql.andBy("productPrice",  Condition.ge, entry.getValue());
			}else if(entry.getKey()=="maxProductPrice"){
				hql.andBy("productPrice",  Condition.le, entry.getValue());
			}else{
				hql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);
		if(products == null || products.size() <= 0) return null;
		return products;
	}
	
	//通用带参分页,审核状态
	public int getListByPageBC(Map<String,Object> map) {
		CountHql cHql=this.newCountHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey()=="productName"){
				cHql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else if(entry.getKey()=="minProductPrice"){
				cHql.andBy("productPrice",  Condition.ge, entry.getValue());
			}else if(entry.getKey()=="maxProductPrice"){
				cHql.andBy("productPrice",  Condition.le, entry.getValue());
			}else{
				cHql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
		int count=this.countByHql(cHql);
		return count;
	}
	
	
	public int getListByPageBC(Map<String,Object> map,long merchantId) {
		CountHql cHql=this.newCountHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			if(entry.getKey()=="productName"){
				cHql.andBy(entry.getKey(),  Condition.lk, entry.getValue());
			}else if(entry.getKey()=="minProductPrice"){
				cHql.andBy("productPrice",  Condition.ge, entry.getValue());
			}else if(entry.getKey()=="maxProductPrice"){
				cHql.andBy("productPrice",  Condition.le, entry.getValue());
			}else{
				cHql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
			}
		}
		cHql.andBy("merchantId",Condition.eq,merchantId);
		int count=this.countByHql(cHql);
		return count;
	}

	//通过模块和类获取商品
	public List<ProductPO> getListHot(String[] fields,Object[] values,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		for (int i=0;i<fields.length;i++){
			hql.andBy(fields[i], Condition.eq, values[i]);
		}
		hql.orderBy("productSaleNum", true);
		List<ProductPO> products = findByHqlPage(hql,pageNum,pageSize);
		if(products==null || products.size()<=0) return null;
		return products;
	}


	//通过模块和类获取商品
	public List<ProductPO> getListFirst(String[] fields,Object[] values,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		for (int i=0;i<fields.length;i++){
			hql.andBy(fields[i], Condition.eq, values[i]);
		}
		hql.orderBy("productSaleNum", true);
		List<ProductPO> products = findByHqlPage(hql,pageNum,pageSize);
		if(products==null || products.size()<=0) return null;
		return products;
	}

	//删除产品
	public void deleteByUuid(String uuid){
		DeleteHql hql = this.newDeleteHql();
		hql.andBy("uuid",Condition.eq, uuid);
		deleteByHql(hql);
	}

	//前端搜索
	public List<ProductPO> findByNP(String pcName,String pcPriceMin,String pcPriceMax,int pageNum,int pageSize){
		QueryHql hql = this.newQueryHql();
		if(pcName!=null&&pcName.length()>0){
			hql.andBy("productName", Condition.lk, pcName);	
		}
		if(pcPriceMin!=null&&pcPriceMin.length()>0){
			int priceMin = Integer.parseInt(pcPriceMin);
			hql.andBy("productName", Condition.ge, priceMin);	
		}
		if(pcPriceMax!=null&&pcPriceMax.length()>0){
			int priceMax = Integer.parseInt(pcPriceMax);
			hql.andBy("productName", Condition.le, priceMax);	
		}

		List<ProductPO> products = findByHqlPage(hql,pageNum,pageSize);
		return products;
	}

	//  ###count未完成
	/**
	 * 获取所有首页推荐商品（App接口调用）
	 * @param count 推荐数量
	 * @return 首页推荐的商品
	 */
	public List<ProductPO> getRecommendProducts(int count) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("productIndexRecommend", Condition.eq, 1);
		List<ProductPO> products = findByHqlAndCount(hql, count);
		if(products==null || products.size()<=0) return null;

		return products;
	}


	/**
	 * 获取某分类下的商品（App接口调用）
	 * @param productModularCode 模块分类标识
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ProductPO> getProductByModularCode(
			String productModularCode, 
			int pageNum, 
			int pageSize) {

		QueryHql hql = this.newQueryHql();
		hql.andBy("productModularCode", Condition.eq, productModularCode);
		List<ProductPO> products =findByHqlPage(hql,pageNum,pageSize);

		return products;
	}


	public ProductPO findById(long id) {
		QueryHql hql = this.newQueryHql();

		hql.andBy("id", Condition.eq, id);
		List<ProductPO> products =findByHql(hql);
		if(products == null || products.size() <= 0) return null;
		return products.get(0);
	}


	public int countByMerchant(ShopAuditStateType type,long merchantId){
		QueryHql hql = this.newQueryHql();

		hql.andBy("productAuditstatus", Condition.eq, type);
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<ProductPO> products =findByHql(hql);
		return products.size();
	}

}
