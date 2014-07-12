package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.PlanedAcademyBill;
import net.cedu.model.page.PageResult;

public interface PlanedAcademyBillBiz {
	/**
	 * 添加应收院校款
	 * @param entity
	 * @return ID
	 * @throws Exception
	 */
	public int add(PlanedAcademyBill entity) throws Exception;
	
	/**
	 * 删除应收院校款
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PlanedAcademyBill findById(int id) throws Exception;
	
	/**
	 * 分页查询
	 * 
	 * @param bill
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<PlanedAcademyBill> find(PlanedAcademyBill bill, PageResult<PlanedAcademyBill> pr) throws Exception;
	
	/**
	 * 分页查询 总数
	 * 
	 * @param bill
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int count(PlanedAcademyBill bill, PageResult<PlanedAcademyBill> pr) throws Exception;
	
	/**
	 * 更新
	 * @param entity
	 * @throws Exception
	 */
	public void update(PlanedAcademyBill entity) throws Exception;
	

	/**
	 * 通过返款单Id查询对应的应收院校款集合
	 * @param academyRebateId 返款单Id
	 * @return
	 * @throws Exception
	 */
	public List<PlanedAcademyBill> findPlanedAcademyBillListByacademyRebateId(int academyRebateId) throws Exception;
	
	
}
