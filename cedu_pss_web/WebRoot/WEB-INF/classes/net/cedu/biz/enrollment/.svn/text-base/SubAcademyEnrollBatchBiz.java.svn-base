package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.SubAcademyEnrollBatch;

public interface SubAcademyEnrollBatchBiz {

	/**
	 * 根据院校招生批次ID，查询其对应的全部子批次
	 * @param academyEnrollBatchId
	 * @return
	 * @throws Exception
	 */
	public List<SubAcademyEnrollBatch> findSubAcademyEnrollBatchByAcademyEnrollBatchId(int academyEnrollBatchId) throws Exception;
	
	/**
	 * 添加
	 * @param subAcademyEnrollBatch
	 * @return
	 */
	public boolean addSubAcademyEnrollBatch(SubAcademyEnrollBatch subAcademyEnrollBatch) throws Exception;
	
	/**
	 * 修改
	 * @param subAcademyEnrollBatch
	 * @return
	 */
	public boolean modifySubAcademyEnrollBatch(SubAcademyEnrollBatch subAcademyEnrollBatch) throws Exception;
	
	/**
	 * 根据主键ID查询院校找绳子批次
	 * @param id
	 * @return
	 */
	public SubAcademyEnrollBatch findSubAcademyEnrollBatchById(int id);
	
	/**
	 * 查询所有院校招生批次设置(delete_flag=0)
	 * @return
	 * @throws Exception
	 */
	public List<SubAcademyEnrollBatch> findAllSubAcademyEnrollBatchByDeleteFlag() throws Exception;
	
	/**
	 * 根据院校招生批次ID 查询当前子批次（isCurrent=1）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<SubAcademyEnrollBatch> findByAcademyBatchIdAndCurrentStatus(int id) throws Exception;
}
