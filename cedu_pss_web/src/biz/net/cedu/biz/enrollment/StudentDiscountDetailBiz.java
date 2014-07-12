package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.StudentDiscountDetail;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
import net.cedu.model.page.PageResult;


/**
 * 学生优惠政策
 * 
 * @author lixiaojun
 * 
 */
public interface StudentDiscountDetailBiz 
{
	
	/**
	 * 根据Id查找学生优惠政策
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentDiscountDetail findStudentDiscountDetailById(int id)throws Exception;
	
	/**
	 * 根据Id查找学生优惠政策详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentDiscountDetail findStudentDiscountDetailDetailsById(int id)throws Exception;
	
	/**
	 * 添加学生优惠政策
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addStudentDiscountDetail(StudentDiscountDetail studentDiscountDetail) throws Exception;
	
	/**
	 * 批量添加学生优惠政策
	 * @param studentDiscountDetailList
	 * @return
	 * @throws Exception
	 */
	public boolean addBatchStudentDiscountDetail(List<StudentDiscountDetail> studentDiscountDetailList) throws Exception;
	
	/**
	 * 删除学生优惠政策(读取配置文件)
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountDetailBiz#deleteStudentDiscountDetailById(int)
	 */
	public boolean deleteConfigStudentDiscountDetailById(int id) throws Exception; 
	
//	/**
//	 * 删除学生优惠政策(物理删除)
//	 * @param 
//	 */
//	public boolean deleteStudentDiscountDetailById(int id) throws Exception;
//	
//	/**
//	 * 删除学生优惠政策(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteStudentDiscountDetailFlag(int id) throws Exception;
	
	/**
	 * 修改学生优惠政策
	 * @param feeBatch
	 */
	public boolean modifyStudentDiscountDetail(StudentDiscountDetail studentDiscountDetail) throws Exception;
	
	/**
	 * 批量修改学生优惠政策
	 * @param studentDiscountDetailList
	 * @return
	 * @throws Exception
	 */
	public boolean updateBatchStudentDiscountDetail(List<StudentDiscountDetail> studentDiscountDetailList) throws Exception;
	
	/**
	 * 获取学生优惠政策数量
	 * @param type
	 * @param pr
	 * @return
	 */
	public int findStudentDiscountDetailCountByDetails(StudentDiscountDetail studentDiscountDetail,PageResult<StudentDiscountDetail> pr) throws Exception;
	
	/**
	 * 获取学生优惠政策列表
	 * @param type
	 * @param pr
	 * @return
	 */
	public List<StudentDiscountDetail> findStudentDiscountDetailListByDetails(StudentDiscountDetail studentDiscountDetail,PageResult<StudentDiscountDetail> pr) throws Exception;

	/**
	 * 按多个条件查询收费政策   
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public List<StudentDiscountDetail> getByProperty(int channelType,int channelId,int feeCountId,int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId) throws Exception;
	
	/**
	 * 按多个条件查询收费政策   (报名后政策)
	 * @param studentDataSource 招生来源Id
	 * @param enrollmentWay 市场途径Id
	 * @param channelType 招生途径Id
	 * @param channelId 合作方Id
	 * @param feeCountId 缴费次数
	 * @param academyId 院校Id
	 * @param batchId 批次Id
	 * @param branchId 学习中心Id
	 * @param levelId 层次Id
	 * @param majorId 专业Id
	 * @param feesubjectId 费用科目Id
	 * @return
	 * @throws Exception
	 */
	public List<StudentDiscountDetail> getByProperty(int studentDataSource,int enrollmentWay,int channelType,int channelId,int feeCountId,int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId) throws Exception;
		
	
	/**
	 * 按多个条件查询收费政策   *报名前政策)
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public List<StudentDiscountDetail> getBeforeDiscountByProperty(int feeCountId,int academyId,int branchId,int feesubjectId) throws Exception;
	
	/**
	 * 批量修改院校收费政策
	 * @param discoutDetailIds
	 * @param discountPolicyIds
	 * @return
	 * @throws Exception
	 */
	public boolean updateBatchStudentDiscountDetail(String discoutDetailIds,String discountPolicyIds) throws Exception;
	
	/**
	 * 通过学生信息获取该学生优惠政策标准集合
	 * @param Student
	 * @return
	 * @throws Exception
	 */
	public List<StudentDiscountPolicy> findStudentDiscountPolicyListByStudent(Student Student) throws Exception;
}
