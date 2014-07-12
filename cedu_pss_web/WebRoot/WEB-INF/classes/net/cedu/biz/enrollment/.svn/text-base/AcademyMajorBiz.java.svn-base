package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;

public interface AcademyMajorBiz {
	/**
	 * 根据Id查询专业
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyMajor findById(int id) throws Exception;
	
	/**
	 * 查询某层次下的所有专业
	 * 
	 * @param academyLevelId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyMajor> findByLevel(int academyLevelId) throws Exception;
	
	
	/**
	 * 查询某层次下的所有专业
	 * 
	 * @param academyLevelId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyMajor> findAcademyMajorByLevel(int academyLevelId) throws Exception;
	
	
	/**
	 * 查询某层次下的所有基础专业（返回的是基础专业集合）
	 * 
	 * @param levelId
	 * @return
	 * @throws Exception
	 */
	public List<Major> findMajorListByLevelId(int levelId) throws Exception;
	
	//查询某层次下的所有基础专业名称
	public String findMajorNameByLevelId(int levelId) throws Exception;
	
	//添加
	public Object addAcademyMajor(AcademyMajor academyMajor);

	/**
	 * 根据多个院校层次ID删除每个院校层次对应的院校专业
	 * @param id
	 * @return
	 */
	public int deleteAcademyMajorByAcademyLevelId(List<AcademyLevel> collegelist);
	
	/**
	 * 按照院校层次查询对应院校基础专业的ID集合
	 * @param academylevelid
	 * @return
	 * @throws Exception
	 */
	public int[] findAcademyMajorIdsByAcademyLevelId(int academylevelid) throws Exception;
	
	/**
	 * 根据院校层次id删除对应专业
	 * @param id
	 * @return
	 */
	public int deleteAcademyMajorByAcademyLevelId(int id);
	
	/**
	 * 批量添加院校专业
	 * @param academyMajor
	 * @param allmajorids
	 * @return
	 */
	public void addSeveralAcademyMajor(AcademyMajor academyMajor,int [] allmajorids)throws Exception;
	
	/**
	 * 查询院校某批次下对应基础层次ID为levelId的MajorLevel下的所有基础专业
	 * @param batchId 院校招生批次ID
	 * @param levelId 基础层次ID
	 * @return
	 * @throws Exception
	 */
	public List<AcademyMajor> findAcademyMajorByLevelId(int batchId, int levelId) throws Exception;
	
	/**
	 * 查询某层次下的所有基础专业（返回的是基础专业集合）定时任务
	 * 
	 * @param levelId
	 * @return
	 * @throws Exception
	 */
	public List<Major> findMajorListByLevelIdForTask(List<Integer> idList) throws Exception;
	
	
	/**
	 * 查询某层次下的所有专业_定时任务
	 * 
	 * @param levelId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyMajor> findByLevelForTask(List<Integer> idList) throws Exception;
}
