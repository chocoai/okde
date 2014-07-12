package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.AcademyLevel;

public interface AcademyLevelBiz {
	/**
	 * 查询所有层次实体
	 * @return
	 * @throws Exception
	 */
	public List<AcademyLevel> findAll() throws Exception;
	
	/**
	 * 根据批次ID查询层次
	 * @param batchId
	 * 
	 * @return
	 */
	public List<AcademyLevel> findBatchId(int batchId) throws Exception;
	
	/**
	 * 根据批次ID查询所有层次_定时任务
	 * @param batchId
	 * 
	 * @return
	 */
	public List<AcademyLevel> findBatchIdForTask(List<Integer> idList) throws Exception;
	
	/**
	 * 根据批次ID查询层次(查询具体名称)
	 * @param batchId
	 * 
	 * @return
	 */
	public List<AcademyLevel> findAcademyLevelNameBatchId(int batchId) throws Exception;
	
	/**
	 * 根据Id查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyLevel findById(int id) throws Exception;
	
	/**
	 * 批量添加 院校层次
	 * @param academyLevel 院校层次实体
	 * @param levelids  levelid的数组
	 * @return
	 */
	public boolean addAcademyLevels(int[]levelids,AcademyLevel academyLevel);
	
	/**
	 * 通过院校招生批次ID 批量删除院校层次
	 * @param batchid
	 * @return
	 */
	public int deleteAcademyLevelByBatchId(int batchid);
	
	/**
	 * 根据招生批次和基础层次ID查询院校层次(批量)
	 * @return
	 * @throws Exception
	 */
	public List<AcademyLevel> findByBatchIdAndLevelid(int[] ids) throws Exception;
	
	/**
	 * 按id批量删除院校层次
	 * @return
	 */
	public int deleteByIds(String ids);
	
	/**
	 * 批量复制其他招生批次的院校层次和专业
	 * @throws Exception
	 */
	public void addAcademyLevelAndMajorfromOtherEnrollmentBatch(int userid,int oldbatchid,int newbatchid,int[] levelIdsArray) throws Exception;
	
	/**
	 * 找出某批次下层次（基础）ID为<code> levelId </code>的院校层次
	 * @param batchId 招生批次ID
	 * @param levelId 基础层次ID
	 * @return
	 * @throws Exception
	 */
	public AcademyLevel findByBatchAndLevel(int batchId, int levelId) throws Exception;

	
	/**
	 * 根据基础层次查询
	 * @param gllevelId 基础层次Id
	 * @return
	 * @throws Exception
	 */
	public List<AcademyLevel> findByGllevel(int gllevelId) throws Exception;
	
	
	
	
	
	
}
