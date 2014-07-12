package net.cedu.biz.enrollment.impl;


import java.util.List;

import net.cedu.biz.enrollment.AcademyBranchFeeWayBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyBranchFeeWayDao;
import net.cedu.entity.enrollment.AcademyBranchFeeWay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校中心缴费方式关系
 * 
 * @author lixiaojun
 * 
 */
@Service
public class AcademyBranchFeeWayBizImpl implements AcademyBranchFeeWayBiz
{
	@Autowired
	private AcademyBranchFeeWayDao academyBranchFeeWayDao;//院校中心缴费方式关系dao
	
	/**
	 * 根据id查找院校中心缴费方式关系
	 * @param id
	 * @return
	 */
	public AcademyBranchFeeWay findAcademyBranchFeeWayById(int id) throws Exception
	{
		return this.academyBranchFeeWayDao.findById(id);
	}
	
	/**
	 * 添加院校中心缴费方式关系
	 * @param academyBranchFeeWay
	 */
	public boolean addAcademyBranchFeeWay(AcademyBranchFeeWay academyBranchFeeWay)throws Exception 
	{

		if (academyBranchFeeWay != null)
		{
			Object object = academyBranchFeeWayDao.save(academyBranchFeeWay);
			if (object != null)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 删除院校中心缴费方式关系(物理删除)
	 * @param 
	 */
	public boolean deleteAcademyBranchFeeWayById(int id) throws Exception 
	{
		if (id != 0) 
		{
			Object object = academyBranchFeeWayDao.deleteById(id);
			if (object != null)
			{
				return true;
			}
		}
		
		return false;
	}

	
	/**
	 * 修改院校中心缴费方式关系
	 * @param academyBranchFeeWay
	 */
	public boolean modifyAcademyBranchFeeWay(AcademyBranchFeeWay academyBranchFeeWay) throws Exception 
	{
		if (academyBranchFeeWay != null) 
		{
			Object object = academyBranchFeeWayDao.update(academyBranchFeeWay);
			if (object != null)
			{
				return true;
			}
		}
		
		return false;
	}
	/**
	 * 查询该院校下某个授权中心的缴费方式关系
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public List<AcademyBranchFeeWay> getByProperty(int academyId,int branchId) throws Exception
	{
		String con=" and academyId="+Constants.PLACEHOLDER+" and branchId="+Constants.PLACEHOLDER;
		return this.academyBranchFeeWayDao.getByProperty(con, new Object[]{academyId,branchId});
	}
	
	/**
	 * 根据批次ID＆学习中心ID查找学习中心的缴费方式
	 * 
	 * @param batchId
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBranchFeeWay> findByBatchAndBranch(int batchId, int branchId) throws Exception
	{
		return academyBranchFeeWayDao.getByProperty(" and batchId="+Constants.PLACEHOLDER+" and branchId="+Constants.PLACEHOLDER, batchId, branchId);
	}
	
	/**
	 * 根据院校批次ID＆学习中心ID删除学习中心的缴费方式
	 * 
	 * @param batchId
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public int deleteByBatchAndBranch(int batchId, int branchId) throws Exception
	{
		int rowCount = 0;
		List<AcademyBranchFeeWay> list = academyBranchFeeWayDao.getByProperty(" and batchId="+Constants.PLACEHOLDER+" and branchId="+Constants.PLACEHOLDER, batchId, branchId);
		
		if(list == null) return 0;
		for(AcademyBranchFeeWay abfw : list)
		{
			academyBranchFeeWayDao.deleteById(abfw.getId());
			rowCount++ ;
		}
		
		return rowCount;
	}
}
