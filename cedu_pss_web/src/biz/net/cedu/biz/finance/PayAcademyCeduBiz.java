package net.cedu.biz.finance;

import java.util.Date;
import java.util.List;

import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.entity.finance.AcademyRebateAddRecords;
import net.cedu.entity.finance.AcademyRebateFenPeiBranch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.entity.finance.PlanedAcademyBill;
import net.cedu.entity.finance.StudentAcademyRebate;
import net.cedu.model.common.Range;
import net.cedu.model.page.PageResult;


/**
 * 院校返款总部 业务接口
 * 
 * @author Sauntor
 *
 */
public interface PayAcademyCeduBiz
{
	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PayAcademyCedu findById(int id) throws Exception;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int add(PayAcademyCedu entity) throws Exception;
	
	/**
	 * 根据ID删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
	
	/**
	 * 更新
	 * @param entity
	 * @throws Exception
	 */
	public void update(PayAcademyCedu entity) throws Exception;
	
	/**
	 *  更新院校返款单
	 * @param pac
	 * @return
	 * @throws Exception
	 */
	public boolean updatePayAcademyCedu(PayAcademyCedu pac) throws Exception;
	
	/**
	 * 分页查询
	 * 
	 * @param example 条件
	 * @param remittanceDateRange 返款日期范围
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public List<PayAcademyCedu> find(PayAcademyCedu example, Range<Date> remittanceDateRange, PageResult<PayAcademyCedu> result) throws Exception;
	
	/**
	 * 条件查询
	 * 
	 * @param example 条件
	 * @param remittanceDateRange 返款日期范围
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public int count(PayAcademyCedu example, Range<Date> remittanceDateRange, PageResult<PayAcademyCedu> result) throws Exception;
	
	public int add(PayAcademyCedu entity, int[] paymentDetailIds, int[] academyBillIds) throws Exception;
	
	/**
	 * 添加院校返款单
	 * @param pac 院校返款单实体
	 * @param fpdlist 缴费单明细集合
	 * @param pablist 应收学校款集合
	 * @return
	 * @throws Exception
	 */
	public boolean addPayAcademyCedu(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist) throws Exception;
	
	/**
	 * 添加院校返款单  2012-07-11  重构
	 * @param pac 院校返款单实体
	 * @param fpdlist 缴费单明细集合
	 * @param pablist 应收学校款集合
	 * @param arfpblist 追加金额分配学习中心集合
	 * @param abrclist 院校返款每个批次的返款总人数集合
	 * @param ararlist 追加院校返款金额集合
	 * @return
	 * @throws Exception
	 */
	public boolean addPayAcademyCeduReview(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist,List<AcademyRebateFenPeiBranch> arfpblist,List<AcademyBatchRebateCount> abrclist,List<AcademyRebateAddRecords> ararlist) throws Exception;
	
	/**
	 * 删除院校返款单
	 * @param pacId 院校返款单Id
	 * @param fpdlist 缴费单明细集合
	 * @param pablist 应收学校款集合
	 * @return
	 * @throws Exception
	 */
	public boolean deletePayAcademyCedu(int pacId, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist) throws Exception;
	
	/**
	 * 删除院校返款单2012-07-11  重构
	 * @param pac 院校返款单实体
	 * @param fpdlist 缴费单明细集合
	 * @param pablist 应收学校款集合
	 * @param abrclist 院校返款每个批次的返款总人数集合
	 * @param arfpblist 追加金额分配学习中心集合
	 * @param ararlist 追加院校返款金额集合
	 * @return
	 * @throws Exception
	 */
	public boolean deletePayAcademyCeduReview(int pacId, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist,List<AcademyBatchRebateCount> abrclist,List<AcademyRebateFenPeiBranch> arfpblist,List<AcademyRebateAddRecords> ararlist) throws Exception;
	
	/**
	 * 更新院校返款单
	 * @param pac 院校返款单实体
	 * @param fpdlist 缴费单明细集合
	 * @param pablist 应收学校款集合
	 * @return
	 * @throws Exception
	 */
	public boolean updatePayAcademyCedu(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist, List<PlanedAcademyBill> pablist) throws Exception;
	
	/**
	 * 更新院校返款单
	 * @param pac 院校返款单实体
	 * @param fpdlist 缴费单明细集合
	 * @param refundlist 退费单集合
	 * @param pablist 应收学校款集合
	 * @return
	 * @throws Exception
	 */
	public boolean updatePayAcademyCeduForRefund(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist,List<FeePaymentDetail> refundlist, List<PlanedAcademyBill> pablist) throws Exception;
	
	/**
	 * 更新院校返款单     2012-05-16重载
	 * @param pac 院校返款单实体
	 * @param fpdlist 缴费单明细集合
	 * @param refundlist 退费单集合
	 * @param pablist 应收学校款集合
	 * @param sarList 学生绑定院校返款标准关系表
	 * @return
	 * @throws Exception
	 */
	public boolean updatePayAcademyCeduForRefund(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist,List<FeePaymentDetail> refundlist, List<PlanedAcademyBill> pablist, List<StudentAcademyRebate> sarList) throws Exception;
	
	/**
	 * 查询院校返款单数量（分页）
	 * @param pac 院校返款单实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param amount 返款金额
	 * @return
	 * @throws Exception
	 */
	public int findPayAcademyCeduCountByPage(PayAcademyCedu pac,String starttime,String endtime,String amount) throws Exception;
	
	/**
	 * 查询院校返款单列表（分页）
	 * @param pac 院校返款单实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param amount 返款金额
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<PayAcademyCedu> findPayAcademyCeduListByPage(PayAcademyCedu pac,String starttime,String endtime,String amount,PageResult<PayAcademyCedu> pr) throws Exception;
	
	/**
	 * 统计院校返款单总金额
	 * @param payAcademyCedu 院校返款单实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param amount 返款金额
	 * @return
	 * @throws Exception
	 */
	public String countPayAcademyCeduAllMoneyByConditions(PayAcademyCedu payAcademyCedu,String starttime,String endtime,String amount) throws Exception;
	
	/**
	 * 添加院校年度返款单
	 * @param pac   院校返款单实体
	 * @param fpdlist  缴费单明细列表
	 * @param arfpblist 追加金额分配学习中心集合
	 * @param abrclist 院校返款每个批次的返款总人数集合
	 * @param ararlist 追加院校返款金额集合
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademyYearRebateReview(PayAcademyCedu pac, List<FeePaymentDetail> fpdlist, List<AcademyRebateFenPeiBranch> arfpblist,List<AcademyBatchRebateCount> abrclist,List<AcademyRebateAddRecords> ararlist) throws Exception;
	
	/**
	 * 删除院校年度返款单
	 * @param pacId  年度返款单Id
	  * @param fpdlist  缴费单明细列表
	 * @param arfpblist 追加金额分配学习中心集合
	 * @param abrclist 院校返款每个批次的返款总人数集合
	 * @param ararlist 追加院校返款金额集合
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAcademyYearRebateReview(int pacId, List<FeePaymentDetail> fpdlist, List<AcademyBatchRebateCount> abrclist,List<AcademyRebateFenPeiBranch> arfpblist,List<AcademyRebateAddRecords> ararlist) throws Exception;
	
}
