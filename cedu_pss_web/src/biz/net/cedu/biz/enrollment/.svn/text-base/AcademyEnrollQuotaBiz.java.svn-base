package net.cedu.biz.enrollment;

import java.util.List;
import java.util.Map;

import net.cedu.entity.admin.User;
import net.cedu.entity.enrollment.AcademyEnrollQuota;
import net.cedu.model.page.PageResult;

/**
 * 院校指标  业务逻辑接口
 * 
 * @author gaolei
 *
 */
public interface AcademyEnrollQuotaBiz {
	

	/**
	 * 添加院校指标
	 * @param batchId
	 * @param branchId
	 * @param academyId
	 * @param target
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademyEnrollQuota(int batchId,int branchId,int academyId,int target,int managerId)throws Exception;
	
	
	
	/**
	 * 查询院校指标按Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyEnrollQuota findAcademyEnrollQuotaById(int id)throws Exception;
	

	/**
	 * 删除院校指标
	 * @param branchId
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public  boolean deleteAcademyEnrollQuotaBybranchIdAndAcademyId(int batchId,int branchId,int academyId )throws Exception;
	

	/**
	 * 查询院校指标按批次和学习中心
	 * @param batchId
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollQuota> findAcademyEnrollQuotaByBatchIdAndBranchId(int batchId,int branchId)throws Exception;


	/**
	 * 查询院校指标按批次和学习中心(列表)
	 * @param batchId
	 * @param branchId
	 * @param pr
	 * @return
	 */
	public List<AcademyEnrollQuota> findAcademyEnrollQuotaByBatchIdAndBranchIdPage(int batchId,int branchId,PageResult<AcademyEnrollQuota> pr)throws Exception;

	
	/**
	 * 查询院校指标按批次Id学习中心Id院校ID
	 * @param batchId
	 * @param branchId
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public AcademyEnrollQuota findAcademyEnrollQuotaByBatchIdAndBranchIdAndAcademyId(int batchId,int branchId,int academyId)throws Exception;
	
	/**
	 * 根据批次查询院校招生指标
	 * @param batchId
	 * @return Map(key:学习中心ID_院校ID value:指标)
	 * @throws Exception
	 */
	public Map<String,Integer> getTargetByBatch(int batchId) throws Exception;
	
	
	
	
	
	
	
	
}
