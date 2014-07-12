package net.cedu.dao.enrollment;

import java.util.Map;

import net.cedu.dao.BaseDao;
import net.cedu.entity.enrollment.AcademyEnrollQuota;

/**
 * 院校指标 数据访问接口
 * @author gaolei
 *
 */
public interface AcademyEnrollQuotaDao extends BaseDao<AcademyEnrollQuota> {
	
	/**
	 * 根据批次查询院校招生指标
	 * @param batchId
	 * @return Map(key:学习中心ID_院校ID value:指标)
	 * @throws Exception
	 */
	public Map<String,Integer> getTargetByBatch(int batchId) throws Exception;

}
