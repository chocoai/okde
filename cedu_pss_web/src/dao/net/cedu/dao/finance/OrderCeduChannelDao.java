package net.cedu.dao.finance;

import net.cedu.dao.BaseDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;

/**
 * 合作方返款单 DAO
 * 
 * @author Sauntor
 *
 */
public interface OrderCeduChannelDao extends BaseDao<OrderCeduChannel>
{

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
	 * 统计招生返款单调整金额（招生返款查询中已招生返款页签统计用）
	 * @param student 学生实体
	 * @param fpd 缴费单明细实体
	 * @return
	 * @throws Exception
	 */
	public String countOrderCeduChannelAdjustPaiedByConditions(Student student,FeePaymentDetail fpd) throws Exception;
	
}
