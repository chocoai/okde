package net.cedu.action.orgstructure.department;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.orgstructure.Department;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据条件查询部门_分页
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonPageListDepartmentAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;	
	
	@Autowired
	private DepartmentBiz departmentBiz;
	
	private Department department=new Department();
	
	// 分页结果
	private PageResult<Department> result = new PageResult<Department>();
	
	@Action(value="page_list_department", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"excludeProperties",
					"result.list.*.code,"+
					"result.list.*.parentId,"+
					"result.list.*.logicNode,"+
					"result.list.*.creator,"+
					"result.list.*.updater,"+
					"list,results,branch"
					} )})
	public String execute()throws Exception
	{
		if(0<department.getParent_Node())
			result.setList(departmentBiz.findListComposite(department,result, BranchEnum.Default));
		else
			result.setList(departmentBiz.findListComposite(department,result, BranchEnum.Root));
		return SUCCESS;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public PageResult<Department> getResult() {
		return result;
	}
}
