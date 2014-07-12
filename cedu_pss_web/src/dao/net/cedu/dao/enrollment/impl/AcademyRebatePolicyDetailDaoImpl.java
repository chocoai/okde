package net.cedu.dao.enrollment.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyRebatePolicyDetailDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;

/**
 * 院校返款政策明细  数据访问层
 * 
 * @author Sauntor
 *
 */
@Repository
public class AcademyRebatePolicyDetailDaoImpl
	extends BaseMDDaoImpl<AcademyRebatePolicyDetail>
	implements AcademyRebatePolicyDetailDao
{
	private static final String CHECK_EXISTS_HQL = " and academyId="+Constants.PLACEHOLDER
	+" and batchId="+Constants.PLACEHOLDER
	+" and levelId="+Constants.PLACEHOLDER
	+" and branchId="+Constants.PLACEHOLDER
	+" and majorId="+Constants.PLACEHOLDER
	+" and feeSubjectId="+Constants.PLACEHOLDER;
//	+" and policyId="+Constants.PLACEHOLDER;
	
	// 判断某个政策是否存在
	public AcademyRebatePolicyDetail checkExists(AcademyRebatePolicyDetail detail) throws Exception
	{
		List<AcademyRebatePolicyDetail> list = super.getByProperty(CHECK_EXISTS_HQL, new Object[]{
				detail.getAcademyId(), detail.getBatchId(), detail.getLevelId(),
				detail.getBranchId(), detail.getMajorId()
				, detail.getFeeSubjectId()
			});
		
		if(list==null || list.size()==0) return null;
		
		if(list.size()>1) throw new RuntimeException();
		
		return list.get(0);
	}
}
