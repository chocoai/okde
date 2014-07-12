package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.DeliveredWay;
import net.cedu.model.page.PageResult;

/**
 * 配送方式业务层接口
 * @author XFY
 *
 */
public interface DeliveredWayBiz {
	/**
	 * 增加
	 * @param textbook
	 * @return
	 * @throws Exception
	 */
	Object addDeliveredWay(DeliveredWay deliveredway)throws Exception;
	
	/**
	 * 分页
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<DeliveredWay> findAllDeliveredWay( PageResult<DeliveredWay> pr)throws Exception;
	
	/**
	 * 分页-数量
	 * @param condition
	 * @param result
	 * @return 
	 * @throws Exception
	 */
	int findAllDeliveredWayCount(PageResult<DeliveredWay> pr)throws Exception;
	
	/**
	 * 根据id删除
	 * 
	 * @param BookCategory
	 * @throws Exception
	 */
	void deleteDeliveredWayById(int id) throws Exception;
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	DeliveredWay findDeliveredWayById(int id)throws Exception;
	
	/**
	 * 修改
	 * @param deliveredway
	 * @throws Exception
	 */
	
	void updateDeliveredWay(DeliveredWay deliveredway)throws Exception;
	/**
	 * 验证方法
	 * @param code
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public DeliveredWay findByNameOrCodeDeliveredWay(String code,String name) throws Exception;
}
