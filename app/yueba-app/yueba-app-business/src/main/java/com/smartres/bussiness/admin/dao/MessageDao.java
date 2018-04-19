package com.smartres.bussiness.admin.dao;

import org.springframework.stereotype.Repository;

import com.smartres.bussiness.admin.po.MerchantPO;
import com.smartres.bussiness.admin.po.MessagePO;

import pub.caterpillar.orm.dao.AbstractBaseDao;

@Repository("com.smartres.bussiness.admin.dao.MessageDao")
public class MessageDao extends AbstractBaseDao<MessagePO>{

}
