package net.cedu.dao.finance;

import java.util.Map;

import net.cedu.dao.BaseDao;
import net.cedu.entity.finance.Receipt;

/**
 * 收据  数据访问接口
 * @author gaolei
 *
 */
public interface ReceiptDao extends BaseDao<Receipt> {
	
	/**
	 * 根据机构，状态，是否核销查询每个机构的收据总数
	 * @param branch
	 * @param status
	 * @param isCannel
	 * @param invoiceBookStatus
	 * @return Map key:invoiceBookId value:count
	 * @throws Exception
	 */
	public Map<String,Integer> findCountByBranchAndStatusAndIsCannel(int branch,int status,int isCannel,int invoiceBookStatus) throws Exception;
	
	/**
	 * 根据机构、状态，是否核销查询全部的收据总数
	 * @param branch
	 * @param status
	 * @param isCannel
	 * @param invoiceBookStatus
	 * @return
	 * @throws Exception
	 */
	public int findAllCountByBranchAndStatusAndIsCannel(int branch,int status,int isCannel,int invoiceBookStatus) throws Exception;
}
