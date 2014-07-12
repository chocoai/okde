package net.cedu.dao.finance.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.cedu.common.Constants;
import net.cedu.dao.finance.OrderBranchCeduDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.finance.OrderBranchCedu;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 学习中心汇款总部单数据访问接口
 * @author Sauntor
 *
 */
@Repository
public class OrderBranchCeduDaoImpl extends BaseMDDaoImpl<OrderBranchCedu> implements OrderBranchCeduDao
{
	/*
	 * 统计汇款单总金额
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDao#countFeePaymentAllMoneyByConditions()
	 */
	public String countOrderBranchCeduAllMoneyByConditions(OrderBranchCedu order,String starttime,String endtime,String amount) throws Exception
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="select sum(amount) from tb_e_order_branch_cedu where 1=1 ";
		List<Object> list=new ArrayList<Object>();
		if(StringUtils.isNotBlank(order.getCode())){
			sql+=" and code like ? ";
			list.add("%"+order.getCode()+"%");
		}
		
		if(StringUtils.isNotBlank(order.getOrderNo())){
			sql+=" and order_no like ? ";
			list.add("%"+order.getOrderNo()+"%");
		}
		
		if(StringUtils.isNotBlank(order.getRemitterAccount())){
			sql+=" and remitter_account like ? ";
			list.add("%"+order.getRemitterAccount()+"%");
		}
		
		if(StringUtils.isNotBlank(order.getRemitteeAccount())){
			sql+=" and remittee_account like ? ";
			list.add("%"+order.getRemitteeAccount()+"%");
		}
		
		if(order.getStatus()>-1){
			sql+=" and status= ? ";
			list.add(order.getStatus());
		}
		
		if(order.getRemitteeId()>-1){
			sql+=" and remittee= ? ";
			list.add(order.getRemitteeId());
		}
		
		if(order.getRemitterId()>-1){
			sql+=" and remitter= ? ";
			list.add(order.getRemitterId());
		}
		//SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT);
		
		if(StringUtils.isNotBlank(starttime)){
			sql+=" and remittance_date>= ? ";
			list.add(starttime);
		}
		
		if(StringUtils.isNotBlank(endtime)){
			sql+=" and remittance_date<= ? ";
			list.add(endtime);
		}
		//回退状态
		if(order.getTypes()!=0)
		{
			sql+=" and types= ? ";
			list.add(order.getTypes());
		}
		
		//汇款金额
		if(amount!=null && !amount.equals(""))
		{
			sql+=" and amount= ? ";
			list.add(Double.valueOf(amount+""));
		}
		
		return (String)jt.queryForObject(sql, list.toArray(), java.lang.String.class);
	}
}
