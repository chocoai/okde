package net.cedu.action.admin.privilege.sub;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.SubPrivilegeBiz;
import net.cedu.entity.admin.privilege.SubPrivilege;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询权限集列表_分页
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonPageListSubPrivilegeAction extends BaseAction implements ModelDriven<SubPrivilege>
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private SubPrivilegeBiz subPrivilegeBiz;
	
	private SubPrivilege subPrivilege=new SubPrivilege();
	
	// 分页结果
	private PageResult<SubPrivilege> result = new PageResult<SubPrivilege>();
	
	@Action(value="page_list_sub_privilege", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		result.setList(subPrivilegeBiz.findListByCondition(result, subPrivilege));
		return SUCCESS;
	}

	public PageResult<SubPrivilege> getResult() {
		return result;
	}

	public SubPrivilege getSubPrivilege() {
		return subPrivilege;
	}

	public void setPrivilege(SubPrivilege subPrivilege) {
		this.subPrivilege = subPrivilege;
	}

	public SubPrivilege getModel() {
		return subPrivilege;
	}
}
