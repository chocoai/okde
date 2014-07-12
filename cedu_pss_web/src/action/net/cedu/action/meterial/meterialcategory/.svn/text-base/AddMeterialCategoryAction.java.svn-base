package net.cedu.action.meterial.meterialcategory;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.entity.meterial.MeterialCategory;

/**
 * 增加物料分类
 * @author YY
 *
 */
public class AddMeterialCategoryAction extends BaseAction {
 
	private static final long serialVersionUID = 2312334477057652909L;

	@Autowired
	private MeterialCategoryBiz  meterialcategorybiz; //物料分类业务层
	
	private MeterialCategory meterialcategory=new MeterialCategory();//物料分类实体  
	
	private boolean bool;//判断参数
	
	@Action(value="add_meterialcategory",results={@Result(name="success",type="redirect",location="index_meterialcategory"),
												  @Result(name="input",location="on.jsp")})
	public String execute()
	{
		
	 try {
		 if(this.isGetRequest())
		 {
			 return INPUT;
		 }
		 if(null!=meterialcategory){
			 //为物料分类赋值
			 meterialcategory.setDeleteFlag(0);
			 meterialcategory.setCreatorId(this.getUser().getUserId());
			 meterialcategory.setUpdaterId(this.getUser().getUserId());
			 meterialcategory.setUpdatedTime(new Date());
			 MeterialCategory meterialCategory=meterialcategorybiz.findByMeterialCategoryNameAndMeterialCategoryCode(meterialcategory.getName(),meterialcategory.getCode());
			 if(meterialCategory==null){
				 meterialcategorybiz.addMeterialCategory(meterialcategory);
			}else{
				  
				 return INPUT;
			}
		 }else{
			 
			 return INPUT;
		 }
	} catch (Exception e) {
		e.printStackTrace();
		 
		return INPUT;
	}
		
		return SUCCESS;
		
	}

	public MeterialCategory getMeterialcategory() {
		return meterialcategory;
	}

	public void setMeterialcategory(MeterialCategory meterialcategory) {
		this.meterialcategory = meterialcategory;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	
}
