package net.cedu.biz.enrollment.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.ChannelPolicyDao;
import net.cedu.dao.enrollment.ChannelPolicyDetailStandardDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 招生返款政策 业务逻辑层实现
 * 
 * @author Sauntor
 *
 */
@Service
public class ChannelPolicyBizImpl implements ChannelPolicyBiz
{
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private ChannelPolicyDao channelPolicyDao;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;
	@Autowired
	private ChannelPolicyDetailStandardDao channelPolicyDetailStandardDao;
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;
	
	/**
	 * 添加 招生返款政策
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int addChannelPolicy(ChannelPolicy entity) throws Exception
	{
		Integer id = new Integer(0);
		Object ret = channelPolicyDao.save(entity);
		if(ret != null){
			id = (Integer)ret;
		}
		
		return id.intValue();
	}
	
	/**
	 * 根据条件查询招生返款政策列表
	 * 
	 * @param condition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicy> findByExample(ChannelPolicy condition, PageResult<ChannelPolicy> pr) throws Exception
	{
		List<ChannelPolicy> list = null;
		
		PageParame param = new PageParame(pr);
		
		buildExmapleCondition(param, condition);
		
		Long[] ids = channelPolicyDao.getIDs(param);
		if(ids==null){
			return null;
		}
		
		list = new LinkedList<ChannelPolicy>();
		
		for(int i=0; i<ids.length; i++){
			list.add(channelPolicyDao.findById(ids[i].intValue()));
		}
		
		return list;
	}
	
	/**
	 * 根据条件查询招生返款政策 列表，并附带查询外键ID对应的‘名称’等字段
	 * @param condition
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicy> findAndWrapp(ChannelPolicy condition, PageResult<ChannelPolicy> pr) throws Exception
	{
		PageParame param = new PageParame(pr);
		
		buildExmapleCondition(param, condition);
		
		Long[] ids = channelPolicyDao.getIDs(param);
		
		if(ids==null) return null;
		
		List<ChannelPolicy> list = new LinkedList<ChannelPolicy>();
		
		for(int i=0; i<ids.length; i++){
			ChannelPolicy cp = channelPolicyDao.findById(ids[i].intValue());
			if(cp.getAcademyId() > Constants.ACADEMY_ID_ALL){
				Academy academy = academyBiz.findAcademyById(cp.getAcademyId());
				
				if(academy != null)
					cp.setAcademyName(academy.getName());
			}
			if(null!=cp.getBranchIds() && !cp.getBranchIds().equals("0") && cp.getBranchIds().indexOf("#")!=-1 )
			{
				String branchName=cp.getBranchIds().substring(1,cp.getBranchIds().length()-1).replace('#', ',');
				List<Branch> branchlst=branchBiz.findAllByIds(branchName);
				branchName="";
				if(null!=branchlst && branchlst.size()>0)
				{	
					StringBuffer branchNameSB = new StringBuffer(",");
					for(int j=0;j<branchlst.size();j++)
					{
//						branchName+=branchlst.get(j).getName()+",";
						if(branchNameSB.toString().equals(",")){
							branchNameSB = new StringBuffer(branchlst.get(j).getName());
						}else {
							branchNameSB.append(","+branchlst.get(j).getName());
						}
					}
					if(branchNameSB.toString().equals(",")){
						branchNameSB = new StringBuffer("");
					}
					branchName = branchNameSB.toString();
				}
				if(branchName!=null)
				{
					cp.setBranchName(branchName);
				}
			}
			
			cp.setStandards(channelPolicyDetailStandardBiz.findByPolicyId(cp.getId()));
			FeeSubject fs = feeSubjectBiz.findFeeSubjectById(cp.getFeeSubjectId());
			if(fs != null)
			{
				cp.setFeeSubjectName(fs.getName());
			}
			
			EnrollmentSource es = enrollmentSourceBiz.findEnrollmentSourceById(cp.getChannelTypeId());
			if(es != null)
			{
				cp.setChannelTypeName(es.getName());
			}
			cp.setIndexcount(this.channelPolicyDetailBiz.findChannelPolicyDetailCountByChannelPolicyIdAuditStatus(cp.getId(), Constants.POLICY_AUDIT_STATUS_GOOD));
			list.add(cp);
		}
		
		return list;
	}
	
	/**
	 * 根据条件查询招生返款政策 总数
	 * 
	 * @param condition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public int countByExample(ChannelPolicy condition) throws Exception
	{
		PageParame param = new PageParame();
		
		buildExmapleCondition(param, condition);
		
		int count = channelPolicyDao.getCounts(param);
		
		return count;
	}
	
	/**
	 * 构造条件查询参数
	 * 
	 * @param param
	 * @param condition
	 */
	private void buildExmapleCondition(PageParame param, ChannelPolicy condition)throws Exception
	{
		List<Object> array = new LinkedList<Object>(); // 参数值列表

		StringBuilder hqlc = new StringBuilder();
		
		if(condition != null)
		{
			if(StringUtils.isNotBlank(condition.getCode())){
				hqlc.append(" and code=").append(Constants.PLACEHOLDER);
				array.add(condition.getCode());
			}
			if(StringUtils.isNotBlank(condition.getTitle())){
				hqlc.append(" and title like ").append(Constants.PLACEHOLDER);
				array.add("%"+condition.getTitle()+"%");
			}
//			if(condition.getCreatorId()!=0){
//				hqlc.append(" and creatorId=").append(Constants.PLACEHOLDER);
//				array.add(condition.getCreatorId());
//			}
			if(condition.getChannelTypeId()>0){
				hqlc.append(" and channelTypeId=").append(Constants.PLACEHOLDER);
				array.add(condition.getChannelTypeId());
			}
//			if(condition.getUpdaterId()!=0){
//				hqlc.append(" and updaterId=").append(Constants.PLACEHOLDER);
//				array.add(condition.getUpdaterId());
//			}
			if(condition.getAcademyId() > 0){
				hqlc.append(" and academyId=").append(Constants.PLACEHOLDER);
				array.add(condition.getAcademyId());
			}
			if(condition.getFeeSubjectId() > 0){
				hqlc.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
				array.add(condition.getFeeSubjectId());
			}
			if(StringUtils.isNotBlank(condition.getBranchIds())){
				hqlc.append(" and branchIds like ").append(Constants.PLACEHOLDER);
				array.add("%"+condition.getBranchIds()+"%");
			}
		}
		
		if(hqlc.length()>0){
			param.setHqlConditionExpression(hqlc.toString());
			param.setValues(array.toArray());
		}
	}
	
