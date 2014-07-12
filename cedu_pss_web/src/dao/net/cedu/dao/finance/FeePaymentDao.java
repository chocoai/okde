package net.cedu.dao.finance;

import net.cedu.dao.BaseDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;

/**
 * 缴费单  数据访问接口
 * @author gaolei
 *
 */
public interface FeePaymentDao extends BaseDao<FeePayment> 
{
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
	 * 统计缴费单总金额(如果缴费单没有打印条件查询则赋值为isPrint=-1)
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
	 * 
	 * @功能：通过学生ID查询全局批次ID
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-6 下午05:05:05
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public int getCommonBatch(int studentId)throws Exception;
	/**
	 * 
	 * @功能：修复缴费单全局批次ID
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-6 下午05:24:19
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @throws Exception
	 */
	public void repairFeePaymentCommonBatch()throws Exception;
	
	/**
	 * 修复缴费单明细各个账户值（只能是完成缴费还没进行其他缴费流程才能修复）
	 * @throws Exception
	 */
	public void repairFeePamymentDetailAllAccount() throws Exception;
	
	/**
	 * 修复缴费单充值金额和总金额（明细的使用充值金额，在缴费单里的充值金额应为负数）
	 * @throws Exception
	 */
	public void repairFeePamymentTotalAmountAndRechargeAmount() throws Exception;
	
	/**
	 * 还原pos直汇院校和网银院校缴费单明细各个账户值、状态还原到前一个状态
	 * （由于需求变动，历史数据处理问题    只允许用一次————2012-04-16已用）
	 * @throws Exception
	 */
	public void revertFeePamymentDetailStatusAccountForPosEbank() throws Exception;
	
	
	/**
	 * 修复缴费单优惠金额和总金额(2012-04-16  所有显示不包含优惠金额)
	 * @throws Exception
	 */
	public void repairFeePamymentTotalAmountAndDiscountAmount() throws Exception;
	
}
