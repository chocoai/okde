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
 * 查询权限子项数量_json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageCountSubPrivilegeAction extends BaseAction implements ModelDriven<SubPrivilege> 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private SubPrivilegeBiz subPrivilegeBiz;
	
	private SubPrivilege subPrivilege=new SubPrivilege();
	
	// 分页结果
	private PageResult<SubPrivilege> result = new PageResult<SubPrivilege>();
	
	@Action(value = "page_count_sub_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(subPrivilegeBiz.findCountByConditionForHQL(subPrivilege));
		return SUCCESS;
	}

	public PageResult<SubPrivilege> getResult() {
		return result;
	}

	public SubPrivilege getSubPrivilege() {
		return subPrivilege;
	}

	public void setSubPrivilege(SubPrivilege subPrivilege) {
		this.subPrivilege = subPrivilege;
	}

	public SubPrivilege getModel() {
		return subPrivilege;
	}
}
