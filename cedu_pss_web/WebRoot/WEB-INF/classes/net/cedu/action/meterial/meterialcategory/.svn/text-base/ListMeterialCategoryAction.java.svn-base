package net.cedu.action.meterial.meterialcategory;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.entity.meterial.MeterialCategory;

public class ListMeterialCategoryAction extends BaseAction {

	private static final long serialVersionUID = 8334881493374850967L;

	@Autowired
	private MeterialCategoryBiz meterialcategorybiz;
	
	private List<MeterialCategory> list=new ArrayList<MeterialCategory>();
	
	@Action(value="list_meterialcategory",results={@Result(name="success",location="list_meterialcategory.jsp")})
	public String execute()
	{
		try {
			list=meterialcategorybiz.findMeterialCategoryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<MeterialCategory> getList() {
		return list;
	}

	public void setList(List<MeterialCategory> list) {
		this.list = list;
	}
	
	
}
