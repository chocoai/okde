package net.cedu.biz.enrollment;

import java.math.BigDecimal;
import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;

/**
 * 院校返款政策明细标准  业务逻辑接口
 */
public interface AcademyRebatePolicyDetailStandardBiz
{
	/**
	 * 根据ID查询明细标准
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicyDetailStandard findById(int id) throws Exception;
	
	/**
	 * 批量保存
	 * @param list
	 * @throws Exception
	 */
	public void addList(List<AcademyRebatePolicyDetailStandard> list) throws Exception;
	
	/**
	 * 保存
	 * @param entity
	 * @return 成功保存政策明细标准的ID
	 * @throws Exception
	 */
	public int addStandard(AcademyRebatePolicyDetailStandard entity) throws Exception;
	
	/**
	 * 查询明细所有标准项
	 * @param policyId 政策明细
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicyDetailStandard> findByPolicyId(int policyId) throws Exception;
	
	/**
	 * 删除指定政策明细的所有标准项
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public int deleteForPolicy(int policyId) throws Exception;
	
	/**
	 * 根据学生和费用科目Id匹配相关的院校返款标准---院校返款用
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目Id
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicyDetailStandard findByStudentIdFeeSubjectIdForAcademyRebate(Student student,int feeSubjectId) throws Exception;
	
	/**
	 * 根据学生、费用科目Id和金额计算出相关的院校返款金额---院校返款用
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目Id
	 * @param money 金额
	 * @return
	 * @throws Exception
	 */
	public double findAcademyRebateByStudentFeeSubjectIdMoney(Student student,int feeSubjectId,BigDecimal money) throws Exception;
	
	/**
	 * 根据缴费单明细Id计算出相关的院校返款金额---院校返款用
	 * @param feePaymentDetailId
	 * @return
	 * @throws Exception
	 */
	public double findAcademyRebateByfeePaymentDetailId(int feePaymentDetailId) throws Exception;
	
	/**
	 * * 2012-05-08  重构
	 * 根据学生和费用科目Id匹配相关的院校返款标准---院校返款用
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目Id
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicyDetailStandard findAcademyRebateStandardByStudentIdFeeSubjectId(Student student,int feeSubjectId) throws Exception;
	
	/**
	 * 2012-05-08 重构
	 * 根据缴费单明细Id计算出相关的院校返款金额---院校返款用
	 * @param fpdId 缴费单明细Id
	 * @return
	 * @throws Exception
	 */
	public boolean updateFpdForAcademyRebateByfpdId(int fpdId) throws Exception;
	
	/**
	 * 2012-05-08 重构
	 * 根据缴费单明细Ids字符串批量计算出相关的院校返款金额---院校返款用
	 * @param fpdIds 缴费单明细Ids字符串集合
	 * @return
	 * @throws Exception
	 */
	public boolean updateBatchFpdForAcademyRebateByfpdIds(String fpdIds) throws Exception;
	
	/**
	 * 2012-07-04  重构
	 * 根据学生和费用科目Id、招生人数匹配相关的院校返款标准---院校返款用
	 * @param student   学生实体
	 * @param feeSubjectId    费用科目Id
	 * @param count  招生人数
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicyDetailStandard findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(Student student,int feeSubjectId,int count) throws Exception;
	
	/**
	 * 2012-07-04 重构
	 * 根据缴费单明细Id和本次需要返款的缴费单明细ids字符串计算出相关的院校返款金额---院校返款用
	 * @param fpdId    缴费单明细Id
	 * @param fpdIds    本次需要返款的缴费单明细ids字符串
	 * @return
	 * @throws Exception
	 */
	public boolean updateFpdForAcademyRebateReviewByFpdIdAndFpdIds(int fpdId,String fpdIds) throws Exception;
	
	
}
