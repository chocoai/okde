package net.cedu.biz.basesetting.impl;

import java.util.List;

import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.FeeBatchDao;
import net.cedu.entity.basesetting.FeeBatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeBatchBizImpl implements FeeBatchBiz{
	
	@Autowired
	private FeeBatchDao feeBatchDao;
	
	/**
	 * 根据ID查找缴费次数
	 * @param id
	 * @return
	 */
	public FeeBatch findFeeBatchById(int id) throws Exception
	{
		return this.feeBatchDao.findById(id);
	}
	
	/**
	 * 添加缴费次数
	 * @param feeBatch
	 */
	public boolean addFeeBatch(FeeBatch feeBatch) throws Exception
	{
		if (feeBatch != null) 
		{
			Object object = feeBatchDao.save(feeBatch);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 删除缴费次数(读取配置文件)
	 * @param 
	 */
	public boolean deleteConfigFeeBatchById(int id) throws Exception
	{
		if (id != 0) 
		{
			Object object = feeBatchDao.deleteConfig(id);
			if (object != null)
			{
				return true;
			}
		}
	
		return false;
	}
	
//	/**
//	 * 删除缴费次数(物理删除)
//	 * @param 
//	 */
//	public boolean deleteFeeBatchById(int id) throws Exception
//	{
//		if (id != 0) 
//		{
//			Object object = feeBatchDao.deleteById(id);
//			if (object != null)
//			{
//				return true;
//			}
//		}
//	
//		return false;
//	}
//	
//	/**
//	 * 删除缴费次数(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteFeeBatchByFlag(int id) throws Exception
//	{
//		if (id != 0) 
//		{
//			int num = feeBatchDao.deleteByFlag(id);
//			if (num>0) 
//			{
//				return true;
//			}
//		}
//		
//		return false;
//	}
//	
	
	/**
	 * 修改缴费次数
	 * @param feeBatch
	 */
	public boolean modifyFeeBatch(FeeBatch feeBatch) throws Exception
	{
		if (feeBatch != null)
		{
			Object object = feeBatchDao.update(feeBatch);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	/**
	 * 按多个条件查询缴费次数   非默认的
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public List<FeeBatch> getByProperty(int status,int academyId,int batchId) throws Exception
	{
		String con=" and status="+Constants.PLACEHOLDER+" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		return this.feeBatchDao.getByProperty(con, new Object[]{status,academyId,batchId,0});
	}
	/**
	 * 根据状态查询默认的缴费次数
	 * @param status
	 * @return
	 */
	public List<FeeBatch> getBystatus(int status) throws Exception
	{
		String con=" and status="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		return this.feeBatchDao.getByProperty(con, new Object[]{status,0});
	}

	//查询全部缴费次数
	public List<FeeBatch> findAllFeeBatch() throws Exception {
	
		return feeBatchDao.findAll();
	}

	//查询全部缴费次数(不包括逻辑删除的数据)
	public List<FeeBatch> findAllFeeBatchByFlag() throws Exception {
		
		return feeBatchDao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
	}
	
	

}
