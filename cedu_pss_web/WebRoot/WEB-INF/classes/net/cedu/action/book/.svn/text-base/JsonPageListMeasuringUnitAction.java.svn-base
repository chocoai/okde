package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.MeasuringUnitBiz;
import net.cedu.entity.book.MeasuringUnit;
import net.cedu.model.page.PageResult;
/**
 * 查询计量单位列表_分页
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageListMeasuringUnitAction extends BaseAction {
 
	private static final long serialVersionUID = 4568523479740067990L;

	@Autowired
	private MeasuringUnitBiz measuringUnitbiz;
	
	private PageResult<MeasuringUnit> result = new PageResult<MeasuringUnit>();
	
	@Action(value="page_list_measuringunit",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()
	{
		try {
			result.setList(measuringUnitbiz.findAllMeasuringUnit(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<MeasuringUnit> getResult() {
		return result;
	}

	public void setResult(PageResult<MeasuringUnit> result) {
		this.result = result;
	}
	
	

	
}
