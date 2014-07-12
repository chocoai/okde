package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.InvoiceBook;
import net.cedu.entity.finance.InvoiceManagement;
import net.cedu.model.page.PageResult;






/**
 * 发票管理  业务逻辑接口
 * 
 * @author gaole
 *
 */
public interface InvoiceManagementBiz {
	
	
	/**
	 * 发票登记
	 * @param invoicemanagement
	 * @return
	 * @throws Exception
	 */
	public boolean addInvoiceManagement(InvoiceManagement invoicemanagement)throws Exception;
	
	
	/**
	 * 按照发票号查询
	 * @param invoiceCode
	 * @return
	 * @throws Exception
	 */
	public InvoiceManagement findInvoiceManagementByInvoiceCode(String invoiceCode)throws Exception;
	
	/**
	 * 修改发票
	 * @param invoicemanagement
	 * @return
	 * @throws Exception
	 */
	public boolean updateInvoiceManagement(InvoiceManagement invoicemanagement)throws Exception;
	
	/**
	 * 按照ID查发票
	 * @return
	 * @throws Exception
	 */
	public InvoiceManagement findInvoiceManagementById(int id)throws Exception;
	
	
	/**
	 * 查询发票按学习中心(分页数量)
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public int countInvoiceManagementByBranchId(int branchId,int isPost,PageResult<InvoiceManagement> pr)throws Exception;
	
	/**
	 * 查询发票按学习中心(分页集合)
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceManagement> findInvoiceManagementByBranchId(int branchId,int isPost,PageResult<InvoiceManagement> pr)throws Exception;
	
	
	
	

	
	/**
	 * 查询发票的缴费单
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceManagement> findInvoiceManagementByInvoiceIds(String invoiceIds,PageResult<InvoiceManagement> pr)throws Exception;
	
	
	
	/**
	 * 查询(已)未签领发票(分页集合)
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceManagement> findInvoiceManagementByParams(int branchId,String studentIds,String postalNo,String invoiceNo,String feePaymentNo,int status,PageResult<InvoiceManagement> pr)throws Exception;
	
	
	/**
	 * 查询(已)未签领发票(分页数量)
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int countInvoiceManagementByParams(int branchId,String studentIds,String postalNo,String invoiceNo,String feePaymentNo,int status,PageResult<InvoiceManagement> pr)throws Exception;
	
	
	/**
	 * 查询发票按学生Id
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public List<InvoiceManagement> findInvoiceManagementByStudentId(int studentId)throws Exception;
	
	
	
}
