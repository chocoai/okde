package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.AcademyBranchFeeWay;

/**
 * 院校中心缴费方式关系
 * 
 * @author lixiaojun
 * 
 */
public interface AcademyBranchFeeWayBiz 
{
	
	/**
	 * 根据id查找院校中心缴费方式关系
	 * @param id
	 * @return
	 */
	public AcademyBranchFeeWay findAcademyBranchFeeWayById(int id) throws Exception;
	
	/**
	 * 添加院校中心缴费方式关系
	 * @param academyBranchFeeWay
	 */
	public boolean addAcademyBranchFeeWay(AcademyBranchFeeWay academyBranchFeeWay) throws Exception;
	
	/**
	 * 删除院校中心缴费方式关系(物理删除)
	 * @param 
	 */
	public boolean deleteAcademyBranchFeeWayById(int id) throws Exception;

	
	/**
	 * 修改院校中心缴费方式关系
	 * @param academyBranchFeeWay
	 */
	public boolean modifyAcademyBranchFeeWay(AcademyBranchFeeWay academyBranchFeeWay) throws Exception;
	
	/**
	 * 按多个条件查询院校中心缴费方式关系 
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public List<AcademyBranchFeeWay> getByProperty(int academyId,int branchId) throws Exception;
	
	/**
	 * 根据院校批次ID＆学习中心ID查找学习中心的缴费方式
	 * 
	 * @param batchId
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBranchFeeWay> findByBatchAndBranch(int batchId, int branchId) throws Exception;
	
	/**
	 * 根据院校批次ID＆学习中心ID删除学习中心的缴费方式
	 * 
	 * @param batchId
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public int deleteByBatchAndBranch(int batchId, int branchId) throws Exception;
}
