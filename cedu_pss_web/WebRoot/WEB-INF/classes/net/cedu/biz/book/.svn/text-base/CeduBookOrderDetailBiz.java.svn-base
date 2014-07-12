package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.CeduBookOrderDetail;
/**
 * 总部订购单明细
 * @author YY
 *
 */
public interface CeduBookOrderDetailBiz {

	/**
	 * 根据id查询明细单
	 * @param order
	 * @return
	 */
	List<CeduBookOrderDetail> findorderIdByCeduBookOrderDetail(int order)throws Exception;
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CeduBookOrderDetail findByid(int id) throws Exception;
	 
	/**
	 * 按照id集合查詢出訂單集合
	 * @param ids
	 * @return list
	 * @throws Exception
	 */
	public List<CeduBookOrderDetail> findIdsByCeduBookOrderDetail(int[] ids)throws Exception;
	
	/**
	 * 修改方法
	 * @param ceduBookOrderDetail
	 * @throws Exception
	 */
	 void UpdateCeduBookByStatus(CeduBookOrderDetail ceduBookOrderDetail)throws Exception;
	 
	 /**
		 * 增加订购单明细
		 * @param ceduBookOrder
		 * @return
		 * @throws Exception
		 */
	 void addCeduBookOrder(CeduBookOrderDetail ceduBookOrderDetail)throws Exception;
}
