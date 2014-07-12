package net.cedu.action.finance.invoicebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.InvoiceBookBiz;
import net.cedu.biz.finance.ReceiptBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.InvoiceBook;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json收据授权
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonInvoiceBookAction extends BaseAction {

	@Autowired
	private InvoiceBookBiz invoicebookbiz;              //票本Biz
	@Autowired
	private BranchBiz branchBiz;                        //学习中心Biz
	@Autowired
	private ReceiptBiz receiptBiz;
	private List<Branch> branchlst=new ArrayList<Branch>();
	private InvoiceBook invoicebook;                    //票本实体
	private int branchId;                               //使用机构
	private int status;                                 //是否授权
	private int invoiceBookNumber;                      //收据册数
	private int allTotal=0;                             //统计收据数
	private int allUseNumber=0;                         //统计使用数
	private int allInvalidNumber=0;                     //统计作废数
	private int allCancelNumber=0;                      //统计核销数
	private int surplus=0;                              //统计剩余数(不计算作废)
	private int unsurplus=0;                            //统计剩余数(计算作废)
	private int id;                                     //票本Id
	private String startNo;                             //开始号段
	private String endNo;                               //结束号段
	private int total;                                  //收据数
	private boolean bol;
	
	
	// 分页结果
	private PageResult<InvoiceBook> result = new PageResult<InvoiceBook>();
	
	
	/**
	 * 查询票本(数量)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countinvoicebook", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,branchId, status, result"
	}) })
	public String CountInvoiceBook() throws Exception {
		try {
				// 查询数量
				result.setRecordCount(invoicebookbiz.countInoviceBookAndReceiptByStatus(branchId, status, result));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return SUCCESS;
	}

	
	/**
	 * 查询票本(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listinvoicebook", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,branchId, status"
	}) })
	public String ListInvoiceBook() throws Exception {
		// 查询集合
		result.setList(invoicebookbiz.findInoviceBookAndReceiptByStatus(branchId, status, result,true));
		return SUCCESS;
	}
	
	
	/**
	 * 查询所有票本
	 * @return
	 * @throws Exception
	 */
	@Action(value = "alllistinvoicebook", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"branchId, status,allTotal,allUseNumber,allCancelNumber,allInvalidNumber,surplus,unsurplus"
	}) })
	public String allListInvoiceBook() throws Exception {
		allTotal = invoicebookbiz.findAllTotalByBranch(branchId, status);
		allUseNumber = receiptBiz.findAllCountByBranchAndStatusAndIsCannel(branchId, Constants.Fee_STATUS_TRUE, Constants.AUDIT_STATUS_INIT,status);
		allCancelNumber = receiptBiz.findAllCountByBranchAndStatusAndIsCannel(branchId, Constants.AUDIT_STATUS_INIT-1, Constants.Fee_STATUS_TRUE,status);
		allInvalidNumber = receiptBiz.findAllCountByBranchAndStatusAndIsCannel(branchId, Constants.AUDIT_STATUS_INIT, Constants.AUDIT_STATUS_INIT,status);
		surplus=allTotal-allUseNumber;  //不计算作废
		unsurplus=surplus-allInvalidNumber;
		return SUCCESS;
	}
	
	/**
	 * 查询未授权票本(数量)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countinvoicebookno", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,status"
	}) })
	public String CountInvoiceBookNo() throws Exception {
		try {
				// 查询数量
				result.setRecordCount(invoicebookbiz.countInoviceBookByStatus( status, result));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return SUCCESS;
	}

	/**
	 * 查询未授权票本(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listinvoicebookno", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"	
	}) })
	public String ListInvoiceBookNo() throws Exception {
		// 查询集合
		try {
			result.setList(invoicebookbiz.findInoviceBookByStatus(status, result,true));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有未授权票本
	 * @return
	 * @throws Exception
	 */
	@Action(value = "alllistinvoicebookno", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,status,invoiceBookNumber,allTotal"
	}) })
	public String AllListInvoiceBookNo() throws Exception {
		// 查询集合
		try {
			
			List<InvoiceBook> iblst=new ArrayList<InvoiceBook>();
			iblst=invoicebookbiz.findInoviceBookByStatus(status, result,false);
			if(iblst==null)
			{
				return SUCCESS;
			}else
			{
				invoiceBookNumber=iblst.size();
				for(int i=0;i<iblst.size();i++)
				{
					allTotal+=iblst.get(i).getTotal();
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	/**
	 * 授权
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updateinvoicebookno", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"	
	}) })
	public String UpdateInvoiceBookNo() throws Exception {
		// 授权
		try {
			InvoiceBook invoiceBook=invoicebookbiz.findInvoiceBookById(id);
			invoiceBook.setStatus(Constants.STATUS_AUTHOR_TRUE);
			invoiceBook.setUpdaterId(super.getUser().getUserId());
			invoiceBook.setUpdatedTime(new Date());
			invoiceBook.setUsedBy(branchId);
			invoicebookbiz.updateInvoiceBookById(invoiceBook);
			return SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 收据登记
	 * @return
	 * @throws Exception
	 */
	@Action(value = "addinvoicebookno", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"startNo,endNo,,total,bol"
			
	}) })
	public String AddInvoiceBookNo() throws Exception {
		// 收据登记
		try {
			InvoiceBook ib=invoicebookbiz.findInvoiceBookByStartNo(startNo+endNo);
			if(ib==null)
			{
				InvoiceBook invoicebook=new InvoiceBook();
				invoicebook.setStartNo(startNo+endNo);
				invoicebook.setEndNo(startNo+total);
				invoicebook.setTotal(total-Integer.parseInt(endNo)+1);
				invoicebook.setCreatorId(super.getUser().getUserId());
				invoicebook.setCreatedTime(new Date());
				bol=invoicebookbiz.addInvoiceBook(invoicebook,startNo,Integer.parseInt(endNo),total);
				//bol=true;
				return SUCCESS;
			}else
			{
				bol=false;
				
			}
			return SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	/**
	 * 查询使用机构
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listbranchinvoice", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"branchlst.*"
	}) })
	public String ListBranchInvoice() throws Exception {
		// 获取使用机构
		try {
			//branchlst=branchBiz.findListById(super.getUser().getOrgId());
			branchlst=branchBiz.findBranchForModel();
			return SUCCESS;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PageResult<InvoiceBook> getResult() {
		return result;
	}

	public void setResult(PageResult<InvoiceBook> result) {
		this.result = result;
	}

	public int getAllTotal() {
		return allTotal;
	}

	public void setAllTotal(int allTotal) {
		this.allTotal = allTotal;
	}

	public int getAllUseNumber() {
		return allUseNumber;
	}

	public void setAllUseNumber(int allUseNumber) {
		this.allUseNumber = allUseNumber;
	}

	public int getAllCancelNumber() {
		return allCancelNumber;
	}

	public void setAllCancelNumber(int allCancelNumber) {
		this.allCancelNumber = allCancelNumber;
	}

	public int getSurplus() {
		return this.allTotal-this.allUseNumber;
	}

	public void setSurplus(int surplus) {
		this.surplus =surplus;
	}

	public int getInvoiceBookNumber() {
		return invoiceBookNumber;
	}

	public void setInvoiceBookNumber(int invoiceBookNumber) {
		this.invoiceBookNumber = invoiceBookNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Branch> getBranchlst() {
		return branchlst;
	}

	public void setBranchlst(List<Branch> branchlst) {
		this.branchlst = branchlst;
	}

	public String getStartNo() {
		return startNo;
	}

	public void setStartNo(String startNo) {
		this.startNo = startNo;
	}

	public String getEndNo() {
		return endNo;
	}

	public void setEndNo(String endNo) {
		this.endNo = endNo;
	}

	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public InvoiceBook getInvoicebook() {
		return invoicebook;
	}

	public void setInvoicebook(InvoiceBook invoicebook) {
		this.invoicebook = invoicebook;
	}

	public int getAllInvalidNumber() {
		return allInvalidNumber;
	}

	public void setAllInvalidNumber(int allInvalidNumber) {
		this.allInvalidNumber = allInvalidNumber;
	}

	public int getUnsurplus() {
		return unsurplus;
	}

	public void setUnsurplus(int unsurplus) {
		this.unsurplus = unsurplus;
	}


	public boolean isBol() {
		return bol;
	}


	public void setBol(boolean bol) {
		this.bol = bol;
	}

	

	 
	
}
