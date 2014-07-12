package net.cedu.dao.finance;

import net.cedu.dao.BaseDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.StudentAccountAmountManagement;

/**
 * 学生账户金额变动  数据访问接口
 * @author gaolei
 *
 */
public interface StudentAccountAmountManagementDao extends BaseDao<StudentAccountAmountManagement> 
{
	
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
	 * 修复单纯充值情况，把单纯充值情况都作为预缴费单处理
	 * 
	 * @throws Exception
	 */
	public void repairStudentAccountDanChunChongZhi() throws Exception;
	
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
	 * @param status 缴费单状态
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
