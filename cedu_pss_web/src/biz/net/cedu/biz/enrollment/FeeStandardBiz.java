package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.FeeStandard;

/**
 * 收费标准
 * 
 * @author lixiaojun
 * 
 */
public interface FeeStandardBiz 
{
	
	/**
	 * 根据Id查找收费标准
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FeeStandard findFeeStandardById(int id)throws Exception;
	
	/**
	 * 添加收费标准
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addFeeStandard(FeeStandard feeStandard) throws Exception;
	
	/**
	 * 删除收费标准(配置文件)
	 * @param 
	 */
	public boolean deleteConfigFeeStandardById(int id) throws Exception;
	
//	/**
//	 * 删除收费标准(物理删除)
//	 * @param 
//	 */
//	public boolean deleteFeeStandardById(int id) throws Exception;
//	
//	/**
//	 * 删除收费标准(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteFeeStandardFlag(int id) throws Exception;
	
	/**
	 * 修改收费标准
	 * @param feeBatch
	 */
	public boolean modifyFeeStandard(FeeStandard feeStandard) throws Exception;
	
	/**
	 * 根据收费政策明细Id查找收费标准
	 * @param id 收费政策明细Id
	 * @return
	 * @throws Exception
	 */
	public List<FeeStandard> findFeeStandardListByFeeId(int id) throws Exception;
	
	/**
	 * 根据学生Id查询报名费收费标准明细
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public FeeStandard findRegistrationFeeStandardByStudentId(int studentId) throws Exception;
	
	/**
	 * 根据学生Id查询测试费收费标准明细
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public FeeStandard findTestFeeStandardByStudentId(int studentId) throws Exception;
	
	/**
	 * 根据学生Id查询所有非招生阶段收费标准明细(除去测试费和报名费)
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<FeeStandard> findOtherFeeStandardByStudentId(int studentId) throws Exception;
	
	/**
	 * 根据学生Id和费用科目Id查询所有收费标准明细
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<FeeStandard> findFeeStandardListsByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception;
	
	/**
	 * 根据学生Id和费用科目Id查询所有收费标准明细（重构之后的方法）Reconstruction
	 * @param studentId  学生Id
	 * @param feeSubjectId 费用科目Id
	 * @return
	 * @throws Exception
	 */
	public List<FeeStandard> findFeeStandardListReconstructionByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception;
}
