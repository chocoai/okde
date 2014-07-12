package net.cedu.biz.basesetting;

import java.io.Serializable;
import java.util.List;

import net.cedu.entity.basesetting.GlobalEnrollBatch;

/**
 * 全局招生批次设置
 * @author HXJ
 *
 */
public interface GlobalEnrollBatchBiz {

	/**
	 * 增加全局招生批次设置
	 * @param globalEnrollBatch
	 * @return
	 * @throws Exception
	 */
	public boolean addGlobalEnrollBatch(GlobalEnrollBatch globalEnrollBatch) throws Exception; 
	
	/**
	 * 修改全局招生批次设置
	 * @param globalEnrollBatch
	 * @return
	 */
	public boolean modifyGlobalEnrollBatch(GlobalEnrollBatch globalEnrollBatch) throws Exception;
	
//	//按主键删除全局招生批次设置(物理删除)
//	public GlobalEnrollBatch deleteGlobalEnrollBatchById(Serializable id);
//	
//	//按主键删除全局招生批次设置(逻辑删除)
//	public int deleteGlobalEnrollBatchByFlag(int id);
	
	/**
	 * 查询所有全局招生批次设置
	 * @return
	 * @throws Exception
	 */
	public List<GlobalEnrollBatch> findAllGlobalEnrollBatchs() throws Exception;
	
	/**
	 * 查询所有全局招生批次设置(delete_flag=0)
	 * @return
	 * @throws Exception
	 */
	public List<GlobalEnrollBatch> findAllGlobalEnrollBatchByDeleteFlag() throws Exception;
	
	/**
	 * 按主键id查询全局招生批次设置
	 * @param id
	 * @return
	 */
	public GlobalEnrollBatch findGlobalEnrollBatchById(Serializable id);
	
	/**
	 * 查询未设置对应院校招生批次的全局招生批次
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	public List<GlobalEnrollBatch> findOtherGlobalEnrollBatchs(Object...objects) throws Exception;
	
	/**
	 * 删除(读取配置文件)
	 * @param id
	 * @return
	 */
	public GlobalEnrollBatch deleteConfigGlobalEnrollBatch(int id);
	
	/**
	 * 根据全局招生批次所属年份查询  全局招生批次列表
	 * @return
	 * @throws Exception
	 */
	public List<GlobalEnrollBatch> findGlobalEnrollBatchByYear(int belongyear) throws Exception;
	
	/**
	 * 根据全局批次Id(多)查询全局批次
	 * @param ids  全局批次Ids
	 * @return
	 * @throws Exception
	 */
	public List<GlobalEnrollBatch> findGlobalEnrollBatchByIds(String ids) throws Exception;
	
	
	
	/**
	 * 根据全局招生批次id查询其对应的全部院校招生批次id数组
	 * @return
	 * @throws Exception
	 */
	public int[] findAcademyBatchIdsbyGlobalBatchId(int batchid)throws Exception;
	
	/**
	 * 根据当前全局批次id获取上一个全局批次id
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public int findLastGlobalEnrollBatchIdByGlobalBatchId(int batchId) throws Exception;
	
	/**
	 * 查询所有全局招生批次所在的年份(delete_flag=0)
	 * @return
	 * @throws Exception
	 */
	public List<GlobalEnrollBatch> findAllGlobalEnrollBatchYearsByDeleteFlag() throws Exception;
	
}
