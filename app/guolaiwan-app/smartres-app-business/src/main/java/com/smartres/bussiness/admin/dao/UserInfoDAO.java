package com.smartres.bussiness.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smartres.bussiness.admin.po.ModularPO;
import com.smartres.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.smartres.bussiness.admin.dao.UserInfoDAO")
public class UserInfoDAO extends AbstractBaseDao<UserInfoPO> {

	public UserInfoPO getModularByCode(String userphone) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userinfo", Condition.eq, userphone);
		List<UserInfoPO> userinfos = findByHql(hql);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos.get(0);
	}

	public List<UserInfoPO> GetListbyPage(int pageNum, int pageSize) {
		QueryHql hql = this.newQueryHql();

		hql.orderBy("id", true);
		List<UserInfoPO> userinfos = findByHqlPage(hql, pageNum, pageSize);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos;
	}

	public UserInfoPO getUserByPhone(String userphone) {
		QueryHql hql = this.newQueryHql();
		hql.andBy("userPhone", Condition.eq, userphone);
		List<UserInfoPO> userinfos = findByHql(hql);
		if (userinfos == null || userinfos.size() <= 0)
			return null;
		return userinfos.get(0);
	}

	// 统计总数
	public int GetCountByPage() {
		CountHql cHql = this.newCountHql();

		int allcount = this.countByHql(cHql);
		return allcount;

	}

	public int countByMerchant(long merchantId) {
		CountHql cHql = this.newCountHql();
        if(merchantId!=0){
        	cHql.andBy("merchantId",Condition.eq,merchantId);
        }
		int allcount = this.countByHql(cHql);
		return allcount;

	}
}
