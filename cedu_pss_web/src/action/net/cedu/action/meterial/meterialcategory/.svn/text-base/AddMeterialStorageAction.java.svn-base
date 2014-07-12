package net.cedu.action.meterial.meterialcategory;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialStorageBiz;
import net.cedu.entity.meterial.MeterialStorage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 入库方式增加
 * @author YY
 *
 */
public class AddMeterialStorageAction extends BaseAction {
 
	private static final long serialVersionUID = -5846687063924189781L;

	@Autowired
	private MeterialStorageBiz  meterialStoragebiz;  //物料入库方式业务层
	
	private MeterialStorage meterialstorage=new MeterialStorage(); //物料入库方式实体
	
	private boolean bool=true;
	
	@Action(value="add_meterialstorage",results={@Result(name="success",type="redirect",location="index_meterialstorage")})
	public String execute()
	{
		
	 try {
		 if(null!=meterialstorage)
		 {
		//赋值
		 meterialstorage.setDeleteFlag(0);
		 meterialstorage.setCreatorId(this.getUser().getUserId());
		 meterialstorage.setUpdaterId(this.getUser().getUserId());
		 meterialstorage.setUpdatedTime(new Date());		 
		 //meterialStoragebiz.FinMeterialStorageByName(meterialstorage.getName());
		 MeterialStorage ms=meterialStoragebiz.findmeterialStorageByName(meterialstorage.getName(),meterialstorage.getCode());
		 if(null!=ms)
		 {
			 return INPUT;
		 }else
		 {
		 meterialStoragebiz.saveMeterialStorage(meterialstorage);
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


	public MeterialStorage getMeterialstorage() {
		return meterialstorage;
	}


	public void setMeterialstorage(MeterialStorage meterialstorage) {
		this.meterialstorage = meterialstorage;
	}

 
}
