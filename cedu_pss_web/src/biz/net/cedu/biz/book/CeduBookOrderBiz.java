package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.CeduBookOrder;
import net.cedu.model.page.PageResult;

/**
 * 总部订购单业务逻辑层 接口
 * 
 * @author YY
 * 
 */
public interface CeduBookOrderBiz {

	/**
	 * 查询全部
	 * 
	 * @return list
	 * @throws Exception
	 */
	List<CeduBookOrder> findAll() throws Exception;

	/**
	 * 根据id查询对象
	 * 
	 * @param id
	 * @return cedubookorder
	 * @throws Exception
	 */
	CeduBookOrder findIdByCeduBookOrder(int id) throws Exception;

	/**
	 * 根据状态和学习中心查询订单
	 * 
	 * @param stauts
	 * @param branch
	 * @return list
	 */
	List<CeduBookOrder> findStautsAndBranchByCeduBookOrder(int stauts,
			int branch[]) throws Exception;
	/**
	 * 通过编号查询对象
	 * @return CeduBookOrder
	 * @throws Exception
	 *  @param orderCode
	 */
	CeduBookOrder findOrderCedeByCeduBookOrder(String orderCode)throws Exception;
	/**
	 * 分页方法（集合）
	 * 
	 * @作者： 杨阳
	 * @作成时间：2012-2-27 上午11:12:44
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 */
	List<CeduBookOrder> findPageListByCeduBookOrder(CeduBookOrder ceduBookOrder,PageResult<CeduBookOrder> pr) throws Exception;
	/**
	 * 分页方法（数量）
	 * 
	 * @作者： 杨阳
	 * @作成时间：2012-2-27 上午11:16:24
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 */
	int findPageCountByCeduBookOrder(CeduBookOrder ceduBookOrder,PageResult<CeduBookOrder> pr)throws Exception;

	
	/**
	 * 增加订购单
	 * @param ceduBookOrder
	 * @return
	 * @throws Exception
	 */
	void addCeduBookOrder(CeduBookOrder ceduBookOrder)throws Exception;
}
