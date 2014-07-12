package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.admin.Branch;
import net.cedu.entity.enrollment.BranchEnrollQuota;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.EnrollQuota;
import net.cedu.model.page.PageResult;

/**
 * 学习中心指标  业务逻辑接口
 * 
 * @author gaolei
 *
 */
public interface BranchEnrollQuotaBiz {
	

	/**
	 * 添加学习中心指标 
	 * @param branchenrollquota
	 * @return
	 * @throws Exception
	 */
	public boolean addBranchEnrollQuota(BranchEnrollQuota branchenrollquota)throws Exception;
	
	

	/**
	 * 查询学习中心指标 按Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BranchEnrollQuota findBranchEnrollQuotaById(int id)throws Exception;
	
	
	/**
	 * 查询学习中心指标 
	 * @return
	 * @throws Exception
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotaAll() throws Exception;
	
	/**
	 * 查询学习中心指标 
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotalist(int batchId,PageResult<BranchEnrollQuota> pr)throws Exception;
	
	
	/**
	 * 批量添加学习中心指标
	 * @param batchId
	 * @param branchIds
	 * @param branchTarget
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
	public boolean addBranchEnrollQuotas(int batchId,Object [] branchIds,Object [] branchTarget,int managerId)throws Exception;
	
	
	
	/**
	 * 批量修改学习中心招生指标
	 * @param branchIds 学习中心多个
	 * @param targets   学习中心指标多个
	 * @return
	 * @throws Exception
	 */
	public boolean updateBranchEnrollQuotas(String branchIds,String targets,int userId)throws Exception;
	
	
	
	/**
	 * 批量删除学习中心指标
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public  boolean deleteBranchEnrollQuotaBybatchId(int batchId )throws Exception;;
	
	
	/**
	 * 刷新并添加没有的学习中心指标
	 * @param batchId 批次Id
	 * @param userId 当前登录用户Id
	 * @return
	 * @throws Exception
	 */
	public int addRefreshBranchEnrollQuotaBybatchId(int batchId,int userId) throws Exception;

	
	/**
	 * 查询所有学习中心(分配指标)按批次
	 * @param batchId
	 * @param pr
	 * @return
	 */
	public List<Branch> findBranchByBtachId(int batchId,PageResult<Branch> pr)throws Exception ;
	
	
	
	/**
	 * 查询学习中心指标按批次和中心Id
	 * @param batchId
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public BranchEnrollQuota findBranchEnrollQuotaByBtachIdAndBranchId(int batchId,int branchId)throws Exception;
	
	
	/**
	 * 查询批次下的所有学习中心
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotaByBId(int batchId)throws Exception;
	
	
	
	/**
	 * 查询学习中心 按学习中心Id&(多),批次Id&(多)
	 * @param batchIds
	 * @param branchIds
	 * @return
	 * @throws Exception
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotaByBranchIds(String batchIds,String branchIds)throws Exception;
	
	
}
