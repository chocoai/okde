package net.cedu.dao.finance;

import net.cedu.dao.BaseDao;
import net.cedu.entity.finance.InvoiceBook;

/**
 * 票本  数据访问接口
 * @author gaolei
 *
 */
public interface InvoiceBookDao extends BaseDao<InvoiceBook> {
	/**
	 * 根据机构查询票本总数
	 * @param branch
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int findAllTotalByBranch(int branch,int status) throws Exception;
}
