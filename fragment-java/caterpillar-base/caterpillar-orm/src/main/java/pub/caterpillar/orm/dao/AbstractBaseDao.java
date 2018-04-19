/**
 * Copyright (C) 2014 Sumavision
 *
 *
 * @className:platform.orm.common.dao.BaseHibernateDao
 * @description:hibernate4的模版的dao,必须在事务环境下使用
 * 
 * @version:v1.0.0 
 * @author:zhuzheng
 * 
 */
package pub.caterpillar.orm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import pub.caterpillar.orm.hql.Condition;
import pub.caterpillar.orm.hql.CountHql;
import pub.caterpillar.orm.hql.DeleteHql;
import pub.caterpillar.orm.hql.Hql;
import pub.caterpillar.orm.hql.QueryHql;
import pub.caterpillar.orm.hql.SubHql;
import pub.caterpillar.orm.po.AbstractBasePO;
import pub.caterpillar.orm.po.PageObject;


/**
 * 所有dao都需要继承此BaseDao
 * 
 * @author zhuzheng
 * 
 * @param <T>
 */

public abstract class AbstractBaseDao<T extends AbstractBasePO> implements IBaseDao<T> {
	
	Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractBaseDao() {
		// 通过泛型获取持久化对象的类型
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	

	protected Class<T> getEntityClass() {
		return entityClass;
	}



	@Resource(name = "hibernate4_sessionFactory")
	SessionFactory sessionFactory;
	
	protected Session getCurrentSession(){
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
	
	protected void setQueryParameter(Hql hql,Query query){
		for (String valueName:hql.getValueMap().keySet()) {
			Object value = hql.getValueMap().get(valueName);
			if (value instanceof Collection<?>){
				query.setParameterList(valueName, (Collection<?>) value);
			}else{
				query.setParameter(valueName, value);
			}
		}
	}
	
	@Override
	public DeleteHql newDeleteHql(){
		return new DeleteHql(getEntityClass());
	}
	
	@Override
	public QueryHql newQueryHql(){
		return new QueryHql(getEntityClass());
	}
	
	@Override
	public CountHql newCountHql(){
		return new CountHql(getEntityClass());
	}
	
	@Override
	public SubHql newSubHql(){
		return new SubHql(getEntityClass());
	}


	// -------------------------------------------------------------------------
	// 数据库操作方法
	// -------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public T load(Serializable id) {
		if (id == null) return null;
		return (T) getCurrentSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> loadAll() {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Serializable id) {
		/*QueryHql hql = new QueryHql(entityClass);
		hql.andBy("id", Condition.eq, id);
		List<T> result = findByHql(hql);
		if(result==null || result.size()<=0) return null;
		return result.get(0);*/
		if (id == null) return null;
		return (T) getCurrentSession().get(entityClass, id);
	}
	
	@Override
	public T get(String uuid) {
		if(uuid == null) return null;
		List<T> result = findByField("uuid", uuid);
		if(result==null || result.isEmpty()) return null;
		return result.get(0);
	}

	@Override
	public List<T> getAllByIds(Collection<Long> ids) {
		if (ids==null||ids.isEmpty()) {
			return null;
		}
		QueryHql hql = new QueryHql(entityClass);
		hql.andIn("id", ids);
		List<T> c = findByHql(hql);
		return c;
	}
	
	@Override
	public List<T> getAllByUuids(Collection<String> uuids){
		if (uuids==null||uuids.isEmpty()) {
			return null;
		}
		QueryHql hql = new QueryHql(entityClass);
		hql.andIn("uuid", uuids);
		return findByHql(hql);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) getCurrentSession()
				.createCriteria(entityClass)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@Override
	public void flush() {
		getCurrentSession().flush();
	}
	
	@Override
	public void delete(Serializable id) {
		if (id == null) return;
		getCurrentSession().delete(get(id));
	}

	@Override
	public void delete(T o) {
		if (o == null) return;
		getCurrentSession().delete(o);
	}

	@Override
	public void deleteAll() {
		final String queryString = "delete from " + entityClass.getName();
		Query query = getCurrentSession().createQuery(queryString);
		query.executeUpdate();

	}
	
	@Override
	public void deleteByUuid(String uuid){
	   	 DeleteHql hql = this.newDeleteHql();
	   	 hql.andBy("uuid",Condition.eq, uuid);
	     deleteByHql(hql);
	}

	@Override
	public void deleteAll(Collection<T> c) {
		if (c==null||c.isEmpty()) return;
		for (T entity : c) {
			getCurrentSession().delete(entity);
		}
	}
	
	@Override
	public void deleteAllByIds(Collection<Long> ids) {
		if (ids==null||ids.isEmpty()) return;
		QueryHql hql = new QueryHql(entityClass);
		hql.andIn("id", ids);
		List<T> c = findByHql(hql);
		for (T t:c){
			getCurrentSession().delete(t);
		}
	}

	@Override
	public void save(T o) {
		if (o==null) return;
		getCurrentSession().save(o);
	}
	
	@Override
	public void saveAndEvict(T o) {
		if (o==null) return;
		getCurrentSession().save(o);
		getCurrentSession().evict(o);
	}
	

	@Override
	public void saveAll(Collection<T> c) {
		if (c==null||c.isEmpty()) return;
		for (T o:c){
			getCurrentSession().save(o);
		}
	}

	@Override
	public void update(T o) {
		if (o==null) return;
		getCurrentSession().update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		if (o==null) return;
		getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public void saveOrUpdateAll(Collection<T> entitys) {
		if (entitys==null||entitys.isEmpty()) return;
		for(T entity : entitys){
			getCurrentSession().saveOrUpdate(entity);
		}		
	}

	@Override
	public void merge(T o) {
		if (o==null) return;
		getCurrentSession().merge(o);
	}

	@Override
	public void evict(T o) {
		if (o == null) return;
		getCurrentSession().evict(o);
	}

	@Override
	public void replicate(T o) {
		if (o==null) return;
		getCurrentSession().replicate(o, ReplicationMode.OVERWRITE);
	}

	// -------------------------------------------------------------------------
	// 数据库查询方法
	// -------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	@Override
	public T getAnyOne() {
		Criteria criteria =getCurrentSession().createCriteria(entityClass);
		criteria.setMaxResults(1);
		return (T) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... values) {
		Query queryObject = getCurrentSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return (List<T>) queryObject.list();
	}
	
	@Override
	public List findSpecialType(String hql) {
		Query queryObject = getCurrentSession().createQuery(hql);
		return queryObject.list();
	}
	
	@Override
	public List<T> findByHqlPage(final QueryHql hql,int pageNum,int pageSize) {
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		//分页
		int firstResult = (pageNum - 1) * pageSize;
	  	query.setFirstResult(firstResult);
	  	query.setMaxResults(pageSize);
		return query.list();
	}


	@Override
	public int deleteByHql(final DeleteHql hql) {
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHql(final QueryHql hql) {
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHqlAndCount(final QueryHql hql,final int count) {
		Query query = getCurrentSession().createQuery(hql.toString());
		query.setMaxResults(count);
		setQueryParameter(hql, query);
		return query.list();
	}
	
	//强制从数据库中获取数据
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHqlIgnoreCache(final QueryHql hql) {
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		query.setCacheMode(CacheMode.IGNORE);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByHql(final QueryHql hql, final int pageNum,
			final int pageSize) {
		Query query = getCurrentSession().createQuery(hql.toString());
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		setQueryParameter(hql, query);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findBySql(String sql){
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findBySql(String sql, int pageNum, int pageSize){
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <O> List<O> findFieldByHql(QueryHql hql) {
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <O> List<O> findFieldByHql(QueryHql hql, int pageNum,
			int pageSize) {
		Query query = getCurrentSession().createQuery(hql.toString());
		int firstResult = (pageNum - 1) * pageSize;
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		setQueryParameter(hql, query);
		return query.list();
	}
	

	
	@SuppressWarnings("unchecked")
	@Override
	public <O> O getFieldByHql(QueryHql hql) {
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		return (O) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getByHql(QueryHql hql) {
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		return (T) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getAnyByHql(QueryHql hql) {
		Query query = getCurrentSession().createQuery(hql.toString());
		query.setMaxResults(1);
		setQueryParameter(hql, query);
		return (T) query.uniqueResult();
	}

	@Override
	public int countByHql(final CountHql hql) {
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		return ((Number) query.uniqueResult()).intValue();
	}

	@Override
	public int countAll() {
		Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	@Override
	public int deleteByField(String propertyName, final Object value) {
		String queryString = "delete from " + entityClass.getSimpleName()
				+ " as model where model." + propertyName + "= ?";
		Query query = getCurrentSession().createQuery(queryString);
		query.setParameter(0, value);
		return query.executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(final int page, final int pageSize) {
		/*Criteria criteria = getCurrentSession().createCriteria(entityClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFirstResult((page - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		return criteria.list();*/
		QueryHql hql = newQueryHql();
		return findByHql(hql, page, pageSize);
	}

	@Override
	public List<T> findByField(String field, Object value) {
		QueryHql hql = newQueryHql();
		hql.andBy(field, Condition.eq, value);
		return findByHql(hql);
	}

	@Override
	public List<T> findByFields(String[] fields, Object[] values) {
		QueryHql hql = newQueryHql();
		for (int i=0;i<fields.length;i++){
			hql.andBy(fields[i], Condition.eq, values[i]);
		}
		return findByHql(hql);
	}

	@Override
	public T getByFields(String[] fields, final Object[] values) {
		QueryHql hql = newQueryHql();
		for (int i=0;i<fields.length;i++){
			hql.andBy(fields[i], Condition.eq, values[i]);
		}
		return getByHql(hql);
	}

	@Override
	public T getByField(final String field, final Object value) {
		QueryHql hql = newQueryHql();
		hql.andBy(field, Condition.eq, value);
		return getByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByField(String field, Object value, int page,
			int pageSize) {
		QueryHql hql = newQueryHql();
		hql.andBy(field, Condition.eq, value);
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		query.setFirstResult((page - 1)
				* pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByFields(String[] fields, final Object[] values,
			final int page, final int pageSize) {
		QueryHql hql = newQueryHql();
		for (int i=0;i<fields.length;i++){
			hql.andBy(fields[i], Condition.eq, values[i]);
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		query.setFirstResult((page - 1)
				* pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public int countByField(String field, Object value) {
		CountHql hql = newCountHql();
		hql.andBy(field, Condition.eq, value);
		return countByHql(hql);
	}

	@Override
	public int countByFields(String[] fields, final Object[] values) {
		CountHql hql = newCountHql();
		for (int i=0;i<fields.length;i++){
			hql.andBy(fields[i], Condition.eq, values[i]);
		}
		return countByHql(hql);
	}


	@Override
	public List<T> findAll(PageObject page)
			throws DataAccessException {
		page.setTotalRows(countAll());
		return findAll((page.getPageNo() - 1)
				* page.getPageSize(), page.getPageSize());
	}
	
	@Override
	@SuppressWarnings( { "unchecked" })
	public List<T> findByPage(QueryHql hql, PageObject page)
			throws DataAccessException {
		page.setTotalRows(countByHql(hql.transToCountHql()));
		Query query = getCurrentSession().createQuery(hql.toString());
		setQueryParameter(hql, query);
		query.setFirstResult((page.getPageNo() - 1)
				* page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return query.list();
	}
	
	@Override
	public void execute(String hql){
		Query executeUpdate = getCurrentSession().createQuery(hql);
		executeUpdate.executeUpdate();
	}
	
	@Override
	public int countByHql(final String hql) {
	    Query query = getCurrentSession().createQuery(hql);
		return ((Number) query.uniqueResult()).intValue();
	}
}
