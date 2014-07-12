package net.cedu.dao.crm;

import java.util.Map;

import net.cedu.dao.BaseDao;
import net.cedu.entity.crm.Student;

/**
 * 学生详情
 * 
 * @author yangdongdong
 * 
 */
public interface StudentDao extends BaseDao<Student> {
	/**
	 * 
	 *
	 * @功能：修复学生专业
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-9 下午06:00:45
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @throws Exception 
	 */
	public void repairProfessionalStudents() throws Exception;
	
	/**
	 * 
	 * @功能：修复学生性别
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-11 上午10:54:33
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @throws Exception
	 */
	public void repairStudentsSex() throws Exception;
	
	/**
	 * 
	 * @功能：修复学生跟进人
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-11 上午10:54:33
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @throws Exception
	 */
	public void repairStudentsUserId() throws Exception;
	
	
	/**
	 * 
	 * @功能：查询完成指标
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-30 下午06:18:33
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @return 返回  key:学习中心ID_全局批次ID_院校ID value:完成指标
	 * @throws Exception
	 */
	public Map<String,Integer> getCompleteCountAll()throws Exception;
	
	/**
	 * 
	 * @功能：(重载)根据全局批次查询完成指标
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-04-28 下午09:45:07
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @return 返回  key:学习中心ID_院校ID value:完成指标
	 * @throws Exception
	 */
	public Map<String,Integer> getCompleteCountAll(int batchId)throws Exception;
	
	/**
	 * 获取重复学生Ids
	 * @return
	 * @throws Exception
	 */
	public String repeatStudentIds(int isEmphasisVerification,Student student,String gbatchIds,String glevelIds,String glmajors)throws Exception;
	
	/**
	 * 根据全局和中心批次查询完成指标（中心为0则全部中心）
	 * @param globalEnrollBatchId,branchId
	 * @return Map(key:branchId_academyId_userId value:完成指标数)
	 * @throws Exception
	 */
	public Map<String,Integer> getCompleteCountAllByGlobalEnrollBatchIdAndBranchId(int globalEnrollBatchId,int branchId) throws Exception;
	
	/**
	 * 根据全局批次查询完成指标（全局批次为0则返回空）
	 * @param globalEnrollBatchId
	 * @return Map(key:branchId value:完成指标数)
	 * @throws Exception
	 */
	public Map<String,Integer> getCompleteCountAllByGlobalEnrollBatchId(int globalEnrollBatchId) throws Exception;
	
	/**
	 * 根据全局批次ids查询学生报名数
	 * @param globalEnrollBatchIds
	 * @return key:全局批次id value:学生数
	 * @throws Exception
	 */
	public Map<String,Integer> getBaoMingCountByGlobalEnrollBatchIds(String globalEnrollBatchIds) throws Exception;
	
	/**
	 * 根据全局批次ids查询学生缴费数(学费)
	 * @param globalEnrollBatchIds
	 * @return key:全局批次id value:学生数
	 * @throws Exception
	 */
	public Map<String,Integer> getJiaoFeiCountByGlobalEnrollBatchIds(String globalEnrollBatchIds) throws Exception;
	
	/**
	 * 根据全局批次ids查询学生录取数
	 * @param globalEnrollBatchIds
	 * @return key:全局批次id value:学生数
	 * @throws Exception
	 */
	public Map<String,Integer> getLuQuCountByGlobalEnrollBatchIds(String globalEnrollBatchIds) throws Exception;
	
	/**
	 * (重写)save方法 增加创建日期验证
	 * @param student
	 * @return
	 */
	public Object save(Student student);
}