	/**
	 * 根据类型(招生/非招生)查询
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicy> findByType(int type) throws Exception
	{
		return channelPolicyDao.getByProperty(" and type="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER, type, Constants.DELETE_FALSE);
	}
	
	/**
	 * 查询招生返款政策
	 * 
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicy findChannelPolicyById(int id) throws Exception
	{
		return channelPolicyDao.findById(id);
		
	}
	
	/**
	 * 添加招生返款政策标准，并保存起对应标准准则
	 * 
	 * @param policy
	 * @return 成功添加时返回新添加的政策标准的ID
	 * @throws Exception
	 */
	public int addPolicyWithStandards(ChannelPolicy policy) throws Exception
	{
		Integer id = null;
		Object ret = channelPolicyDao.save(policy);
		
		if(ret instanceof Integer || ret instanceof Long){
			id = (Integer) ret;
		}
		
		Iterator<ChannelPolicyDetailStandard> stdIter = policy.getStandards().iterator();
		while(stdIter.hasNext())
		{
			ChannelPolicyDetailStandard std = stdIter.next();
			if(std==null)
			{
				stdIter.remove();
				continue;
			}
			
			std.setPolicyId(id);
		}
		
		channelPolicyDetailStandardBiz.addList(policy.getStandards());
		
		return id;
	}
	
	// 更新招生返款政策标准
	public void updateWithStandards(ChannelPolicy policy, List<ChannelPolicyDetailStandard> oldStdList) throws Exception
	{
		ChannelPolicy old = channelPolicyDao.findById(policy.getId());
		old.setAcademyId(policy.getAcademyId());
		old.setBranchIds(policy.getBranchIds());
		old.setChannelTypeId(policy.getChannelTypeId());
		old.setFeeSubjectId(policy.getFeeSubjectId());
		old.setRequiresAudit(policy.getRequiresAudit());
		old.setTitle(policy.getTitle());
		old.setUpdatedTime(new Date());
		old.setUpdaterId(policy.getUpdaterId());
		
//		channelPolicyDetailStandardBiz.deleteByPolicyId(policy.getId());
//		channelPolicyDao.deleteById(policy.getId());
//		List<ChannelPolicyDetailStandard> oldStdList = channelPolicyDetailStandardDao.getByProperty("policyId", policy.getId());
		if(oldStdList != null)
		{
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<oldStdList.size(); i++){
//				channelPolicyDetailStandardDao.delete(oldStdList.get(i));
				sb.append(",").append(oldStdList.get(i).getId());
			}
			
			sb.deleteCharAt(0);
			channelPolicyDetailStandardDao.deleteByIds(sb.toString());
		}
		
