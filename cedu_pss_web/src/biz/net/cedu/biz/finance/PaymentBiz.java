package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

/**
 * （财务管理）缴费Biz
 * 
 * @author yangdongdong
 * 
 */
public interface PaymentBiz {
	/**
	 * 查询学生跟进总条数
	 * 
	 * @param feePayment
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws
	 */
	public int findFeePaymentsPageCount(FeePayment feePayment, Student student,
			PageResult<FeePayment> pr) throws Exception;

	/**
	 * 查询学生跟进集合
	 * 
	 * @param feePayment
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws
	 */
	public List<FeePayment> findFeePaymentsPageList(FeePayment feePayment,
			Student student, PageResult<FeePayment> pr) throws Exception;

	/**
	 * 查询学生跟进总条数
	 * 
	 * @param feePaymentDetail
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws
	 */
	public int findFeePaymentDetailsPageCount(
			FeePaymentDetail feePaymentDetail, FeePayment feePayment,
			Student student, PageResult<FeePaymentDetail> pr) throws Exception;

	/**
	 * 查询学生跟进集合
	 * 
	 * @param feePaymentDetail
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws
	 */
	public List<FeePaymentDetail> findFeePaymentDetailsPageList(
			FeePaymentDetail feePaymentDetail, FeePayment feePayment,
			Student student, PageResult<FeePaymentDetail> pr) throws Exception;

	/**
	 * 更新缴费单状态，以及缴费单明细状态 (收费确认用)
	 * 
	 * @param status
	 * @throws Exception
	 */
	public void updateFeePaymentStatus(int status, int fpId) throws Exception;
	
	/**
	 * 缴费单作废（更新缴费单状态，以及缴费单明细状态，及其所有关联缴费单的表都还原）
	 * 
	 * @param status
	 * @throws Exception
	 */
	public void updateFeePaymentInvalidStatus(int status, int fpId,int userId) throws Exception;

	/**
	 * 更新缴费单打印状态
	 * 
	 * @param isPrint
	 * @param fpId
	 * @throws Exception
	 */
	public void updateFeePaymentPrintStatus(int isPrint, int fpId)
			throws Exception;

	/**
	 * 更新缴费单明细类型
	 * 
	 * @param type
	 * @throws Exception
	 */
	public void updateFeePaymentDetailType(int type, int fpdId)
			throws Exception;
	
	/**
	 * 查询属于  <b>汇款总部单</b>  的所有缴费单明细
	 * @param orderBranchCeduId
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findDetailByOrderBranchCeduId(int orderBranchCeduId) throws Exception;
	
	/**
	 * 查询属于  <b>院校打款单</b>  的所有缴费单明细
	 * @param orderBranchCeduId
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findDetailByOrderCeduAcademyId(int orderCeduAcademyId) throws Exception;
	
	/**
	 * 查询属于  <b>院校返款单</b>  的所有缴费单明细
	 * @param orderBranchCeduId
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findDetailByOrderAcademyCeduId(int orderAcademyCeduId) throws Exception;
	
	/**
	 * 查询缴费单明细 关联（外键）字段（填充到对应的名称中）
	 * @param list
	 * @throws Exception
	 */
	public void wrapFeePaymentDetail(List<FeePaymentDetail> list) throws Exception;
	
	/**
	 * 缴测试费
	 * @param feePaymentDetail
	 * @param feePayment
	 * @param isFee
	 * @return
	 * @throws Exception
	 */
	public boolean addFeePaymentDetailFeePaymentPending(FeePaymentDetail feePaymentDetail,FeePayment feePayment,int isFee) throws Exception;
	
	/**
	 * 缴报名费和测试费(通用接口)
	 * 操纵6个表(缴费单、缴费单明细、待缴费单、优惠券、收据、学生状态)
	 * @param feePaymentDetailList 缴费单明细集合
	 * @param feePayment	缴费单
	 * @param isFee	是否收款
	 * @param isstatus 是否修改学生状态（缴报名费和测试费时需要修改   其他的不需要）
	 * @return
	 * @throws Exception
	 */
	public int addAllFeePaymentDetailFeePaymentPending(List<FeePaymentDetail> feePaymentDetailList,FeePayment feePayment,int isFee, boolean isstatus) throws Exception;
	
