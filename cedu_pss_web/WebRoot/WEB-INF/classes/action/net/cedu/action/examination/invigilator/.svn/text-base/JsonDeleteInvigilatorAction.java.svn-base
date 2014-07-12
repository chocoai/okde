package net.cedu.action.examination.invigilator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorBiz;

/**
 * 删除监考老师
 * @author panyuheng
 *
 */
@ParentPackage("json-default")
public class JsonDeleteInvigilatorAction extends BaseAction {

	private static final long serialVersionUID = -2159351755560754424L;
	
	@Autowired
	private InvigilatorBiz invigilatorbiz;
	
	private int id;
	private boolean results=false;

	public boolean isResults() {
		return results;
	}


	public void setResults(boolean results) {
		this.results = results;
	}


	@Action(value = "delete_invigilator", 
			results = { @Result(name = "success", type = "json", params = {
					"contentType", "text/json"
					}) })
	public String DeleteInvigilator()
	{
		try
	{
		Object obj=invigilatorbiz.deleteInvigilator(id);
		if(null!=obj)
		{
				results=true;
			}else
		{
				results=false;
			}
			
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
}
