package com.smartres.bussiness.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.smartres.bussiness.admin.enumeration.ShopAuditStateType;
import com.smartres.bussiness.admin.po.MerchantPO;
import com.smartres.bussiness.admin.po.ProductPO;
import com.smartres.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

/**
 * 
 * <p>Title: MerchantDAO</p>
 * <p>Description: 商户DAO</p>
 * <p>Company: guolaiwan</p> 
 * @author Mr.Sun
 * @date 2017年11月27日 下午1:22:48
 */

@Repository("com.smartres.bussiness.merchant.dao.MerchantDAO")
public class MerchantDAO extends AbstractBaseDao<MerchantPO> {

	// 统计总条数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();

		int allcount=this.countByHql(cHql);
		return allcount;
	}
	// 分页查询
	public List<MerchantPO> getListByPageA(String[] names,String[] values,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();

		for(int i=0;i<names.length;i++){
			hql.andBy(names[i],  Condition.eq, values[i]);
		}
		List<MerchantPO> merchants = findByHqlPage(hql,pageNum,pageSize);
		if(merchants==null || merchants.size()<=0) return null;
		return merchants;
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

	// 审核状态分页查询
	public List<MerchantPO> getFieldListbyPage(ShopAuditStateType type,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("shopAuditState", Condition.eq, type);
		List<MerchantPO> merchants = findByHqlPage(hql,pageNum,pageSize);
		if(merchants==null || merchants.size()<=0) return null;
		return merchants;
	}
	
	public List<MerchantPO> findByName(String name) {
		QueryHql hql = this.newQueryHql();
		if(name!=null&&!name.isEmpty()){
			hql.andBy("shopName", Condition.lk, name);
		}
		
		List<MerchantPO> merchants = findByHql(hql);
		if(merchants==null || merchants.size()<=0) return null;
		return merchants;
	}

	//通用带参分页,审核状态
	public List<MerchantPO> getListByPageE(Map<String,Object> map,int pageNum,int pageSize) {
		QueryHql hql = this.newQueryHql();
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			hql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
		}

		List<MerchantPO> merchants =findByHqlPage(hql,pageNum,pageSize);
		if(merchants == null || merchants.size() <= 0) return null;
		return merchants;
	}

	//通用带参
	public int  getCountByCity(Map<String,Object> map){
		CountHql cHql=this.newCountHql();

		for (Map.Entry<String,Object> entry : map.entrySet()) {
			cHql.andBy(entry.getKey(),  Condition.eq, entry.getValue());
		}
		int allcount=this.countByHql(cHql);
		System.out.println("共有：" + allcount);
		return allcount;
	}


}
