package net.cedu.action.orgstructure.department;

import java.util.List;

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
 * 添加部门_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddDepartmentAction extends BaseAction implements ModelDriven<Department>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private DepartmentBiz departmentBiz;
	
	private Department department=new Department();
	
	private List<Department> list;
	
	private boolean results=false;
	
	@Action(value = "add_department", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			Department parent=departmentBiz.findDepartmentById(department.getParent_Node());
			
			if(null!=parent)
			{
				department.setLogicNode(parent.getLogicNode());
				department.setCreateBy(getUser().getUserId());
				departmentBiz.addNew(department);
			}
			else
			{
				department.setLogicNode(getLogicNode());
				department.setCreateBy(getUser().getUserId());
				departmentBiz.addNew(department);
			}
			list=departmentBiz.findListByCondition(department);
			if(null!=list&&0<list.size())
			{
				department=list.get(0);
				department.setLogicNode(StringUtil.ChangeLogicNode(department.getLogicNode(),department.getId()));
				departmentBiz.modify(department);
				results=true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//生成逻辑节点
	private String getLogicNode()
	{
		return "_0_";
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
