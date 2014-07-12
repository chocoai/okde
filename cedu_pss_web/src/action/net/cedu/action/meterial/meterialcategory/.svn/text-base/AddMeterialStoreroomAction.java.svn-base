package net.cedu.action.meterial.meterialcategory;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;
import net.cedu.entity.meterial.MeterialStoreroom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 增加库房设置
 * @author YY
 *
 */
public class AddMeterialStoreroomAction extends BaseAction {
 
	private static final long serialVersionUID = 1798769981138082899L;

	@Autowired
	private MeterialStoreroomBiz  meterialStoreroomBiz; //物料库房设置业务层
 
	private MeterialStoreroom meterialstoreroom=new MeterialStoreroom(); //物料库房设置
	
	private boolean bool=true;  //判断参数
	
	@Action(value="add_meterialstoreroom",results={@Result(name="success",type="redirect",location="index_meterialstoreroom"),
													@Result(name="input",location="on.jsp")})
	public String execute()
	{
		
	 try {
		 if(null!=meterialstoreroom)
		 {	
 
			 //更新物料库房设置
			 meterialstoreroom.setDeleteFlag(0);
			 meterialstoreroom.setCreatorId(this.getUser().getUserId());
			 meterialstoreroom.setUpdaterId(this.getUser().getUserId());
			 meterialstoreroom.setUpdatedTime(new Date());		 
			 //meterialStoragebiz.FinMeterialStorageByName(meterialstorage.getName());
			//判断是否重命名
			 MeterialStoreroom ms=meterialStoreroomBiz.findMeterialMeterialStoreroomByName(meterialstoreroom.getName(),meterialstoreroom.getCode());
			 if(null!=ms)
			 {
				 return INPUT;
			 }else
			 {
			 meterialStoreroomBiz.saveMeterialStoreroom(meterialstoreroom);
			 }
		 }
	} catch (Exception e) {
		e.printStackTrace();
		return INPUT;
	}		
		return SUCCESS;		
   }
 

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}


	public MeterialStoreroom getMeterialstoreroom() {
		return meterialstoreroom;
	}


	public void setMeterialstoreroom(MeterialStoreroom meterialstoreroom) {
		this.meterialstoreroom = meterialstoreroom;
	}

 


 
 
}
