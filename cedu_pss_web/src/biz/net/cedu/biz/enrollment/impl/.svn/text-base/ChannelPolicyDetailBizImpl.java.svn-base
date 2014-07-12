package net.cedu.biz.enrollment.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.ChannelPolicyDetailDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.entity.enrollment.Major;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 招生返款政策 业明细务逻辑层实现
 * 
 * @author gaolei
 *
 */
@Service
public class ChannelPolicyDetailBizImpl implements ChannelPolicyDetailBiz
{
	@Autowired
	private ChannelPolicyDetailDao channelPolicyDetailDao;  //招生返款政策明细 业务接口
//	@Autowired
//	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;  //招生返款政策明细 业务接口
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private AcademyBiz academyBiz;                          //院校 业务接口
//	@Autowired
//	private BranchBiz branchBiz;                          //院校 业务接口
	@Autowired
	private LevelBiz levelBiz;                          //层次 业务接口
	@Autowired
	private MajorBiz majorBiz;                          //专业 业务接口
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//院校招生批次 业务接口
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;  //招生返款政策业务接口
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;  //费用科目业务接口
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;  //费用科目业务接口
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	
	/**
	 * 根据条件查询渠道政策 明细
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetail findChannelPolicyDetailById(int Id) throws Exception
	{
		
		
			List<ChannelPolicyDetail> channelpolicydetaillst = null;
			
			// Ids集合
			PageParame p = new PageParame();
//		    p.setPage(false);                //不使用分页
			p.setHqlConditionExpression("and id="+Constants.PLACEHOLDER+" and deleteFlag = "+Constants.PLACEHOLDER);
			p.setValues(new Object[]{Id,Constants.DELETE_FALSE});
					String branchName="";
					String majorName="";
					String feeSubjectName="";
					ChannelPolicyDetail channelpolicydetail = channelPolicyDetailDao.findById(Id);
					if (channelpolicydetail != null) {
						
						Academy academy=academyBiz.findAcademyById(channelpolicydetail.getAcademyId());	
						channelpolicydetail.setAcademy(academy);//外键表院校
//						EnrollmentSource enrollmentsource=enrollmentSourceBiz.findEnrollmentSourceById(channelpolicydetail.getChannelId());
//						channelpolicydetail.setEnrollmentsource(enrollmentsource);
						ChannelPolicy channelpolicy=channelPolicyBiz.findChannelPolicyById(channelpolicydetail.getPolicyId());
						channelpolicydetail.setChannelpolicy(channelpolicy);//外键表招生返款政策
						AcademyEnrollBatch academyenrollbatch=academyEnrollBatchBiz.findAcademyEnrollBatchById(channelpolicydetail.getBatchId());
						channelpolicydetail.setAcademyenrollbatch(academyenrollbatch);//外键表院校招生批次
						Level level=levelBiz.findLevelById(channelpolicydetail.getLevelId());
						channelpolicydetail.setLevel(level);//外键表层次
//						if(channelpolicydetail.getBranches()!=null)
//						{
//							Object[] branchobj=channelpolicydetail.getBranches().split("_");
//							for(int m=1;m<=branchobj.length;m++)
//							{
//								Branch branch=branchBiz.findBranchById(m);
//								if(branchobj.length==1)
//								{
//									branchName+=branch.getName();
//								}
//								branchName+=branch.getName()+",";
//							}
//						}
//						channelpolicydetail.setBranches(branchName);
						
//						if(channelpolicydetail.getMajors()!=null)
//						{
//							Object[] majorobj=channelpolicydetail.getMajors().split("_");
//							for(int j=1;j<=majorobj.length;j++)
//							{
//								Major major=majorBiz.findMajorById(j);
//								if(majorobj.length==1)
//								{
//									majorName+=major.getName();
//								}
//								majorName+=major.getName()+",";
//							}
//						}
						
//						channelpolicydetail.setMajors(majorName);//外键表专业
//						if(channelpolicydetail.getFeeSubjects()!=null)
//						{
//							Object[] subjectobj=channelpolicydetail.getFeeSubjects().split("_");
//							for(int k=1;k<=subjectobj.length;k++)
//							{
//								FeeSubject feesubject=feeSubjectBiz.findFeeSubjectById(k);
//								if(subjectobj.length==1)
//								{
//									feeSubjectName+=feesubject.getName();
//								}
//								feeSubjectName+=feesubject.getName()+",";
//							}
//						}
//						channelpolicydetail.setFeeSubjects(feeSubjectName);//费用科目

					
					}
				
			
			return channelpolicydetail;
		
	}
	
	
	
	
	

	
	/*
	 * 根据条件查询渠道政策 列表
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicyDetail> findChannelPolicyDetailBypolicychannelId(int policychannelId) throws Exception
	{
		
		
			List<ChannelPolicyDetail> channelpolicydetaillst = null;
			
			// Ids集合
			PageParame p = new PageParame();
//		    p.setPage(false);                //不使用分页
			p.setHqlConditionExpression("and policyId="+Constants.PLACEHOLDER+" and deleteFlag = "+Constants.PLACEHOLDER);
			p.setValues(new Object[]{policychannelId,Constants.DELETE_FALSE});
			Long[] ChannelPolicyDetailIds = channelPolicyDetailDao.getIDs(p);
			if (ChannelPolicyDetailIds != null && ChannelPolicyDetailIds.length != 0) {
				channelpolicydetaillst = new ArrayList<ChannelPolicyDetail>();
				for (int i = 0; i < ChannelPolicyDetailIds.length; i++) {
					String majorName="";
					String feeSubjectName="";
					ChannelPolicyDetail channelpolicydetail = channelPolicyDetailDao.findById(Integer.parseInt(ChannelPolicyDetailIds[i].toString()));
					if (channelpolicydetail != null) {
						ChannelPolicyDetail cpdobj=channelpolicydetail;
						Academy academy=academyBiz.findAcademyById(cpdobj.getAcademyId());	
						cpdobj.setAcademy(academy);//外键表院校
						ChannelPolicy channelpolicy=channelPolicyBiz.findChannelPolicyById(cpdobj.getPolicyId());
						cpdobj.setChannelpolicy(channelpolicy);//外键表招生返款政策
						AcademyEnrollBatch academyenrollbatch=academyEnrollBatchBiz.findAcademyEnrollBatchById(cpdobj.getBatchId());
						cpdobj.setAcademyenrollbatch(academyenrollbatch);//外键表院校招生批次
						Level level=levelBiz.findLevelById(cpdobj.getLevelId());
						cpdobj.setLevel(level);//外键表层次
//						if(cpdobj.getMajors()!=null)
//						{
//							Object[] majorobj=cpdobj.getMajors().split("_");
//							for(int j=1;j<=majorobj.length;j++)
//							{
//								Major major=majorBiz.findMajorById(j);
//								majorName+=major.getName()+"_";
//							}
//						}
//						cpdobj.setMajors(majorName.replace("_","<br />"));//外键表专业
//						if(cpdobj.getFeeSubjects()!=null)
//						{
//							Object[] subjectobj=cpdobj.getFeeSubjects().split("_");
//							for(int k=1;k<=subjectobj.length;k++)
//							{
//								FeeSubject feesubject=feeSubjectBiz.findFeeSubjectById(k);
//								feeSubjectName+=feesubject.getName()+"_";
//							}
//						}
//						cpdobj.setFeeSubjects(feeSubjectName.replace("_","<br />"));//费用科目

						channelpolicydetaillst.add(cpdobj);
					}
				}
			}
			return channelpolicydetaillst;
	
	}
	
	/**
	 * 创建招生政策
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int addDetail(ChannelPolicyDetail entity) throws Exception
	{
		Object id = channelPolicyDetailDao.save(entity);
		
		if(id instanceof Integer){
			return ((Integer)id).intValue();
		} else if(id instanceof Long){
			return ((Long)id).intValue();
		}
		
		return 0;
	}
	
	/**
	 * 修改审批状态
	 */
	public void updateAuditStatus(int detailId, int newStatus, int auditer) throws Exception
	{
		ChannelPolicyDetail detail = channelPolicyDetailDao.findById(detailId);
		detail.setAuditStatus(newStatus);
		detail.setAuditer(auditer);
		detail.setAuditDate(new Date());
		
		channelPolicyDetailDao.update(detail);
	}
	
