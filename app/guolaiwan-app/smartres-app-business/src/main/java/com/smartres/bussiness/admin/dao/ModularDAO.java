package com.smartres.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smartres.bussiness.admin.po.ModularPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.smartres.bussiness.admin.dao.ModularDAO")

public class ModularDAO extends AbstractBaseDao<ModularPO>{
	public ModularPO getModularByCode(String modularcode){
	   	 QueryHql hql = this.newQueryHql();
	   	 hql.andBy("modular", Condition.eq, modularcode);
	   	 List<ModularPO> modulars = findByHql(hql);
	   	 if(modulars==null || modulars.size()<=0) return null;
	   	 return modulars.get(0);
	    }
	public List<ModularPO> GetListbyPage(int pageNum,int pageSize) {
		 QueryHql hql = this.newQueryHql();
		
		 hql.orderBy("id", true);
	   	 List<ModularPO> modulars =findByHqlPage(hql,pageNum,pageSize);
	   	 if(modulars==null || modulars.size()<=0) return null;
	   	 return modulars;
	}
	//统计总数
			public int  GetCountByPage() {
				CountHql cHql=this.newCountHql();
				
				int allcount=this.countByHql(cHql);
				return allcount;
			}

}
