package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.BranchEnrollQuota;
import net.cedu.entity.enrollment.EnrollQuotaBatch;

/**
 * 批次总指标  业务逻辑接口
 * 
 * @author gaolei
 *
 */
public interface EnrollQuotaBatchBiz {
	

	/**
	 * 添加批次总指标
	 * @param enrollquotabatch
	 * @return
	 * @throws Exception
	 */
	public boolean addEnrollQuotaBatch(int quotaId,List<GlobalEnrollBatch> globalenrollbatchlst ,int managerId)throws Exception;
	
	
	/**
	 * 修改批次总指标
	 * @param enrollquotabatch
	 * @return
	 * @throws Exception
	 */
	public boolean updateEnrollQuotaBatch(EnrollQuotaBatch enrollquotabatch)throws Exception;
	
	
	
	/**
	 * 删除批次总指标
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteEnrollQuotaBatch(int id)throws Exception;
	
	/**
	 * 查询批次总指标按Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EnrollQuotaBatch findEnrollQuotaBatchById(int id)throws Exception;
	
	
	/**
	 * 查询批次总指标
	 * @param batchIds
	 * @return
	 * @throws Exception
	 */
	public List<EnrollQuotaBatch> findEnrollQuotaBatchBybatchIds(int quotaId,String batchIds) throws Exception;
	
	
	
	/**
	 *  查询批次总指标按年度ID，批次ID
	 * @param quotaId
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public EnrollQuotaBatch findEnrollQuotaBatchByQuotaIdBatchId(int quotaId,int batchId) throws Exception;
	
	
	/**
	 * 查询批次总指标附带年度信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public EnrollQuotaBatch  findEnrollQuotaBatchAllById(int id) throws Exception;
	
	
	/**
	 * 查询批次总指标批次
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public List<EnrollQuotaBatch> findEnrollQuotaBatch()throws Exception;
	
	
	
	
	
	
	
	
	


}
