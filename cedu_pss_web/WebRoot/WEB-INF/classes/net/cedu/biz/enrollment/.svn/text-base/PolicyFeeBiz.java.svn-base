package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.common.Constants;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

/**
 * 院校收费政策标准
 * 
 * @author lixiaojun
 * 
 */

public interface PolicyFeeBiz 
{
	
	/**
	 * 根据Id查找院校收费政策标准
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyFee findPolicyFeeById(int id)throws Exception;
	
	/**
	 * 添加院校收费政策标准
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addPolicyFee(PolicyFee policyFee) throws Exception;
	
	/**
	 * 添加院校收费政策标准及其明细列表
	 * @param policyFee
	 * @param feeStandardList
	 * @return
	 * @throws Exception
	 */
	public boolean addPolicyFeeFeeStandardList(PolicyFee policyFee,List<FeeStandard> feeStandardList) throws Exception;
	
	/**
	 * 删除院校收费政策标准(读取配置文件)
	 * @param 
	 */
	public boolean deleteConfigPolicyFeeById(int id) throws Exception;
	
	/**
	 * 删除院校收费政策标准及其明细（物理删除）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deletePolicyFeeAndFeeStandById(int id) throws Exception;
	
//	/**
//	 * 删除院校收费政策标准(物理删除)
//	 * @param 
//	 */
//	public boolean deletePolicyFeeById(int id) throws Exception;
//	
//	/**
//	 * 删除院校收费政策标准(逻辑删除)
//	 * @param 
//	 */
//	public boolean deletePolicyFeeFlag(int id) throws Exception;
	
	/**
	 * 修改院校收费政策标准
	 * @param feeBatch
	 */
	public boolean modifyPolicyFee(PolicyFee policyFee) throws Exception;
	
	/**
	 * 修改院校收费政策标准及其明细列表
	 * @param policyFee
	 * @param feeStandardList
	 * @return
	 * @throws Exception
	 */
	public boolean updatePolicyFeeFeeStandardList(PolicyFee policyFee,List<FeeStandard> feeStandardList) throws Exception;
	
	/**
	 * 获取院校收费政策标准数量
	 * @param type
	 * @param pr
	 * @return
	 */
	public int findPolicyFeeCountByDetails(int academyId, int feesubjectId,PageResult<PolicyFee> pr) throws Exception;
	
	/**
	 * 获取院校收费政策标准列表
	 * @param type
	 * @param pr
	 * @return
	 */
	public List<PolicyFee> findPolicyFeeListByDetails(int academyId, int feesubjectId,PageResult<PolicyFee> pr) throws Exception;

	/**
	 * 根据Id查找院校收费政策标准及其规则详细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyFee findPolicyFeeAndFeeStandardById(int id)throws Exception;
}