	/**
	 * 缴其他所有费用(除去报名费和测试费(通用接口))
	 * 操纵7个表(缴费单、缴费单明细、待缴费单、优惠券、收据、学生账户、学生账户明细)
	 * @param feePaymentDetailList 缴费单明细集合
	 * @param feePayment	缴费单
	 * @param isFee	是否收款
	 * @return
	 * @throws Exception
	 */
	public int addAllOtherFeePaymentDetailFeePaymentPending(List<FeePaymentDetail> feePaymentDetailList,FeePayment feePayment,int isFee) throws Exception;
	
	
	/**
	 * 添加历史缴费单
	 * @param feePaymentDetailList 缴费单明细集合
	 * @param feePayment 缴费单
	 * @return
	 * @throws Exception
	 */
	public int addHistoryFeePaymenAndDetails(List<FeePaymentDetail> feePaymentDetailList,FeePayment feePayment,int isFee) throws Exception;
	
	/**
	 * 缴其他所有费用(除去报名费和测试费(通用接口))--重构之后的方法
	 * 操纵6个表(缴费单、缴费单明细、优惠券、收据、学生账户、学生账户明细)
	 * @param feePaymentDetailList  缴费单明细集合
	 * @param feePayment	缴费单实体
	 * @param isFee 是否收费
	 * @return
	 * @throws Exception
	 */
	public int addSchoolPaymentRewrite(List<FeePaymentDetail> feePaymentDetailList,FeePayment feePayment,int isFee) throws Exception;
	
	/**
	 * 查询缴费单数量（缴费单查询用     是否打印要设置为-1）
	 * @param feePayment 缴费单实体
	 * @param student 学生实体
	 * @param feemoney 缴费金额
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentCountBySearch(FeePayment feePayment, Student student,
			String feemoney,String starttime,String endtime) throws Exception;
	
	/**
	 * (重写方法)查询缴费单数量（缴费单查询用     是否打印要设置为-1）
	 * @param feePayment 缴费单实体
	 * @param student 学生实体
	 * @param feemoney 缴费金额
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param globalids 全局批次id集合
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentCountBySearch(FeePayment feePayment, Student student,
			String feemoney,String starttime,String endtime,String globalids) throws Exception;
	
	/**
	 * 查询缴费单集合（缴费单查询用     是否打印要设置为-1）
	 * @param feePayment 缴费单实体
	 * @param student 学生实体
	 * @param feemoney 缴费金额
	 * @param starttime 开始时间	
	 * @param endtime 结束时间
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePayment> findFeePaymentListBySearch(FeePayment feePayment,
			Student student,String feemoney,String starttime,
			String endtime, PageResult<FeePayment> pr) throws Exception;
	
	/**
	 * (重写方法)查询缴费单集合（缴费单查询用     是否打印要设置为-1）
	 * @param feePayment 缴费单实体
	 * @param student 学生实体
	 * @param feemoney 缴费金额
	 * @param starttime 开始时间	
	 * @param endtime 结束时间
	 * @param globalids 全局批次id集合
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePayment> findFeePaymentListBySearch(FeePayment feePayment,
			Student student,String feemoney,String starttime,
			String endtime,String globalids, PageResult<FeePayment> pr) throws Exception;
	
	
	/**
	 *  缴费单作废(2012-01-16 重构)（更新缴费单状态，以及缴费单明细状态，及其所有关联缴费单的表都还原）
	 *  
	 * @param fpId
	 * @return  0代表成功   1代表不满足退费情况   2代表报名费和测试费的缴费单不是开单情况不能作废  3代表退费失败
	 * @throws Exception
	 */
	public int updateFeePaymentInvalidStatusRewrite(int fpId) throws Exception;
	
	/**
	 * 预缴费单作废
	 * @param fpId 缴费单Id
	 * @return  0代表成功   3代表退费失败
	 * @throws Exception
	 */
	public int updateFeePaymentInvalidStatusRewriteYuJiaoFei(int fpId) throws Exception;
	
	/**
	 * 释放收据号
	 * @param feePayment
	 * @return
	 * @throws Exception
	 */
	public boolean updateFpdBarCode(FeePayment feePayment) throws Exception;
	
}
