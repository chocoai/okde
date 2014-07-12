package net.cedu.dao.enrollment;

import java.util.Map;

import net.cedu.dao.BaseDao;
import net.cedu.entity.enrollment.UserEnrollQuota;

/**
 * 中心人员指标 数据访问接口
 * @author gaolei
 *
 */
public interface UserEnrollQuotaDao extends BaseDao<UserEnrollQuota> {
	/**
	 * 查询中心人员指标总数按用户Id(并且按用户所属中心id)
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int findUserEnrollQuotaSumByUserId(int userId)throws Exception;
	
	/**
	 * 根据批次和中心查询中心人员指标返回指标数和期望指标数（中心为0则查询全部中心）
	 * @param batchId
	 * @return Map(key:branchId_academyId_userId value:target_expectTarget)
	 * @throws Exception
	 */
	public Map<String,String> findUserEnrollQuotaMapByBatchIdAndBranchId(int batchId,int branchId) throws Exception;
}
