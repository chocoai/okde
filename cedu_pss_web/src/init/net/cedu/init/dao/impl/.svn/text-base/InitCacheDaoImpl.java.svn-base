package net.cedu.init.dao.impl;

import java.util.List;

import net.cedu.dao.impl.MasterMysqlDao;
import net.cedu.dao.impl.SlaveMysqlDao;
import net.cedu.init.dao.InitCacheDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 系统初始化接口实现类
 * 
 * @author yangdongdong
 * 
 */
@Repository
public class InitCacheDaoImpl implements
		InitCacheDao {

	@Autowired
	private MasterMysqlDao masterMysqlDao;
	
	private SlaveMysqlDao slaveMysqlDao;
	/*
	 * 查询所有信息
	 * 
	 * @see net.cedu.init.dao.InitCacheDao#findAll(java.lang.String)
	 */
	public List<Object> findAll(String entityName) {
		return masterMysqlDao.getHibernateTemplate().find("from " + entityName);
	}

	public Object save(Object entity) {
		return masterMysqlDao.getHibernateTemplate().save(entity);
	}

	public int update(String sql) {
		return getJdbcTemplate().update(sql);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		try {
			JdbcTemplate jt = new JdbcTemplate(masterMysqlDao.getDataSource());
			return jt;
		} catch (DataAccessResourceFailureException e) {
			return null;
		}
	}

}
