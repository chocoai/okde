package net.cedu.action.admin.role;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.RoleBiz;
import net.cedu.entity.admin.Role;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据条件查询角色列表_分页
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonPageListRoleAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private RoleBiz roleBiz;
	
	private Role role=new Role();
	
	// 分页结果
	private PageResult<Role> result = new PageResult<Role>();
	
	@Action(value="page_list_role", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		result.setList(roleBiz.findRolePageListByBranchIdAdmin(role, result));
		return SUCCESS;
	}

	public PageResult<Role> getResult() {
		return result;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
