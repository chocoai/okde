package net.cedu.dao.finance;

import net.cedu.dao.BaseDao;
import net.cedu.entity.finance.PayCeduAcademy;

/**
 * 院校打款单 数据接口
 * 
 * @author Sauntor
 *
 */
public interface PayCeduAcademyDao extends BaseDao<PayCeduAcademy>
{
	
	/**
	 * 统计院校打款单总金额
	 * @param payCeduAcademy 院校打款单实体
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param status 备用状态查询条件
	 * @param amount 打款金额
	 * @return
	 * @throws Exception
	 */
	public String countPayCeduAcademyAllMoneyByConditions(PayCeduAcademy payCeduAcademy,String starttime,String endtime,int status,String amount) throws Exception;
}
