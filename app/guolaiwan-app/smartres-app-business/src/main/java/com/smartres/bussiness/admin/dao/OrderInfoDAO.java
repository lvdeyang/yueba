package com.smartres.bussiness.admin.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.smartres.bussiness.admin.enumeration.OrderStateType;
import com.smartres.bussiness.admin.po.AdminPO;
import com.smartres.bussiness.admin.po.OrderInfoPO;
import com.smartres.reserve.po.OrderReportDTO;

import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.orm.dao.AbstractBaseDao;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.QueryHql;

@Repository("com.smartres.bussiness.admin.dao.OrderInfoDAO")
public class OrderInfoDAO extends AbstractBaseDao<OrderInfoPO>{
	public OrderInfoPO getOrderByNo(String orderno){
	   	 QueryHql hql = this.newQueryHql();
	   	 hql.andBy("OrderNO", Condition.eq, orderno);
	   	 List<OrderInfoPO> orders = findByHql(hql);
	   	 if(orders==null || orders.size()<=0) return null;
	   	 return orders.get(0);
	    }
		
		public List<OrderInfoPO> GetListbyPage(int pageNum,int pageSize) {
			 QueryHql hql = this.newQueryHql();
			 
			 hql.orderBy("id", true);
		   	 List<OrderInfoPO> orders =findByHqlPage(hql,pageNum,pageSize);
		   	 if(orders==null || orders.size()<=0) return null;
		   	 return orders;
		}
		
		public int countTodayOrders(long merchantId) throws ParseException {
			 CountHql hql = this.newCountHql();
			 if(merchantId!=0){
				 hql.andBy("merchantId",Condition.eq,merchantId);
			 }
			 hql.andBy("createDate",Condition.ge,DateUtil.parse(DateUtil.dateToString(new Date(), "yyyy-MM-dd")+" 00:00:00"));
		   	 return countByHql(hql);
		   	 
		}
		public int countOrdersBydate(long merchantId,Date startDate,Date endDate) {
			 CountHql hql = this.newCountHql();
			 if(merchantId!=0){
				 hql.andBy("merchantId",Condition.eq,merchantId);
			 }
			 hql.andBy("createDate",Condition.ge,startDate);
			 hql.andBy("createDate",Condition.le,endDate);
		   	 return countByHql(hql);
		   	 
		}
		
		
		
		public List<OrderInfoPO> GetListbyPageAndTableAndDate(int pageNum,int pageSize,long tableId,String startDate,String endDate) throws ParseException {
			 QueryHql hql = this.newQueryHql();
			 
			 if(tableId!=0){
				 hql.andBy("tableId",Condition.eq,tableId);
			 }
			 if(!startDate.isEmpty()&&!endDate.isEmpty()){
				hql.andBy("createDate",Condition.ge,DateUtil.parse(startDate));
				hql.andBy("createDate",Condition.le,DateUtil.parse(endDate));
			 }
			 hql.orderBy("id", true);
		   	 List<OrderInfoPO> orders =findByHqlPage(hql,pageNum,pageSize);
		   	 if(orders==null || orders.size()<=0) return null;
		   	 return orders;
		}
		
		

		public List<OrderInfoPO> getAllPrintOrder(long merchantId,long tableId){
			 QueryHql hql = this.newQueryHql();
			 hql.andBy("merchantId",Condition.eq,merchantId);
			 hql.andBy("print",Condition.eq,0);
			 hql.andBy("confirm",Condition.eq,1);
			 hql.andBy("tableId",Condition.eq,tableId);
		   	 List<OrderInfoPO> orders =findByHql(hql);
		   	 if(orders==null || orders.size()<=0) return null;
		   	 return orders;
		}
		public List<OrderInfoPO> getAllPayPrintOrder(long merchantId,long tableId){
			 QueryHql hql = this.newQueryHql();
			 hql.andBy("merchantId",Condition.eq,merchantId);
			 hql.andBy("print",Condition.eq,1);
			 hql.andBy("confirm",Condition.eq,1);
			 hql.andBy("orderState",Condition.eq,OrderStateType.PAYFINISH);
			 hql.andBy("payPrint",Condition.eq,0);
			 hql.andBy("tableId",Condition.eq,tableId);
		   	 List<OrderInfoPO> orders =findByHql(hql);
		   	 if(orders==null || orders.size()<=0) return new ArrayList<OrderInfoPO>();
		   	 return orders;
		}
		