	/**
	 * 根据Id查询
	 * 
	 * @param detailId
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetail findById(int detailId) throws Exception
	{
		return channelPolicyDetailDao.findById(detailId);
	}
	
	/**
	 * 批量添加
	 * @param list
	 * @throws Exception
	 */
	public List<ChannelPolicyDetail> addList(List<ChannelPolicyDetail> list) throws Exception
	{
		if(list==null) return null;
		
		List<ChannelPolicyDetail> conflictList = new LinkedList<ChannelPolicyDetail>();
		
		Iterator<ChannelPolicyDetail> iter = list.iterator();
		ChannelPolicyDetail detail = null;
		while(iter.hasNext())
		{
			detail = iter.next();
			ChannelPolicyDetail old = channelPolicyDetailDao.isExist(detail);
			if(old==null)
				channelPolicyDetailDao.save(detail);
			else
				conflictList.add(old);
		}
		
		return conflictList;
	}
	
	/**
	 * 更新政策
	 * 
	 * @param detail
	 * @throws Exception
	 */
	public void update(ChannelPolicyDetail detail) throws Exception
	{
		channelPolicyDetailDao.update(detail);
	}
	
	/**
	 * 查询属于某合作方的政策
	 */
	public List<ChannelPolicyDetail> findForChannel(int channelId, int branchId, int academyId, int batchId, int levelId, int majorId, int feeSubjectId, int auditStatus, PageResult<ChannelPolicyDetail> result) throws Exception
	{
		List<Object> params = new ArrayList<Object>(6);
		StringBuilder hql = new StringBuilder();
		
		hql.append(" and channelId=").append(Constants.PLACEHOLDER);
		params.add(channelId);
		hql.append(" and deleteFlag=").append(Constants.PLACEHOLDER);
		params.add(Constants.DELETE_FALSE);
		
		if(branchId>0)
		{
			hql.append(" and branchId=").append(Constants.PLACEHOLDER);
			params.add(branchId);
		}
		if(academyId>0)
		{
			hql.append(" and academyId=").append(Constants.PLACEHOLDER);
			params.add(academyId);
		}
		if(batchId>0)
		{
			hql.append(" and batchId=").append(Constants.PLACEHOLDER);
			params.add(batchId);
		}
		if(levelId>0)
		{
			hql.append(" and levelId=").append(Constants.PLACEHOLDER);
			params.add(levelId);
		}
		if(majorId>0)
		{
			hql.append(" and majorId=").append(Constants.PLACEHOLDER);
			params.add(majorId);
		}
		if(feeSubjectId>0)
		{
			hql.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
			params.add(feeSubjectId);
		}
		if(auditStatus>=0)
		{
			hql.append(" and auditStatus=").append(Constants.PLACEHOLDER);
			params.add(auditStatus);
		}
		
		PageParame param = new PageParame(result);
		param.setHqlConditionExpression(hql.toString());
		param.setValues(params.toArray());
		
		Long[] ids = channelPolicyDetailDao.getIDs(param);
		
		if(ids == null) return null;
		
		List<ChannelPolicyDetail> list = new LinkedList<ChannelPolicyDetail>();
		
		for(Long id : ids)
		{
			list.add(channelPolicyDetailDao.findById(id.intValue()));
		}
		
		return list;
	}
	
