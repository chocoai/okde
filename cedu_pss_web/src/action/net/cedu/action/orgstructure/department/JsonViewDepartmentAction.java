package net.cedu.action.orgstructure.department;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.entity.orgstructure.Department;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询部门
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewDepartmentAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private DepartmentBiz departmentBiz;
	
	private Department department=null;
	
	private int id=0;
	
	@Action(value = "view_department", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			department=departmentBiz.findDepartmentById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}



	public void setId(int id) {
		this.id = id;
	}
}
