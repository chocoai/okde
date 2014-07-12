package net.cedu.demo.biz;

import java.util.List;

import net.cedu.demo.entity.DCredit;
import net.cedu.model.page.PageResult;

/**
 * demo接口
 * 
 * @author yangdongdong
 * 
 */
public interface DUserBiz {
	/**
	 * 查询分数
	 * 
	 * @param type
	 * @param p
	 * @return
	 */
	public List<DCredit> findCreditByDetails(int type, PageResult<DCredit> p);

	/**
	 * 条数
	 * 
	 * @param type
	 * @param p
	 * @return
	 */
	public int findCreditCountByDetails(int type, PageResult<DCredit> p);
}
