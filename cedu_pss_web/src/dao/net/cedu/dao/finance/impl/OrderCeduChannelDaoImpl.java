package net.cedu.dao.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.dao.finance.OrderCeduChannelDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 合作方返款 数据DAO默认实现
 * 
 * @author Sauntor
 *
 */
@Repository
public class OrderCeduChannelDaoImpl extends BaseMDDaoImpl<OrderCeduChannel> implements OrderCeduChannelDao
{
	
	/*
	 * 统计招生返款单总金额
	 * 
	 * @see net.cedu.dao.finance.OrderCeduChannelDao#countOrderCeduChannelAllMoneyByConditions(net.cedu.entity.finance.OrderCeduChannel, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String countOrderCeduChannelAllMoneyByConditions(OrderCeduChannel orderCeduChannel,String starttime,String endtime,String amount) throws Exception
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="select sum(amount_paied) from tb_e_order_cedu_channel where 1=1 ";
		List<Object> list=new ArrayList<Object>();
		//学习中心
		if (orderCeduChannel.getBranchId()!=0) {
			sql+=" and branch_id = ? ";
			list.add(orderCeduChannel.getBranchId());
		}
		else if(orderCeduChannel.getParamsString().get("branchIds")!=null && !orderCeduChannel.getParamsString().get("branchIds").equals(""))
		{
			//渠道经理审核只需看到管辖下的学习中心
			sql += " and branch_id in ( "+orderCeduChannel.getParamsString().get("branchIds")+" ) ";		
		}
		// 合作方类型
		if (orderCeduChannel.getChannelType()!=0){
			sql+=" and channel_type = ? ";
			list.add(orderCeduChannel.getChannelType());
		}
		// 合作方
		if (orderCeduChannel.getChannelId()!=0) {
			sql+=" and channel_id = ? ";
			list.add(orderCeduChannel.getChannelId());
		}
		// 状态
		if (orderCeduChannel.getStatus()!=0) {
			sql+=" and status = ? ";
			list.add(orderCeduChannel.getStatus());
		}
		// 回退状态
		if (orderCeduChannel.getTypes()!=0) 
		{
			sql += " and  types = ? ";
			list.add(orderCeduChannel.getTypes());
		}
		//开始时间
		if(StringUtils.isNotBlank(starttime)){
			sql+=" and pay_date>= ? ";
			list.add(starttime);
		}
		//结束时间
		if(StringUtils.isNotBlank(endtime)){
			sql+=" and pay_date<= ? ";
			list.add(endtime);
		}
		
		//实返金额
		if(amount!=null && !amount.equals(""))
		{
			sql+=" and amount_paied= ? ";
			list.add(Double.valueOf(amount+""));
		}
		
		return (String)jt.queryForObject(sql, list.toArray(), java.lang.String.class);
	}
	
	/*
	 * 统计招生返款单调整金额（招生返款查询中已招生返款页签统计用）
	 * 
	 * @see net.cedu.dao.finance.OrderCeduChannelDao#countOrderCeduChannelAdjustPaiedByConditions(net.cedu.entity.crm.Student, net.cedu.entity.finance.FeePaymentDetail)
	 */
	public String countOrderCeduChannelAdjustPaiedByConditions(Student student,FeePaymentDetail fpd) throws Exception
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="select ifnull(sum(adjust_paied),0) from tb_e_order_cedu_channel where 1=1 ";
		List<Object> list=new ArrayList<Object>();
		if(student!=null && fpd!=null)
		{
			//学习中心
			if (student.getBranchId()!=0)
			{
				sql+=" and branch_id = ? ";
				list.add(student.getBranchId());
			}
			// 合作方类型
			if (student.getEnrollmentSource()!=0)
			{
				sql+=" and channel_type = ? ";
				list.add(student.getEnrollmentSource());
			}
			// 合作方
			if (student.getChannelId()!=0) 
			{
				sql+=" and channel_id = ? ";
				list.add(student.getChannelId());
			}
			// 状态
			if (fpd.getRebateStatus()!=0) 
			{
				sql+=" and status = ? ";
				list.add(fpd.getRebateStatus());
			}
			//招生返款期
			if(fpd.getChannelRebateTimeId()>0)
			{
				sql += " and  channel_rebate_time_id = ? ";
				list.add(fpd.getChannelRebateTimeId());
			}
			
			return (String)jt.queryForObject(sql, list.toArray(), java.lang.String.class);
		}
		else
		{
			return "0";
		}
	}
	
}
