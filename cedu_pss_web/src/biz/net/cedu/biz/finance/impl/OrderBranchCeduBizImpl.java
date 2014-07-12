package net.cedu.biz.finance.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderBranchCeduBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.dao.finance.OrderBranchCeduDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderBranchCedu;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 汇款总部单业务逻辑层实现
 * 
 * @author Sauntor
 *
 */
@Service
public class OrderBranchCeduBizImpl implements OrderBranchCeduBiz
{
	@Autowired
	private OrderBranchCeduDao orderBranchCeduDao;
	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;
	@Autowired
	private BranchBiz branchBiz;
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int add(OrderBranchCedu entity, List<FeePaymentDetail> fpdList, int userId) throws Exception
	{
		Integer id = null;
		
		Object ret = orderBranchCeduDao.save(entity);
		
		if(ret != null && ret instanceof Integer){
			id =  (Integer)ret;
		}
		
		Date updatedTime = new Date();
		
		for(FeePaymentDetail fpd : fpdList){
			fpd.setOrderBranchCeduId(id);
			fpd.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN);
			fpd.setUpdatedTime(updatedTime);
			fpd.setUpdaterId(userId);
			
			feePaymentDetailDao.update(fpd);
		}
		
		return id;
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void update(OrderBranchCedu entity) throws Exception
	{
		orderBranchCeduDao.update(entity);
	}
	
