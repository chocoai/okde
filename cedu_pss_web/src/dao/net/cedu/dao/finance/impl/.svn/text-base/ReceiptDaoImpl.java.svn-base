package net.cedu.dao.finance.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.dao.finance.ReceiptDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.finance.Receipt;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *  收据  数据访问层实现
 * @author gaolei
 *
 */
@Repository
public class ReceiptDaoImpl extends BaseMDDaoImpl<Receipt> implements ReceiptDao {

	/*
	 * 根据机构，状态，是否核销查询每个机构的收据总数(Map key:invoiceBookId value:count)
	 * @see net.cedu.dao.finance.ReceiptDao#findCountByBranchAndStatusAndIsCannel(int, int, int, int)
	 */
	public Map<String, Integer> findCountByBranchAndStatusAndIsCannel(int branch,int status,
			int isCannel,int invoiceBookStatus) throws Exception {
		JdbcTemplatePlus jdbcTemplatePlus = getJdbcTemplatePlus();
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql = "select invoice_book_id,count(*) as count from tb_e_receipt where 1=1 ";
		//机构id
		sql += " and invoice_book_id in (select id from tb_e_invoice_book where 1=1 ";
		sql += " and status = "+invoiceBookStatus;
		if(branch>0)
		{
			sql += " and used_by = "+branch;
		}
		sql += ")";
		//状态
		if(status>=Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			sql += " and status="+status;
		}
		//是否核销
		if(isCannel>Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			sql += " and is_canceled="+isCannel;
		}
		sql += " and delete_flag = "+Constants.DELETE_FALSE;
		sql += " group by invoice_book_id";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("invoice_book_id", resultSet.getInt("invoice_book_id"));
						map.put("count", resultSet.getInt("count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("invoice_book_id")+"", map.get("count"));
			}
		}
		return mapResult;
	}

	/*
	 * 根据机构、状态，是否核销查询全部的收据总数
	 * @see net.cedu.dao.finance.ReceiptDao#findAllCountByBranchAndStatusAndIsCannel(int, int, int, int)
	 */
	public int findAllCountByBranchAndStatusAndIsCannel(int branch, int status,
			int isCannel,int invoiceBookStatus) throws Exception {
		JdbcTemplatePlus jdbcTemplatePlus = getJdbcTemplatePlus();
		String sql = "select count(*) from tb_e_receipt where 1=1 ";
		//机构id
		sql += " and invoice_book_id in (select id from tb_e_invoice_book where 1=1 ";
		sql += " and status = "+invoiceBookStatus;
		if(branch>0)
		{
			sql += " and used_by = "+branch;
		}
		sql += ")";
		//状态
		if(status>=Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			sql += " and status="+status;
		}
		//是否核销
		if(isCannel>Constants.PAYMENT_STATUS_ZUO_FEI)
		{
			sql += " and is_canceled="+isCannel;
		}
		sql += " and delete_flag = "+Constants.DELETE_FALSE;
		return jdbcTemplatePlus.queryForInt(sql);
	}
	
}
