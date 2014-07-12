package net.cedu.action.meterial.meterialapplication;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialApplicationBiz;
import net.cedu.entity.meterial.MeterialApplication;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 中心申请分页
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonMeterialApplicationAction extends BaseAction implements ModelDriven<MeterialApplication> {

	private static final long serialVersionUID = 57511150979983262L;

	@Autowired
	private MeterialApplicationBiz meterialappbiz; //中心申请业务层

	PageResult<MeterialApplication> result = new PageResult<MeterialApplication>();  //分页参数

	private MeterialApplication meterialapplication = new MeterialApplication(); //中心申请实体

	private List<MeterialApplication> list = new ArrayList<MeterialApplication>(); //中心实体集合

	/*
	 * 分页 (集合)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Action(value = "list_meterialapplication", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {

		try {
			list = meterialappbiz
					.findMeterialApplicationPageListByCodeApplication(
							meterialapplication, result);

			 
			result.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	 * 查询显示行数 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Action(value = "count_meterialapplication", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String count() {
		try {
			result.setRecordCount(meterialappbiz
					.findMeterialApplicationPageCountByCodeApplication(
							meterialapplication, result));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<MeterialApplication> getResult() {
		return result;
	}

	public void setResult(PageResult<MeterialApplication> result) {
		this.result = result;
	}

	public MeterialApplication getMeterialapplication() {
		return meterialapplication;
	}

	public void setMeterialapplication(MeterialApplication meterialapplication) {
		this.meterialapplication = meterialapplication;
	}

	public MeterialApplication getModel() {
		 
		return meterialapplication;
	}

}
