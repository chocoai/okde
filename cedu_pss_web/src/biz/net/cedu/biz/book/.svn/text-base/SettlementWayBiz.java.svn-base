package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.SettlementWay;
import net.cedu.model.page.PageResult;

/**
 * 结算方式
 * @author 	XFY
 *
 */
public interface SettlementWayBiz {
	/**
	 * 增加
	 * @param textbook
	 * @return
	 * @throws Exception
	 */
	Object addSettlementWay(SettlementWay settlementway)throws Exception;
	
	/**
	 * 分页-list
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<SettlementWay> findAllSettlementWay( PageResult<SettlementWay> pr)throws Exception;
	
	/**
	 * 分页-数量
	 * @param condition
	 * @param result
	 * @return 
	 * @throws Exception
	 */
	int findAllSettlementWayCount(PageResult<SettlementWay> pr)throws Exception;
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteSettlementWay(int id) throws Exception;
	/**
	 * 按ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SettlementWay findSettlementWayById(int id) throws Exception;
	/**
	 * 修改
	 * @param settlementway
	 * @throws Exception
	 */
	void updateSettlementWay(SettlementWay settlementway) throws Exception;
	
	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	List<SettlementWay> findAllSettlementWay()throws Exception;
	/**
	 * 验证方法
	 * @param code
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public SettlementWay findByNameOrCodeSettlementWay(String code,String name) throws Exception;
}
