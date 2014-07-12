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
 * 查询部门数量_json
 * @author 谭必庆
 *
 */
@ParentPackage("json-default")
public class JsonPageCountDepartmentAction extends BaseAction 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private DepartmentBiz departmentBiz;
	
	private Department department=new Department();
	
	// 分页结果
	private PageResult<Department> result = new PageResult<Department>();
	
	/**
	 * 根据条件查询学习中心数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_department", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		if(0<department.getParent_Node())
			result.setRecordCount(departmentBiz.findCountComposite(department,BranchEnum.Default));
		else
			result.setRecordCount(departmentBiz.findCountComposite(department,BranchEnum.Root));
		return SUCCESS;
	}

	public PageResult<Department> getResult() {
		return result;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