	public int countForChannel(int channelId, int branchId, int academyId, int batchId, int levelId, int majorId, int feeSubjectId,int auditStatus, PageResult<ChannelPolicyDetail> result) throws Exception
	{
		List<Object> params = new ArrayList<Object>(6);
		StringBuilder hql = new StringBuilder();
		
		hql.append(" and channelId=").append(Constants.PLACEHOLDER);
		params.add(channelId);
		hql.append(" and deleteFlag=").append(Constants.PLACEHOLDER);
		params.add(Constants.DELETE_FALSE);
		
		if(branchId>0)
		{
			hql.append(" and branchId=").append(Constants.PLACEHOLDER);
			params.add(branchId);
		}
		if(academyId>0)
		{
			hql.append(" and academyId=").append(Constants.PLACEHOLDER);
			params.add(academyId);
		}
		if(batchId>0)
		{
			hql.append(" and batchId=").append(Constants.PLACEHOLDER);
			params.add(batchId);
		}
		if(levelId>0)
		{
			hql.append(" and levelId=").append(Constants.PLACEHOLDER);
			params.add(levelId);
		}
		if(majorId>0)
		{
			hql.append(" and majorId=").append(Constants.PLACEHOLDER);
			params.add(majorId);
		}
		if(feeSubjectId>0)
		{
			hql.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
			params.add(feeSubjectId);
		}
		if(auditStatus>=0)
		{
			hql.append(" and auditStatus=").append(Constants.PLACEHOLDER);
			params.add(auditStatus);
		}
		
		PageParame param = new PageParame(result);
		param.setHqlConditionExpression(hql.toString());
		param.setValues(params.toArray());
		
		int count = channelPolicyDetailDao.getCounts(param);
		
		return count;
	}
	
