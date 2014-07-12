package net.cedu.action.meterial.meterialcategory;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;
import net.cedu.entity.meterial.MeterialStoreroom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 库房设置-修改
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonUpdatemeterialStoreroomAction extends BaseAction {
 
	private static final long serialVersionUID = -7560930628861011694L;

	@Autowired
	private MeterialStoreroomBiz meterialStoreroomBiz; //库房设置业务层
	
	private MeterialStoreroom meterialstoreroom=new MeterialStoreroom(); //库房设置实体
 
	private boolean results=false; //参数
	
	@Action(value="update_meterialstoreroom",
			results={@Result(name="success",type="json",params={
				"contentType","text/json",
				"includeproperties","results"
			})})
	public String execute()
	{
		try {
			if(null!=meterialstoreroom)
			{
				if(meterialstoreroom.getId()!=0)
				{
					//更新库房设置
					MeterialStoreroom ms=meterialStoreroomBiz.findMeterialStoreroomById(meterialstoreroom.getId());
					ms.setUpdaterId(this.getUser().getUserId());
					ms.setName(meterialstoreroom.getName());
					ms.setCode(meterialstoreroom.getCode());
					ms.setTypes(meterialstoreroom.getTypes());
					ms.setPosition(meterialstoreroom.getPosition());
					ms.setUpdatedTime(new Date());
					meterialStoreroomBiz.updateMeterialStoreroom(ms);
					results=true;
				}			
			}
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}



	public MeterialStoreroom getMeterialstoreroom() {
		return meterialstoreroom;
	}



	public void setMeterialstoreroom(MeterialStoreroom meterialstoreroom) {
		this.meterialstoreroom = meterialstoreroom;
	}



 
	
}
