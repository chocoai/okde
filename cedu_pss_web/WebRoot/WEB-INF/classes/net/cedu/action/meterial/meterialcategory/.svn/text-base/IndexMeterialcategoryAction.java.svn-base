package net.cedu.action.meterial.meterialcategory;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.entity.meterial.MeterialCategory;
/**
 * 物料分类查询全部
 * @author YY
 *
 */
public class IndexMeterialcategoryAction extends BaseAction{
 
	private static final long serialVersionUID = -2839470116734873100L;

	@Autowired
	private MeterialCategoryBiz meterialcategorybiz; //物料分类业务层
	
	List<MeterialCategory> list= new ArrayList<MeterialCategory>(); //物料分类集合
	private boolean bool ;

	@Action(value="index_meterialcategory",results={@Result(name="success",location="index_meterialcategory.jsp")})
	public String execute(){
		
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

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	
}
