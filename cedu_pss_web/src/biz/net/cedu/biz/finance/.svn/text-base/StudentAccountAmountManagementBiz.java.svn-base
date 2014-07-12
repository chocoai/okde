package net.cedu.biz.finance;

import java.math.BigDecimal;
import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageResult;



/**
 * 学生账户金额变动管理 业务逻辑接口
 * 
 * @author gaole
 *
 */
public interface StudentAccountAmountManagementBiz {
	
	
	/**
	 * 学生账户充值(消费)
	 * @param studentaccountmanagement
	 * @return
	 * @throws Exception
	 */
	public boolean addStudentAccountAmountManagement(StudentAccountAmountManagement saam)throws Exception;
	
	/**
	 * 学生账户充值(消费)
	 * @param saam  充值（消费）记录实体
	 * @param sam  账户实体
	 * @param feePayment 缴费单实体
	 * @return
	 * @throws Exception
	 */
	public boolean addBatchStuAccAmManag(
			StudentAccountAmountManagement saam,StudentAccountManagement sam,FeePayment feePayment) throws Exception;
	
	/**
	 * 根据账户Id查询学生账户金额变动
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentAccountAmountManagement findStudentAccountAmountManagementById(int id)throws Exception;
	
	
	/**
	 * 学生账户金额变动记录(数量)
	 * @param accountId
	 * @param types
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int countStudentAccountAmountManagementByAccountId(int accountId,int types,PageResult<StudentAccountAmountManagement> pr)throws Exception;
	
	/**
	 * 学生账户金额变动记录(集合)
	 * @param accountId
	 * @param types
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<StudentAccountAmountManagement> findStudentAccountAmountManagementByAccountId(int accountId,int types,PageResult<StudentAccountAmountManagement> pr)throws Exception;
	
	
	/**
	 * 查询学生单个费用余额（2012.01.09重构   (申请退费中的不扣除)）
	 * @param studentId 学生ID
	 * @param feeSubjectId 费用科目ID
	 * @return
	 */
	public BigDecimal findStudentAccountAmountManagementBySidAndFeeSubject(int studentId,int feeSubjectId)throws Exception;
	
	/**
	 * 查询学生单个费用余额（2012.01.09重构   (申请退费中的扣除)）
	 * @param studentId 学生Id
	 * @param feeSubjectId 费用科目ID
	 * @return
	 * @throws Exception
	 */
	public BigDecimal findStudentAccountFeesubjectBalanceByStudentIdFeeSubjectId(int studentId, int feeSubjectId) throws Exception;
	
	/**
	 * 根据缴费单明细查询学生账户明细具体消费、充值记录
	 * @param feePaymentDetailId
	 * @return
	 * @throws Exception
	 */
	public List<StudentAccountAmountManagement> findStudentAccountAmountManagementByFeePaymentDetailId(int feePaymentDetailId) throws Exception;
	
	/**
	 * 根据缴费单Id和学生账户明细查询学生账户退费情况(退费时用到、不能用于其它地方 2012.01.09)
	 * @param feePaymentId 缴费单Id
	 * @param types  学生账户明细状态
	 * @return
	 * @throws Exception
	 */
	public List<StudentAccountAmountManagement> findStudentAccountAmountManagementListByFeePaymentIdTypes(int feePaymentId,int types) throws Exception;
	
	/**
	 * 根据缴费单Id学生账户缴费时的充值情况(查看缴费单时用到、不能用于其它地方 2012.01.17)
	 * @param feePaymentId 缴费单Id
	 * @return
	 * @throws Exception
	 */
	public List<StudentAccountAmountManagement> findStudentAccountAmountManagementListByFeePaymentIdForViewFeePayment(int feePaymentId) throws Exception;
	
	/**
	 * 修改学生账户明细
	 * @param saam 学生账户明细实体
	 * @return
	 * @throws Exception
	 */
	public boolean updateStudentAccountAmountManagement(StudentAccountAmountManagement saam) throws Exception;
	
	/**
	 * 根据条件查询学生单纯充值的数量（分页）
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public int findStudentSimpleRechargeCountByPage(Student student,String starttime,String endtime) throws Exception;
	
	/**
	 * 根据条件查询学生单纯充值的集合（分页）
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<StudentAccountAmountManagement> findStudentSimpleRechargeListByPage(Student student,String starttime,String endtime,PageResult<StudentAccountAmountManagement> pr) throws Exception;
	
	/**
	 * 统计学生单纯充值
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public String countStudentSimplyAccountMoney(Student student ,String starttime,String endtime) throws Exception;
	
	/**
	 * 根据条件查询学生充值的数量（分页）_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public int findStudentRechargeCountByPage(Student student,String starttime,String endtime,String feeSubjectIds) throws Exception;
	
	/**
	 * 根据条件查询学生充值的集合（分页）_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<StudentAccountAmountManagement> findStudentRechargeListByPage(Student student,String starttime,String endtime,String feeSubjectIds, PageResult<StudentAccountAmountManagement> pr) throws Exception;
	
	/**
	 * 根据条件查询缴费单明细数量（分页）_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param status 缴费单明细状态
	 * @param ccStartTime 总部确认时间起
	 * @param ccEndTime 总部确认时间止
	 * @param feeWayId 缴费方式Id
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentDetailCountByPageForSearch(Student student,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId) throws Exception;
	
	/**
	 * 根据条件查询缴费单明细集合（分页）_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param status 缴费单明细状态
	 * @param ccStartTime 总部确认时间起
	 * @param ccEndTime 总部确认时间止
	 * @param feeWayId 缴费方式Id 
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForSearch(Student student,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 统计学生充值_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param feeSubjectIds 费用科目Ids字符串
	 * @return
	 * @throws Exception
	 */
	public String countStudentRechargeMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception;
	
	/**
	 * 统计学生消费_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param feeSubjectIds 费用科目Ids字符串
	 * @return
	 * @throws Exception
	 */
	public String countStudentXiaoFeiMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception;
	
	/**
	 * 统计学生退费_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param feeSubjectIds 费用科目Ids字符串
	 * @return
	 * @throws Exception
	 */
	public String countStudentTuiFeiMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception;
	
	/**
	 * 统计缴费单明细金额_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param feeSubjectIds 费用科目Ids字符串
	 * @param status 缴费单明细状态
	 * @param ccStartTime 总部确认时间起
	 * @param ccEndTime 总部确认时间止
	 * @param feeWayId 缴费方式Id 
	 * @return
	 * @throws Exception
	 */
	public String countStudentFeePaymentDetailMoney(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId) throws Exception;
	
	/**
	 * 统计缴费单明细使用充值金额_缴费单明细查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param feeSubjectIds 费用科目Ids字符串
	 * @param status 缴费单状态
	 * @param feeWayId 缴费方式Id
	 * @return
	 * @throws Exception
	 */
	public String countStudentFeePaymentDetailShiYongChongZhiMoney(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId) throws Exception;
	
	/**
	 * 统计缴费单明细金额_日收款单查询页面
	 * @param student 学生实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param feeSubjectIds 费用科目Ids字符串
	 * @param status 缴费单状态
	 * @param ccStartTime 总部确认时间起
	 * @param ccEndTime 总部确认时间止
	 * @return
	 * @throws Exception
	 */
	public String countStudentfpdMoneyForCeduConfirmDay(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime) throws Exception;
	
}
