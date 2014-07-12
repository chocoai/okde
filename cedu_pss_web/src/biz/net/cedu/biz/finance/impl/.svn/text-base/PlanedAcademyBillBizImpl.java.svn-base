package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.PlanedAcademyBillDao;
import net.cedu.entity.finance.PlanedAcademyBill;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanedAcademyBillBizImpl implements PlanedAcademyBillBiz
{
	@Autowired
	private PlanedAcademyBillDao planedAcademyBillDao;
	
	/**
	 * 添加应收院校款
	 * @param entity
	 * @return ID
	 * @throws Exception
	 */
	public int add(PlanedAcademyBill entity) throws Exception
	{
		Integer id = null;
		
		Object ret = planedAcademyBillDao.save(entity);
		
		if(ret != null && ret instanceof Integer)
			id = (Integer)ret;
		
		return id;
	}
	
	/**
	 * 删除应收院校款
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception{
		planedAcademyBillDao.deleteConfig(id);
	}
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PlanedAcademyBill findById(int id) throws Exception{
		return planedAcademyBillDao.findById(id);
	}
	
	/**
	 * 分页查询 列表
	 * 
	 * @param bill
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<PlanedAcademyBill> find(PlanedAcademyBill bill, PageResult<PlanedAcademyBill> pr) throws Exception
	{
		List<PlanedAcademyBill> list = new LinkedList<PlanedAcademyBill>();
		
		PageParame p = new PageParame(pr);
		
		StringBuilder hql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		if(bill != null){
			if(bill.getAcademyId()>0){
				hql.append(" and academyId=").append(Constants.PLACEHOLDER);
				params.add(bill.getAcademyId());
			}
			if(bill.getBranchId()>0){
				hql.append(" and branchId=").append(Constants.PLACEHOLDER);
				params.add(bill.getBranchId());
			}
			
			if(bill.getReceivedWay()>0){
				hql.append(" and receivedWay=").append(Constants.PLACEHOLDER);
				params.add(bill.getReceivedWay());
			}
			
			if(bill.getAcademyRebateId() > 0){
				hql.append(" and academyRebateId=").append(Constants.PLACEHOLDER);
				params.add(bill.getAcademyRebateId());
			}
			if(bill.getIsRebate()==Constants.FALSE || bill.getIsRebate()==Constants.TRUE){
				hql.append(" and isRebate=").append(Constants.PLACEHOLDER);
				params.add(bill.getIsRebate());
			}
			
			if(bill.getDeleteFlag()==Constants.FALSE || bill.getDeleteFlag()==Constants.TRUE){
				hql.append(" and deleteFlag=").append(Constants.PLACEHOLDER);
				params.add(bill.getDeleteFlag());
			}
		}
		
		if(params.size()>0){
			p.setHqlConditionExpression(hql.toString());
			p.setValues(params.toArray());
		}
		
		Long[] ids = planedAcademyBillDao.getIDs(p);
		
		if(ids != null){
			for(Long id : ids){
				list.add(planedAcademyBillDao.findById(id.intValue()));
			}
		}
		
		return list;
	}
	
	/**
	 * 分页查询 总数
	 * 
	 * @param bill
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int count(PlanedAcademyBill bill, PageResult<PlanedAcademyBill> pr) throws Exception
	{
		PageParame p = new PageParame(pr);
		
		StringBuilder hql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		if(bill != null){
			if(bill.getAcademyId() > 0){
				hql.append(" and academyId=").append(Constants.PLACEHOLDER);
				params.add(bill.getAcademyId());
			}
			
			if(bill.getBranchId()>0){
				hql.append(" and branchId=").append(Constants.PLACEHOLDER);
				params.add(bill.getBranchId());
			}
			
			if(bill.getReceivedWay() > 0){
				hql.append(" and receivedWay=").append(Constants.PLACEHOLDER);
				params.add(bill.getReceivedWay());
			}
			
			if(bill.getDeleteFlag() > -1){
				hql.append(" and deleteFlag=").append(Constants.PLACEHOLDER);
				params.add(bill.getDeleteFlag());
			}
		}
		
		if(params.size()>0){
			p.setHqlConditionExpression(hql.toString());
			p.setValues(params.toArray());
		}
		
		int count = planedAcademyBillDao.getCounts(p);
		
		return count;
	}
	
	/**
	 * 更新
	 * @param entity
	 * @throws Exception
	 */
	public void update(PlanedAcademyBill entity) throws Exception
	{
		planedAcademyBillDao.update(entity);
	}
	
	/*
	 * 通过返款单Id查询对应的应收院校款集合
	 * 
	 * @see net.cedu.biz.finance.PlanedAcademyBillBiz#findPlanedAcademyBillListByacademyRebateId(int)
	 */
	public List<PlanedAcademyBill> findPlanedAcademyBillListByacademyRebateId(int academyRebateId) throws Exception
	{
		List<PlanedAcademyBill> pabList=null;
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (academyRebateId != 0) {
			hqlparam += "and academyRebateId =" + Constants.PLACEHOLDER;
			list.add(academyRebateId);
		}
		hqlparam += "and deleteFlag=" + Constants.PLACEHOLDER + ")";
		list.add(Constants.DELETE_FALSE);

		pabList = planedAcademyBillDao.getByProperty(hqlparam, list);
		return pabList;
	}
}