		public List<OrderInfoPO> GetConfirmListbyTable(long tableId) {
			 QueryHql hql = this.newQueryHql();
			 hql.andBy("tableId",Condition.eq,tableId);
			 hql.andBy("confirm",Condition.eq,1);
		   	 List<OrderInfoPO> orders =findByHql(hql);
		   	 if(orders==null || orders.size()<=0) return new ArrayList<OrderInfoPO>();
		   	 return orders;
		}
		
		
		
		//统计总数
		public int  GetCountByPage(){
			CountHql cHql=this.newCountHql();
			
			int allcount=this.countByHql(cHql);
			return allcount;
		}
		
		public int  GetCountByTableAndDate(long tableId,String startDate,String endDate) throws ParseException{
			CountHql cHql=this.newCountHql();
			if(tableId!=0){
				cHql.andBy("tableId",Condition.eq,tableId);
			}
			if(!startDate.isEmpty()&&!endDate.isEmpty()){
				cHql.andBy("createDate",Condition.ge,DateUtil.parse(startDate));
				cHql.andBy("createDate",Condition.le,DateUtil.parse(endDate));
			}
			int allcount=this.countByHql(cHql);
			return allcount;
		}
		
		
		public List<OrderInfoPO> getOrdersByState(long userId, OrderStateType state) {
			
			QueryHql hql = this.newQueryHql();
		   	 hql.andBy("userId", Condition.eq, userId);
		   	hql.andBy("orderState", Condition.eq, state);
		   	List<OrderInfoPO> orders = findByHql(hql);
			return orders;
		}
		
        public List<OrderInfoPO> getPhoneOrders(OrderStateType state
        		,long tableId,long merchantId) {
			
			QueryHql hql = this.newQueryHql();
		   	hql.andBy("orderState", Condition.eq, state);
		   	hql.andBy("tableId", Condition.eq,tableId);
		   	hql.andBy("merchantId", Condition.eq,merchantId);
		   	List<OrderInfoPO> orders = findByHql(hql);
			return orders;
		}
        public List<OrderInfoPO> getPhoneOrdersByPrint(OrderStateType state
        		,long tableId,long merchantId) {
			
			QueryHql hql = this.newQueryHql();
		   	hql.andBy("orderState", Condition.eq, state);
		   	hql.andBy("tableId", Condition.eq,tableId);
		   	hql.andBy("merchantId", Condition.eq,merchantId);
		   	hql.andBy("print", Condition.eq,1);
		   	List<OrderInfoPO> orders = findByHql(hql);
			return orders;
		}
		
		
		public List<OrderInfoPO> getOrdersByState(long userId, OrderStateType state, int pageNum, int pageSize) {
			
			QueryHql hql = this.newQueryHql();
		   	 hql.andBy("userId", Condition.eq, userId);
		   	hql.andBy("orderState", Condition.eq, state);
		   	List<OrderInfoPO> orders =findByHqlPage(hql,pageNum,pageSize);
		   	
			return orders;
		}

		public List<OrderInfoPO> findByFieldByPage(String field, Object value, int pageNum, int pageSize) {
			QueryHql hql = newQueryHql();
			hql.andBy(field, Condition.eq, value);
			List<OrderInfoPO> orders =findByHqlPage(hql,pageNum,pageSize);
			return orders;
		}
		
		public List<OrderReportDTO> queryByReport(long merchantId) {
			StringBufferWrapper sqlBuffer = new StringBufferWrapper().append("SELECT count(*) allNum,productName")
																	  .append(" FROM t_Sys_OrderInfo")
																	  .append("");
			if(merchantId!=0){
				sqlBuffer.append(" WHERE merchantId=").append(merchantId).append(" ");
			}
			sqlBuffer.append(" group by productName ORDER BY allNum DESC ");
			SQLQuery query = getCurrentSession().createSQLQuery(sqlBuffer.toString())
												.addScalar("allNum", StandardBasicTypes.INTEGER)
												.addScalar("productName");
			query.setResultTransformer(Transformers.aliasToBean(OrderReportDTO.class));
			query.setMaxResults(8);
			return query.list();
		}
		
}
