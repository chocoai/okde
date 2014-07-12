package net.cedu.action.meterial.meterialcategory;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.entity.meterial.MeterialCategory;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询物料分类列表_分页
 * 
 * @author YY
 * 
 */
@ParentPackage("json-default")
public class JsonMeterialCategoryAction extends BaseAction {
 
 
	private static final long serialVersionUID = -4081474673514676416L;

	@Autowired
	private MeterialCategoryBiz meterialcategorybiz;  //物料分类业务层

	private PageResult<MeterialCategory> result = new PageResult<MeterialCategory>(); //分页参数

	@Action(value = "page_list_meterialcategory", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	
		/**
		 * 分页集合
		 */
		public String execute() {
		try {
			
			result.setList(meterialcategorybiz
					.findAllMeterialCategoryList(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	 * 根据条件查询教材分类数量
	 */
	@Action(value = "page_count_meterialcategory", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String count() throws Exception {
		result.setRecordCount(meterialcategorybiz
				.findAllMeterialCategoryCount(result));
		return SUCCESS;
	}

	public PageResult<MeterialCategory> getResult() {
		return result;
	}

	public void setResult(PageResult<MeterialCategory> result) {
		this.result = result;
	}

}
