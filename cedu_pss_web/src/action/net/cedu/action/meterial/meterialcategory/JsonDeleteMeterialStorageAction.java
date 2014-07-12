package net.cedu.action.meterial.meterialcategory;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialStorageBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 删除入库方式
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteMeterialStorageAction extends BaseAction {
 
	private static final long serialVersionUID = -8114240548658117012L;
	@Autowired
	private MeterialStorageBiz meterialStoragebiz; //入库方式业务层
	private int id; //入库方式id
	private boolean results; //判断参数
	
	@Action(value="delete_meterialstorage",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			//删除ajax方法
			meterialStoragebiz.deleteMeterialStorage(id);
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
