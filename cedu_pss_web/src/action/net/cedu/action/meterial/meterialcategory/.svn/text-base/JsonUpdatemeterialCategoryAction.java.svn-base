package net.cedu.action.meterial.meterialcategory;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.entity.meterial.MeterialCategory;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 物料类型-修改
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonUpdatemeterialCategoryAction extends BaseAction {
 
	private static final long serialVersionUID = 2332103091531315481L;

	@Autowired
	private MeterialCategoryBiz meterialcategorybiz; //物料分类业务层
	
	private MeterialCategory meterialcategory=new MeterialCategory(); //物料分页实体 
	
	private boolean results=false; //判断参数
	
	@Action(value="update_meterialcategory",
			results={@Result(name="success",type="json",params={
				"contentType","text/json",
				"includeproperties","results"
			})})
	public String execute()
	{
		try {
			if(null!=meterialcategory)
			{
				if(meterialcategory.getId()!=0)
				{
			        //查询物料分类实体 并赋值 
					MeterialCategory mc=meterialcategorybiz.findMeterialCategoryById(meterialcategory.getId());
					mc.setUpdaterId(this.getUser().getUserId());
					mc.setName(meterialcategory.getName());
					mc.setCode(meterialcategory.getCode());
					mc.setUpdatedTime(new Date());
					//更新物料分类实体
					meterialcategorybiz.updateMeterialCategory(mc);
					results=true;
				}
				
			}
			
			
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public MeterialCategory getMeterialcategory() {
		return meterialcategory;
	}

	public void setMeterialcategory(MeterialCategory meterialcategory) {
		this.meterialcategory = meterialcategory;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}

	 
	
	
}
