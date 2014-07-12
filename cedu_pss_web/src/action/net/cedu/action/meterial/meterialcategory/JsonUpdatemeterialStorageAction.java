package net.cedu.action.meterial.meterialcategory;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialStorageBiz;
import net.cedu.entity.meterial.MeterialStorage;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 入库方式-修改
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonUpdatemeterialStorageAction extends BaseAction {
 
	private static final long serialVersionUID = -1384231622723491156L;

	@Autowired
	private MeterialStorageBiz meterialStoragebiz; //物料入库方式业务层
	
	private MeterialStorage meterialstorage =new MeterialStorage(); //物料入库方式实体
	
	private boolean results=false; //判断参数
	
	@Action(value="update_meterialstorage",
			results={@Result(name="success",type="json",params={
				"contentType","text/json",
				"includeproperties","results"
			})})
	public String execute()
	{
		try {
			if(null!=meterialstorage)
			{
				if(meterialstorage.getId()!=0)
				{
					//查询入库方式实体并赋值
					MeterialStorage ms=meterialStoragebiz.findMeterialStorageById(meterialstorage.getId());
					ms.setUpdaterId(this.getUser().getUserId());
					ms.setName(meterialstorage.getName());
					ms.setCode(meterialstorage.getCode());
					ms.setUpdatedTime(new Date());
					//更新入库方式
					meterialStoragebiz.updateMeterialStoryage(ms);
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



	public MeterialStorage getMeterialstorage() {
		return meterialstorage;
	}



	public void setMeterialstorage(MeterialStorage meterialstorage) {
		this.meterialstorage = meterialstorage;
	}
 
	
	
}
