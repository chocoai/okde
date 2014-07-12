package net.cedu.action.finance.invoicebook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.ReceiptBiz;
import net.cedu.entity.finance.Receipt;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json收据查询
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonReceiptAction extends BaseAction {

	@Autowired
	private ReceiptBiz Receiptbiz;                      //票本Biz
	private int status;                                 //是否授权
	private int isCancel;                               //是否核销
	private int id;                                     //票本Id
	
	
	// 分页结果
	private PageResult<Receipt> result = new PageResult<Receipt>();
	
	
	/**
	 * 查询收据(数量)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countreceipt", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
	}) })
	public String CountReceipt() throws Exception {
		try {
				// 查询数量
				result.setRecordCount(Receiptbiz.countReceiptByInvoiceBookId(id, status, isCancel, result));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return SUCCESS;
	}

	

	/**
	 * 查询收据(数据)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listreceipt", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"	
	}) })
	public String ListReceipt() throws Exception {
		// 查询集合
		try {
			result.setList(Receiptbiz.findReceiptByInvoiceBookId(id, status, isCancel, result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	

	/**
	 * 修改收据
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updatereceipt", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"	
	}) })
	public String UpdateReceipt() throws Exception {
		// 修改收据
		try {
			Receipt receipt=Receiptbiz.findReceiptById(id);
			if(receipt!=null)
			{
				receipt.setStatus(status);
				receipt.setUpdatedTime(new Date());
				receipt.setUpdaterId(super.getUser().getUserId());
				Receiptbiz.updateReceiptById(receipt);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	

	/**
	 * 修改收据
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updatereceiptiscancel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"	
	}) })
	public String UpdateReceiptIscancel() throws Exception {
		// 修改收据
		try {
			Receipt receipt=Receiptbiz.findReceiptById(id);
			receipt.setIsCanceled(isCancel);
			receipt.setUpdatedTime(new Date());
			receipt.setUpdaterId(super.getUser().getUserId());
			Receiptbiz.updateReceiptById(receipt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(int isCancel) {
		this.isCancel = isCancel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PageResult<Receipt> getResult() {
		return result;
	}

	public void setResult(PageResult<Receipt> result) {
		this.result = result;
	}
	
	
}