	/**
	 * 查询外键表相关数据（ID==>Name,Entity）
	 * @param list
	 * @param wrapTo
	 * @throws Exception
	 */
	public void wrapList(List<ChannelPolicyDetail> list, WrapType wrapTo) throws Exception
	{
		if(list==null) return;
		
		Iterator<ChannelPolicyDetail> iter = list.iterator();
		while(iter.hasNext())
		{
			ChannelPolicyDetail detail = iter.next();
			
			/*院校*/
			Academy academy = academyBiz.findAcademyById(detail.getAcademyId());
			if(wrapTo == WrapType.ToName)
			{
				if(academy != null)
					detail.setAcademyName(academy.getName());
			}
			else if(wrapTo == WrapType.ToEntity)
			{
				detail.setAcademy(academy);
			}
			
			/* 批次 */
			AcademyEnrollBatch batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
			if(wrapTo == WrapType.ToName)
			{
				if(batch != null)
					detail.setBatchName(batch.getEnrollmentName());
			}
			else if(wrapTo == WrapType.ToEntity)
			{
				detail.setBatch(batch);
			}
			
			/*层次*/
			Level level = levelBiz.findLevelById(detail.getLevelId());
			if(wrapTo == WrapType.ToName)
			{
				if(level != null)
					detail.setLevelName(level.getName());
			}
			else if(wrapTo == WrapType.ToEntity)
			{
				detail.setLevelName(level.getName());
			}
			
			/*专业*/
			Major major = majorBiz.findMajorById(detail.getMajorId());
			if(wrapTo == WrapType.ToName)
			{
				if(major != null)
					detail.setMajorName(major.getName());
			}
			else if(wrapTo == WrapType.ToEntity)
			{
				detail.setMajor(major);
			}
			
			/*院校*/
			FeeSubject feeSubject = feeSubjectBiz.findFeeSubjectById(detail.getFeeSubjectId());
			if(wrapTo == WrapType.ToName)
			{
				if(feeSubject != null)
					detail.setFeeSubjectName(feeSubject.getName());
			}
			else if(wrapTo == WrapType.ToEntity)
			{
				detail.setFeeSubject(feeSubject);
			}
			
			/* 合作方 */
			Channel channel = channelBiz.findChannel(detail.getChannelId());
			if(wrapTo == WrapType.ToName)
			{
				if(channel != null)
					detail.setChannelName(channel.getName());
			}
			else if(wrapTo == WrapType.ToEntity)
			{
				EnrollmentSource es = enrollmentSourceBiz.findEnrollmentSourceById(channel.getType());
				channel.setChannelTypeName(es.getName());
				detail.setChannel(channel);
			}
				
			
			/* 合作方政策标准 */
			ChannelPolicy policy = channelPolicyBiz.findChannelPolicyById(detail.getPolicyId());
			if(policy != null)
				channelPolicyBiz.wrap(policy);
			
			detail.setChannelpolicy(policy);
		}
	}
	
