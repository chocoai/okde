package net.cedu.action.meterial.meterialpurchase;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialPurchaseBiz;
import net.cedu.entity.meterial.MeterialPurchase;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 总部采购
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonMeterialPurchaseAction extends BaseAction implements
		ModelDriven<MeterialPurchase> {

	private static final long serialVersionUID = 57511150979983262L;

	@Autowired
	private MeterialPurchaseBiz meterialpurchasebiz; //总部采购业务层

	PageResult<MeterialPurchase> result = new PageResult<MeterialPurchase>(); //分页参数

	private MeterialPurchase meterialpurchase = new MeterialPurchase(); //总部采购实体

	private List<MeterialPurchase> list = new ArrayList<MeterialPurchase>(); //总部采购集合

	/*
	 * 分页 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	//@SuppressWarnings("unchecked")
	@Action(value = "list_meterialpurchase", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {

		try {
			list = meterialpurchasebiz
					.findMeterialPurchasePageListByCodeApplication(
							meterialpurchase, result);
 
			result.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

 
	/**
	 * 分页数量
	 * @return SUCCESS
	 */
	@Action(value = "count_meterialpurchase", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String count() {
		try {
			result.setRecordCount(meterialpurchasebiz
					.findMeterialPurchasePageCountByCodeApplication(
							meterialpurchase, result));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public PageResult<MeterialPurchase> getResult() {
		return result;
	}



	public void setResult(PageResult<MeterialPurchase> result) {
		this.result = result;
	}



	public MeterialPurchase getMeterialpurchase() {
		return meterialpurchase;
	}



	public void setMeterialpurchase(MeterialPurchase meterialpurchase) {
		this.meterialpurchase = meterialpurchase;
	}



	public List<MeterialPurchase> getList() {
		return list;
	}



	public void setList(List<MeterialPurchase> list) {
		this.list = list;
	}



	public MeterialPurchase getModel() {
		return meterialpurchase;
	}

}
