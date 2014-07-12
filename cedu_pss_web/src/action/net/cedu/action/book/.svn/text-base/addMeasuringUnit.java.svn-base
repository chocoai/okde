package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.biz.book.MeasuringUnitBiz;
import net.cedu.entity.book.BookCategory;
import net.cedu.entity.book.MeasuringUnit;

/**
 * 添加计量单位
 * @author XFY
 *
 */
public class addMeasuringUnit extends BaseAction {
	@Autowired
	private MeasuringUnitBiz measuringunitbiz;
	
	private MeasuringUnit measuringunit;
	
	private String resultiterate; //结果
	
	@Action(value="add_measuringunit",results={@Result(type="redirect",location="index_measuring_unit"),@Result(name="input",location="../book/error.jsp")})
	
	public String execute()
	{
		
		try {
			measuringunit.setCreatorId(this.getUser().getUserId());
			measuringunit.setCreatedTime(new Date());
			MeasuringUnit unit=measuringunitbiz.findByNameOrCodeMeasuringUnit(measuringunit.getCode(), measuringunit.getName());
			if(null==unit)
			{
			measuringunitbiz.addMeasuringUnit(measuringunit);
			}else
			{
				return INPUT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	

	public MeasuringUnit getMeasuringunit() {
		return measuringunit;
	}



	public void setMeasuringunit(MeasuringUnit measuringunit) {
		this.measuringunit = measuringunit;
	}



	public String getResultiterate() {
		return resultiterate;
	}

	public void setResultiterate(String resultiterate) {
		this.resultiterate = resultiterate;
	}
	
	
	
}
