package net.cedu.action.meterial.meterialstock;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialTransferBiz;
import net.cedu.entity.meterial.MeterialTransfer;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 移库分页
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonMeterialTransferAction extends BaseAction implements ModelDriven<MeterialTransfer>    {

	private static final long serialVersionUID = 2896388062903923861L;

	@Autowired
	private MeterialTransferBiz meterialTransferBiz; // 移庫业务逻辑
	private List<MeterialTransfer> list = new ArrayList<MeterialTransfer>(); // 移库集合
	PageResult<MeterialTransfer> result = new PageResult<MeterialTransfer>(); // 分頁參數

	private MeterialTransfer meterialtransfer = new MeterialTransfer(); // 移库实体


	
	

	/*
	 * 分页 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	// @SuppressWarnings("unchecked")
	@Action(value = "list_meterialtransfer", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {

		try {
			list = meterialTransferBiz.findMeterialTransferByParams(
					meterialtransfer, result);
			result.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	/*
	 * 查询显示行数
	 */
	@Action(value = "count_meterialtransfer", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String count() {
		try {
			result.setRecordCount(meterialTransferBiz
					.countMeterialTransferByParams(meterialtransfer, result));

		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	 
 

	public PageResult<MeterialTransfer> getResult() {
		return result;
	}

	public void setResult(PageResult<MeterialTransfer> result) {
		this.result = result;
	}

	public MeterialTransfer getMeterialtransfer() {
		return meterialtransfer;
	}

	public void setMeterialtransfer(MeterialTransfer meterialtransfer) {
		this.meterialtransfer = meterialtransfer;
	}

	public List<MeterialTransfer> getList() {
		return list;
	}

	public void setList(List<MeterialTransfer> list) {
		this.list = list;
	}

	public MeterialTransfer getModel() {
	 
		return meterialtransfer;
	}

 
	
	
}
