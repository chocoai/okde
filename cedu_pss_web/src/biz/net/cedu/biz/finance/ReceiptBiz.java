package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.Receipt;
import net.cedu.model.page.PageResult;



/**
 * 收据  业务逻辑接口
 * 
 * @author gaole
 *
 */
public interface ReceiptBiz {
	
	
	
	
	/**
	 * 添加收据
	 * @param receipt
	 * @return
	 * @throws Exception
	 */
	public boolean addReceipt(Receipt receipt)throws Exception;
	
	
	/**
	 * 根据Id查询收据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Receipt findReceiptById(int id)throws Exception;
	
	/**
	 * 修改收据
	 * @param receipt
	 * @return
	 * @throws Exception
	 */
	public boolean updateReceiptById(Receipt receipt)throws Exception;
	
	/**
	 * 根据收据号段查询收据
	 * @param invoiceBookId
	 * @param status
	 * @param isCannel
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int countReceiptByInvoiceBookId(int invoiceBookId,int status,int isCannel,PageResult<Receipt> pr)throws Exception;
	

	/**
	 * 根据收据号段查询收据
	 * @param invoiceBookId
	 * @param status
	 * @param isCannel
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Receipt> findReceiptByInvoiceBookId(int invoiceBookId,int status,int isCannel,PageResult<Receipt> pr)throws Exception;
	
	
	/**
	 * 修改收据使用状态  返回值 1.修改成功  2.数据重复  3.数据不存在  4.操作失败
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public int updateReceiptStatusByCode(String code)throws Exception;
	
	
	/**
	 * 作废收据
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public boolean updateReceiptByCode(String code)throws Exception;
	
	/**
	 * 释放收据号
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public boolean updateReceiptStatusByCodeForShiFang(String code) throws Exception;
	
	/**
	 * 查询可以使用的收据
	 * @param code 收据编号
	 * @return
	 * @throws Exception
	 */
	public boolean findReceiptCanUsing(String code) throws Exception; 
	
	/**
	 * 使用收据
	 * @param code 收据编号
	 * @return
	 * @throws Exception
	 */
	public boolean updateReceiptUsedByPayment(String code) throws Exception;
	/**
	 * 
	 * @功能：通过收据code 字符串查询收据ID集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-12 下午01:35:01
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @return
	 * @throws Exception
	 */
	public Long [] findReceiptIdArrayByCodeIds(String codeIds)throws Exception;
	
	/**
	 * 根据机构、状态，是否核销查询全部的收据总数
	 * @param branch
	 * @param status
	 * @param isCannel
	 * @param invoiceBookStatus
	 * @return
	 * @throws Exception
	 */
	public int findAllCountByBranchAndStatusAndIsCannel(int branch,int status,int isCannel,int invoiceBookStatus) throws Exception;
}
