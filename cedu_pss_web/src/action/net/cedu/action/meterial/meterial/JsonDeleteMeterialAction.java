package net.cedu.action.meterial.meterial;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 删除 
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteMeterialAction extends BaseAction 
{
	private static final long serialVersionUID = -2159351755560754424L;
	
	@Autowired
	private MeterialBiz meterialBiz; //物料业务层
	
	private int id; //物料id
	private boolean results=false;
	
	@Action(value = "delete_meterial", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	//物料删除ajax方法
	public String execute()
	{
		try
		{
			meterialBiz.deleteMeterial(id);
			results=true;
		}
		catch(Exception e)
		{e.printStackTrace();}
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
