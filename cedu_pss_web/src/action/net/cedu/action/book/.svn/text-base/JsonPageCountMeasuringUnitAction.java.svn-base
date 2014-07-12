package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.MeasuringUnitBiz;
import net.cedu.entity.book.MeasuringUnit;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询计量单位条数_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageCountMeasuringUnitAction extends BaseAction {
 
	private static final long serialVersionUID = -5901806525820899397L;

	@Autowired
	private MeasuringUnitBiz measuringunitbiz;
	
	private PageResult<MeasuringUnit> result = new PageResult<MeasuringUnit>();
	/**
	 * 根据条件查询教材分类数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_measuringunit", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(measuringunitbiz.findAllMeasuringUnitCount(result));
		return SUCCESS;
	}
	public PageResult<MeasuringUnit> getResult() {
		return result;
	}
	public void setResult(PageResult<MeasuringUnit> result) {
		this.result = result;
	}
	
}
