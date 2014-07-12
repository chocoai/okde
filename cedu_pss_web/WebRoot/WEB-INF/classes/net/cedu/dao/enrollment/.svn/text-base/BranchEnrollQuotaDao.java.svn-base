package net.cedu.dao.enrollment;

import java.util.List;
import java.util.Map;

import net.cedu.dao.BaseDao;
import net.cedu.entity.enrollment.BranchEnrollQuota;

/**
 * 学习中心指标 数据访问接口
 * @author gaolei
 *
 */
public interface BranchEnrollQuotaDao extends BaseDao<BranchEnrollQuota> {
	
	/**
	 * 
	 * @功能：通过批次查询学习中心指标
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-30 下午03:30:17
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotaList(int batchId)throws Exception;
	
	/**
	 * @功能：通过批次查询学习中心指标Map
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-04-23 17:27:15
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param batchId
	 * @return Map key:branch value:target
	 * @throws Exception
	 */
	public Map<String,Integer> findBranchEnrollQuotaMapByBatch(int batchId)throws Exception;

}
