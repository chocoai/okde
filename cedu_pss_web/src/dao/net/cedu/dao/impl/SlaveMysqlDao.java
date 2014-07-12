package net.cedu.dao.impl;

import javax.sql.DataSource;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 操作从库mysql数据源
 * 
 * @author yangdongdong
 * 
 */
public class SlaveMysqlDao extends HibernateDaoSupport {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		return dataSource;
	}

}