	/*
	 * 修改汇款总部单
	 * 
	 * @see net.cedu.biz.finance.OrderBranchCeduBiz#updateOrderBranchCedu(net.cedu.entity.finance.OrderBranchCedu)
	 */
	public boolean updateOrderBranchCedu(OrderBranchCedu order) throws Exception
	{
		if (order != null) 
		{
			Object object = orderBranchCeduDao.update(order);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据ID查询
	 * @return
	 * @throws Exception
	 */
	public OrderBranchCedu findById(int id) throws Exception
	{
		return orderBranchCeduDao.findById(id);
	}
	
	/**
	 * 条件查询
	 * @param order
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<OrderBranchCedu> find(OrderBranchCedu order, Date from, Date to,String amount, PageResult<OrderBranchCedu> pr) throws Exception
	{
		StringBuilder hql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		if(StringUtils.isNotBlank(order.getCode())){
			hql.append(" and code=").append(Constants.PLACEHOLDER);
			params.add("'"+order.getCode()+"");
		}
		
		if(StringUtils.isNotBlank(order.getOrderNo())){
			hql.append(" and orderNo like ").append(Constants.PLACEHOLDER);
			params.add("'%"+order.getOrderNo()+"%'");
		}
		
		if(StringUtils.isNotBlank(order.getRemitterAccount())){
			hql.append(" and remitterAccount like ").append(Constants.PLACEHOLDER);
			params.add("'%"+order.getRemitterAccount()+"%'");
		}
		
		if(StringUtils.isNotBlank(order.getRemitteeAccount())){
			hql.append(" and remitteeAccount like ").append(Constants.PLACEHOLDER);
			params.add("'%"+order.getRemitteeAccount()+"%'");
		}
		
		if(order.getStatus()>-1){
			hql.append(" and status=").append(Constants.PLACEHOLDER);
			params.add(order.getStatus());
		}
		
		if(order.getRemitteeId()>-1){
			hql.append(" and remitteeId=").append(Constants.PLACEHOLDER);
			params.add(order.getRemitteeId());
		}
		
		if(order.getRemitterId()>-1){
			hql.append(" and remitterId=").append(Constants.PLACEHOLDER);
			params.add(order.getRemitterId());
		}
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT);
		
		if(from != null){
			hql.append(" and remittanceDate>=").append(Constants.PLACEHOLDER);
			params.add("'"+sdf.format(from)+"'");
		}
		
		if(to != null){
			hql.append(" and remittanceDate<=").append(Constants.PLACEHOLDER);
			params.add("'"+sdf.format(to)+"'");
		}
		
		//回退状态
		if(order.getTypes()!=0)
		{
			hql.append(" and types=").append(Constants.PLACEHOLDER);
			params.add(order.getTypes());
		}
		
		//汇款金额
		if(amount!=null && !amount.equals(""))
		{
			hql.append(" and amount=").append(Constants.PLACEHOLDER);
			params.add(Double.valueOf(amount+""));
		}
		
		PageParame pageParam = new PageParame(pr);
		
		pageParam.setHqlConditionExpression(hql.toString());
		pageParam.setValues(params.toArray());
		
		Long[] ids = orderBranchCeduDao.getIDs(pageParam);
		
		if(ids == null)
			return null;
		
		List<OrderBranchCedu> list = new LinkedList<OrderBranchCedu>();
		
		for(Long id : ids){
			OrderBranchCedu obc = orderBranchCeduDao.findById(id.intValue());
			list.add(obc);
		}
		
		return list;
	}
	
	/*
	 *  条件查询数量（分页）
	 * @see net.cedu.biz.finance.OrderBranchCeduBiz#countOrderBranchCeduByPage(net.cedu.entity.finance.OrderBranchCedu, java.util.Date, java.util.Date)
	 */
	public int countOrderBranchCeduByPage(OrderBranchCedu order, Date from, Date to,String amount) throws Exception
	{
		StringBuilder hql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		if(StringUtils.isNotBlank(order.getCode())){
			hql.append(" and code=").append(Constants.PLACEHOLDER);
			params.add("'"+order.getCode()+"");
		}
		
		if(StringUtils.isNotBlank(order.getOrderNo())){
			hql.append(" and orderNo like ").append(Constants.PLACEHOLDER);
			params.add("'%"+order.getOrderNo()+"%'");
		}
		
		if(StringUtils.isNotBlank(order.getRemitterAccount())){
			hql.append(" and remitterAccount like ").append(Constants.PLACEHOLDER);
			params.add("'%"+order.getRemitterAccount()+"%'");
		}
		
		if(StringUtils.isNotBlank(order.getRemitteeAccount())){
			hql.append(" and remitteeAccount like ").append(Constants.PLACEHOLDER);
			params.add("'%"+order.getRemitteeAccount()+"%'");
		}
		
		if(order.getStatus()>-1){
			hql.append(" and status=").append(Constants.PLACEHOLDER);
			params.add(order.getStatus());
		}
		
		if(order.getRemitteeId()>-1){
			hql.append(" and remitteeId=").append(Constants.PLACEHOLDER);
			params.add(order.getRemitteeId());
		}
		
		if(order.getRemitterId()>-1){
			hql.append(" and remitterId=").append(Constants.PLACEHOLDER);
			params.add(order.getRemitterId());
		}
		
		SimpleDateFormat sdf =new SimpleDateFormat(Constants.DATE_FORMAT);
		
		if(from != null){
			hql.append(" and remittanceDate>=").append(Constants.PLACEHOLDER);
			params.add("'"+sdf.format(from)+"'");
		}
		
		if(to != null){
			hql.append(" and remittanceDate<=").append(Constants.PLACEHOLDER);
			params.add("'"+sdf.format(to)+"'");
		}
		
		//回退状态
		if(order.getTypes()!=0)
		{
			hql.append(" and types=").append(Constants.PLACEHOLDER);
			params.add(order.getTypes());
		}
		
		//汇款金额
		if(amount!=null && !amount.equals(""))
		{
			hql.append(" and amount=").append(Constants.PLACEHOLDER);
			params.add(Double.valueOf(amount+""));
		}
		
		PageParame pageParam = new PageParame();
		
		pageParam.setHqlConditionExpression(hql.toString());
		pageParam.setValues(params.toArray());
				
		return orderBranchCeduDao.getCounts(pageParam);
	}
	
	/**
	 * 查询外键
	 * @param list
	 * @throws Exception
	 */
	public void wrap(List<OrderBranchCedu> list) throws Exception
	{
		if(list == null) return;
		
		for(OrderBranchCedu order : list)
		{
			Branch remitter = branchBiz.findBranchById(order.getRemitterId());
			order.setRemitterName(remitter.getName());
			
			Branch remittee = branchBiz.findBranchById(order.getRemitteeId());
			order.setRemitteeName(remittee.getName());
		}
	}
	
	/**
	 * 根据ID删除<b>汇款总部单</b>
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception
	{
		orderBranchCeduDao.deleteConfig(id);
	}
	
	/**
	 * 根据删除汇款总部单和更新其相关的缴费单明细
	 * @param orderBranchCeduId  汇款单总部
	 * @param fpdList
	 * @return
	 * @throws Exception
	 */
	public void deleteOrderBranchCeduUpdateFeePaymentDetails(int orderBranchCeduId,List<FeePaymentDetail> fpdList) throws Exception
	{
		orderBranchCeduDao.deleteById(orderBranchCeduId);
		for(FeePaymentDetail fpd : fpdList)
		{
			feePaymentDetailDao.update(fpd);
		}
	}
	
	
	/**
	 * 更新汇款单状态（并更新对应缴费单的状态）
	 * @param order 汇款总部单
	 * @param fpdList 缴费单明细
	 * @throws Exception
	 */
	public void updateStatus(OrderBranchCedu order, List<FeePaymentDetail> fpdList) throws Exception
	{
		orderBranchCeduDao.update(order);
		
		for(FeePaymentDetail fpd : fpdList)
		{
			feePaymentDetailDao.update(fpd);
		}
	}
	
	/*
	 * 更新汇款单状态（并更新对应缴费单的状态）
	 * @see net.cedu.biz.finance.OrderBranchCeduBiz#updateOrderAncFpdStatus(net.cedu.entity.finance.OrderBranchCedu, java.util.List)
	 */
	public boolean updateOrderAncFpdStatus(OrderBranchCedu order, List<FeePaymentDetail> fpdList) throws Exception
	{
		boolean isback=false;
		if(order!=null && fpdList!=null && fpdList.size()>0)
		{
			orderBranchCeduDao.update(order);
			
			for(FeePaymentDetail fpd : fpdList)
			{
				isback=feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		return isback;
	}
	
	/*
	 * 更新汇款单状态（并更新对应缴费单的状态及其退费单明细(退费单明细状态为审批过待确认)状态、重新计算缴费单明细各个账户的值和重新分配退费单明细归属问题）
	 * 
	 * @see net.cedu.biz.finance.OrderBranchCeduBiz#updateOrderBranchCeduFpdListRefundfpdList(net.cedu.entity.finance.OrderBranchCedu, java.util.List, java.util.List)
	 */
	public boolean updateOrderBranchCeduFpdListRefundfpdList(OrderBranchCedu order, List<FeePaymentDetail> fpdList,List<FeePaymentDetail> refundfpdList) throws Exception
	{
		boolean isback=false;
		//更新汇款单状态
		orderBranchCeduDao.update(order);
		//历史缴费单明细
		if(fpdList!=null && fpdList.size()>0)
		{
			for(FeePaymentDetail fpd:fpdList)
			{
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		//退费单明细
		if(refundfpdList!=null && refundfpdList.size()>0)
		{
			for(FeePaymentDetail rfpd:refundfpdList)
			{
				this.feePaymentDetailBiz.updateFeePaymentDetail(rfpd);
			}
			
			//*********2012-04-11重构******* 退费后续流程
			this.feePaymentBiz.addRefundHouXuLiuChengOtherConfirm(refundfpdList);
		}
		return isback;
	}
	
	/*
	 * 添加汇款单
	 * 
	 * @see net.cedu.biz.finance.OrderBranchCeduBiz#addOrderBranchCedu(net.cedu.entity.finance.OrderBranchCedu)
	 */
	public boolean addOrderBranchCedu(OrderBranchCedu orderBranchCedu) throws Exception
	{
		if (orderBranchCedu != null) 
		{
			Object object = orderBranchCeduDao.save(orderBranchCedu);
			if (object != null)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 添加汇款单更新缴费单明细集合状态
	 * 
	 * @see net.cedu.biz.finance.OrderBranchCeduBiz#addOrderBranchCeduUpdateFeePaymentDetails(net.cedu.entity.finance.OrderBranchCedu, java.util.List)
	 */
	public boolean addOrderBranchCeduUpdateFeePaymentDetails(OrderBranchCedu orderBranchCedu,List<FeePaymentDetail> feePaymentDetailList) throws Exception
	{
		boolean isback=false;
		if(orderBranchCedu!=null && feePaymentDetailList!=null && feePaymentDetailList.size()>0)
		{
			isback=this.addOrderBranchCedu(orderBranchCedu);
			for(FeePaymentDetail fpd:feePaymentDetailList)
			{
				fpd.setOrderBranchCeduId(orderBranchCedu.getId());
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
		return isback;
	}
	
	/*
	 * 统计汇款单总金额
	 * @see net.cedu.biz.finance.OrderBranchCeduBiz#countOrderBranchCeduAllMoneyByConditions(net.cedu.entity.finance.OrderBranchCedu, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String countOrderBranchCeduAllMoneyByConditions(OrderBranchCedu order,String starttime,String endtime,String amount) throws Exception
	{
		return this.orderBranchCeduDao.countOrderBranchCeduAllMoneyByConditions(order, starttime, endtime, amount);
	}
}
