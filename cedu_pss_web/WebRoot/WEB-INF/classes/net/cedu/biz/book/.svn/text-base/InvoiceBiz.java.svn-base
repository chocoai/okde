package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.Invoice;
import net.cedu.model.page.PageResult;

/**
 * 总部发货单业务逻辑层 接口
 * 
 * @author YY
 * 
 */
public interface InvoiceBiz {

	/**
	 * 查询全部
	 * 
	 * @return list
	 * @throws Exception
	 */
	List<Invoice> findAll() throws Exception;

	/**
	 * 根据id查询对象
	 * 
	 * @param id
	 * @return Invoice
	 * @throws Exception
	 */
	Invoice findIdByInvoice(int id) throws Exception;

	/**
	 * 分页结果
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Invoice> findInvoicePageListByConditions(Invoice invoice, PageResult<Invoice> pr)throws Exception;
	/**
	 * 分页数量
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findInvoicePageCountByConditions(Invoice invoice, PageResult<Invoice> pr)throws Exception;
	
	/**
	 * 增加方法
	 * @param invoice
	 * @return invoice
	 * @throws Exception
	 */
	public void addInvoice(Invoice invoice) throws Exception;
 
}
