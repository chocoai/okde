package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.MeasuringUnitBiz;
/**
 * 删除计量单位
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteMeasuringUnitAction extends BaseAction {
	 
	private static final long serialVersionUID = 9101510135155256431L;

	@Autowired
	private MeasuringUnitBiz measuringunitbiz;
	
	private int id;
	private boolean results;
	
	@Action(value="delete_measuringunit",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			measuringunitbiz.deleteMeasuringUnitById(id);
			results=true;
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}
	
	
	
}
