package net.cedu.action.orgstructure.department;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.orgstructure.Department;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改部门
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateDepartmentAction extends BaseAction implements ModelDriven<Department>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private DepartmentBiz departmentBiz;
	
	private Department department=new Department();
	
	private boolean results=false;
	
	@Action(value = "update_department", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			Department parent=departmentBiz.findDepartmentById(department.getParent_Node());
			Department old=departmentBiz.findDepartmentById(department.getId());
			old.setLogicNode(StringUtil.ChangeLogicNode(parent.getLogicNode(),department.getId()));
			old.setParent_Node(department.getParent_Node());
			old.setName(department.getName());
			old.setOrders(department.getOrders());
			departmentBiz.modify(old);
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getModel() {
		return department;
	}	
}
