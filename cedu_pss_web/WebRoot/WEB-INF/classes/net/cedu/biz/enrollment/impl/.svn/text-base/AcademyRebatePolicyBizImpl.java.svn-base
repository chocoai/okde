package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyRebatePolicyDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校返款政策  业务逻辑接口 实现
 * 
 * @author Sauntor
 *
 */
@Service
public class AcademyRebatePolicyBizImpl implements AcademyRebatePolicyBiz
{
	private static final String IS_EXISTS_HQL = " and academyId="+Constants.PLACEHOLDER
		+" and batchId = "+Constants.PLACEHOLDER
		+" and levelId = "+Constants.PLACEHOLDER
		+" and majorId like "+Constants.PLACEHOLDER
		+" and branchId like "+Constants.PLACEHOLDER
		+" and policyId = "+Constants.PLACEHOLDER;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private AcademyRebatePolicyDao academyRebatePolicyDao;
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;
	
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;
	
	/**
	 * 院校返款政策检索
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicy> list(AcademyRebatePolicy condition, PageResult<AcademyRebatePolicy> pr) throws Exception
	{
		Long[] ids = null;
		PageParame param = new PageParame(pr);
		
		buildListParam(param, condition);
		
		ids = academyRebatePolicyDao.getIDs(param);
		
		List<AcademyRebatePolicy> list = null; 
		
		if(ids == null) return null;
		
		list = new LinkedList<AcademyRebatePolicy>();
		for(Long policyId : ids){
			AcademyRebatePolicy policy = academyRebatePolicyDao.findById(policyId.intValue());
			
			if(policy.getAcademyId()!=AcademyRebatePolicy.ACADEMY_ID_ALL){
				Academy academy = academyBiz.findAcademyById(policy.getAcademyId());
				
				if(academy != null)
					policy.setAcademyName(academy.getName());
			}
			
			policy.setStandards(academyRebatePolicyDetailStandardBiz.findByPolicyId(policy.getId()));
			FeeSubject fs = feeSubjectBiz.findFeeSubjectById(policy.getFeeSubjectId());
			if(fs != null)
			{
				policy.setFeeSubjectName(fs.getName());
				policy.setIndexcount(academyRebatePolicyDetailBiz.findAcademyRebatePolicyDetailCountByPolicyIdAuditStatus(policy.getId(), Constants.POLICY_AUDIT_STATUS_GOOD));
			}
			
			list.add(policy);
		}
		
		return list;
	}

	/**
	 * 按条件查询院校返款政策并返回符合条件的总记录数
	 * @param condition
	 * 
	 * @return
	 * @throws Exception
	 */
	public int countList(AcademyRebatePolicy condition) throws Exception
	{
		PageParame param = new PageParame();
		
		buildListParam(param, condition);
		
		int count = academyRebatePolicyDao.getCounts(param);
		
		return count;
	}
	
	/**
	 * 构造列表查询参数
	 * 
	 * @param param
	 * @param condition
	 */
	private void buildListParam(PageParame param, AcademyRebatePolicy condition){
		List<Object> dynamicParam = new LinkedList<Object>();
		
		if(condition != null){
			StringBuilder hqlc=new StringBuilder();
			
			if(condition.getAcademyId()>0){
				hqlc.append(" and academyId=").append(Constants.PLACEHOLDER);
				dynamicParam.add(condition.getAcademyId());
			}
			
			if(StringUtils.isNotBlank(condition.getTitle())){
				hqlc.append(" and title like ").append(Constants.PLACEHOLDER);
				dynamicParam.add("%"+condition.getTitle()+"%");
			}
			if(condition.getFeeSubjectId()>0){
				hqlc.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
				dynamicParam.add(condition.getFeeSubjectId());
			}
			if(condition.getDeleteFlag()==Constants.DELETE_FALSE || condition.getDeleteFlag()==Constants.DELETE_TRUE) {
				hqlc.append(" and deleteFlag="+Constants.PLACEHOLDER);
				dynamicParam.add(condition.getDeleteFlag());
			}
			if(condition.getCreatorId() != 0){
				hqlc.append(" and creatorId="+Constants.PLACEHOLDER);
				dynamicParam.add(condition.getCreatorId());
			}
			
			if(hqlc.length()>0){
				param.setHqlConditionExpression(hqlc.toString());
			
				param.setValues(dynamicParam.toArray());
			}
		}
	}
	
