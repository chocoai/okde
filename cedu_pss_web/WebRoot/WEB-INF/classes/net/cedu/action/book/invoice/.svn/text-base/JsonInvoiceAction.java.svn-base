package net.cedu.action.book.invoice;

import java.util.ArrayList;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.InvoiceBiz;
import net.cedu.entity.book.Invoice;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 发货分页列表
 */
@ParentPackage("json-default")
public class JsonInvoiceAction extends BaseAction implements ModelDriven<Invoice>{
 
	private static final long serialVersionUID = 6960447948381437327L;

	
	@Autowired
	private InvoiceBiz invoiceBiz; // 发货业务逻辑
	private List<Invoice> list = new ArrayList<Invoice>(); // 发货集合
	PageResult<Invoice> result = new PageResult<Invoice>(); // 分頁參數

	private Invoice invoice = new Invoice(); // 发货实体
	
	/*
	 * 分页 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	// @SuppressWarnings("unchecked")
	@Action(value = "list_invoice", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {

		try {
			list = invoiceBiz.findInvoicePageListByConditions(invoice, result);
			result.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	 * 查询显示行数
	 */
	@Action(value = "count_invoice", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String count() {
		try {
			result.setRecordCount(invoiceBiz
					.findInvoicePageCountByConditions(invoice, result));

		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public List<Invoice> getList() {
		return list;
	}

	public void setList(List<Invoice> list) {
		this.list = list;
	}

	public PageResult<Invoice> getResult() {
		return result;
	}

	public void setResult(PageResult<Invoice> result) {
		this.result = result;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Invoice getModel() {
		 
		return invoice;
	}
	
}
