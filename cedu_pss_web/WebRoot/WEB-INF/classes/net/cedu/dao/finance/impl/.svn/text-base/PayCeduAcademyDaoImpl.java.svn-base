package net.cedu.dao.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.common.Constants;
import net.cedu.dao.finance.PayCeduAcademyDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.finance.PayCeduAcademy;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 院校打款单  数据访问默认实现
 * 
 * @author Sauntor
 *
 */
@Repository
public class PayCeduAcademyDaoImpl extends BaseMDDaoImpl<PayCeduAcademy> implements PayCeduAcademyDao
{
	/*
	 * 统计院校打款单总金额
	 * 
	 * @see net.cedu.dao.finance.PayCeduAcademyDao#countPayCeduAcademyAllMoneyByConditions(net.cedu.entity.finance.PayCeduAcademy, java.lang.String, java.lang.String, int, java.lang.String)
	 */
	public String countPayCeduAcademyAllMoneyByConditions(PayCeduAcademy payCeduAcademy,String starttime,String endtime,int status,String amount) throws Exception
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="select sum(amount) from tb_e_pay_cedu_academy where 1=1 ";
		List<Object> list=new ArrayList<Object>();
		// 汇款单位
		if (payCeduAcademy.getRemitterId()!=0)  {
			sql+=" and remitter = ? ";
			list.add(payCeduAcademy.getRemitterId());
		}
		//总部确认中心打款院校用到
		if (payCeduAcademy.getCeduId()!=0) 
		{
			sql+=" and remitter <> ? ";
			list.add(payCeduAcademy.getCeduId());
		}
		// 收款单位
		if (payCeduAcademy.getRemitteeId()!=0){
			sql+=" and remittee = ? ";
			list.add(payCeduAcademy.getRemitteeId());
		}
		// 汇款单号
		if (payCeduAcademy.getRemittanceNo()!=null && !payCeduAcademy.getRemittanceNo().equals("")){
			sql+=" and remittance_no like ? ";
			list.add('%'+payCeduAcademy.getRemittanceNo()+'%');
		}
		// 汇款账号
		if (payCeduAcademy.getRemitterAccount()!=null && !payCeduAcademy.getRemitterAccount().equals("")){
			sql+=" and remitter_account like ? ";
			list.add('%'+payCeduAcademy.getRemitterAccount()+'%');
		}
		// 收款账号
		if (payCeduAcademy.getRemitteeAccount()!=null && !payCeduAcademy.getRemitteeAccount().equals("")){
			sql+=" and remittee_account like ? ";
			list.add('%'+payCeduAcademy.getRemitteeAccount()+'%');
		}
		//回退状态
		if(payCeduAcademy.getTypes()!=0)
		{
			sql += " and  types = ?";
			list.add(payCeduAcademy.getTypes());
		}
		// 状态
		if (payCeduAcademy.getStatus()!=0){
			sql+=" and status = ? ";
			list.add(payCeduAcademy.getStatus());
		}
		// 汇款单状态  出纳汇款时不需要前面的状态
		if (status!=0) 
		{
			sql+=" and status >= ? ";
			list.add(status);
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
		
		//金额
		if(amount!=null && !amount.equals(""))
		{
			sql+=" and amount= ? ";
			list.add(Double.valueOf(amount+""));
		}
		
		return (String)jt.queryForObject(sql, list.toArray(), java.lang.String.class);
	}
}
