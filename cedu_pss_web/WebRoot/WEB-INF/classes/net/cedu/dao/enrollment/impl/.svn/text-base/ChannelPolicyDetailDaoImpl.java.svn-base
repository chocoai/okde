package net.cedu.dao.enrollment.impl;

import java.util.List;

import net.cedu.common.Constants;
import net.cedu.dao.enrollment.ChannelPolicyDetailDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.enrollment.ChannelPolicyDetail;

import org.springframework.stereotype.Repository;

/**
 * 招生返款政策明细 数据访问层实现
 * 
 * @author gaolei
 *
 */
@Repository
public class ChannelPolicyDetailDaoImpl extends BaseMDDaoImpl<ChannelPolicyDetail> implements ChannelPolicyDetailDao
{
	static final public String EXIST_HQL = " and channelId="+Constants.PLACEHOLDER
		+ " and academyId="+Constants.PLACEHOLDER
		+ " and batchId="+Constants.PLACEHOLDER
		+ " and levelId="+Constants.PLACEHOLDER
		+ " and majorId="+Constants.PLACEHOLDER
		+ " and branchId="+Constants.PLACEHOLDER
		+ " and feeSubjectId="+Constants.PLACEHOLDER
		;
	
	static final public String EXIST_HQL_1 = EXIST_HQL ;
	//修改人 董溟浩 需求改变：通用合作方招生政策只能存在一个
//		+ " and policyId=" + Constants.PLACEHOLDER;
	
	
//	/**
//	 * 检查合作方有没有政策ID为policyId的通用政策存在
//	 * 
//	 * @param channelId
//	 * @param policyId
//	 * @return
//	 * @throws Exception
//	 */
//	public ChannelPolicyDetail isExist(int channelId, int policyId) throws Exception
//	{
//		List<ChannelPolicyDetail> list = getByProperty(EXIST_HQL_1, channelId, policyId);
//		
//		if(list==null) return null;
//		
//		if(list.size()==1) return list.get(0);
//		
//		throw new RuntimeException(/*"data is in error status"*/);
//	}
	
	/**
	 * 验证招生返款政策是否存在
	 * 
	 * @param detail 政策明细
	 * @return 存在时返回政策明细，否则返回NULL
	 */
	public ChannelPolicyDetail isExist(ChannelPolicyDetail detail) throws Exception
	{
		
		List<ChannelPolicyDetail> list = null;
		if(detail.getAcademyId() > 0)
		{
			list = getByProperty(EXIST_HQL,
					detail.getChannelId(), detail.getAcademyId(),
					detail.getBatchId(), detail.getLevelId(),
					detail.getMajorId(), detail.getBranchId(),
					detail.getFeeSubjectId()
				);
		}
		else
		{
			list = getByProperty(EXIST_HQL_1,
					detail.getChannelId(), detail.getAcademyId(),
					detail.getBatchId(), detail.getLevelId(),
					detail.getMajorId(), detail.getBranchId(),
					detail.getFeeSubjectId()//,detail.getPolicyId()
				);
		}
		
		if(list==null || list.size()==0) return null;
		
		if(list.size()==1) return list.get(0);
		
		throw new RuntimeException(/*"data is in error status"*/);
	}
//	
//	public void updateOrder(List<ChannelPolicyDetail> list) throws Exception
//	{
//		this.batchUpdate(list);
//	}
}
