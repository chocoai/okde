package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.AcademyBatchBranch;

/**
 * 院校授权学习中心  业务逻辑接口
 * 
 * @author Sauntor
 *
 */
public interface AcademyBatchBranchBiz {
	
	
	/**
	 * 按所授权中心查询所有院校
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<Academy> findAcademyByBranchId(int branchId)throws Exception;
	
	
	/**
	 * 查询院校在指定批次<b>已授权</b>中心
	 * @param academyId 院校ID
	 * @param batchId 批次ID
	 * @return
	 * @throws Exception 
	 */
	public List<Branch> findGrantedBranch(int academyId, int batchId) throws Exception;

	/**
	 * 查询院校在指定批次<b>未授权</b>中心
	 * @param academyId 院校ID
	 * @param batchId 批次ID
	 * @return
	 * @throws Exception 
	 */
	public List<Branch> findUngrantedBranch(int academyId, int batchId) throws Exception;
	
	/**
	 * 添加院校授权学习中心
	 * 
	 * @param entity
	 * @return 新添加授权中心ID
	 * @throws Exception
	 */
	public int addAcademyBatchBranch(AcademyBatchBranch entity) throws Exception;
	
	/**
	 * 清除院校在指定招生批次的授权学习中心
	 * 
	 * @param academyId
	 * @param batchId
	 * @return 返回原所有已授权中心总数
	 * @throws Exception
	 */
	public int deleteByAcademyAndEnrollBatch(int academyId, int batchId) throws Exception;
	
	/**
	 * 按所授权中心查询所有全局批次
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<GlobalEnrollBatch> findGlobalEnrollBatchByBranchId(int branchId)throws Exception;
	
	/**
	 * 按所授权中心和全局批次查询院校
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<Academy> findAcademyByBranchIdAndGlobalBatchId(int branchId,int globalBatchId)throws Exception;
	
	/**
	 * 根据院校招生批次ID查询已授权的学习中心
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findBranchByBatchId(int batchId) throws Exception;
	
	/**
	 * 批量添加某批次授权中心(并清除元授权中心)
	 * @param batchId
	 * @param branchIds
	 * @throws Exception
	 */
	public void update(int batchId, int[] branchIds) throws Exception;
}
