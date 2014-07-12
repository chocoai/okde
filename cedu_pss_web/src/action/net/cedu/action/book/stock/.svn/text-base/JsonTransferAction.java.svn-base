package net.cedu.action.book.stock;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StockTransferBiz;
import net.cedu.entity.book.StockTransfer;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
public class JsonTransferAction extends BaseAction implements ModelDriven<StockTransfer>    {
 
	private static final long serialVersionUID = 1975976651889883895L;
	@Autowired
	private StockTransferBiz stockTransferBiz; // 移庫业务逻辑
	private List<StockTransfer> list = new ArrayList<StockTransfer>(); // 移库集合
	PageResult<StockTransfer> result = new PageResult<StockTransfer>(); // 分頁參數

	private StockTransfer stockTransfer = new StockTransfer(); // 移库实体
 
	/*
	 * 分页 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	// @SuppressWarnings("unchecked")
	@Action(value = "list_stocktransfer", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {

		try {
			list = stockTransferBiz.findStockTransferByParams(stockTransfer, result);
			result.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	 * 查询显示行数
	 */
	@Action(value = "count_stocktransfer", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String count() {
		try {
			result.setRecordCount(stockTransferBiz
					.countStockTransferByParams(stockTransfer, result));

		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public List<StockTransfer> getList() {
		return list;
	}

	public void setList(List<StockTransfer> list) {
		this.list = list;
	}

	public PageResult<StockTransfer> getResult() {
		return result;
	}

	public void setResult(PageResult<StockTransfer> result) {
		this.result = result;
	}

	public StockTransfer getStockTransfer() {
		return stockTransfer;
	}

	public void setStockTransfer(StockTransfer stockTransfer) {
		this.stockTransfer = stockTransfer;
	}

	public StockTransfer getModel() {
		 
		return stockTransfer;
	}

	 
 

 
	
	
}