	/**
	 * 新建收费政策
	 * 
	 * @param policy
	 * @throws Exception
	 */
	public int addAcademyRebatePolicy(AcademyRebatePolicy policy) throws Exception
	{
		Object id = academyRebatePolicyDao.save(policy);
		if(id != null){
			return Integer.valueOf(id.toString());
		}
		
		return 0;
	}
	
	/**
	 * 根据id查询院校返款政策
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicy getAcademyRebatePolicyById(int id) throws Exception
	{
		return academyRebatePolicyDao.findById(id);
	}
	
	/**
	 * 根据id删除院校收费政策
	 * 
	 * @param id
	 * @throws Exception
	 */
	public AcademyRebatePolicy deleteAcademyRebatePolicyById(int id) throws Exception
	{
		return academyRebatePolicyDao.deleteConfig(id);
	}

	/**
	 * 修改 院校返款政策
	 * 
	 * @param policy
	 * @throws Exception
	 */
	public AcademyRebatePolicy updateAcademyRebatePolicy(AcademyRebatePolicy policy) throws Exception
	{
		return academyRebatePolicyDao.update(policy);
	}
	
	/**
	 * 修改院校返款政策并保存其新的标准准则
	 * 
	 * @param policy
	 * @throws Exception
	 */
	public void updateWithStandards(AcademyRebatePolicy policy) throws Exception
	{
		academyRebatePolicyDao.update(policy);
		
		academyRebatePolicyDetailStandardBiz.deleteForPolicy(policy.getId());
		
		Iterator<AcademyRebatePolicyDetailStandard> stdIter = policy.getStandards().iterator();
		while(stdIter.hasNext()){
			AcademyRebatePolicyDetailStandard std = stdIter.next();
			std.setPolicyId(policy.getId());
			academyRebatePolicyDetailStandardBiz.addStandard(std);
		}
	}
	
	/**
	 * 查询某个院校可用的返款政策
	 * 
	 * @param academyId 院校ID
	 * @param feeSubjectId 费用项目
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicy> findAvailableForAcademy(int academyId, int feeSubjectId) throws Exception
	{
		List<Object> params = new ArrayList<Object>(2);
		
		StringBuilder hql = new StringBuilder();
		if(academyId > 0)
		{
			hql.append(" and (academyId=0 or academyId=").append(Constants.PLACEHOLDER).append(") ");
			params.add(academyId);
		}
		else
		{
			hql.append(" and academyId=").append(Constants.PLACEHOLDER);
			params.add(AcademyRebatePolicy.ACADEMY_ID_ALL);
		}
		
		if(feeSubjectId>0)
		{
			hql.append(" and feeSubjectId=").append(Constants.PLACEHOLDER);
			params.add(feeSubjectId);
		}
		
		List<AcademyRebatePolicy> list = academyRebatePolicyDao.getByProperty(hql.toString(), params.toArray());
		
		
		Iterator<AcademyRebatePolicy> arpIter = list.iterator();
		while(arpIter.hasNext())
		{
			AcademyRebatePolicy arp = arpIter.next();
			
			if(arp.getAcademyId()!=AcademyRebatePolicy.ACADEMY_ID_ALL){
				Academy academy = academyBiz.findAcademyById(arp.getAcademyId());
				
				if(academy != null)
					arp.setAcademyName(academy.getName());
			}
			
			arp.setStandards(academyRebatePolicyDetailStandardBiz.findByPolicyId(arp.getId()));
			FeeSubject fs = feeSubjectBiz.findFeeSubjectById(arp.getFeeSubjectId());
			if(fs != null)
			{
				arp.setFeeSubjectName(fs.getName());
			}
		}
		
		return list;
	}
	
	/**
	 * 判断某个政策是否存在
	 * @param academyId
	 * @param batchId
	 * @param levelId
	 * @param majorId
	 * @param branchId
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicy isPolicyExist(int academyId, int batchId, int levelId, int majorId, int branchId, int policyId) throws Exception
	{
		List<AcademyRebatePolicy> list = academyRebatePolicyDao.getByProperty(IS_EXISTS_HQL, new Object[]{ academyId, batchId, levelId, "%_"+majorId+"_%", "%_"+branchId+"_%", policyId });
		if(list == null) return null;
		if(list.size()>1) throw new Exception("database in error");
		
		return list.get(0);
	}
}
