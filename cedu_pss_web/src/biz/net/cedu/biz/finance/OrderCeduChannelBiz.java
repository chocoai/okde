package net.cedu.biz.finance;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.ChannelBatchRecruitRebateStandard;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;
import net.cedu.model.page.PageResult;

/**
 * 合作方返款单业务接口
 * 
 * @author Sauntor
 *
 */
public interface OrderCeduChannelBiz {
	/**
	 * 创建合作方返款单
	 * 
	 * @param order
	 * 			返款单
	 * @param adjustedAmount
	 * 			调整金额
	 * @param  policyCondition
	 * 			合作方返款政策筛选范围
	 * @param fpdIds
	 * 			缴费单明细ID列表
	 * @throws Exception
	 */
	public void add(OrderCeduChannel order, double adjustedAmount, Student policyCondition, int[] fpdIds) throws Exception;
	
	/**
	 * 计算应返款合作方金额
	 * 
	 * @param condition
	 * @param fpdList
	 * @return
	 * @throws Exception
	 */
	public CalcResult calcMoneyToChannel(Student condition, List<FeePaymentDetail> fpdList) throws Exception;
	
	/**
	 * 查询合作方反款单
	 * 
	 * @param branchId
	 * @param channelType
	 * @param channelId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<OrderCeduChannel> find(int branchId, int channelType, int channelId, int status) throws Exception;
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public OrderCeduChannel findById(int id) throws Exception;
	
	/**
	 * 修改招生返款单
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderCeduChannel(OrderCeduChannel order) throws Exception;
	
	/**
	 * 招生返款单审核
	 * 
	 * @param id
	 * @param newStatus 审核后新状态
	 * @throws Exception
	 */
	public void updateForAudition(int id, int newStatus, int auditorId) throws Exception;
	
	/**
	 * 应返款合作方金额计算结果
	 * 
	 * @author Sauntor
	 *
	 */
	public class CalcResult
	{
		private BigDecimal amount;
		private Map<Integer, List<FeePaymentDetail>> groups;

		public CalcResult(BigDecimal amount,
				Map<Integer, List<FeePaymentDetail>> groups) {
			this.amount = amount;
			this.groups = groups;
		}

		public BigDecimal getAmount() {
			return amount;
		}
		
		public Map<Integer, List<FeePaymentDetail>> getGroups() {
			return groups;
		}
	}
	
	/**
	 * 添加招生返款单----招生返款用
	 * @param orderCeduChannel  招生返款单实体
	 * @param fpdList 缴费单明细实体列表
	 * @return
	 * @throws Exception
	 */
	public boolean addOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList) throws Exception;
	
	/**
	 * 添加招生返款单----招生返款用(2012-04-18重构   更新学生信息   消除固定金额重复选择)
	 * @param orderCeduChannel  招生返款单实体
	 * @param fpdList 缴费单明细实体列表
	 * @param stuList 学生列表
	 * @return
	 * @throws Exception
	 */
	public boolean addOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList,List<Student> stuList) throws Exception;
	
	/**
	 * 查询招生返款单数量 (分页)
	 * @param orderCeduChannel 招生返款单实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param amount 实返金额
	 * @return
	 * @throws Exception
	 */
	public int findOrderCeduChannelCountByPage(OrderCeduChannel orderCeduChannel,String starttime,String endtime,String amount) throws Exception;
	
	/**
	 * 查询招生返款单列表(分页)
	 * @param orderCeduChannel 招生返款单实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param amount 实返金额
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<OrderCeduChannel> findOrderCeduChannelListByPage(OrderCeduChannel orderCeduChannel,String starttime,String endtime,String amount,PageResult<OrderCeduChannel> pr) throws Exception;
	
	/**
	 * 删除招生返款单----招生返款用
	 * @param orderCeduChannelId 招生返款单ID
	 * @param fpdList 缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOrderCeduChannel(int orderCeduChannelId,List<FeePaymentDetail> fpdList) throws Exception;
	
	/**
	 * 删除招生返款单----招生返款用((2012-04-18重构   更新学生信息   释放该学生以便可以继续返款))
	 * @param orderCeduChannelId 招生返款单ID
	 * @param fpdList 缴费单明细列表
	 * @param stuList 学生列表
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOrderCeduChannel(int orderCeduChannelId,List<FeePaymentDetail> fpdList,List<Student> stuList) throws Exception;
	
	/**
	 * 更新招生返款单----招生返款用
	 * @param orderCeduChannel 招生返款单实体
	 * @param fpdList 缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList) throws Exception;
	
	/**
	 * 批量更新招生返款单----招生返款用
	 * @param orderCeduChannelList	招生返款单列表
	 * @param fpdList	缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	public boolean updateBatchOrderCeduChannel(List<OrderCeduChannel> orderCeduChannelList,List<FeePaymentDetail> fpdList) throws Exception;
	
	/**
	 * 更新招生返款单----招生返款用_hr汇款渠道时要级联更新学生
	 * @param orderCeduChannel 招生返款单实体
	 * @param fpdList 缴费单明细列表
	 * @param stulist 学生列表
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList,List<Student> stulist) throws Exception;
	
	/**
	 * 方法重载
	 * 更新招生返款单----招生返款用_hr汇款渠道时要级联更新学生——2012-05-28重载招生返款标准绑定到合作方上
	 * @param orderCeduChannel 招生返款单实体
	 * @param fpdList 缴费单明细列表
	 * @param stulist 学生列表
	 * @param cbrslist 合作方与招生返款标准关系集合
	 * @return
	 * @throws Exception
	 */
	public boolean updateOrderCeduChannel(OrderCeduChannel orderCeduChannel,List<FeePaymentDetail> fpdList,List<Student> stulist,List<ChannelBatchRecruitRebateStandard> cbrslist) throws Exception;
	
	/**
	 * 统计招生返款单总金额
	 * @param orderCeduChannel 招生返款单实体
	 * @param starttime 开始时间
	 * @param endtime	结束时间
	 * @param amount	实返金额
	 * @return
	 * @throws Exception
	 */
	public String countOrderCeduChannelAllMoneyByConditions(OrderCeduChannel orderCeduChannel,String starttime,String endtime,String amount) throws Exception;
	
	
	/**
	 * 根据招生返款期id和状态查询还未汇款的招生返款单数量
	 * @param channelRebateTimeId  招生返款期Id
	 * @param status 状态
	 * @return
	 * @throws Exception
	 */
	public int findOrderCeduChannelCountByChannelRebateTimeIdStatus(int channelRebateTimeId,int status) throws Exception;
	
	/**
	 * 统计招生返款单调整金额（招生返款查询中已招生返款页签统计用）
	 * @param student 学生实体
	 * @param fpd 缴费单明细实体
	 * @return
	 * @throws Exception
	 */
	public String countOrderCeduChannelAdjustPaiedByConditions(Student student,FeePaymentDetail fpd) throws Exception;
	
}
