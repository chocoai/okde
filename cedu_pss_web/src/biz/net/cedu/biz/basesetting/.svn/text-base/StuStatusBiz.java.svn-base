package net.cedu.biz.basesetting;

import java.util.List;

import net.cedu.entity.basesetting.StudentStatus;

/**
 * 学生状态 HXJ
 */
public interface StuStatusBiz {

	/**
	 * 查询所有学生状态列
	 * @return
	 * @throws Exception
	 */
	public List<StudentStatus> findAllStudentStatus() throws Exception;
	
	/**
	 * 查询所有学生状态列(delete_flag=0)
	 * @return
	 * @throws Exception
	 */
	public List<StudentStatus> findAllStudentStatusByDeleteFlag() throws Exception;
	
	/**
	 * 按阶段查询学生状态
	 * @param stage
	 * @return
	 * @throws Exception
	 */
	public List<StudentStatus> findStatusNamesByStage(String stage) throws Exception;
	
	/**
	 * 按阶段查询学生状态
	 * @param stageCode 状态阶段Code
	 * @return
	 * @throws Exception
	 */
	public List<StudentStatus> findStatusNamesByStageCode(String stageCode) throws Exception;
	
	
	/**
	 * 查询学生阶段
	 * @return
	 * @throws Exception
	 */
	public List<StudentStatus> findStatusStage() throws Exception;

	
	/**
	 * 根据Id查询学生状态
	 * @param id 学生状态Id
	 * @return
	 * @throws Exception
	 */
	public StudentStatus findStudentStatusById(int id) throws Exception;
	
	
	/**
	 * 查询两个id之间的学生状态
	 * @param startId 开始id
	 * @param endId	结束Id
	 * @return
	 * @throws Exception
	 */
	public List<StudentStatus> findStatusNamesByStartIdAndEndId(int startId,int endId) throws Exception; 
	
	
}
