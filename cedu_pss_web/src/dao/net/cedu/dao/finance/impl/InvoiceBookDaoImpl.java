package net.cedu.dao.finance.impl;

import net.cedu.common.Constants;
import net.cedu.dao.finance.InvoiceBookDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.finance.InvoiceBook;

import org.springframework.stereotype.Repository;

/**
 *  票本  数据访问层实现
 * @author gaolei
 *
 */
@Repository
public class InvoiceBookDaoImpl extends BaseMDDaoImpl<InvoiceBook> implements InvoiceBookDao {

	/*
	 * 根据机构查询票本总数
	 * @see net.cedu.dao.finance.InvoiceBookDao#findAllTotalByBranch(int)
	 */
	public int findAllTotalByBranch(int branch,int status) throws Exception {
		JdbcTemplatePlus jdbcTemplatePlus = getJdbcTemplatePlus();
		String sql = "select sum(total) from tb_e_invoice_book where 1=1 ";
		if(branch>0)
		{
			sql += " and used_by = "+branch;
		}
		sql += " and status = "+status;
		sql += " and delete_flag = "+Constants.DELETE_FALSE;
		return jdbcTemplatePlus.queryForInt(sql);
	}
	
}
