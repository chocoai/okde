/**
 * 文件名：StudentSourceReportImpl.java
 * 包名：net.cedu.report.dao.impl
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-23 上午09:54:54
 *
 */
package net.cedu.report.dao.impl;

import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.dao.impl.MasterMysqlDao;
import net.cedu.report.dao.BaseReportDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseReportDaoImpl implements BaseReportDao {

	@Autowired
	private MasterMysqlDao masterMysqlDao;

	public JdbcTemplatePlus getJdbcTemplatePlus() {
		JdbcTemplatePlus jdbcTemplate = new JdbcTemplatePlus(
				masterMysqlDao.getDataSource());
		//jdbcTemplate.setUseOneConnection(true);
		return jdbcTemplate;
	}

}
