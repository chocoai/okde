package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.academy.AcademyDao;
import net.cedu.dao.enrollment.AcademyBatchBranchDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.AcademyBatchBranch;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademyBatchBranchBizImpl implements AcademyBatchBranchBiz {
	@Autowired
	private AcademyBatchBranchDao academyBatchBranchDao;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private AcademyDao academyDao;

	@Autowired
	private AcademyBiz academyBiz;
	
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz;//全局招生批次业务层接口
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;

	
	/*
	 * 按所授权中心查询所有院校
	 * @see net.cedu.biz.enrollment.AcademyBatchBranchBiz#findAcademyByBranchId(int)
	 */
	public List<Academy> findAcademyByBranchId(int branchId) throws Exception {
		List<Academy> academylst = null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (branchId != 0) {
			hqlparam += " and  branchId= " + Constants.PLACEHOLDER;
			list.add(branchId);
		}
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] academyids = academyBatchBranchDao.getIDs(p);
		if (academyids != null && academyids.length != 0) {
			academylst = new ArrayList<Academy>();
			for (int i = 0; i < academyids.length; i++) {
				AcademyBatchBranch academybatchbranch = academyBatchBranchDao
						.findById(Integer.valueOf(academyids[i].toString()));
				Academy academy = academyBiz.findAcademyById(academybatchbranch
						.getAcademyId());
				academylst.add(academy);
			}
		}
		return academylst;
	}

	/**
	 * 查询院校在指定批次<b>已授权</b>中心
	 * 
	 * @param academyId
	 *            院校ID
	 * @param batchId
	 *            批次ID
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findGrantedBranch(int academyId, int batchId)
			throws Exception {
		List<AcademyBatchBranch> list = academyBatchBranchDao.getByProperty(
				" and academyId=" + Constants.PLACEHOLDER + " and batchId="
						+ Constants.PLACEHOLDER, academyId, batchId);

		List<Branch> branches = new LinkedList<Branch>();
		if (list == null)
			return null;
		Iterator<AcademyBatchBranch> iter = list.iterator();
		while (iter.hasNext()) {
			AcademyBatchBranch grantedBranch = iter.next();
			Branch branch = branchBiz.findBranchById(grantedBranch
					.getBranchId());

			branches.add(branch);
		}

		return branches;
	}

	/**
	 * 查询院校在指定批次<b>未授权</b>中心
	 * 
	 * @param academyId
	 *            院校ID
	 * @param batchId
	 *            批次ID
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findUngrantedBranch(int academyId, int batchId)
			throws Exception {
		List<AcademyBatchBranch> grantedBranches = academyBatchBranchDao
				.getByProperty(" and academyId=" + Constants.PLACEHOLDER
						+ " and batchId=" + Constants.PLACEHOLDER, academyId,
						batchId);
		Iterator<AcademyBatchBranch> grantedIter = null;

		List<Branch> allBranches = branchBiz.findBranchForModel();
		Iterator<Branch> allIter = allBranches.iterator();

		while (allIter.hasNext()) {
			Branch branch = allIter.next();

			grantedIter = grantedBranches.iterator();

			while (grantedIter.hasNext()) {
				AcademyBatchBranch _branch = grantedIter.next();
				if (_branch.getBranchId() == branch.getId()) {
					allIter.remove();
					break;
				}
			}
		}

		return allBranches;
	}

	/**
	 * 添加院校授权学习中心
	 * 
	 * @param entity
	 * @return 新添加授权中心ID
	 * @throws Exception
	 */
	public int addAcademyBatchBranch(AcademyBatchBranch entity)
			throws Exception {
		Object id = academyBatchBranchDao.save(entity);

		return (Integer) id;
	}

	/**
	 * 清除院校在指定招生批次的授权学习中心
	 * 
	 * @param academyId
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public int deleteByAcademyAndEnrollBatch(int academyId, int batchId)
			throws Exception {
		int rowCount = 0;

		List<AcademyBatchBranch> list = academyBatchBranchDao.getByProperty(
				" and academyId=" + Constants.PLACEHOLDER + " and batchId="
						+ Constants.PLACEHOLDER, new Object[] { academyId,
						batchId });

		if (list == null)
			return 0;

		try {
			for (rowCount = 0; rowCount < list.size(); rowCount++) {
				academyBatchBranchDao.deleteConfig(list.get(rowCount).getId());
			}
		} catch (Exception e) {
			throw new RuntimeException("Exception occurred with id:"
					+ list.get(rowCount).getId(), e);
		}

		return rowCount;
	}
	
	/*
	 * 按所授权中心查询所有全局批次
	 * @see net.cedu.biz.enrollment.AcademyBatchBranchBiz#findGlobalEnrollBatchByBranchId(int)
	 */
	public List<GlobalEnrollBatch> findGlobalEnrollBatchByBranchId(int branchId)throws Exception
	{
		List<GlobalEnrollBatch> globalBatchList = null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam += " and  branchId= " + Constants.PLACEHOLDER+" group by globalBatchId order by globalBatchId desc";
		list.add(branchId);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] academyids = academyBatchBranchDao.getIDs(p);
		if (academyids != null && academyids.length != 0) {
			globalBatchList = new ArrayList<GlobalEnrollBatch>();
			for (int i = 0; i < academyids.length; i++) {
				AcademyBatchBranch academybatchbranch = academyBatchBranchDao
						.findById(Integer.valueOf(academyids[i].toString()));
				GlobalEnrollBatch globalEnrollBatch = globalEnrollBatchBiz.findGlobalEnrollBatchById(academybatchbranch.getGlobalBatchId());
				globalBatchList.add(globalEnrollBatch);
			}
		}
		return globalBatchList;
	}
	
	/*
	 * 按所授权中心和全局批次查询院校
	 * @see net.cedu.biz.enrollment.AcademyBatchBranchBiz#findAcademyByBranchIdAndGlobalEnrollBatchId(int, int)
	 */
	public List<Academy> findAcademyByBranchIdAndGlobalBatchId(int branchId,int globalBatchId)throws Exception
	{
		List<Academy> academylst = null;
//		PageParame p = new PageParame();
//		String hqlparam = "";
//		List list = new ArrayList();
//		hqlparam += " and  branchId= " + Constants.PLACEHOLDER;
//		list.add(branchId);
//		hqlparam += " and  globalBatchId= " + Constants.PLACEHOLDER;
//		list.add(globalBatchId);
//
//		p.setHqlConditionExpression(hqlparam);
//		p.setValues(list.toArray());
//		Long[] academyids = academyBatchBranchDao.getIDs(p);
		 List<AcademyBatchBranch> academyBatchBranch= academyDao.findAcademyByBranchIdAndGlobalBatchId(branchId,globalBatchId);
		if (academyBatchBranch != null && academyBatchBranch.size() != 0) {
			academylst = new ArrayList<Academy>();
			for (int i = 0; i < academyBatchBranch.size(); i++) {
				AcademyBatchBranch academybatchbranch = academyBatchBranch.get(i);
				Academy academy = academyBiz.findAcademyById(academybatchbranch.getAcademyId());
				academylst.add(academy);
			}
		}
		return academylst;
	}
	
	/**
	 * 根据院校招生批次ID查询已授权的学习中心
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findBranchByBatchId(int batchId) throws Exception
	{
		StringBuilder hql = new StringBuilder();
		
		hql.append("batchId=").append(Constants.PLACEHOLDER);
		
		List<AcademyBatchBranch> list = academyBatchBranchDao.getByProperty("batchId", batchId);
		if(list != null)
		{
			List<Branch> al = new LinkedList<Branch>();
			
			AcademyBatchBranch abb = null;
			Iterator<AcademyBatchBranch> iter = list.iterator();
			
			while(iter.hasNext())
			{
				abb = iter.next();
				Branch acdm = branchBiz.findBranchById(abb.getBranchId());
				al.add(acdm);
			}
			
			return al;
		}
		
		return null;
	}
	
	/*
	 * 添加授权中心
	 * @see net.cedu.biz.enrollment.AcademyBatchBranchBiz#add(int, int[])
	 */
	public void update(int batchId, int[] branchIds) throws Exception
	{
		AcademyEnrollBatch batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(batchId);
		
		List<AcademyBatchBranch> abbList = academyBatchBranchDao.getByProperty("batchId", batchId);
		
		for(AcademyBatchBranch e : abbList){
			academyBatchBranchDao.deleteById(e.getId());
		}
		if(branchIds!=null && branchIds.length>0)
		{
			for(int branchId : branchIds){
				AcademyBatchBranch entity = new AcademyBatchBranch();
				
				entity.setAcademyId(batch.getAcademyId());
				entity.setBatchId(batchId);
				entity.setBranchId(branchId);
				entity.setGlobalBatchId(batch.getGlobalEnrollBatchId());
				
				academyBatchBranchDao.save(entity);
			}
		}
	}
}
