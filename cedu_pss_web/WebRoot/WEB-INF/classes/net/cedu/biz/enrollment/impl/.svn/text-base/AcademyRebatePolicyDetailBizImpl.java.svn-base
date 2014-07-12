package net.cedu.biz.enrollment.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyRebatePolicyDetailDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.enrollment.Major;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校返宽政策明细 业务逻辑层 实现
 */
@Service
public class AcademyRebatePolicyDetailBizImpl implements AcademyRebatePolicyDetailBiz
{
	private static final String BATCH_UPDATE_POLICY_ID = " set policyId="+Constants.PLACEHOLDER
		+", updaterId="+Constants.PLACEHOLDER
		+", updatedTime="+Constants.PLACEHOLDER;
	
	@Autowired
	private AcademyRebatePolicyDetailDao academyRebatePolicyDetailDao;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private MajorBiz majorBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;
	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	
	/**
	 * 根据条件查询政策明细
	 * @param condition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicyDetail> findByCondition(AcademyRebatePolicyDetail condition, PageResult<AcademyRebatePolicyDetail> pr)  throws Exception
	{
		List<AcademyRebatePolicyDetail> list = new LinkedList<AcademyRebatePolicyDetail>();

		PageParame param = new PageParame(pr);

		buildCondition(param, condition);
		
		Long[] ids = academyRebatePolicyDetailDao.getIDs(param);
		
		if(ids==null) return null;
		
		for(Long id : ids){
			list.add(academyRebatePolicyDetailDao.findById(id.intValue()));
		}
		
		return list;
	}
	
	/**
	 * 条件查询，并包装查询到的列表
	 * 
	 * @param condition
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicyDetail> findWrappedARPDByCondition(AcademyRebatePolicyDetail condition, PageResult<AcademyRebatePolicyDetail> pr)  throws Exception
	{
		List<AcademyRebatePolicyDetail> list = new LinkedList<AcademyRebatePolicyDetail>();

		PageParame param = new PageParame(pr);

		buildCondition(param, condition);
		
		Long[] ids = academyRebatePolicyDetailDao.getIDs(param);
		
		if(ids==null) return null;
		
		for(Long id : ids){
			AcademyRebatePolicyDetail detail = academyRebatePolicyDetailDao.findById(id.intValue());
			
			/* 院校 */
			Academy academy = academyBiz.findAcademyById(detail.getAcademyId());
			if(academy != null)
				detail.setAcademyName(academy.getName());
			
			/*  */
			AcademyEnrollBatch aeb = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
			if(aeb != null)
				detail.setBatchName(aeb.getEnrollmentName());
			
			detail.setPolicy(academyRebatePolicyBiz.getAcademyRebatePolicyById(detail.getPolicyId()));
			detail.setStandards(academyRebatePolicyDetailStandardBiz.findByPolicyId(detail.getPolicyId()));
			
			/* 学习中心 */
			Branch branch = branchBiz.findBranchById(detail.getBranchId());
			if(branch != null)
				detail.setBranchName(branch.getName());
			
			/* 费用科目 */
			FeeSubject feeSubject = feeSubjectBiz.findFeeSubjectById(detail.getFeeSubjectId());
			
			if(feeSubject != null)
				detail.setFeeSubjectName(feeSubject.getName());
			
			/* 专业 */
			Major major = majorBiz.findMajorById(detail.getMajorId());
			if(major != null)
				detail.setMajorName(major.getName());
			
			/* 层次 */
			Level level = levelBiz.findLevelById(detail.getLevelId());
//			detail.setLevel(level);
			if(level != null)
				detail.setLevelName(level.getName());
			
			/* 批次 */
			AcademyEnrollBatch batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
			if(batch != null){
//				detail.setBatch(batch);
				detail.setBatchName(batch.getEnrollmentName());
			}
			
