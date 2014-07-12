package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
import net.cedu.model.page.PageResult;

/**
 * 学生优惠政策标准
 * 
 * @author lixiaojun
 * 
 */
public interface StudentDiscountPolicyBiz 
{
	
	/**
	 * 根据Id查找学生优惠政策标准
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentDiscountPolicy findStudentDiscountPolicyById(int id)throws Exception;
	
	/**
	 * 根据Id查找学生优惠政策标准详细信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentDiscountPolicy findStudentDiscountPolicyDetailsById(int id)throws Exception;
	
	/**
	 * 添加学生优惠政策标准
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addStudentDiscountPolicy(StudentDiscountPolicy studentDiscountPolicy) throws Exception;
	
	/*
	 * 删除学生优惠政策标准(读取配置文件)
	 * 
	 * @see net.cedu.biz.enrollment.StudentDiscountPolicyBiz#deleteStudentDiscountPolicyById(int)
	 */
	public boolean deleteConfigStudentDiscountPolicyById(int id) throws Exception;
	
//	/**
//	 * 删除学生优惠政策标准(物理删除)
//	 * @param 
//	 */
//	public boolean deleteStudentDiscountPolicyById(int id) throws Exception;
//	
//	/**
//	 * 删除学生优惠政策标准(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteStudentDiscountPolicyFlag(int id) throws Exception;
	
	/**
	 * 修改学生优惠政策标准
	 * @param feeBatch
	 */
	public boolean modifyStudentDiscountPolicy(StudentDiscountPolicy studentDiscountPolicy) throws Exception;
	
	/**
	 * 获取学生优惠政策标准数量
	 * @param type
	 * @param pr
	 * @return
	 */
	public int findStudentDiscountPolicyCountByDetails(StudentDiscountPolicy studentDiscountPolicy,PageResult<StudentDiscountPolicy> pr) throws Exception;
	
	/**
	 * 获取学生优惠政策标准列表
	 * @param type
	 * @param pr
	 * @return
	 */
	public List<StudentDiscountPolicy> findStudentDiscountPolicyListByDetails(StudentDiscountPolicy studentDiscountPolicy,PageResult<StudentDiscountPolicy> pr) throws Exception;
	
	
	/**
	 * 通过学生Id和是否需要申请  获取该学生优惠政策标准集合
	 * @param studentId  学生Id
	 * @param isApplicationNeeded  是否需要申请
	 * @return
	 * @throws Exception
	 */
	public List<StudentDiscountPolicy> findStudentDiscountPolicyListByStudentId(int studentId,int isApplicationNeeded) throws Exception;
	
	/**
	 * 通过学生Id和是否需要申请  获取只有中心跟院校其他条件无关的优惠政策标准集合
	 * @param studentId  学生Id
	 * @param isApplicationNeeded  是否需要申请
	 * @return
	 * @throws Exception
	 */
	public List<StudentDiscountPolicy> findStudentDiscountPolicyListByStudentIdAndBranch(int studentId,int isApplicationNeeded) throws Exception;

}
