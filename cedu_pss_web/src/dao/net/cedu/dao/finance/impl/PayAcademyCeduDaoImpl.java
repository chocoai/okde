package net.cedu.dao.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.common.Constants;
import net.cedu.dao.finance.PayAcademyCeduDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.finance.PayAcademyCedu;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 院校返款总部 数据访问默认实现
 * 
 * @author Sauntor
 *
 */
@Repository
public class PayAcademyCeduDaoImpl extends BaseMDDaoImpl<PayAcademyCedu> implements PayAcademyCeduDao
{
	/*
	 * 统计院校返款单总金额
	 * 
	 * @see net.cedu.dao.finance.PayAcademyCeduDao#countPayAcademyCeduAllMoneyByConditions(net.cedu.entity.finance.PayAcademyCedu, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String countPayAcademyCeduAllMoneyByConditions(PayAcademyCedu payAcademyCedu,String starttime,String endtime,String amount) throws Exception
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="select sum(amount_paied) from tb_e_pay_academy_cedu where 1=1 ";
		List<Object> list=new ArrayList<Object>();
		//返款院校
		if (payAcademyCedu.getRemitterId()!=0) {
			sql+=" and remitter = ? ";
			list.add(payAcademyCedu.getRemitterId());
		}
		// 状态
		if (payAcademyCedu.getStatus()!=0) {
			sql+=" and status = ? ";
			list.add(payAcademyCedu.getStatus());
		}
		//开始时间
		if(StringUtils.isNotBlank(starttime)){
			sql+=" and remittance_date>= ? ";
			list.add(starttime);
		}
		//结束时间
		if(StringUtils.isNotBlank(endtime)){
			sql+=" and remittance_date<= ? ";
			list.add(endtime);
		}
		
		//实返金额
		if(amount!=null && !amount.equals(""))
		{
			sql+=" and amount_paied= ? ";
			list.add(Double.valueOf(amount+""));
		}
		//是否年度返款
		if(payAcademyCedu.getIsYearCount()!=0)
		{
			sql += " and  is_year_count =? ";
			list.add(payAcademyCedu.getIsYearCount());
		}	
		//年度返款的具体年数
		if(payAcademyCedu.getCurrentYear()!=0)
		{
			sql += " and  current_year =? ";
			list.add(payAcademyCedu.getCurrentYear());
		}
		
		return (String)jt.queryForObject(sql, list.toArray(), java.lang.String.class);
	}
}
