package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.MeasuringUnitBiz;
import net.cedu.entity.book.MeasuringUnit;
/**
 * 计量单位修改
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateMeasuringUnitAction extends BaseAction {
 
	private static final long serialVersionUID = -6312472425846404056L;

	@Autowired
	private MeasuringUnitBiz measuringunitbiz;
	
	private MeasuringUnit measuringunit;
	
	private boolean results=false;
	
	@Action(value="update_measuringunit",
			results={@Result(name="success",type="json",params={
				"contentType","text/json",
				"includeproperties","results"
			})})
	public String execute()
	{
		try {
			if(measuringunit!=null)
			{
				if(measuringunit.getId()!=0)
				{
					MeasuringUnit mu=measuringunitbiz.findMeasuringUnitById(measuringunit.getId());
					mu.setUpdatedTime(new Date());
					mu.setUpdater_id(this.getUser().getUserId());
					mu.setCode(measuringunit.getCode());
					mu.setName(measuringunit.getName());
					MeasuringUnit unit=measuringunitbiz.findByNameOrCodeMeasuringUnit(measuringunit.getCode(), measuringunit.getName());
					if(null==unit)
					{
					measuringunitbiz.updateMeasuringUnit(mu);
					results=true;
					}else
					{
						results=false;
					}
				}
			}
			measuringunitbiz.updateMeasuringUnit(measuringunit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public MeasuringUnit getMeasuringunit() {
		return measuringunit;
	}

	public void setMeasuringunit(MeasuringUnit measuringunit) {
		this.measuringunit = measuringunit;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}
	
}