	/**
	 * 删除政策
	 * 
	 * @param detailId
	 * @throws Exception
	 */
	public void deleteById(int detailId) throws Exception
	{
		channelPolicyDetailDao.deleteById(detailId);
	}
	
	/**
	 * 批量更新审批状态
	 * 
	 * @param ids
	 * @param auditStatus
	 * @param uid
	 * @throws Exception
	 */
	public void updateAuditStatusBatched(int[] ids, int auditStatus,int uid) throws Exception
	{
		if(ids==null) return;
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		
		String now = "'" + sdf.format(new Date()) + "'";
		
		sdf.applyPattern("yyyy-MM-dd");
		String today = "'" +  sdf.format(new Date()) + "'";
		
		
		
		StringBuilder hql = new StringBuilder();
		
		hql.append(" set updaterId=").append(Constants.PLACEHOLDER);
		hql.append(" ,updatedTime=").append(Constants.PLACEHOLDER);
		hql.append(" ,auditer=").append(Constants.PLACEHOLDER);
		hql.append(" ,auditStatus=").append(Constants.PLACEHOLDER);
		hql.append(" ,auditDate=").append(Constants.PLACEHOLDER);
		//批量审批时自动启用
		hql.append(" ,enable=").append(Constants.PLACEHOLDER);

		List<Object> params = new LinkedList<Object>();
		params.add(uid);
		params.add(now);
		params.add(uid);
		params.add(auditStatus);
		params.add(today);
		if(auditStatus==Constants.POLICY_AUDIT_STATUS_GOOD)
		{
			params.add(Constants.STATUS_ENABLED);
		}
		else
		{
			params.add(Constants.STATUS_DISABLE);
		}
		StringBuilder idbuf = new StringBuilder();
		for(int i=0; i<ids.length; i++)
		{
			idbuf.append(ids[i]).append(",");
		}
		
		idbuf.deleteCharAt(idbuf.length()-1);

		channelPolicyDetailDao.update(hql.toString(), idbuf.toString(), params.toArray());
		
//		System.err.println("Update");
	}
	
	/**
	 * 批量更新政策为指定之标准
	 * 
	 * @param ids 政策ID列表
	 * @param policyId 政策标准ID
	 * @param uid 执行当前操作之用户
	 * @throws Exception
	 */
	public void updatePolicyId(int[] ids, int policyId,int uid) throws Exception
	{
		if(ids==null) return;
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		
		String now = "'" + sdf.format(new Date()) + "'";

		StringBuilder hql = new StringBuilder();
		
		hql.append(" set updaterId=").append(Constants.PLACEHOLDER);
		hql.append(" ,updatedTime=").append(Constants.PLACEHOLDER);
		hql.append(" ,policyId=").append(Constants.PLACEHOLDER);

		List<Object> params = new LinkedList<Object>();
		params.add(uid);
		params.add(now);
		params.add(policyId);
		
		StringBuilder idbuf = new StringBuilder();
		for(int i=0; i<ids.length; i++)
		{
			idbuf.append(ids[i]).append(",");
		}
		
		idbuf.deleteCharAt(idbuf.length()-1);

		channelPolicyDetailDao.update(hql.toString(), idbuf.toString(), params.toArray());
		
//		System.err.println("Update");
	}
	
