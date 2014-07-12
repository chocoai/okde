package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.InvoiceBook;
import net.cedu.model.page.PageResult;



/**
 * 票本  业务逻辑接口
 * 
 * @author gaole
 *
 */
public interface InvoiceBookBiz {
	
	
	/**
	 * 查询票本收据按照授权状态 或中心机构(分页数量)
	 * @param branchId
	 * @param status
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int countInoviceBookAndReceiptByStatus(int branchId,int status,PageResult<InvoiceBook> pr)throws Exception;
	
	
	/**
	 *  查询票本收据按照授权状态 或中心机构(分页数据)
	 * @param branchId
	 * @param status
	 * @param pr
	 * @param bol
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceBook> findInoviceBookAndReceiptByStatus(int branchId,int status,PageResult<InvoiceBook> pr,boolean bol)throws Exception;
	
	
	

	/**
	 * 查询票本收据按照未授权状态(分页数量)
	 * @param status
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int countInoviceBookByStatus(int status,PageResult<InvoiceBook> pr)throws Exception;
	
	
	/**
	 * 查询票本收据按照未授权状态(分页数据)
	 * @param status
	 * @param pr
	 * @param bol
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceBook> findInoviceBookByStatus(int status,PageResult<InvoiceBook> pr,boolean bol)throws Exception;
	
	
	
	/**
	 *  收据授权
	 * @param invoiceBook
	 * @return
	 * @throws Exception
	 */
	public boolean updateInvoiceBookById(InvoiceBook invoiceBook)throws Exception;
	
	
	/**
	 * 收据登记
	 * @param invoiceBook
	 * @param strNo
	 * @param starNo
	 * @param endNo
	 * @return
	 * @throws Exception
	 */
	public boolean addInvoiceBook(InvoiceBook invoiceBook,String strNo,int starNo,int endNo )throws Exception;
	
	
	/**
	 * 查询票本按Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InvoiceBook findInvoiceBookById(int id)throws Exception;
	
	
	/**
	 * 重复性校验票本按开始号段
	 * @param startNo
	 * @return
	 * @throws Exception
	 */
	public InvoiceBook findInvoiceBookByStartNo(String startNo)throws Exception;
	
	/**
	 * 根据机构查询票本总数
	 * @param branch
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int findAllTotalByBranch(int branch,int status) throws Exception;
}
