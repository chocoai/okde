package net.cedu.dao.finance;

import net.cedu.dao.BaseDao;
import net.cedu.entity.finance.OrderBranchCedu;

/**
 * 学习中心汇款总部单数据访问接口
 * @author Sauntor
 *
 */
public interface OrderBranchCeduDao extends BaseDao<OrderBranchCedu>
{
	
	/**
	 * 统计汇款单总金额
	 * @param order 汇款单
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param amount 汇款金额
	 * @return
	 * @throws Exception
	 */
	public String countOrderBranchCeduAllMoneyByConditions(OrderBranchCedu order,String starttime,String endtime,String amount) throws Exception;
}
