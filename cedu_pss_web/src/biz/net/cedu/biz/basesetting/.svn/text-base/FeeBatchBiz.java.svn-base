package net.cedu.biz.basesetting;

import java.util.List;

import net.cedu.entity.basesetting.FeeBatch;

public interface FeeBatchBiz {
	
	/**
	 * 根据ID查找缴费次数
	 * @param id
	 * @return
	 */
	public FeeBatch findFeeBatchById(int id) throws Exception;
	
	/**
	 * 添加缴费次数
	 * @param feeBatch
	 */
	public boolean addFeeBatch(FeeBatch feeBatch) throws Exception;
	
	/**
	 * 删除缴费次数(读取配置文件)
	 * @param 
	 */
	public boolean deleteConfigFeeBatchById(int id) throws Exception;
	
//	/**
//	 * 删除缴费次数(物理删除)
//	 * @param 
//	 */
//	public boolean deleteFeeBatchById(int id) throws Exception;
//	
//	/**
//	 * 删除缴费次数(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteFeeBatchByFlag(int id) throws Exception;
	
	/**
	 * 修改缴费次数
	 * @param feeBatch
	 */
	public boolean modifyFeeBatch(FeeBatch feeBatch) throws Exception;
	
	/**
	 * 按多个条件查询缴费次数   非默认的
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public List<FeeBatch> getByProperty(int status,int academyId,int batchId) throws Exception;
	
	/**
	 * 根据状态查询默认的缴费次数
	 * @param status
	 * @return
	 */
	public List<FeeBatch> getBystatus(int status) throws Exception;

	//查询全部缴费次数
	public List<FeeBatch> findAllFeeBatch() throws Exception;
	
	//查询全部缴费次数(不包括逻辑删除的数据)
	public List<FeeBatch> findAllFeeBatchByFlag() throws Exception;
}
