package com.smartres.bussiness.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.smartres.bussiness.admin.po.PicturePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.QueryHql;


@Repository("com.smartres.bussiness.admin.dao.PictureDAO")
public class PictureDAO extends AbstractBaseDao<PicturePO> {
	public List<PicturePO> getPictureByPage(int pageNum,int pageSize){
   	 QueryHql hql = this.newQueryHql();
   	 hql.orderBy("updateTime", true);
     List<PicturePO> pictures= findByHqlPage(hql,pageNum,pageSize);
   	 if(pictures==null || pictures.size()<=0) return null;
   	 return pictures;
    }
	
	public List<PicturePO> getPictureByPage(int pageNum,int pageSize,long merchantId){
   	 QueryHql hql = this.newQueryHql();
   	 hql.andBy("merchantId",Condition.eq,merchantId);
   	 hql.orderBy("updateTime", true);
     List<PicturePO> pictures= findByHqlPage(hql,pageNum,pageSize);
   	 if(pictures==null || pictures.size()<=0) return null;
   	 return pictures;
    }
	
	//统计总数
	public int  getCountByPage() {
		CountHql cHql=this.newCountHql();
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	//统计总数
	public int  getCountByPage(long merchantId) {
		CountHql cHql=this.newCountHql();
		cHql.andBy("merchantId", Condition.eq, merchantId);
		int allcount=this.countByHql(cHql);
		return allcount;
	}
	
	//
	public void deleteByUuid(String uuid){
	   	 DeleteHql hql = this.newDeleteHql();
	   	 hql.andBy("uuid",Condition.eq, uuid);
	     deleteByHql(hql);
	    }
	
	
}
