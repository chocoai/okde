package net.cedu.action.meterial.meterialusagerecord;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialUsageRecordBiz;
import net.cedu.entity.meterial.MeterialUsageRecord;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 个人领用
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonMeterialUsagerecordAction extends BaseAction implements ModelDriven<MeterialUsageRecord> {

	private static final long serialVersionUID = 57511150979983262L;

	@Autowired
	private MeterialUsageRecordBiz meterialusagerecordbiz; //个人领用业务层

	PageResult<MeterialUsageRecord> result = new PageResult<MeterialUsageRecord>(); //分页参数

	private MeterialUsageRecord meterialusagerecord=new MeterialUsageRecord();  //个人领用实体

	private List<MeterialUsageRecord> list = new ArrayList<MeterialUsageRecord>(); //个人领用集合


	/*
	 * 分页 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "list_meterialusagerecord", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {

		try {
			list = meterialusagerecordbiz
					.findMeterialUsageRecordPageListByCodeApplication(meterialusagerecord, result);
 

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
	@Action(value = "count_meterialusagerecord", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String count() {
		try {
			result.setRecordCount(meterialusagerecordbiz
	.findMeterialUsageRecordPageCountByCodeApplication(meterialusagerecord, result));
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	
	public MeterialUsageRecord getModel() {
		return meterialusagerecord;
	}
 

	public PageResult<MeterialUsageRecord> getResult() {
		return result;
	}

	public void setResult(PageResult<MeterialUsageRecord> result) {
		this.result = result;
	}

	public MeterialUsageRecord getMeterialusagerecord() {
		return meterialusagerecord;
	}

	public void setMeterialusagerecord(MeterialUsageRecord meterialusagerecord) {
		this.meterialusagerecord = meterialusagerecord;
	}

	public List<MeterialUsageRecord> getList() {
		return list;
	}

	public void setList(List<MeterialUsageRecord> list) {
		this.list = list;
	}
}