			list.add(detail);
		}
		
		return list;
	}
	
	/**
	 * 根据条件查询政策明细总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int countByCondition(AcademyRebatePolicyDetail condition) throws Exception
	{
		PageParame param = new PageParame();
		
		buildCondition(param, condition);
		
		int total = academyRebatePolicyDetailDao.getCounts(param);
		
		return total;
	}
	
	/**
	 * 根据条件构造查询参数
	 * 
	 * @param param
	 * @param condition
	 */
	private void buildCondition(PageParame param, AcademyRebatePolicyDetail condition)
	{
		List<Object> objs = new LinkedList<Object>();
		StringBuilder hql = new StringBuilder();
		
		if(condition!=null){
			if(condition.getAcademyId()!=0){
				hql.append(" and academyId=").append(Constants.PLACEHOLDER);
				objs.add(condition.getAcademyId());
			}
			if(condition.getPolicyId()>0){
				hql.append(" and policyId=").append(Constants.PLACEHOLDER);
				objs.add(condition.getPolicyId());
			}
			if(condition.getBatchId()>0){
				hql.append(" and batchId=").append(Constants.PLACEHOLDER);
				objs.add(condition.getBatchId());
			}
			if(condition.getLevelId()>0){
				hql.append(" and levelId=").append(Constants.PLACEHOLDER);
				objs.add(condition.getLevelId());
			}
			if(condition.getDeleteFlag()==Constants.DELETE_FALSE || condition.getDeleteFlag()==Constants.DELETE_TRUE){
				hql.append(" and deleteFlag=").append(Constants.PLACEHOLDER);
				objs.add(condition.getDeleteFlag());
			}
			if(condition.getBranchId() != 0){
				hql.append(" and branchId =").append(Constants.PLACEHOLDER);
				objs.add(condition.getBranchId());
			}
			if(condition.getMajorId() != 0){
				hql.append(" and majorId = ").append(Constants.PLACEHOLDER);
				objs.add(condition.getMajorId());
			}
			if(condition.getFeeSubjectId()>0){
				hql.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
				objs.add(condition.getFeeSubjectId());
			}
			if(condition.getAuditerId()!=0){
				hql.append(" and auditerId=").append(Constants.PLACEHOLDER);
				objs.add(condition.getAuditerId());
			}
			if(condition.getAuditStatus() > Constants.POLICY_AUDIT_STATUS_ALL){
				hql.append(" and auditStatus=").append(Constants.PLACEHOLDER);
				objs.add(condition.getAuditStatus());
			}
			if(condition.getCreatorId()!=0){
				hql.append(" and creatorId=").append(Constants.PLACEHOLDER);
				objs.add(condition.getCreatorId());
			}
		}
		
		if(hql.length()>0){
			param.setHqlConditionExpression(hql.toString());
			param.setValues(objs.toArray());
		}
	}
	
	/**
	 * 根据明细ID查询明细
	 * @param detailId
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicyDetail findPolicyDetailById(int detailId) throws Exception
	{
		return academyRebatePolicyDetailDao.findById(detailId);
	}
	
	/**
	 * 保存
	 * @param entity
	 * @return id
	 * @throws Exception
	 */
	public int addDetail(AcademyRebatePolicyDetail entity) throws Exception
	{
		Object id = academyRebatePolicyDetailDao.save(entity);
		
		if(id != null && id instanceof Integer)
		{
			return (Integer)id;
		}
		
		return 0;
	}
	
	/**
	 * 批量添加
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicyDetail> addList(List<AcademyRebatePolicyDetail> list) throws Exception
	{
		List<AcademyRebatePolicyDetail> conflictList = new LinkedList<AcademyRebatePolicyDetail>();
		Iterator<AcademyRebatePolicyDetail> iterator = list.iterator();
		while(iterator.hasNext())
		{
			AcademyRebatePolicyDetail detail = iterator.next();
			if(detail==null) continue;
			AcademyRebatePolicyDetail old = academyRebatePolicyDetailDao.checkExists(detail);
			if(old != null)
				conflictList.add(old);
			else
				academyRebatePolicyDetailDao.save(detail);
		}
		
		return conflictList;
	}

	/**
	 * 更新政策明细
	 * 
	 * @param entity
	 * 
	 * @throws Exception
	 */
	public void updateDetail(AcademyRebatePolicyDetail entity) throws Exception
	{
		academyRebatePolicyDetailDao.update(entity);
	}
	
	/**
	 * 批量更改政策之政策标准
	 * @param detailIds 政策ID列表
	 * @param policyId 政策标准ID
	 * @throws Exception
	 */
	public void updateListToStandard(int[] detailIds, int policyId, int updaterId) throws Exception
	{
		if(detailIds == null || detailIds.length==0)
			return;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = "'"+sdf.format(new Date())+"'";
		StringBuilder idBuf = new StringBuilder();
		
		for(int id : detailIds)
		{
			idBuf.append(id).append(',');
		}
		
		idBuf.deleteCharAt(idBuf.length()-1);
		
		academyRebatePolicyDetailDao.update(BATCH_UPDATE_POLICY_ID, idBuf.toString(), policyId, updaterId, time);
	}
	
	/**
	 * 批量更新政策明细
	 * 
	 * @param list 明细列表
	 * 
	 * @throws Exception
	 */
	public void updateList(List<AcademyRebatePolicyDetail> list) throws Exception
	{
		if(list == null) return;
		
		for(AcademyRebatePolicyDetail e : list){
			academyRebatePolicyDetailDao.update(e);
		}
	}
	
	/**
	 * 根据ID删除政策明细
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception
	{
		academyRebatePolicyDetailDao.deleteConfig(id);
	}

	/*
	 * 查询适用于某学生的院校返款政策
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz#findForStudnet(net.cedu.entity.crm.Student)
	 */
	public AcademyRebatePolicyDetail findForStudnet(Student student, int feeSubjectId, boolean forView) throws Exception {
		StringBuilder hql = new StringBuilder();
		
		hql.append(" and academyId=").append(Constants.PLACEHOLDER);
		hql.append(" and batchId=").append(Constants.PLACEHOLDER);
		hql.append(" and branchId=").append(Constants.PLACEHOLDER);
		hql.append(" and levelId=").append(Constants.PLACEHOLDER);
		hql.append(" and majorId=").append(Constants.PLACEHOLDER);
		hql.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
		
		if(!forView){
			hql.append(" and deleteFlag=").append(Constants.DELETE_FALSE);
			hql.append(" and enable=").append(Constants.TRUE);
		}
		
		Object[] params = new Object[]{
			student.getAcademyId(),
			student.getEnrollmentBatchId(),
			student.getBranchId(),
			student.getLevelId(),
			student.getMajorId(),
			feeSubjectId
		};
		
		List<AcademyRebatePolicyDetail> list = academyRebatePolicyDetailDao.getByProperty(hql.toString(), params);
		
		if(list==null || list.size()==0) return null;
		
		if(list.size()>1) throw new RuntimeException();
		
		return list.get(0);
	}
	
	/*
	 * 根据返款标准Id和审批状态查询政策数量
	 * @see net.cedu.biz.enrollment.PolicyFeeDetailBiz#findPolicyFeeDetailCountByPolicyFeeIdAuditStatus(int, int)
	 */
	public int findAcademyRebatePolicyDetailCountByPolicyIdAuditStatus(int policyId,int auditStatus)throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam += " and policyId= " + Constants.PLACEHOLDER;
		list.add(policyId);
		hqlparam += " and auditStatus= " + Constants.PLACEHOLDER;
		list.add(auditStatus);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return academyRebatePolicyDetailDao.getCounts(p);
	}
	
}
