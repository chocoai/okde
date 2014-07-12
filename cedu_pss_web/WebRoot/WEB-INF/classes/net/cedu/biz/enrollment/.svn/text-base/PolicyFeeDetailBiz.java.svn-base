package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.entity.enrollment.PolicyFeeDetail;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

/**
 * 收费政策明细
 * 
 * @author lixiaojun
 * 
 */
public interface PolicyFeeDetailBiz 
{
	
	/**
	 * 根据Id查找收费政策明细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyFeeDetail findPolicyFeeDetailById(int id)throws Exception;
	/**
	 * 根据Id查找收费政策详细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyFeeDetail findPolicyFeeDetailDetailsById(int id)throws Exception;
	
	/**
	 * 添加收费政策明细
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addPolicyFeeDetail(PolicyFeeDetail policyFeeDetail) throws Exception;
	
	/**
	 * 批量添加收费政策明细
	 * @param policyFeeDetailList
	 * @return
	 * @throws Exception
	 */
	public boolean addBatchPolicyFeeDetail(List<PolicyFeeDetail> policyFeeDetailList) throws Exception;
	
	/**
	 * 删除收费政策(读取配置文件)
	 * @param 
	 */
	public boolean deleteConfigPolicyFeeDetailById(int id) throws Exception;
	
	/**
	 * 删除收费政策明细(物理删除)
	 * @param 
	 */
	public boolean deletePolicyFeeDetailById(int id) throws Exception;
//	
//	/**
//	 * 删除收费政策明细(逻辑删除)
//	 * @param 
//	 */
//	public boolean deletePolicyFeeDetailFlag(int id) throws Exception;
	
	/**
	 * 修改收费政策明细
	 * @param feeBatch
	 */
	public boolean modifyPolicyFeeDetail(PolicyFeeDetail policyFeeDetail) throws Exception;
	
	/**
	 * 批量 修改收费政策明细
	 * @param policyFeeDetailList
	 * @return
	 * @throws Exception
	 */
	public boolean updateBatchPolicyFeeDetail(List<PolicyFeeDetail> policyFeeDetailList) throws Exception;
	
	/**
	 * 获取收费政明细数量
	 * @param type
	 * @param pr
	 * @return
	 */
	public int findPolicyFeeDetailCountByDetails(int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId,int aduitStatus,PageResult<PolicyFeeDetail> pr) throws Exception;
	
	/**
	 * 获取收费政策明细列表
	 * @param type
	 * @param pr
	 * @return
	 */
	public List<PolicyFeeDetail> findPolicyFeeDetailListByDetails(int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId,int aduitStatus,PageResult<PolicyFeeDetail> pr) throws Exception;

	/**
	 * 按多个条件查询收费政策   
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public List<PolicyFeeDetail> getByProperty(int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId) throws Exception;
	
	/**
	 * 批量审批收费政策
	 * @param feeBatch
	 */
	public void updateBatchPolicyFeeDetail(String policyFeeDetailIds,int aduitStatus,int userId) throws Exception;
	
	/**
	 * 根据学生信息和缴费科目Id匹配收费标准
	 * @param student
	 * @param feeSubjectId
	 * @return
	 * @throws Exception
	 */
	public List<PolicyFee> findPolicyFeeByStudent(Student student,int feeSubjectId) throws Exception;
	
	/**
	 * 根据收费标准Id和审批状态查询政策数量
	 * @param policyFeeId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public int findPolicyFeeDetailCountByPolicyFeeIdAuditStatus(int policyFeeId,int auditStatus)throws Exception;
}
