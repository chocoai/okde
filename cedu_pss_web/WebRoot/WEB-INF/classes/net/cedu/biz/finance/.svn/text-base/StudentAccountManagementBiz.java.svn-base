package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.Receipt;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageResult;



/**
 * 学生账户管理  业务逻辑接口
 * 
 * @author gaole
 *
 */
public interface StudentAccountManagementBiz {
	
	
	/**
	 * 新增学生账户
	 * @param studentaccountmanagement
	 * @return
	 * @throws Exception
	 */
	public boolean addStudentAccountManagement(StudentAccountManagement studentaccountmanagement)throws Exception;
	
	/**
	 * 根据账户Id查询学生账户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentAccountManagement findStudentAccountManagementById(int id)throws Exception;
	
	/**
	 * 修改学生账户
	 * @param studentaccountmanagement
	 * @return
	 * @throws Exception
	 */
	public boolean updateStudentAccountManagementById(StudentAccountManagement studentaccountmanagement)throws Exception;
	
	/**
	 * 删除学生账户
	 * @param studentaccountmanagement
	 * @return
	 * @throws Exception
	 */
	public boolean deleteStudentAccountManagementById(int id)throws Exception;
	
	/**
	 * 查询学生账户(数量)
	 * @param code 学生账号
	 * @param studentName 学生姓名
	 * @param cardNo 身份证
	 * @param pr 
	 * @return
	 * @throws Exception
	 */
	public int countStudentAccountManagementByParams(String code,String studentName,String cardNo,PageResult<StudentAccountManagement> pr)throws Exception;
	
	/**
	 * 查询学生账户(集合)
	 * @param code 学生账号
	 * @param studentName 学生姓名
	 * @param cardNo 身份证
	 * @param pr 
	 * @return
	 * @throws Exception
	 */
	public List<StudentAccountManagement> findStudentAccountManagementByParams(String code,String studentName,String cardNo,PageResult<StudentAccountManagement> pr)throws Exception;
	
	/**
	 * 批量创建学生账户
	 * @return
	 * @throws Exception
	 */
	public int addStudentAccounts(int userId)throws Exception; 
	
	
	/**
	 * 查询学生账户按学生Id
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public StudentAccountManagement findStudentAccountManagementByStudentId(int studentId)throws Exception; 
	
	
	
	/**
	 * 查询学生账户按学生Id 如果没有结果则创建新账户
	 * @param studentId 学生Id
	 * @param userId 创建者
	 * @return
	 * @throws Exception
	 */
	public StudentAccountManagement updateStudentAccountManagementByStudentIdForFee(int studentId,int userId)throws Exception; 
	
	
	
	
	
	
	
	
	
	
	
	
}