		if(policy.getStandards() != null)
		{
			Iterator<ChannelPolicyDetailStandard> stdIter = policy.getStandards().iterator();
			while(stdIter.hasNext())
			{
				ChannelPolicyDetailStandard std = stdIter.next();
				std.setPolicyId(policy.getId());
				
				channelPolicyDetailStandardDao.save(std);
			}
		}
		channelPolicyDao.update(old);
//		throw new Exception();
	}
	
	/**
	 * 查询合作方可用的返款标准
	 * 
	 * @param channelId 合作方ID
	 * @param academyId 院校ID
	 * @param feeSubject 费用项目
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicy> findAvaliableToChannel(int channelId, int academyId, int feeSubject,int branchId) throws Exception
	{
		int channelType = channelBiz.findChannel(channelId).getType();
		StringBuilder hql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		hql.append(" and (channelTypeId=").append(-1);
		hql.append(" or channelTypeId=").append(Constants.PLACEHOLDER).append(')');
		params.add(channelType);
		
		hql.append(" and (academyId=").append(-1);
		hql.append(" or academyId=").append(Constants.PLACEHOLDER).append(')');
		params.add(academyId);
		
		if(feeSubject > 0)
		{
			hql.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
			params.add(feeSubject);
		}
		if(branchId>1)
		{
			hql.append(" and branchIds like ").append(Constants.PLACEHOLDER);
			params.add("%#"+branchId+"#%");
		}
		hql.append(" and deleteFlag=").append(Constants.DELETE_FALSE);
		
		hql.append(" order by createdTime desc");
		
		List<ChannelPolicy> list = channelPolicyDao.getByProperty(hql.toString(), params);
		
		Iterator<ChannelPolicy> cpIter = list.iterator();
		while(cpIter.hasNext())
		{
			ChannelPolicy cp = cpIter.next();
			
			if(cp.getAcademyId() > 0){
				Academy academy = academyBiz.findAcademyById(cp.getAcademyId());
				
				if(academy != null)
					cp.setAcademyName(academy.getName());
				
				
				
				
				
			}
			
			if(null!=cp.getBranchIds() && !cp.getBranchIds().equals("0")  && cp.getBranchIds().indexOf("#")!=-1 )
			{
				String branchName=cp.getBranchIds().substring(1,cp.getBranchIds().length()-1).replace('#', ',');
				List<Branch> branchlst=branchBiz.findAllByIds(branchName);
				branchName="";
				StringBuffer branchNameSB = new StringBuffer(",");
				if(null!=branchlst && branchlst.size()>0)
				{	
					for(int j=0;j<branchlst.size();j++)
					{
//						branchName+=branchlst.get(j).getName()+",";
						if(branchNameSB.toString().equals(",")){
							branchNameSB = new StringBuffer(branchlst.get(j).getName());
						}else {
							branchNameSB.append(","+branchlst.get(j).getName());
						}
					}
					if(branchNameSB.toString().equals(",")){
						branchNameSB = new StringBuffer("");
					}
					branchName = branchNameSB.toString();
				}
				
				if(branchName!=null)
				{
					cp.setBranchName(branchName);
				}
			}else
			{
				cp.setBranchName("");
			}
			
			
			
			cp.setStandards(channelPolicyDetailStandardBiz.findByPolicyId(cp.getId()));
			FeeSubject fs = feeSubjectBiz.findFeeSubjectById(cp.getFeeSubjectId());
			if(fs != null)
			{
				cp.setFeeSubjectName(fs.getName());
			}
			
			EnrollmentSource es = enrollmentSourceBiz.findEnrollmentSourceById(cp.getChannelTypeId());
			if(es != null)
			{
				cp.setChannelTypeName(es.getName());
			}
		}
		
		return list;
	}
	
	/**
	 * 包装关联表ID到对应的Name
	 * @param cp
	 * @throws Exception
	 */
	public void wrap(ChannelPolicy cp) throws Exception
	{
		if(cp.getAcademyId() > Constants.ACADEMY_ID_ALL){
			Academy academy = academyBiz.findAcademyById(cp.getAcademyId());
			
			if(academy != null)
				cp.setAcademyName(academy.getName());
		}
		
		cp.setStandards(channelPolicyDetailStandardBiz.findByPolicyId(cp.getId()));
		FeeSubject fs = feeSubjectBiz.findFeeSubjectById(cp.getFeeSubjectId());
		if(fs != null)
		{
			cp.setFeeSubjectName(fs.getName());
		}
		
		EnrollmentSource es = enrollmentSourceBiz.findEnrollmentSourceById(cp.getChannelTypeId());
		if(es != null)
			cp.setChannelTypeName(es.getName());
	}
}
