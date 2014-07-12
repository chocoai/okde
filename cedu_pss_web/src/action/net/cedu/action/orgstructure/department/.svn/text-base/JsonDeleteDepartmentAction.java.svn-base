package net.cedu.action.orgstructure.department;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.DepartmentBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除部门
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonDeleteDepartmentAction extends BaseAction
{
	private static final long serialVersionUID = 3251241713100596348L;

	@Autowired
	private DepartmentBiz departmentBiz;
	
	private boolean results=false;
	private int id;
	
	@Action(value = "delete_department", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			departmentBiz.deleteConfigById(id);
			results=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return SUCCESS;
	}

	public boolean getResults() {
		return results;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
