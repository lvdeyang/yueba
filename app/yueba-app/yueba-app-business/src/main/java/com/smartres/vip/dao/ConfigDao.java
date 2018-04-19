package com.smartres.vip.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smartres.vip.po.ConfigPo;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.smartres.vip.dao.ConfigDao")
public class ConfigDao extends AbstractBaseDao<ConfigPo> {

	public void deleteByMerchantId(long merchantId){
		DeleteHql dHql=this.newDeleteHql();
		dHql.andBy("merchantId",Condition.eq,merchantId);
		deleteByHql(dHql);
	}
	
	public ConfigPo findByMerchantId(long merchantId){
		QueryHql hql=this.newQueryHql();
		hql.andBy("merchantId",Condition.eq,merchantId);
		List<ConfigPo> configPos=this.findByHql(hql);
		if(configPos.isEmpty()){
			return null;
		}else{
			return configPos.get(0);
		}
	}
}