	/**
	 * 更新{@code list }中所有的渠道返款政策明细
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailBiz#updateList(java.util.List)
	 */
	public void updateList(List<ChannelPolicyDetail> list) throws Exception
	{
		for(int i=0 ; i<list.size(); i++){
			channelPolicyDetailDao.update(list.get(i));
		}
	}
	
	/**
	 * 查询适用于学生{@code student }且收费类型为{@code feeSubjectId }的招生返款增长
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailBiz#findByStudent(net.cedu.entity.crm.Student, int)
	 */
	public ChannelPolicyDetail findByStudent(Student student, int feeSubjectId) throws Exception {
		StringBuilder hql = new StringBuilder();
		
		hql.append(" and academyId=").append(Constants.PLACEHOLDER);
		hql.append(" and batchId=").append(Constants.PLACEHOLDER);
		hql.append(" and branchId=").append(Constants.PLACEHOLDER);
		hql.append(" and levelId=").append(Constants.PLACEHOLDER);
		hql.append(" and majorId=").append(Constants.PLACEHOLDER);
		hql.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
		hql.append(" and channelId=").append(Constants.PLACEHOLDER);
		
//		if(!forView){
			hql.append(" and deleteFlag=").append(Constants.DELETE_FALSE);
			hql.append(" and enable=").append(Constants.TRUE);
//		}
		
		Object[] params = new Object[]{
			student.getAcademyId(),
			student.getEnrollmentBatchId(),
			student.getBranchId(),
			student.getLevelId(),
			student.getMajorId(),
			feeSubjectId,
			student.getChannelId()
		};
		
		List<ChannelPolicyDetail> list = channelPolicyDetailDao.getByProperty(hql.toString(), params);
		
		if(list==null || list.size()==0) return null;
		
		if(list.size()>1) throw new RuntimeException("Too many policies matched for the student");
		
		return list.get(0);
	}
	/**
	 * 查询通过审批的数量
	 * @param channelId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public int findChannelPolicyDetailBychannelIdAndauditStatus(int channelId,
			int auditStatus) throws Exception {
		 String sql="";
		 List<Object> list=new ArrayList<Object>();
		 PageParame p = new PageParame();
		 if(0!=channelId)
		 {
			 sql+="and channelId ="+Constants.PLACEHOLDER;
			 list.add(channelId);
		 }
		 if(0!=auditStatus)
		 {
			 sql+="and auditStatus ="+Constants.PLACEHOLDER;
			 list.add(auditStatus);
		 }
		 p.setHqlConditionExpression(sql);
		 p.setValues(list.toArray());
		 
		return channelPolicyDetailDao.getCounts(p);
	}
	/**
	 * 查询审批的数量
	 * @param channelId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public int findChannelPolicyDetailBychannelId(int channelId) throws Exception {
		 String sql="";
		 
		 List<Object> list=new ArrayList<Object>();
		 PageParame p = new PageParame();
		 if(0!=channelId)
		 {
			 sql+="and channelId ="+Constants.PLACEHOLDER;
			 list.add(channelId);
		 }
		 
		 p.setHqlConditionExpression(sql);
		 p.setValues(list.toArray());
		 
		return channelPolicyDetailDao.getCounts(p);
	}
 
	/*
	 * 根据招生返款标准Id和审批状态查询政策数量
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailBiz#findChannelPolicyDetailCountByChannelPolicyIdAuditStatus(int, int)
	 */
	public int findChannelPolicyDetailCountByChannelPolicyIdAuditStatus(int channelPolicyId,int auditStatus)throws Exception
	{
		if(channelPolicyId>0)
		{
			PageParame p = new PageParame();
			String hqlparam = "";
			List<Object> list = new ArrayList<Object>();
			hqlparam += " and policyId= " + Constants.PLACEHOLDER;
			list.add(channelPolicyId);
			hqlparam += " and auditStatus= " + Constants.PLACEHOLDER;
			list.add(auditStatus);
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			return channelPolicyDetailDao.getCounts(p);
		}
		else
		{
			return 0;
		}
	}
	
}
