package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.PendingFeePayment;


/**
 * 待缴费单
 * 
 * @author lixiaojun
 * 
 */
public interface PendingFeePaymentBiz 
{
	
	/**
	 * 根据Id查找待缴费单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PendingFeePayment findPendingFeePaymentById(int id)throws Exception;
	
	/**
	 * 添加待缴费单
	 * @param pendingFeePayment
	 * @return
	 * @throws Exception
	 */
	public boolean addPendingFeePayment(PendingFeePayment pendingFeePayment) throws Exception;
	
	/**
	 * 删除待缴费单(读取配置文件)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteConfigPendingFeePaymentById(int id) throws Exception; 
	
	/**
	 * 批量删除待缴费单(慎用，真删)
	 * @param pendingFeePaymentList 待缴费单列表
	 * @throws Exception
	 */
	public void deleteBatchPendingFeePayment(List<PendingFeePayment> pendingFeePaymentList) throws Exception;
	
	/**
	 * 根据学生Id批量删除待缴费单(慎用，真删)
	 * @param studentId 学生Id
	 * @return
	 * @throws Exception
	 */
	public boolean deletePendingFeePaymentByStudentId(int studentId) throws Exception;
	
	/**
	 * 修改待缴费单
	 * @param studentDiscountDetail
	 * @return
	 * @throws Exception
	 */
	public boolean updatePendingFeePayment(PendingFeePayment pendingFeePayment) throws Exception;
	
	/**
	 * 通过学生Id查找待缴费单列表
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<PendingFeePayment> findPendingFeePaymentListByStudentId(int studentId) throws Exception;
	
	/**
	 * 通过学生Id和收费政策明细Id查询待缴费单数量
	 * @param studentId
	 * @param feeStandardId
	 * @return
	 * @throws Exception
	 */
	public int findPendingFeePaymentListByStudentIdFeeStandardId(int studentId,int feeStandardId) throws Exception;
	
	/**
	 * 批量添加所有生成的待缴费单(添加)
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean addBatchPendingFeePayment(List<PendingFeePayment> list) throws Exception;
	
	/**
	 * 通过院校Id和费用科目Id生成所有待缴费单列表(不添加，未批量添加服务)
	 * @param academyId
	 * @param feeSubjectId
	 * @return
	 * @throws Exception
	 */
	public List<PendingFeePayment> findAddPendingFeePaymentByAcademyIdFeeSubjectId(int academyId,int feeSubjectId) throws Exception;
	
	/**
	 * 通过院校Id和费用科目Id生成所有待缴费单列表(不添加，未批量添加服务)_定时任务用
	 * @param academyId
	 * @param feeSubjectId
	 * @return
	 * @throws Exception
	 */
	public List<PendingFeePayment> findAddPendingFeePaymentByAcademyIdFeeSubjectIdFortask() throws Exception;
	
	/**
	 * 通过院校Id和费用科目Id生成所有待缴费单列表(不添加,为添加方法服务)(重构之后的方法)
	 *
	 * @param academyId   院校ID
	 * @param feeSubjectId  费用科目Id
	 * @return
	 * @throws Exception
	 */
	public List<PendingFeePayment> generatePendingFeePaymentListByAcademyIdFeeSubjectId(int academyId,int feeSubjectId) throws Exception;
	
	/**
	 * 通过学生Id和费用科目Id查找该学生需要缴费的缴费单列表
	 * @param studentId
	 * @param feeSubjectId
	 * @return
	 * @throws Exception
	 */
	public List<PendingFeePayment> findPendingFeePaymentListByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception;

	/**
	 * 通过学生Id和费用科目Id查找该学生需要缴费的待缴费单列表(重构之后的方法)
	 * @param studentId  学生Id
	 * @param feeSubjectId 费用科目Id
	 * @return
	 * @throws Exception
	 */
	public List<PendingFeePayment> findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception;
	
	/**
	 * 通过学生Id和费用科目Id生成该学生所有待缴费单列表(不添加,为添加方法服务)(重构之后的优化方法)
	 * @param studentId  学生Id
	 * @param feeSubjectId 费用科目Id
	 * @return
	 * @throws Exception
	 */
	public List<PendingFeePayment> generatePendingFeePaymentListByStudentIdFeeSubjectId(int studentId,int feeSubjectId) throws Exception;
	
}
