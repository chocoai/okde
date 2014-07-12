package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.InvoiceBook;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageResult;



/**
 * 缴费单  业务逻辑接口
 * 
 * @author gaole
 *
 */
public interface FeePaymentBiz {
	
	/**
	 * 查看学生缴费单
	 * @param 学生Id
	 * @return 
	 * @throws Exception
	 */
	public List<FeePayment> findFeePaymentByStudentId(int studentId,int status)throws Exception;
	
	/**
	 * 新增缴费单
	 * @param feePayment
	 * @return
	 * @throws Exception
	 */
	public boolean addFeePayment(FeePayment feePayment) throws Exception;
	
	
	/**
	 * 按主键ID查询缴费单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FeePayment findFeePaymentById(int id)throws Exception;
	
	/**
	 * 按缴费单号查询缴费单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FeePayment findFeePaymentByCode(String code)throws Exception;
	
	
	/**
	 * 查看发票缴费单
	 * @param FeePaymentIds
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<FeePayment> findFeePaymentByFeePaymentIds(String FeePaymentIds,PageResult<FeePayment> pr)throws Exception;
	
	
	
	/**
	 * 查看学生缴费单(供统计查询模块使用)
	 * @param studentId 学生Id	
	 * @param status 缴费单状态
	 * @return
	 * @throws Exception
	 */
	public List<FeePayment> findFeePaymentBySId(int studentId,int status)throws Exception;
	
	
	/**
	 * 修改学生缴费单
	 * @param feePayment 缴费单实体
	 * @return
	 * @throws Exception
	 */
	public boolean updateFeePayment(FeePayment feePayment) throws Exception;
	
	/**
	 * 统计缴费单总金额(如果缴费单没有打印条件查询则赋值为isPrint=-1)
	 * @param feePayment 缴费单实体
	 * @param student 学生实体
	 * @param feemoney 缴费金额
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public String countFeePaymentAllMoneyByConditions(FeePayment feePayment, Student student,
			String feemoney,String starttime,String endtime) throws Exception; 
	
	/**
	 * (重载方法)统计缴费单总金额(如果缴费单没有打印条件查询则赋值为isPrint=-1)
	 * @param feePayment 缴费单实体
	 * @param student 学生实体
	 * @param feemoney 缴费金额
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param globalids 全局批次id集合
	 * @return
	 * @throws Exception
	 */
	public String countFeePaymentAllMoneyByConditions(FeePayment feePayment, Student student,
			String feemoney,String starttime,String endtime,String globalids) throws Exception;
	
	/**
	 * 添加退费单
	 * @param feePayment 缴费单实体
	 * @param feePaymentDetailList 缴费单明细实体集合
	 * @param stuaaList  学生账户明细集合
	 * @param historyfpdList 历史缴费单集合
	 * @return
	 * @throws Exception
	 */
	public boolean addRefundPayment(FeePayment feePayment,List<FeePaymentDetail> feePaymentDetailList,List<StudentAccountAmountManagement> stuaaList,List<FeePaymentDetail> historyfpdList) throws Exception;
	
	/**
	 * 审批退费单
	 * @param feePayment 缴费单实体
	 * @param feePaymentDetailList 缴费单明细实体集合
	 * @param studentAccountManagement 学生账户实体
	 * @param stuaaList  学生账户明细集合
	 * @param historyfpdList 历史缴费单集合
	 * @return
	 * @throws Exception
	 */
	public boolean updateRefundPaymentForConfirm(FeePayment feePayment,List<FeePaymentDetail> feePaymentDetailList,StudentAccountManagement studentAccountManagement,List<StudentAccountAmountManagement> stuaaList,List<FeePaymentDetail> historyfpdList) throws Exception;
	
	/**
	 * 查询一定条件下的退费单明细（供缴费单 明细流程确认时使用）
	 * @param feePaymentDetailId  缴费单明细Id
	 * @param stratStatusId 开始状态
	 * @param endStatusId 结束状态
	 * @param tfStatusId 退费单明细状态
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(int feePaymentDetailId,int stratStatusId,int endStatusId,int tfStatusId) throws Exception;
	
	
	/**
	 * 修复缴费单明细各个账户值（只能是完成缴费还没进行其他缴费流程才能修复）
	 * @throws Exception
	 */
	public void updateRepairFeePamymentDetailAllAccount() throws Exception;
	
	/**
	 * 根据缴费单明细Id查找相应的退费单明细集合
	 * @param feePaymentDetailId
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findRefundFpdListByFeePaymentDetailId(int feePaymentDetailId) throws Exception;
	/**
	 * 
	 * @功能：通过主键ID集合查询缴费单集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-17 下午05:44:25
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<FeePayment> findFeePaymentByIds(String ids) throws Exception;
	
	/**
	 * 按收据号查询缴费单
	 * @param barCode 收据号
	 * @return
	 * @throws Exception
	 */
	public FeePayment findFeePaymentByBarCode(String barCode) throws Exception;
	
	/**
	 * 修改学生缴费单和缴费单明细集合
	 * @param feePayment
	 * @param fpdList
	 * @return
	 * @throws Exception
	 */
	public boolean updateFeePaymentAndFeePaymentDetailList(FeePayment feePayment,List<FeePaymentDetail> fpdList) throws Exception;
	
	/**
	 * * 处理钱在途   退费单审批通过但是钱最终归属未确定的情况
	 * 
	 * 增加审批退费单通过的后续流程
	 * @param refundFpdList 退费单集合
	 * @return
	 * @throws Exception
	 */
	public boolean addRefundHouXuLiuChengOtherConfirm(List<FeePaymentDetail> refundFpdList) throws Exception;
	
	/**
	 * 更新缴费单日期
	 * @param fpId 缴费单id
	 * @param createdTime 创建时间
	 * @param userId 更新人
	 * @return 0：更新成功    1   缴费单明细已总部确认
	 * @throws Exception
	 */
	public int updateFeePaymentCreatedTime(int fpId,String createdTime,int userId) throws Exception;
	
	/**
	 * 根据学生id查询未作废的缴费单数量
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentNoInvalidByStudentId(int studentId) throws Exception;
}
