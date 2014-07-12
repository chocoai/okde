package net.cedu.dao.finance;

import net.cedu.dao.BaseDao;
import net.cedu.entity.finance.PayAcademyCedu;

/**
 * 院校返款总部 数据访问接口
 * 
 * @author Sauntor
 *
 */
public interface PayAcademyCeduDao extends BaseDao<PayAcademyCedu>
{
	
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
}
