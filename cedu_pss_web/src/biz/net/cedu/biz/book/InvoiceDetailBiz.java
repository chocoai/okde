package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.InvoiceDetail;
/**
 * 总部发货单明细
 * @author YY
 *
 */
public interface InvoiceDetailBiz {

	/**
	 * 根据id查询明细单
	 * @param invoice
	 * @return
	 */
	List<InvoiceDetail> findorderIdByInvoiceDetail(int code)throws Exception;
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InvoiceDetail findByid(int id) throws Exception;
	 
	/**
	 * 按照id集合查詢出訂單集合
	 * @param ids
	 * @return list
	 * @throws Exception
	 */
	public List<InvoiceDetail> findIdsByInvoiceDetail(int[] ids)throws Exception;
	
	/**
	 * 增加方法
	 * @param invoiceDetail
	 */
	void addInvoiceDetail(InvoiceDetail invoiceDetail);
}
