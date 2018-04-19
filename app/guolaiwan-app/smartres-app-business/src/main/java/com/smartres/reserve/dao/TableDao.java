package com.smartres.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smartres.reserve.po.TablePo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.smartres.reverse.dao.TableDao")
public class TableDao extends AbstractBaseDao<TablePo>{
	public List<TablePo> findTablesByMerchant(long merchantId,int page,int pageSize){
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId",Condition.eq,merchantId);
		return findByHql(hql, page, pageSize);
	}
	
	
	public List<TablePo> findTablesByMerchantAndNum(String start,String end,long merchantId){
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId",Condition.eq,merchantId);
		hql.andBy("status",Condition.eq,0);
		if(!start.isEmpty()&&!end.isEmpty()){
			hql.andBy("count",Condition.ge, Integer.parseInt(start));
			hql.andBy("count",Condition.le, Integer.parseInt(end));
		}
		return findByHql(hql);
	}
	
	public List<TablePo> findTablesByUserAndStatus(long userId){
		QueryHql hql = newQueryHql();
		hql.andBy("userId",Condition.eq,userId);
		hql.andBy("status",Condition.eq,2);
		return findByHql(hql);
	}
	
	
	
	public List<TablePo> findAllTablesByMerchant(long merchantId){
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId",Condition.eq,merchantId);
		return findByHql(hql);
	}
	public List<TablePo> findTablesByMerchantAndStatus(long merchantId,int status){
		QueryHql hql = newQueryHql();
		hql.andBy("status",Condition.ne,status);
		return findByHql(hql);
	}
	public int countTablesByMerchant(long merchantId){
		QueryHql hql = newQueryHql();
		hql.andBy("merchantId",Condition.eq,merchantId);
		return findByHql(hql).size();
	}
	public List<TablePo> findTablesByPage(int page,int pageSize){
		QueryHql hql = newQueryHql();
		return findByHql(hql, page, pageSize);
	}
	
}
